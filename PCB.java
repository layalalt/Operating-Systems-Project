public class PCB implements Comparable<PCB>
{
    
    String processID;
    int priority;
    int arrivalTime;
    int cpuBurst;
    int startTime;
    int terminationTime;
    int turnaroundTime;
    int waitingTime;
    int responseTime;
    int remainingTime;
    int timeSliceRemainingTime;

    PCB(int ID, int priority, int arrivalTime, int cpuBurst) 
    {
        this.processID = "P" + ID;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
        responseTime = -1;
        timeSliceRemainingTime = -1;
        remainingTime = cpuBurst;
    }

    public String toString() 
    {
        return "PCB { " + "processID=" + processID + ", priority=" + priority + ", arrivalTime=" + arrivalTime + ", cpuBurst=" + cpuBurst + ", startTime=" + startTime + ", terminationTime=" + terminationTime + ", turnaroundTime=" + turnaroundTime + ", waitingTime=" + waitingTime + ", responseTime=" + responseTime +  " }";
    }
    
    public int compareTo(PCB p) //for SJF sorting
    {
        return this.cpuBurst - p.cpuBurst;
    }
    
    public void displayInfo() 
    {
        System.out.print("Process ID: " + processID);
        System.out.print(" - Priority: " + priority);
        System.out.print(" - Arrival Time: " + arrivalTime);
        System.out.print(" - CPU Burst: " + cpuBurst);
        System.out.print(" - Start Time: " + startTime);
        System.out.print(" - Termination Time: " + terminationTime);
        System.out.print(" - Turnaround Time: " + turnaroundTime);
        System.out.print(" - Waiting Time: " + waitingTime);
        System.out.print(" - Response Time: " + responseTime);
        System.out.println();
        
    }
}
