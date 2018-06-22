/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlockdetection;

import static deadlockdetection.DeadlockDetection.getStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class MenuController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    private static int resourceAmount;
    private Resource resource[] = new Resource[10];
    private static int clickedResource;
    private int resourceIndex;
    private DetectorTool tool = new DetectorTool();
    private boolean isAdded[] = new boolean[10];
    private RotateTransition startRotate[] = new RotateTransition[2];
    private SequentialTransition startEffect;
    private SequentialTransition init;
    private SequentialTransition logoLoop;
    private ScaleTransition wppScale[] = new ScaleTransition[2];
    private RotateTransition wppRotate[] = new RotateTransition[3];
    private SequentialTransition fullEffect;
    private TranslateTransition move = new TranslateTransition();
    private FadeTransition tutorialFade = new FadeTransition();
    private TranslateTransition resourcePaneMov = new TranslateTransition();
    private TranslateTransition skipMove = new TranslateTransition();
    private RotateTransition skipRotation = new RotateTransition();
    private ParallelTransition skipEffect;
    private ParallelTransition wppEffect[] = new ParallelTransition[2];
    private FadeTransition fade = new FadeTransition();
    private FadeTransition initFade[] = new FadeTransition[2];
    public boolean tutorial;
    private final String AUDIO = getClass().getResource("audios/menu-back.mp3").toString();
    AudioClip clip = new AudioClip(AUDIO);
    
    @FXML
    private Pane menu;
    @FXML
    private ImageView addResource;
    @FXML
    private ImageView start;
    @FXML
    private Pane resourcePane;
    @FXML
    private ImageView selected;
    @FXML
    private Pane pane0;
    @FXML
    public Label name0;
    @FXML
    private Pane pane1;
    @FXML
    public Label name1;
    @FXML
    private Pane pane2;
    @FXML
    public Label name2;
    @FXML
    private Pane pane3;
    @FXML
    public Label name3;
    @FXML
    private Pane pane4;
    @FXML
    public Label name4;
    @FXML
    private Pane pane5;
    @FXML
    public Label name5;
    @FXML
    private Pane pane6;
    @FXML
    public Label name6;
    @FXML
    private Pane pane7;
    @FXML
    public Label name7;
    @FXML
    private Pane pane8;
    @FXML
    public Label name8;
    @FXML
    private Pane pane9;
    @FXML
    public Label name9;
    @FXML
    private ImageView plus;
    @FXML
    private ImageView minus;
    @FXML
    private ImageView done;
    @FXML
    private Label resourceAmountLbl;
    @FXML
    private ImageView topBar;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView close;
    @FXML
    private ImageView addResourcePressed;
    @FXML
    private ImageView background;
    @FXML
    private Pane resourceAmountPane;
    @FXML
    private ImageView plusDown;
    @FXML
    private ImageView minusDown;
    @FXML
    private Pane topPane;
    @FXML
    private Pane logo;
    @FXML
    private Pane tutorialPane;
    @FXML
    private Text tutorialTxt;
    @FXML
    private ImageView confirmButton;
    @FXML
    private ImageView skipButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clip.play();//Inicial music
        //-----> Logo animation
        initFade[0] = new FadeTransition();
        initFade[1] = new FadeTransition();
        initFade[0].setFromValue(0);
        initFade[0].setToValue(1);
        initFade[0].setDuration(Duration.seconds(3));
        initFade[0].setNode(logo);
        initFade[0].play();
        initFade[1].setFromValue(0);
        initFade[1].setToValue(1);
        initFade[1].setDuration(Duration.seconds(1));
        initFade[1].setNode(menu);
        logoLoop = new SequentialTransition(initFade[0], new PauseTransition(Duration.seconds(1)));
        logoLoop.setAutoReverse(true);
        logoLoop.setCycleCount(2);
        logoLoop.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            logo.setVisible(false);
        });
        init = new SequentialTransition(logoLoop, initFade[1]);
        init.play();
        //----->
        //-----> Menu background animation
        wppScale[0] = new ScaleTransition();
        wppScale[1] = new ScaleTransition();
        wppRotate[0] = new RotateTransition();
        wppRotate[1] = new RotateTransition();
        wppRotate[2] = new RotateTransition();
        wppScale[0].setFromX(2);
        wppScale[0].setFromY(2);
        wppScale[0].setToX(1);
        wppScale[0].setToY(1);
        wppScale[0].setDuration(Duration.seconds(20));
        wppScale[0].setNode(background);
        wppRotate[0].setFromAngle(-20);
        wppRotate[0].setToAngle(0);
        wppRotate[0].setDuration(Duration.seconds(20));
        wppRotate[0].setNode(background);
        wppEffect[0] = new ParallelTransition(wppScale[0], wppRotate[0]);
        wppScale[1].setFromX(1);
        wppScale[1].setFromY(1);
        wppScale[1].setToX(2);
        wppScale[1].setToY(2);
        wppScale[1].setDuration(Duration.seconds(20));
        wppScale[1].setNode(background);
        wppRotate[1].setFromAngle(0);
        wppRotate[1].setToAngle(20);
        wppRotate[1].setDuration(Duration.seconds(20));
        wppRotate[1].setNode(background);
        wppEffect[1] = new ParallelTransition(wppScale[1], wppRotate[1]);
        wppRotate[2].setFromAngle(20);
        wppRotate[2].setToAngle(-20);
        wppRotate[2].setDuration(Duration.seconds(20));
        wppRotate[2].setNode(background);
        fullEffect = new SequentialTransition(wppEffect[0], wppEffect[1], wppRotate[2]);
        fullEffect.setCycleCount(Timeline.INDEFINITE);
        fullEffect.play();  
        //----->
        //-----> Start icon animation
        startRotate[0] = new RotateTransition();
        startRotate[1] = new RotateTransition();
        startRotate[0].setByAngle(160);
        startRotate[0].setDuration(Duration.millis(300));
        startRotate[0].setNode(start);
        startRotate[1].setByAngle(-40);
        startRotate[1].setDuration(Duration.millis(150));
        startRotate[1].setNode(start);
        //----->
        //-----> SkipButton animation
        skipRotation.setByAngle(360);
        skipRotation.setDuration(Duration.millis(800));
        skipRotation.setNode(skipButton);
        skipMove.setFromX(-100);
        skipMove.setToX(14);
        skipMove.setDuration(Duration.millis(800));
        skipMove.setNode(skipButton);
        skipEffect = new ParallelTransition(skipRotation, skipMove);
        skipEffect.setDelay(Duration.seconds(11));
        skipEffect.play();
        //-----> 
        //Set effects
        startEffect = new SequentialTransition(startRotate[0], startRotate[1]);
        resourcePane.setOpacity(0);
        setFade(tutorialFade, tutorialPane, 0, 1, 500);
        tutorialFade.delayProperty().set(Duration.seconds(10));
        tutorialFade.play();
        tutorialFade.delayProperty().set(Duration.ZERO);
        //Initialize resources
        for (int i = 0; i < 10; i++) {
            resource[i] = new Resource();
        }
        resourceIndex = 0;
        tutorial = true;
    }    
    //Tutorial steps
    public void showTutorial(int step){
        switch (step) {
            case 0:
                tutorialPane.setOpacity(0);
                confirmButton.setVisible(false);
                tutorialTxt.setText("Para começar, adicione algum recurso.");
                tutorialPane.setLayoutY(200);
                tutorialPane.setLayoutX(378);
                tutorialFade.play();
                addResource.setVisible(true);
                break;
            case 1:
                tutorialPane.setOpacity(0);
                tutorialPane.setLayoutX(40);
                tutorialPane.setLayoutY(344);
                tutorialTxt.setText("Para adicionar um recurso, clique no recurso desejado e em seguida aperte o botão azul (+) que surgirá.");
                transitionX(resourcePaneMov, resourcePane, 235, 800);
                resourcePaneMov.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent1) -> {
                    tutorialFade.play();
                });
                resourcePaneMov.play();
                break;
            case 2:
                tutorialPane.setLayoutX(40);
                tutorialPane.setLayoutY(344);
                tutorialTxt.setText("Você também pode remover um recurso, basta selecioná-lo da caixa de recursos adicionados e apertar o botão vermelho (-) que surgirá.");
                break;
            case 3:
                tutorialPane.setLayoutX(40);
                tutorialPane.setLayoutY(344);
                tutorialTxt.setText("Adicione mais recursos se desejar. \nQuando tudo estiver pronto, clique no botão verde, na parte inferior da tela.");
                break;
            case 4:
                tutorialPane.setOpacity(0);
                tutorialPane.setLayoutY(550);
                tutorialPane.setLayoutX(703);
                tutorialTxt.setText("Clique no play, para começar.");
                tutorialFade.play();
                break;
            default:
                break;
        }
    }
    
    @FXML
    private void startOut(MouseEvent event) {
        start.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void startIn(MouseEvent event) {
        start.setEffect(new ColorAdjust(0, 0, 0, 0.2));
        getStage().getScene().setCursor(Cursor.HAND);
        startEffect.play();
    }

    @FXML
    private void startClick(MouseEvent event) throws IOException {
        tool.setResourceAmount(resourceAmount);
        //Set all added resources names and id's
        if (resourceAmount <= 0) {
            System.out.println("Adicione algum recurso!");
        } else {
            
        if (Resource.getIsAdded(0)) {
            resource[resourceIndex].setId(0);
            resource[resourceIndex].setResourceName(name0.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(1)) {
            resource[resourceIndex].setId(1);
            resource[resourceIndex].setResourceName(name1.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(2)) {
            resource[resourceIndex].setId(2);
            resource[resourceIndex].setResourceName(name2.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(3)) {
            resource[resourceIndex].setId(3);
            resource[resourceIndex].setResourceName(name3.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(4)) {
            resource[resourceIndex].setId(4);
            resource[resourceIndex].setResourceName(name4.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(5)) {
            resource[resourceIndex].setId(5);
            resource[resourceIndex].setResourceName(name5.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(6)) {
            resource[resourceIndex].setId(6);
            resource[resourceIndex].setResourceName(name6.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(7)) {
            resource[resourceIndex].setId(7);
            resource[resourceIndex].setResourceName(name7.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(8)) {
            resource[resourceIndex].setId(8);
            resource[resourceIndex].setResourceName(name8.getText());
            resourceIndex++;
        }
        if (Resource.getIsAdded(9)) {
            resource[resourceIndex].setId(9);
            resource[resourceIndex].setResourceName(name9.getText());
            resourceIndex++;
        }
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setDuration(Duration.seconds(3));
            fade.setNode(menu);
            fade.play();
            
            //Loads the main window
            FXMLLoader main = new FXMLLoader(getClass().getResource("Main.fxml"));
            main.setController(new MainController(this, tool, resource));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setScene(new Scene(main.load()));
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 4);
            stage.show();
            clip.stop();
        }
    }
    
    @FXML
    private void plusOut(MouseEvent event) {
        plus.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void plusIn(MouseEvent event) {
        plus.setEffect(new ColorAdjust(0, 0, 0, 0.05));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void plusClick(MouseEvent event) {
        
    }

    @FXML
    private void minusOut(MouseEvent event) {
        minus.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void minusIn(MouseEvent event) {
        minus.setEffect(new ColorAdjust(0, 0, 0, 0.05));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void minusClick(MouseEvent event) {
    }

    @FXML
    private void doneOut(MouseEvent event) {
        done.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void doneIn(MouseEvent event) {
        done.setEffect(new ColorAdjust(0, 0, 0, 0.2));
        getStage().getScene().setCursor(Cursor.HAND);
    }
    
    @FXML
    private void doneClick(MouseEvent event) {
        setFade(fade, resourcePane, 1, 0, 300);
        fade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            resourcePane.setVisible(false);
            if(tutorial){
                resourcePane.setLayoutX(resourcePane.getLayoutX() - 235);
            }
        });
        fade.play();
        if (resourceAmount > 0) {
            if(tutorial){
                showTutorial(4);
            }
            start.setVisible(true);
        }
        else{
            if(tutorial){showTutorial(0);}
            
            start.setVisible(false);
        }
        transitionY(move, resourceAmountPane, 80, -60, 500);
        move.play();
    }
    
    public void setFade(FadeTransition f, Node node, double from, double to, double time) {
        f.setFromValue(from);
        f.setToValue(to);
        f.setDuration(Duration.millis(time));
        f.setNode(node);
    }
    private void transitionY(TranslateTransition transition, Node node, double fromY, double toY, double duration) {
        transition.setFromY(fromY);
        transition.setToY(toY);
        transition.setDuration(Duration.millis(duration));
        transition.setNode(node);
    }
    private void transitionX(TranslateTransition transition, Node node, double byX, double duration) {
        transition.setByX(byX);
        transition.setDuration(Duration.millis(duration));
        transition.setNode(node);
    }

    @FXML
    private void minimizeWindowOut(MouseEvent event) {
        minimize.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void minimizeWindowIn(MouseEvent event) {
        minimize.setEffect(new ColorAdjust(0, 0, 0, 0.5));
    }

    @FXML
    private void closeWindowOut(MouseEvent event) {
        close.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void closeWindowIn(MouseEvent event) {
        close.setEffect(new ColorAdjust(0, 0, 0, 0.4));
    }

    @FXML
    private void pane0Out(MouseEvent event) {
        pane0.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane0In(MouseEvent event) {
        pane0.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane0Click(MouseEvent event) {
        selected.setVisible(true);
        //Is defines which icon will be showed
        if (pane0.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }

        selected.setLayoutY(pane0.getLayoutY() - 2);
        selected.setLayoutX(pane0.getLayoutX() - 2);
        clickedResource = 0;
    }

    @FXML
    private void pane1Our(MouseEvent event) {
        pane1.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane1In(MouseEvent event) {
        pane1.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane1Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane1.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane1.getLayoutY() - 2);
        selected.setLayoutX(pane1.getLayoutX() - 2);
        clickedResource = 1;
    }

    @FXML
    private void pane2Out(MouseEvent event) {
        pane2.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane2In(MouseEvent event) {
        pane2.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane2Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane2.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane2.getLayoutY() - 2);
        selected.setLayoutX(pane2.getLayoutX() - 2);
        clickedResource = 2;
    }

    @FXML
    private void pane3Out(MouseEvent event) {
        pane3.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane3In(MouseEvent event) {
        pane3.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane3Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane3.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane3.getLayoutY() - 2);
        selected.setLayoutX(pane3.getLayoutX() - 2);
        clickedResource = 3;
    }

    @FXML
    private void pane4Out(MouseEvent event) {
        pane4.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane4In(MouseEvent event) {
        pane4.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane4Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane4.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane4.getLayoutY() - 2);
        selected.setLayoutX(pane4.getLayoutX() - 2);
        clickedResource = 4;
    }

    @FXML
    private void pane5Out(MouseEvent event) {
        pane5.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane5In(MouseEvent event) {
        pane5.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane5Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane5.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane5.getLayoutY() - 2);
        selected.setLayoutX(pane5.getLayoutX() - 2);
        clickedResource = 5;
    }

    @FXML
    private void pane6Out(MouseEvent event) {
        pane6.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane6In(MouseEvent event) {
        pane6.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane6Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane6.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane6.getLayoutY() - 2);
        selected.setLayoutX(pane6.getLayoutX() - 2);
        clickedResource = 6;
    }

    @FXML
    private void pane7Out(MouseEvent event) {
        pane7.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane7In(MouseEvent event) {
        pane7.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane7Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane7.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane7.getLayoutY() - 2);
        selected.setLayoutX(pane7.getLayoutX() - 2);
        clickedResource = 7;
    }

    @FXML
    private void pane8Out(MouseEvent event) {
        pane8.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane8In(MouseEvent event) {
        pane8.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane8Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane8.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane8.getLayoutY() - 2);
        selected.setLayoutX(pane8.getLayoutX() - 2);
        clickedResource = 8;
    }

    @FXML
    private void pane9Out(MouseEvent event) {
        pane9.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void pane9In(MouseEvent event) {
        pane9.setEffect(new ColorAdjust(0, 0, 0, 0.06));
    }

    @FXML
    private void pane9Click(MouseEvent event) {
        selected.setVisible(true);
        if (pane9.getLayoutX() == 17) {
            plus.setVisible(true);
            minus.setVisible(false);
        } else {
            plus.setVisible(false);
            minus.setVisible(true);
        }
        selected.setLayoutY(pane9.getLayoutY() - 2);
        selected.setLayoutX(pane9.getLayoutX() - 2);
        clickedResource = 9;
    }

    @FXML
    private void topBarPress(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void moveScreen(MouseEvent event) {
        DeadlockDetection.getStage().setX(event.getScreenX() - xOffset);
        DeadlockDetection.getStage().setY(event.getScreenY() - yOffset);
    }
      

    @FXML
    private void resourcePaneClick(MouseEvent event) {
    }


    @FXML
    private void minimizeWindow(MouseEvent event) {
        DeadlockDetection.getStage().setIconified(true);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        DeadlockDetection.getStage().close();
    }

    @FXML
    private void addResourceRelease(MouseEvent event) {
        transitionY(move, resourceAmountPane, -60, 80, 500);
        move.play();
        addResource.setVisible(true);
        addResourcePressed.setVisible(false);
        setFade(fade, resourcePane, 0, 1, 300);
        fade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            if(tutorial){
            showTutorial(1);
            }
        });
        
        if(!resourcePane.visibleProperty().get()){
            fade.play();
        }
        resourcePane.setVisible(true);
    }

    @FXML
    private void addResourcePress(MouseEvent event) {
        addResource.setVisible(false);
        addResourcePressed.setVisible(true);
        
    }

    @FXML
    private void addResourceOut(MouseEvent event) {
        addResource.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void addResourceIn(MouseEvent event) {
        addResource.setEffect(new ColorAdjust(0, 0, 0, 0.07));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void plusRelease(MouseEvent event) {
        plus.setVisible(true);
        plusDown.setVisible(false);
        selected.setVisible(false);
        if (clickedResource == 0) {
            pane0.setLayoutX(386);
            Resource.setAdded(0, true);
            resourceAmount++;
        }
        if (clickedResource == 1) {
            pane1.setLayoutX(386);
            Resource.setAdded(1, true);
            resourceAmount++;
        }
        if (clickedResource == 2) {
            pane2.setLayoutX(386);
            Resource.setAdded(2, true);
            resourceAmount++;
        }
        if (clickedResource == 3) {
            pane3.setLayoutX(386);
            Resource.setAdded(3, true);
            resourceAmount++;
        }
        if (clickedResource == 4) {
            pane4.setLayoutX(386);
            Resource.setAdded(4, true);
            resourceAmount++;
        }
        if (clickedResource == 5) {
            pane5.setLayoutX(386);
            Resource.setAdded(5, true);
            resourceAmount++;
        }
        if (clickedResource == 6) {
            pane6.setLayoutX(386);
            Resource.setAdded(6, true);
            resourceAmount++;
        }
        if (clickedResource == 7) {
            pane7.setLayoutX(386);
            Resource.setAdded(7, true);
            resourceAmount++;
        }
        if (clickedResource == 8) {
            pane8.setLayoutX(386);
            Resource.setAdded(8, true);
            resourceAmount++;
        }
        if (clickedResource == 9) {
            pane9.setLayoutX(386);
            Resource.setAdded(9, true);
            resourceAmount++;
        }
        clickedResource = -1;
        if(tutorial && resourceAmount == 1){
            showTutorial(2);
        }else if(tutorial && resourceAmount > 1){
            resourceAmountLbl.setText(Integer.toString(resourceAmount));
            showTutorial(3);
        }
    }

    @FXML
    private void plusPress(MouseEvent event) {
        plus.setVisible(false);
        plusDown.setVisible(true);
    }

    @FXML
    private void minusRelease(MouseEvent event) {
        minus.setVisible(true);
        minusDown.setVisible(false);
        
        selected.setVisible(false);
        if (clickedResource == 0) {
            pane0.setLayoutX(17);
            Resource.setAdded(0, false);
            resourceAmount--;
        }
        if (clickedResource == 1) {
            pane1.setLayoutX(17);
            Resource.setAdded(1, false);
            resourceAmount--;
        }
        if (clickedResource == 2) {
            pane2.setLayoutX(17);
            Resource.setAdded(2, false);
            resourceAmount--;
        }
        if (clickedResource == 3) {
            pane3.setLayoutX(17);
            Resource.setAdded(3, false);
            resourceAmount--;
        }
        if (clickedResource == 4) {
            pane4.setLayoutX(17);
            Resource.setAdded(4, false);
            resourceAmount--;
        }
        if (clickedResource == 5) {
            pane5.setLayoutX(17);
            Resource.setAdded(5, false);
            resourceAmount--;
        }
        if (clickedResource == 6) {
            pane6.setLayoutX(17);
            Resource.setAdded(6, false);
            resourceAmount--;
        }
        if (clickedResource == 7) {
            pane7.setLayoutX(17);
            Resource.setAdded(7, false);
            resourceAmount--;
        }
        if (clickedResource == 8) {
            pane8.setLayoutX(17);
            Resource.setAdded(8, false);
            resourceAmount--;
        }
        if (clickedResource == 9) {
            pane9.setLayoutX(17);
            Resource.setAdded(9, false);
            resourceAmount--;
        }
        if (tutorial) {
            showTutorial(3);
        }
        clickedResource = -1;
        resourceAmountLbl.setText(Integer.toString(resourceAmount));
    }

    @FXML
    private void minusPress(MouseEvent event) {
        minus.setVisible(false);
        minusDown.setVisible(true);
    }

    @FXML
    private void confirmButtonClick(MouseEvent event) {
        showTutorial(0);
    }

    @FXML
    private void skipTutorial(MouseEvent event) {
        addResource.setVisible(true);
        tutorial = false;
        tutorialPane.setVisible(false);
        
        skipRotation.setByAngle(-360);
        skipRotation.setDuration(Duration.millis(800));
        skipRotation.setNode(skipButton);

        skipMove.setFromX(14);
        skipMove.setToX(-100);
        skipMove.setDuration(Duration.millis(800));
        skipMove.setNode(skipButton);
        skipEffect.setDelay(Duration.ZERO);
        skipEffect.play();
        skipEffect.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            if (tutorialPane.getLayoutX() == 40) {
                transitionX(resourcePaneMov, resourcePane, -235, 800);
                resourcePaneMov.play();
            }
        });
    }

    @FXML
    private void skipOut(MouseEvent event) {
        skipButton.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void skipIn(MouseEvent event) {
        skipButton.setEffect(new ColorAdjust(0, 0, 0, 0.1));
        getStage().getScene().setCursor(Cursor.HAND);
    }
    
    @FXML
    private void confirmButtonEnter(MouseEvent event) {
        confirmButton.setEffect(new ColorAdjust(0, 0, 0, 0.1));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void confirmButtonExit(MouseEvent event) {
        confirmButton.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

}
