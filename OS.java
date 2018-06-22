package deadlockdetection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OS extends Thread{
    private static DetectorTool tool;
    private static int[][] cycle = new int[5][10];
    private static int blockedAmount;
    private MainController main;
    private static ImageView process[] = new ImageView[10];
    private static final int LINE1 = 37;
    private static final int LINE2  = 84;
    private static final int DL = 132;
    private static final int BLOCKED = 264;
    private int verifyDelta;
    private static int changeTab;
    TranslateTransition scan = new TranslateTransition();
    
    public OS(int delta, DetectorTool dt, MainController m) {
        tool = dt;
        main = m;
        this.verifyDelta = delta;
        changeTab = 0;
        main.newTransitionY(scan, main.verify, -100, 800, 1000);
        scan.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            verify();
        });
        
    }
    
    @Override
    public void run(){
        while(true){
            try {
                sleep(verifyDelta * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OS.class.getName()).log(Level.SEVERE, null, ex);
            }
            scan.play();
        }
    }
    
    public void verify(){
        if (tool.findDeadlock()) {
            main.deadlockPulse();
            if (changeTab == 0) {
                main.changeTab(1);
                changeTab++;
            }
            main.deadlockLog();
            cycle = tool.getCycle();
            deadlockSituation();
            if (tool.getCycleAmount() == 1) {
                main.setCycleAmountText("1 ciclo encontrado");
            } else {
                main.setCycleAmountText(tool.getCycleAmount() + " ciclos encontrados");
            }
            if (tool.findBlocked()) {
                blockedAmount = DetectorTool.getIndex();
                System.out.print("Processos impedidos: ");
                for (int i = 0; i < blockedAmount; i++) {
                    System.out.print(tool.getBlockedProcesses(i) + " ");
                }
                System.out.println("");
                blockedSituation();
            }
        } else {
            main.setCycleAmountText("");
            if (changeTab == 1) {
                System.out.println("Mudou pra log");
                main.changeTab(0);
                changeTab = 0;
            }
            reorganize();
            main.noDeadlockLog();
        }
    }
    
    public void reorganize(){
        for (int i = 0; i < tool.getProcessAmount(); i++) {
            if (i < 5) {
                move(i, LINE1);
            } else {
                move(i, LINE2);
            }
        }
    }
    
    public void deadlockSituation(){
        reorganize();        
        for (int i = 0; i < tool.getCycleAmount(); i++) {
            for (int j = 0; j < tool.getEcpa(i); j++) {
                main.setBackStyle(cycle[i][j], 0, 0.5);
                if(cycle[i][j] < 5 ){
                    move(cycle[i][j], LINE1 + DL);
                }
                else{
                    move(cycle[i][j], LINE2 + DL);
                }
            }
        }
    }
    public void cleanBackStyle() {
        for (int i = 0; i < tool.getCycleAmount(); i++) {
            for (int j = 0; j < tool.getEcpa(i); j++) {
                main.setBackStyle(cycle[i][j], 0.3, 0.6);
            }
        }
    }
    
    public void blockedSituation(){
        for (int i = 0; i < blockedAmount; i++) {
            if (tool.getBlockedProcesses(i) < 5) {
                move(tool.getBlockedProcesses(i), LINE1 + BLOCKED);
            } else {
                move(tool.getBlockedProcesses(i), LINE2 + BLOCKED);
            }
        }
    }

    
    public void move(int index, int position){
        Platform.runLater(()->{
            main.move(index, position);
        });
    }
}
