package deadlockdetection;

import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Arrow {
    private double angle;
    private double distance;
    private double pX;
    private double pY;
    private double rX;
    private double rY;
    
    public void printArrow(ImageView arrow, ImageView process, ImageView resource) throws InterruptedException{
        Platform.runLater(() -> {
            this.pX = process.getLayoutX() + (process.getFitWidth() / 2);
            this.pY = process.getLayoutY() + (process.getFitHeight() / 2);
            this.rX = resource.getLayoutX() + (resource.getFitWidth() / 2);
            this.rY = resource.getLayoutY() + (resource.getFitHeight() / 2);
            arrow.setVisible(true);
            arrow.setFitWidth(getDistance() * 2);
            arrow.setLayoutX(pX - getDistance());
            arrow.setLayoutY(pY);
            arrow.setRotate(getAngle());
        });
    }
    
    private double getDistance(){
        this.distance = (float) Math.sqrt(Math.pow((pX - rX), 2) + Math.pow((pY - rY), 2));
        return distance;
    }
    
    private double getAngle(){
        this.angle = (float) Math.toDegrees(Math.atan2(pY - rY, pX - rX));
        return angle;
    }
    
//    public void update(ImageView arrow, double processX, double processY, ImageView resource){
//        Platform.runLater(() -> {
//            this.pX = processX;
//            this.pY = processY;
//            this.rX = resource.getLayoutX() + (resource.getFitWidth() / 2);
//            this.rY = resource.getLayoutY() + (resource.getFitHeight() / 2);
//            arrow.setVisible(true);
//            arrow.setFitWidth(getDistance() * 2);
//            arrow.setLayoutX(pX - getDistance());
//            arrow.setLayoutY(pY);
//            arrow.setRotate(getAngle());
//        });
//    }
}
