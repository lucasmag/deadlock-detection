package deadlockdetection;

public class DetectorTool {
    private static int index;
    private static boolean have;
    private static boolean proceed;
    private static boolean pass;
    private static int pivot;
    private static int aux;
    private static int cycleAmount;
    private static int deltaOS;
    private static int processAmount;
    private static int resourceAmount;
    private static int[] blockedProcesses = new int[8];
    private static int[] ecpa = new int [10];//Each Cycle Process Amount
    private static int[][] cycle = new int[5][10];
    private static int state[][] = new int[10][10];

    public DetectorTool(){
        processAmount = 0;
        resourceAmount = 0;
    }
    
      public int getBlockedProcesses(int position) {
        return blockedProcesses[position];
    }
        
    public static int getIndex() {
        return index;
    }

    public int getCycleAmount() {
        return cycleAmount;
    }

    public int getEcpa(int position) {
        return ecpa[position];
    }
    
    public void wantResource(int process, int resource){
        state[process][resource] = 2;
    }
    
    public void haveResource(int process, int resource) {
        state[process][resource] = 1;
    }
    
    public void freeResource(int process, int resource) {
        state[process][resource] = 0;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public void setResourceAmount(int ra) {
        resourceAmount = ra;
    }
        
    public int getState(int process, int resource){
        return state[process][resource];
    }
    
    public int getProcessAmount() {
        return processAmount;
    }

    public void setProcessAmount(int pa) {
        processAmount = pa;
    }
   
    public int getDeltaOS() {
        return deltaOS;
    }

    public void setDeltaOS(int dos) {
        deltaOS = dos;
    }
    
    public int getPivot() {
        return pivot;
    }

    public void setPivot(int p) {
        pivot = p;
    }
    
    public int[][] getCycle(){
        return cycle;
    }
    
    public boolean findDeadlock(){
        for (int i = 0; i < processAmount; i++) {
            for (int j = 0; j < resourceAmount; j++) {
                System.out.print(state[i][j] + "  ");
            }
            System.out.println("");
        }
        proceed = false;//Flag to shows if there is some cycle
        cycleAmount = 0;
        //Starts looking for deadlock
        for (int i = 0; i < processAmount; i++) {
            pivot = i;
            //----- This part of code skips processes/threads that are already in a cicle, skipping cycles that are already found
            pass = false;
            for (int j = 0; j < cycleAmount; j++) {
                for (int k = 0; k < ecpa[j]; k++) {
                    if (pivot == cycle[j][k]){
                        pass = true;
                    }
                }
            }
            if(pass){continue;}
            //-----
            aux = 0;
            search(pivot);
        }
        if(proceed){
            proceed = false;//Resets the flag for future searches
            System.out.println(cycleAmount + " deadlock(s) encontrado(s)!");
            for (int i = 0; i < cycleAmount ; i++) {
                System.out.print("Threads no ciclo " + i +": ");
                for (int j = 0; j < ecpa[i]; j++) {
                    System.out.print(cycle[i][j] + "  ");
                }
                System.out.println("");
            }
            return true;
        }
        else{
            return false;   
        }
    }
    //This recursive method search for a process waiting for a resource from another process and so on until it finds a cycle
    public void search(int next){
        if(aux < 9){//This avoids an infinity loop. The worst case will have a maximum of 10 processes in its cicle
            for (int j = 0 ; j < resourceAmount ; j++) {
                if (state[next][j] == 2) {//Looking into avaliable resources if pivot is waiting for some
                    for (int k = 0; k < processAmount ; k++) {
                        if (state[k][j] == 1) {//Looking into processes the one who has that resource
                            cycle[cycleAmount][aux] = k;
                            if (k == pivot) {//If k == pivot, than we have a cicle...
                                ecpa[cycleAmount] = aux + 1;//The amount of processes in this cycle
                                cycleAmount++;//new cycle added
                                proceed = true;//This line ensures that it has at least one cycle
                            } else {//...else, it keeps looking for. Now 'k' is the new pivot
                                aux++;//One more process in this possible cycle
                                search(k);//Keep searching
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean findBlocked() {
        have = false;//Flag that shows if there is some blocked process
        index = 0;
        for (int i = 0; i < cycleAmount; i++) {
            for (int j = 0; j < ecpa[i]; j++) {
                searchBlocked(cycle[i][j]);//Search others processes waiting resources for all in-cycle processes.
            }
        }
        return have;
    }

    public void searchBlocked(int process) {
        boolean validation;//For each in-cycle process a new validation flag is created
        for (int k = 0; k < 10; k++) {//Search in all possible resources
            if (state[process][k] == 1) {//Verify if this in-cycle process has some resource
                for (int l = 0; l < 10; l++) {//Search in all possible processes
                    if (state[l][k] == 2) {//Verify, for that resource, if some other process is waiting
                        validation = true;//If exists, it's a possible blocked process
                        for (int i = 0; i < cycleAmount; i++) {//Tests all in-cycle processes
                            for (int j = 0; j < ecpa[i]; j++) {
                                if (l == cycle[i][j]) {//If true, that one process found previously is a in-cycle process
                                    validation = false;//So, it's not a blocked process
                                }
                            }
                        }
                        if (validation) {//If the waiting process is not a in-cycle process, then is a blocked one
                            blockedProcesses[index] = l;//One more blocked process
                            index++;
                            have = true;//This flag ensures that there is at least one blocked process
                            searchBlocked(l);//Keep searching more blocked processes, recursively
                        }
                    }
                }
            }
        }
    }
}
