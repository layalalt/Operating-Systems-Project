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
            int totalTime;
            //PCB processes [], Q1 [], Q2 [];
            //String Gantt [];
            
            switch(menu)
            {
                case 1:
                    
                    System.out.print("How many processes do you have: ");
                    int processNum = key.nextInt();
                    //processes = new PCB[processNum];
                    //Q1 = new PCB[processNum];
                    //Q2 = new PCB[processNum];
                    
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
                       
                       //processes[i] = new PCB(i+1, priority, arrivalTime, CPUBurst);
                    }
                    //Gantt = new String[totalTime+1];
                    break;
                    
                case 2:
                    
                    for(int i=0; i<totalTime; i++)
                    {
                        
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
