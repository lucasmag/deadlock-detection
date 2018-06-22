package deadlockdetection;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Resource {
    private Semaphore sem = new Semaphore(1);
    private static boolean isAdded[] = new boolean[10];
    private ImageView icon;
    private ImageView resourceArrow = new ImageView();
    private Arrow arrow = new Arrow();
    private int id;
    private String resourceName;

    public static boolean getIsAdded(int index) {
        return isAdded[index];
    }
    
    public static void setAdded(int index, boolean add){
        isAdded[index] = add;
    }
    
    public static void setIsAdded(boolean[] add) {
        isAdded = add;
    }
    
    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
        this.icon.setVisible(true);
    }

    public void setResourceArrow(ImageView resourceArrow) {
        this.resourceArrow = resourceArrow;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public void downResource(String processAlias, int name) throws InterruptedException {
        sem.acquire();
    }
    public void gotResourceArrow(ImageView process) throws InterruptedException{
        arrow.printArrow(resourceArrow, process, icon);
    }
    
    public void upResource(String processAlias, int name) {
        Platform.runLater(()->{ this.resourceArrow.setVisible(false); });
        sem.release();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return resourceName;
    }

    public void getName(String nome) {
        this.resourceName = nome;
    }
    
    public void safeRelease(){
        sem.release();
    }
    
    public void safeAcquire() {
        try {
            sem.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getPermits() {
        return sem.availablePermits();
    }
    
}
