
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

    @Override
    public String toString() {
        return "PCB{" + "processID=" + processID + ", priority=" + priority + ", arrivalTime=" + arrivalTime + ", cpuBurst=" + cpuBurst + ", startTime=" + startTime + ", terminationTime=" + terminationTime + ", turnaroundTime=" + turnaroundTime + ", waitingTime=" + waitingTime + ", responseTime=" + responseTime + '}';
    }
    
    public int compareTo(PCB p) 
    {
        return this.cpuBurst - p.cpuBurst;
    }
}
