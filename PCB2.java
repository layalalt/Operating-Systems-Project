class PCB {
    
    private String processID;
    private int Priority;
    private int ArrivalTime;
    private int CPU_BurstTime;
    private int StartTime;
    private int TerminationTime;
    private int TurnAroundTime;
    private int WaitingTime;
    private int ResponseTime;


    public PCB (String processID, int priority, int arrivalTime, int burstTime) {
        this.processID = processID;
        this.Priority = priority;
        this.ArrivalTime = arrivalTime;
        this.CPU_BurstTime = burstTime;
    }

   
    public String getProcessID() {
        return processID;
    }

    public int getPriority() {
        return Priority;
    }

    public int getArrivalTime() {
        return ArrivalTime;
    }

    public int getBurstTime() {
        return CPU_BurstTime;
    }

    public int getStartTime() {
        return StartTime;
    }

    public void setStartTime(int startTime) {
        this.StartTime = startTime;
    }

   

    public void setTerminationTime(int terminationTime) {
        this.TerminationTime = terminationTime;
    }
    
    public int getTerminationTime() {
        return TerminationTime;
    }
    


    public void calculateTurnaroundTime() {
        TurnAroundTime = TerminationTime - ArrivalTime;
    }
    
    public int getTurnaroundTime() {
        return TurnAroundTime;
    }
    

    public void calculateWaitingTime() {
        WaitingTime = TurnAroundTime - CPU_BurstTime;
    }
    
    
    public int getWaitingTime() {
        return WaitingTime;
    }
    

    public void calculateResponseTime() {
        ResponseTime = StartTime - ArrivalTime;
    }

   public int getResponseTime() {
        return ResponseTime;
    }
   
    
    public void displayInfo() {
        System.out.println("Process ID: " + processID);
        System.out.println("Priority: " + Priority);
        System.out.println("Arrival Time: " + ArrivalTime);
        System.out.println("CPU Burst: " + CPU_BurstTime);
        System.out.println("Start Time: " + StartTime);
        System.out.println("Termination Time: " + TerminationTime);
        System.out.println("Turnaround Time: " + TurnAroundTime);
        System.out.println("Waiting Time: " + WaitingTime);
        System.out.println("Response Time: " + ResponseTime);
        
        
        
    }

 
}
...
