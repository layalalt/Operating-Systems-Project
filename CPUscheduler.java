import java.util.*;
public class CPUscheduler {
    
    public static Scanner key = new Scanner(System.in);
    
    public static void main(String [] args)
    {
        key.useDelimiter("\n");
        boolean loopFlag = true;
        do
        {
            System.out.println("Hello!! What would you like to do?");
            System.out.println("1. Enter process’ information.\n" +
                               "2. Report detailed information about each process and different scheduling criteria.\n" +
                               "3. Exit the program.");
            System.out.print("Enter the number: ");
            int menu = key.nextInt();
            int totalTime = 0;
            PCB processes [], Q1 [], Q2 [];
            int Q1h = 0, Q1t = 0, Q2h = 0, Q2t = 0;
            String Gantt [];
            int processNum = 0;
            
            switch(menu)
            {
                case 1:
                    
                    System.out.print("How many processes do you have: ");
                    processNum = key.nextInt();
                    processes = new PCB[processNum];
                    Q1 = new PCB[processNum];
                    Q2 = new PCB[processNum];
                    
                    totalTime = 0;
                    
                    for(int i=0; i<processNum; i++)
                    {
                       System.out.print("Enter process #" + (i+1) + "'s priority(1 or 2): ");
                       int priority = key.nextInt();
                       
                       System.out.print("Enter process #" + (i+1) + "'s arrival time: ");
                       int arrivalTime = key.nextInt();
                       totalTime+=arrivalTime;
                       
                       System.out.print("Enter process #" + (i+1) + "'s CPU burst(in ms): ");
                       int CPUBurst = key.nextInt();  
                       
                       processes[i] = new PCB(i+1, priority, arrivalTime, CPUBurst);
                    }
                    Gantt = new String[totalTime+1];
                    break;
                    
                case 2:
                    
                    for(int i=0; i<totalTime; i++)
                    {
                        for(int j=0; j<processNum; j++)
                        {
                            if(processes[j].arrivalTime == i)
                            {
                              if(processes[j].priority == 1)
                              {
                                  Q1[Q1t] = processes[j];
                                  Q1t = (Q1t+1)%processNum;
                              }
                              else
                              {
                                  //need to implement a way to ensure SJF ordering maybe call a method that adds based on SJF
                                  Q2[Q2t] = processes[j];
                                  Q2t = (Q2t+1)%processNum;
                              }
                            }
                        }
                        if(Q1[Q1h] != Q1[Q1t]) //fix this rule by making an empty method
                        {
                            PCB currentProcess = Q1[Q1h];
                            if(currentProcess.responseTime == 0) //did not get scheduled yet
                            {
                               currentProcess.responseTime = i - currentProcess.arrivalTime;
                               currentProcess.startTime = i;
                            }

                            int timeSlice = Math.min(3, currentProcess.remainingTime);
                            currentProcess.remainingTime -= timeSlice;

                            if (currentProcess.remainingTime == 0) 
                            {
                              currentProcess.terminationTime = i;
                              currentProcess.turnaroundTime = currentProcess.terminationTime - currentProcess.arrivalTime;
                              currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.cpuBurst;
                            } 
                            else 
                            {            
                               Q1[Q1t] = currentProcess;
                               Q1t = (Q1t+1)%processNum;
                            }  
                        }
                        //else if(Q2 not empty)
                        {
                          
                        
                        
                        }
                        else
                        //terminate scheduler
                        
                        //gantt filling
                            
                            
                            }
                            
                        }
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    }
                
                    
                case 3:
                    
                    loopFlag = false;
                    break;
                
                default:
                    
                    System.out.println("Please ensure the number is within the allowed range!");
                    break;
                    
                
            }
               
            
        }while(loopFlag);
        
    }       
    
    
}
    }       
    
    
}
