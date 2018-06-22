package deadlockdetection;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Process extends Thread{
    
    private static Arrow arrow = new Arrow();
    private static DetectorTool tool;
    private int index;
    public Resource resource[];
    private static int resourceDrawn[] = new int[10];
    public MainController main;
    public static int removeProcess;
    private static boolean stayHere;
    private static Semaphore safeData = new Semaphore(1);
    private static Semaphore safeZone = new Semaphore(1);
    public static Semaphore remover = new Semaphore(0);
    private static Semaphore mutex = new Semaphore(1);
    public static int ldbr; //Last resource Drawn By process to be Removed
    /*Each process has one 'drawn resource', that is the last resource requested by the process. 
      'ldbr' is the last resource requested by the process that the user chose to remove*/
    
    private String alias;
    private ImageView icon;
    private ImageView wantArrow;
    private int use;
    private int request;
    private int requestTimeline;
    private int useTimeline[] = new int[10];
    private int drawn;
    private boolean sameResource;
    private int processResources;

    public Process(DetectorTool dt, Resource[] rsc, MainController m) {
        main = m;
        tool = dt;
        resource = rsc;
        removeProcess = -1;
        stayHere = true;
        ldbr = -2;
        this.processResources = 0;
        this.sameResource = false;
    }
    //Method where threads/processes are going to run -> 1 lap per second
    public void working() throws InterruptedException {
        main.visibility(this.getIndex(), true);
        main.setBackStyle(this.getIndex(), 0.7, 0.5);
        
        while(stayHere) {
            if(this.getDrawn() != ldbr){
                sleep(1000);
                use();
            }
            if (this.getTimelineRequest() == 0) {
                 if (this.getProcessResources() < tool.getResourceAmount()) {//If process have all of the avaliable resources, it stops resquesting
                    request();
                 }
            }
            main.updateTimelineRequest(this.getIndex(), this.getTimelineRequest());
            this.timelineRequest();
            
            safeZone.acquire();
            if(Integer.parseInt(this.getName()) == removeProcess){
                stayHere = false;                
            }else{safeZone.release();}
        }
        stayHere = true;
        removeProcess = -1;
        ldbr = -2;
        Platform.runLater(()->{ //Remove the process' visual objects from the screen
            this.getWantArrow().setVisible(false);
            this.getIcon().setVisible(false);
        });
        main.visibility(this.getIndex(), false);
        main.removedLog(this.getAlias(), Integer.parseInt(this.getName()));
        //If removed process has some resource, it free all resources it was holding
        for (int i = 0; i < tool.getResourceAmount(); i++) {
            if(tool.getState(this.getIndex(), i) == 1){
                resource[i].upResource(this.getAlias(), Integer.parseInt(this.getName()));
                tool.freeResource(this.getIndex(), i);
                main.updateTimelineUse(resource[i].getId(), "");
            }
            if(tool.getState(this.getIndex(), i) == 2){
                tool.freeResource(this.getIndex(), i);
            }
        }
        safeZone.release();
    }
    //If current process has some resource, it uses that resource until reaches it use time
    public void use(){
        for (int i = 0; i < tool.getResourceAmount(); i++) {
            if (tool.getState(this.getIndex(), i) == 1) {
                this.timelineUse(i);
                main.updateTimelineUse(resource[i].getId(), Integer.toString(this.getTimelineUse(i)));
                if (this.getTimelineUse(i) == 0) {
                    this.lessResource();
                    tool.freeResource(this.getIndex(),i);
                    this.resetUse(i);
                    main.updateTimelineUse(resource[i].getId(), "");
                    resource[i].upResource(this.getAlias(), Integer.parseInt(this.getName()));
                    System.out.println("Processo " + this.getAlias() + "[" + this.getName() + "]" + " terminou de usar " + resource[i].getName() + "[" + resource[i].getId() + "]");
                    main.concludedLog(this.getAlias(), Integer.parseInt(this.getName()), resource[i].getName(), resource[i].getId());
                }
            }
        }
    }
    //Process will draw one resource when it reaches it 'request new resource' time
    public void request() throws InterruptedException{
            mutex.acquire();
            this.resetRequest();
            if(!this.isSameResource()){
            /*In positive case, does nothing, just set its flag false and come back to its initial position
              In negative case, the current process can drawn one new resource*/
                drawResource();
            }else{
                this.setSameResource(false);}
            mutex.release();
            //2º part of requesting a resource - Try to get it
            resource[this.getDrawn()].downResource(this.getAlias(), Integer.parseInt(this.getName()));
            mutex.acquire();
            if(this.getDrawn() == ldbr){/*Important! Verify if the last resource requested by current process is the same of the removed process
                In positive case its set one internal flag that permits this current process to request this same resource again on the next lap
                without changing any variable or distort its timeline*/
                this.setSameResource(true);//Do not drawn another resource
                this.requestAgain();//Request this same resource again
                sieve();
            }
            else{
                getResourceDrawn();
            }
            mutex.release();
        
    }
    
    //1º part of requesting a resource - Drawn one of avaliable ones
    public void drawResource() throws InterruptedException {
//      if (this.getSorteado() != ldbr || tool.getState(this.getIndex(), ldbr) == 1) {
            this.setDrawn(draw(this.getIndex()));
//      }
        System.out.println("Processo " + this.getAlias() + "[" + this.getName() + "]" + " solicitou " + resource[this.getDrawn()].getName() + "[" + resource[this.getDrawn()].getId() + "]");
        tool.wantResource(this.getIndex(), this.getDrawn());
        arrow.printArrow(this.getWantArrow(), this.getIcon(), resource[this.getDrawn()].getIcon());
        main.requestLog(this.getAlias(), Integer.parseInt(this.getName()), resource[this.getDrawn()].getName(), resource[this.getDrawn()].getId());
        if(resource[this.getDrawn()].getPermits() < 1){
            main.setBackStyle(this.getIndex(), 0.3, 0.6);
        }
    }
    
    //3º part of requesting a resource - If process got the resource it starts to use it
    public void getResourceDrawn() throws InterruptedException {
        Platform.runLater(()->{this.getWantArrow().setVisible(false);});
        resource[this.getDrawn()].gotResourceArrow(this.getIcon());
        main.setBackStyle(this.getIndex(), 0.7, 0.5);
        //Platform.runLater(()->{this.getIcon().setOpacity(1);});
        tool.haveResource(this.getIndex(), this.getDrawn());
        this.resetUse(this.getDrawn());
        main.useLog(this.getAlias(), Integer.parseInt(this.getName()), resource[this.getDrawn()].getName(), resource[this.getDrawn()].getId());
        System.out.println("Processo " + this.getAlias() + "[" + this.getName() + "]" + " usando " + resource[this.getDrawn()].getName());
        this.moreResource();
    }
    
   //Returns a random resource not owned by current process
    public int draw(int process) {
        do {resourceDrawn[process] = (int) (Math.random() * tool.getResourceAmount());} 
        while (tool.getState(process, resourceDrawn[process]) == 1);
        return resourceDrawn[process];
    }
    
    public void sieve(){//Detect the process to be removed
        if(Integer.parseInt(this.getName()) == removeProcess){
            this.getWantArrow().setVisible(false);
            tool.freeResource(this.getIndex(), this.getDrawn());
            remover.release();
        }
        else{
            remover.release();
        }
    }
    
    @Override
    public void run() {
        try {
            working();
        } catch (InterruptedException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getProcessResources() {
        return processResources;
    }

    public void moreResource() {
        this.processResources = processResources+1;
    }
    
    public void lessResource() {
        this.processResources = processResources-1;
    }

    public void setWantArrow(ImageView wantArrow) {
        this.wantArrow = wantArrow;
    }

    public ImageView getWantArrow() {
        return wantArrow;
    }

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }
    
    //Processes' alias
    public String getAlias() {
        return alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    //Processes' use time
    public int getUse() {
        return use;
    }
    
    public void setUse(int use) {
        this.use = use;
    }

    //Processes' request time
    public int getRequest() {
        return request;
    }
    
    public void setRequest(int request) {
        this.request = request;
    }
    
    //Processes' timeline use. This is updated every second
    public void timelineUse(int resource){
        this.useTimeline[resource]--;
    }
    
    public int getTimelineUse(int resource){
        return useTimeline[resource];
    }
    
    public void resetUse(int resource){
        this.useTimeline[resource] = use;
    }
    
    public int getTimelineRequest(){
        return requestTimeline;
    }
    
    public void timelineRequest(){
        if(this.getTimelineRequest() > 0){
            this.requestTimeline--;
        }
    }
    public void resetRequest(){
        this.requestTimeline = request;
    }
    
    public void requestAgain(){
        this.requestTimeline = 1;
    }

    public int getIndex() {
        return index;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int sorteado) {
        this.drawn = sorteado;
    }
    
    public boolean isSameResource() {
        return sameResource;
    }

    public void setSameResource(boolean sameResource) {
        this.sameResource = sameResource;
    }
}