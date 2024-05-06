import java.util.*;
import java.io.*;

public class CPUscheduler 
{
    
    public static Scanner key = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        key.useDelimiter("\n");
        boolean loopFlag = true;
        
        System.out.println("Hello!! What would you like to do?");
        System.out.println("1. Enter process’ information.\n" +
                               "2. Report detailed information about each process and different scheduling criteria.\n" +
                               "3. Exit the program.");
        System.out.print("Enter the number: ");
        int menu = key.nextInt();
        int totalTime = 0, totalTimeR = 0 ,totalTimeW = 0, totalTimeTA = 0;
        PCB processes [] = new PCB[1], Q1 [], Q2 [];
        int Q1h = 0, Q1t = 0, Q2h = 0, Q2t = 0; //head and tail pointers for the queues
        String Gantt [] = new String[1]; //Gantt chart simulator for testing
        int GanttIndex = 0;
        int processNum = 0;
            
        do
        {
            switch(menu)
            {
                case 1:
                    
                    System.out.print("How many processes do you have: ");
                    processNum = key.nextInt();
                    processes = new PCB[processNum]; //initial array that will store processes
                    int Q1size = 1, Q2size = 1;
                    int Q2processCounter = 0; //counts number of processes in Q2 for proper sorting
                    totalTime = 0;
                    

                    for(int i=0; i<processNum; i++) //filling in process information
                    {
                       System.out.print("Enter process #" + (i+1) + "'s priority(1 or 2): ");
                       int priority = key.nextInt();
                       if(priority == 1)
                          Q1size++;
                       else if(priority == 2)
                          Q2size++; 
                       else
                       {
                           System.out.println("The only allowed numbers are 1 or 2!! Please try again."); //number entered is out of range
                           i--;
                           continue;
                       }
                       
                       System.out.print("Enter process #" + (i+1) + "'s arrival time: ");
                       int arrivalTime = key.nextInt();
                       
                       System.out.print("Enter process #" + (i+1) + "'s CPU burst(in ms): ");
                       int CPUBurst = key.nextInt();  
                       totalTime += CPUBurst;
                      
                       processes[i] = new PCB(i+1, priority, arrivalTime, CPUBurst);
                       System.out.println();
                    }
                    
                    Q1 = new PCB[Q1size];               
                    Q2 = new PCB[Q2size];
                    Gantt = new String[totalTime]; 
                    
                    for(int i=0; i<100; i++) //just assumes it will take less than 100 ms (clock simulator) 
                    {
                        int startIndex = Q2h, endIndex; 
                        boolean SJFInserted = false;
                        for(int j=0; j<processNum; j++)
                        {
                            if(processes[j].arrivalTime == i)
                            {
                              if(processes[j].priority == 1)
                              {
                                  Q1[Q1t] = processes[j]; 
                                  Q1t = (Q1t+1)%Q1size; //regular insertion              
                              }
                              else //priority == 2
                              {
                                  Q2[Q2t] = processes[j];
                                  Q2t = (Q2t+1)%Q2size;
                                  Q2processCounter++;
                                  SJFInserted = true;
                              }
                            }
                        }
                        
                        endIndex = Q2processCounter; //prevents null pointer exceptions by ensuring only initialized processes are sorted
                        if(SJFInserted) //sorts only if a new element is added to the SJF queue
                          Arrays.sort(Q2, startIndex, endIndex);
                        
                        if(Q1[Q1h] != null && (Q1[Q1h].timeSliceRemainingTime == 0)) //time slice of executing process finished and must be readded in RR queue
                        {         
                             Q1[Q1h].timeSliceRemainingTime = -1;
                             Q1[Q1t] = Q1[Q1h];
                             Q1t = (Q1t+1)%Q1size;
                             Q1h = (Q1h+1)%Q1size; 
                        }  
                        
                        if(Q1[Q1h] != null && (Q1[Q1h].remainingTime != 0)) //not empty
                        {
                            if(Q1[Q1h].responseTime == -1) //did not get scheduled yet
                            {
                               Q1[Q1h].responseTime = i - Q1[Q1h].arrivalTime;
                               Q1[Q1h].startTime = i;
                            }

                            if(Q1[Q1h].timeSliceRemainingTime == -1) //didn't start executing in this burst
                                Q1[Q1h].timeSliceRemainingTime = Math.min(3, Q1[Q1h].remainingTime);
                            
                            Gantt[GanttIndex++] = Q1[Q1h].processID;
                            
                            Q1[Q1h].remainingTime -= 1;
                            Q1[Q1h].timeSliceRemainingTime -= 1;
                          
                            if(Q1[Q1h].remainingTime == 0) //process terminated
                            {
                              Q1[Q1h].terminationTime = i+1;
                              Q1[Q1h].turnaroundTime = Q1[Q1h].terminationTime - Q1[Q1h].arrivalTime;
                              Q1[Q1h].waitingTime = Q1[Q1h].turnaroundTime - Q1[Q1h].cpuBurst;
                              Q1h = (Q1h+1)%Q1size;
                            }                
                        }
                        
                        else if(Q2[Q2h] != null && (Q2[Q2h].remainingTime != 0)) //not empty 
                        {
                          
                            PCB currentProcess = Q2[Q2h]; //for simplifying code
                            if(currentProcess.responseTime == -1) //did not get scheduled yet
                            {
                               currentProcess.responseTime = i - currentProcess.arrivalTime;
                               currentProcess.startTime = i;
                            }
                            
                            Gantt[GanttIndex++] = currentProcess.processID; 
                            
                            currentProcess.remainingTime -= 1;

                            if(currentProcess.remainingTime == 0) //process terminated
                            {
                              currentProcess.terminationTime = i+1;
                              currentProcess.turnaroundTime = currentProcess.terminationTime - currentProcess.arrivalTime;
                              currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.cpuBurst;
                              Q2h = (Q2h+1)%Q2size;
                            } 
                        } 
                        
                        else //check whether to terminate scheduler
                        {
                          boolean terminationFlag = true;
                          for(int j=0; j<processNum; j++)
                          {
                              if(processes[j].remainingTime != 0)
                                  terminationFlag = false;
                          }
                          if(terminationFlag)
                             break;
                          
                        }    
                    }
                    break;
                    
                    
                case 2: 
                    
                    if(processNum == 0)
                    {
                        System.out.println("\nNo processes entered.\n");
                        break;
                    }
                        
                    totalTime = 0;
                    totalTimeR = 0;
                    totalTimeW = 0;
                    totalTimeTA = 0;
                    
                    System.out.println();
                    
                    for(int i=0; i<processNum; i++)      
                        processes[i].displayInfo();
                      
                    
                   /** for(int i=0; i<Gantt.length; i++) //for testing
                       System.out.print(Gantt[i] + " ");*/
                    
                    System.out.println("\n");

                    StringBuilder order = new StringBuilder();
                    Set<String> seenProcesses = new HashSet<>();

                    for(String process : Gantt) 
                    {
                       if(!seenProcesses.contains(process)) //first time encountering the process
                       {
                         if(order.length() != 0) 
                            order.append("|");
                
                         order.append(process);
                         seenProcesses.add(process);
                       } 
                       else if(!process.equals(order.substring(order.length() - 2))) //second time encountering it and it is separated by another process
                           order.append("|").append(process);
            
                    }

                    System.out.println("[" + order + "]");
                   
                            
                    System.out.println("\n");

                    
                    for(int i=0; i<processNum; i++)
		    {
			totalTimeW += processes[i].waitingTime;
			totalTimeTA += processes[i].turnaroundTime;
                        totalTimeR += processes[i].responseTime;
		    }

		    System.out.printf("Average waiting time: %.2f \n", totalTimeW/(double)processNum);
		    System.out.printf("Average turnaround time: %.2f \n", totalTimeTA/(double)processNum);
                    System.out.printf("Average response time: %.2f \n\n", totalTimeR/(double)processNum);

                    
                  try
                  {
                    PrintWriter file = new PrintWriter(new FileOutputStream(new File("Report.txt")));
               
                    file.print("Scheduling order of the processes: ");
                    file.println("[" + order + "]");
                    
                    for(int i=0; i<processNum; i++) 
                    { 
                       file.println("Process ID : " + processes[i].processID ) ;  
                       file.println("Process priority : " + processes[i].priority ) ;                         
                       file.println("Arrival time : " + processes[i].arrivalTime) ;        
                       file.println("CPU burst : " + processes[i].cpuBurst) ;
                       file.println("Start time : " + processes[i].startTime) ;
                       file.println("Termination time : " + processes[i].terminationTime) ;   
                       file.println("Turnaround time : " + processes[i].turnaroundTime) ;
                       file.println("Waiting time : " + processes[i].waitingTime) ;
                       file.println("Response time : " + processes[i].responseTime) ;    
                       file.println();
                    }
            
                    file.printf("Average turnaround time : %.2f \n", totalTimeTA/(double)processNum ) ;
                    file.printf("Average waiting time : %.2f \n", totalTimeW/(double)processNum) ;
                    file.printf("Average response time : %.2f \n", totalTimeR/(double)processNum) ; 
            
                    file.close() ;
                  }
                  catch(Exception e)
                  { 
                     e.printStackTrace();
                  }
        
                   break;
                    
                    
                    
                case 3:
                    
                    loopFlag = false;
                    break;
                
                default:
                    
                    System.out.println("Please ensure the number is within the allowed range!");
                    break; 
                
            }   
            if(!loopFlag)
               break;
            
            System.out.println("\nHello!! What would you like to do?");
            System.out.println("1. Enter process’ information.\n" +
                               "2. Report detailed information about each process and different scheduling criteria.\n" +
                               "3. Exit the program.");
            System.out.print("Enter the number: ");
            menu = key.nextInt();
            
            if(menu == 1) //reinitializes queues
            { 
                totalTime = 0;
                totalTimeR = 0; 
                totalTimeW = 0; 
                totalTimeTA = 0;
                processes = new PCB[1];
                Q1 = null;
                Q2 = null;
                Q1h = 0;
                Q1t = 0;
                Q2h = 0;
                Q2t = 0;
                Gantt = new String[1]; 
                GanttIndex = 0;
                processNum = 0;
            }
            
        }while(loopFlag);  
        
        System.out.println();
        System.out.println("Thank you!");
    }      
}






    





    


