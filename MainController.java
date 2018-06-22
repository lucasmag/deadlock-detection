/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deadlockdetection;

import com.sun.scenario.effect.Blend;
import com.sun.scenario.effect.Blend.Mode;
import static com.sun.scenario.effect.Blend.Mode.SRC_OVER;
import static deadlockdetection.DeadlockDetection.getStage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class MainController implements Initializable {
    private static boolean removing;
    List lista = new ArrayList();
    private DetectorTool tool;
    private Process process[] = new Process[10];
    private OS os;
    private int SODelta;
    private double xOffset;
    private double yOffset;
    private int stepUp;
    private int resourceIndex;
    private Resource[] resource;
    private Arrow arrow = new Arrow();
    MenuController menu;
    private TranslateTransition slide = new TranslateTransition();
    private TranslateTransition info[] = new TranslateTransition[3];
    private FadeTransition fade[] = new FadeTransition[2];
    private FadeTransition osFade = new FadeTransition();
    private FadeTransition mainFade = new FadeTransition();
    private FadeTransition tutorialFade = new FadeTransition();
    private FadeTransition deadlockPulse = new FadeTransition();
    private TranslateTransition skipMove = new TranslateTransition();
    private RotateTransition skipRotation = new RotateTransition();
    private ParallelTransition skipEffect;
    private FadeTransition trashPaneFade = new FadeTransition();
    private SequentialTransition balloon;
    private int processAmount;
    private int tutorialStep;
    
    @FXML
    private TextArea log;
    @FXML
    private Pane back;
    @FXML
    private ImageView minimize;
    @FXML
    private ImageView close;
    @FXML
    private Pane removePane;
    @FXML
    private TextField requestField;
    @FXML
    private TextField useField;
    @FXML
    private Label processAlias;
    @FXML
    private ImageView processIcon;
    @FXML
    private ImageView photoshop;
    @FXML
    private ImageView topBar;
    @FXML
    private ImageView reader;
    @FXML
    private ImageView word;
    @FXML
    private ImageView illustrator;
    @FXML
    private ImageView netbeans;
    @FXML
    private ImageView steam;
    @FXML
    private Label processId;
    @FXML
    private ImageView spotify;
    @FXML
    private ImageView utorrent;
    @FXML
    private ImageView chrome;
    @FXML
    private ImageView process2;
    @FXML
    private ImageView process3;
    @FXML
    private ImageView process4;
    @FXML
    private ImageView process5;
    @FXML
    private ImageView process6;
    @FXML
    private ImageView process7;
    @FXML
    private ImageView process8;
    @FXML
    private ImageView process9;
    @FXML
    private ImageView process0;
    @FXML
    private ImageView process1;
    @FXML
    private ImageView dropbox;
    @FXML
    private ImageView process0Arrow;
    @FXML
    private ImageView process1Arrow;
    @FXML
    private ImageView process2Arrow;
    @FXML
    private ImageView mouseArrow;
    @FXML
    private ImageView headsetArrow;
    @FXML
    private ImageView joystickArrow;
    @FXML
    private ImageView webcamArrow;
    @FXML
    private ImageView floppyArrow;
    @FXML
    private ImageView cpuArrow;
    @FXML
    private ImageView scannerArrow;
    @FXML
    private ImageView printerArrow;
    @FXML
    private ImageView pendriveArrow;
    @FXML
    private ImageView hdArrow;
    @FXML
    private ImageView process3Arrow;
    @FXML
    private ImageView process5Arrow;
    @FXML
    private ImageView process6Arrow;
    @FXML
    private ImageView process7Arrow;
    @FXML
    private ImageView process8Arrow;
    @FXML
    private ImageView process9Arrow;
    @FXML
    private ImageView process4Arrow;
    @FXML
    private Pane newProcessPane;
    @FXML
    private Label process0Label;   
    @FXML
    private Label process1Label;
    @FXML
    private Label process2Label;
    @FXML
    private Label process3Label;
    @FXML
    private Label process4Label;
    @FXML
    private Label process5Label;
    @FXML
    private Label process6Label;
    @FXML
    private Label process7Label;
    @FXML
    private Label process8Label;
    @FXML
    private Label process9Label;
    @FXML
    private ImageView situation2;
    @FXML
    private ImageView situation0;
    @FXML
    private ImageView situation3;
    @FXML
    private ImageView situation1;
    @FXML
    private ImageView situation4;
    @FXML
    private ImageView situation7;
    @FXML
    private ImageView situation5;
    @FXML
    private ImageView situation8;
    @FXML
    private ImageView situation6;
    @FXML
    private ImageView situation9;
    @FXML
    private ImageView p0Back;
    @FXML
    private ImageView p1Back;
    @FXML
    private ImageView p2Back;
    @FXML
    private ImageView p3Back;
    @FXML
    private ImageView p4Back;
    @FXML
    private ImageView p5Back;
    @FXML
    private ImageView p6Back;
    @FXML
    private ImageView p7Back;
    @FXML
    private ImageView p8Back;
    @FXML
    private ImageView p9Back;
    @FXML
    private Label resource0Label;
    @FXML
    private Label resource1Label;
    @FXML
    private Label resource2Label;
    @FXML
    private Label resource3Label;
    @FXML
    private Label resource4Label;
    @FXML
    private Label resource5Label;
    @FXML
    private Label resource6Label;
    @FXML
    private Label resource7Label;
    @FXML
    private Label resource8Label;
    @FXML
    private Label resource9Label;
    @FXML
    private Pane processInfo;
    @FXML
    private ImageView backInfo;
    @FXML
    private Label nameInfoP;
    @FXML
    private Label idInfoP;
    @FXML
    private Label requestInfo;
    @FXML
    private Label useInfo;
    @FXML
    private Pane resourceInfo;
    @FXML
    private ImageView backInfo1;
    @FXML
    private Label nameInfoR;
    @FXML
    private Label idInfoR;
    @FXML
    private ImageView cancelRemove;
    @FXML
    private ImageView addProcessButton;
    @FXML
    private ImageView cancelAdd;
    @FXML
    private ImageView resource0;
    @FXML
    private ImageView resource8;
    @FXML
    private ImageView resource7;
    @FXML
    private ImageView resource1;
    @FXML
    private ImageView resource4;
    @FXML
    private ImageView resource3;
    @FXML
    private ImageView resource6;
    @FXML
    private ImageView resource2;
    @FXML
    private ImageView resource5;
    @FXML
    private ImageView resource9;        
    @FXML
    private Pane iconBar;
    @FXML
    private Pane infoBar;
    @FXML
    private Label infoText;
    @FXML
    private Pane addIcon;
    @FXML
    private Label addIconText;
    @FXML
    private Pane resourceInfoLeft;
    @FXML
    private Label nameInfoLeft;
    @FXML
    private Pane resourceInfoRight;
    @FXML
    private Label nameInfoRight;
    @FXML
    private Pane OSPane;
    @FXML
    private TextField OSTime;
    @FXML
    private ImageView confirm;
    @FXML
    private Tab logTab;
    @FXML
    private Tab situationTab;
    @FXML
    private ImageView trashPane;
    @FXML
    private ImageView trash;
    @FXML
    private Pane topBarPane;
    @FXML
    private ImageView newProcessDown;
    @FXML
    private ImageView newProcess;
    @FXML
    private ImageView trashOpen;
    @FXML
    private TabPane tabPane;
    SingleSelectionModel<Tab> selectionModel;
    @FXML
    public ImageView verify;
    @FXML
    private ImageView removeProcessDown;
    @FXML
    private ImageView removeProcess;
    @FXML
    private Label cycleAmount;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private ImageView deadlock;
    @FXML
    private GridPane tutorial;
    @FXML
    private Text tutorialText;
    @FXML
    private ImageView tutorialButton;
    @FXML
    private ImageView skipButton;
    
    public MainController(MenuController m, DetectorTool dt, Resource[] rsc) throws IOException {
        tool = dt;
        resource = rsc;
        menu = m;
    }
    
       /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Sets all previously added resouces
        if (Resource.getIsAdded(0)) {
            resource[resourceIndex].setResourceArrow(hdArrow);
            resource[resourceIndex].setIcon(resource0);
            resource0Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(1)) {
            resource[resourceIndex].setResourceArrow(headsetArrow);
            resource[resourceIndex].setIcon(resource1);
            resource1Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(2)) {
            resource[resourceIndex].setResourceArrow(joystickArrow);
            resource[resourceIndex].setIcon(resource2);
            resource2Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(3)) {
            resource[resourceIndex].setResourceArrow(mouseArrow);
            resource[resourceIndex].setIcon(resource3);
            resource3Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(4)) {
            resource[resourceIndex].setResourceArrow(webcamArrow);
            resource[resourceIndex].setIcon(resource4);
            resource4Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(5)) {
            resource[resourceIndex].setResourceArrow(floppyArrow);
            resource[resourceIndex].setIcon(resource5);
            resource5Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(6)) {
            resource[resourceIndex].setResourceArrow(cpuArrow);
            resource[resourceIndex].setIcon(resource6);
            resource6Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(7)) {
            resource[resourceIndex].setResourceArrow(scannerArrow);
            resource[resourceIndex].setIcon(resource7);
            resource7Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(8)) {
            resource[resourceIndex].setResourceArrow(printerArrow);
            resource[resourceIndex].setIcon(resource8);
            resource8Label.setVisible(true);
            resourceIndex++;
        }
        if (Resource.getIsAdded(9)) {
            resource[resourceIndex].setResourceArrow(pendriveArrow);
            resource[resourceIndex].setIcon(resource9);
            resource9Label.setVisible(true);
            resourceIndex++;
        }
        for (int i = 0; i < 10; i++) {//Create processes
            process[i] = new Process(tool, resource, this);
        }
        processAmount = 0;
        selectionModel = tabPane.getSelectionModel();//Setting object to change tabs automatically
        removing = false;//Flag to be activated when removing processes
        tutorialStep = 0;
        
        //Visual effects - Setting transitions
        fade[0] = new FadeTransition(); 
        fade[1] = new FadeTransition();
        setFade(osFade, OSPane, 1, 0, 500);
        osFade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            OSPane.setVisible(false);
        });
        //Skip animations
        skipRotation.setByAngle(360);
        skipRotation.setDuration(Duration.millis(800));
        skipRotation.setNode(skipButton);
        skipMove.setFromX(0);
        skipMove.setToX(100);
        skipMove.setDuration(Duration.millis(800));
        skipMove.setNode(skipButton);
        skipEffect = new ParallelTransition(skipRotation, skipMove);
        skipEffect.setDelay(Duration.seconds(1));
        setFade(mainFade, mainAnchor, 0, 1, 2000);
        setFade(deadlockPulse, deadlock, 0, 1, 300);
        setFade(trashPaneFade, trashPane, 0, 1, 300);
        setFade(fade[0], infoBar, 0, 1, 500);
        setFade(fade[1], infoBar, 1, 0, 500);
        setFade(tutorialFade, tutorial, 0, 1, 300);
        
        balloon = new SequentialTransition(fade[0], new PauseTransition(Duration.seconds(3)), fade[1]);
        deadlockPulse.setAutoReverse(true);
        deadlockPulse.setCycleCount(2);
        deadlockPulse.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            deadlock.setVisible(false);
        });
        if(menu.tutorial){
            newProcess.setVisible(false);
            removeProcess.setVisible(false);
            cancelRemove.setVisible(false);
            cancelAdd.setVisible(false);
            mainFade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
                tutorial.setVisible(true);
                tutorialFade.play();
                skipEffect.play();
                skipEffect.setDelay(Duration.ZERO);
            });
        }
        mainFade.play();
    }
    //Tutorial steps
    public void showTutorial(int step){
        switch(step){
            case 0:
                tutorialText.setText("Tudo certo? Clique em confirmar.");
                break;
            case 1:
                tutorial.setOpacity(0);
                tutorialStep = 1;
                tutorialButton.setVisible(true);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(200);
                tutorialText.setText("Esta é a area de grafos. Os recursos adicionados aparecerão aqui, "
                        + "bem como os processos que você vier a adicionar.");
                tutorialFade.play();
                break;
            case 2:
                tutorialStep = 2;
                tutorialButton.setVisible(true);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(200);
                tutorialText.setText("Dependendo do tempo definido para o S.O., você deve ter percebido uma luz branca se movendo de cima para baixo nessa área.\n\n"
                        + "Isso indica que o sistema operacional está fazendo um escaneamento nos processos aqui presentes, no exato momento da passagem desta luz.");
                break;
            case 3:
                tutorialStep = 3;
                tutorialButton.setVisible(true);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(200);
                tutorialText.setText("Caso o S.O. encontre um ciclo você verá uma pulsação vermelha nessa área.");
                deadlockPulse.setCycleCount(6);
                deadlockPulse();
                deadlockPulse.setCycleCount(2);
                break;
            case 4:
                tutorial.setOpacity(0);
                tutorialStep = 4;
                tutorialButton.setVisible(true);
                tutorial.setLayoutX(300);
                tutorial.setLayoutY(100);
                tutorialText.setText("Essas informações também podem ser vistas na aba de logs ao lado, bem como as informações sobre cada"
                        + " ação dos processos adicionados, como posse ou requisição de um novo recurso, por exemplo.");
                tutorialFade.play();
                break;
            case 5:
                tutorialStep = 5;
                changeTab(1);
                tutorialText.setText("Voltando a falar do sistema operacional, temos a aba 'Situação', que como o nome sugere, mostra a "
                        + "situação de cada processo a cada verificação do S.O. \n\nO processo pode estar livre, em um ciclo(deadlock)"
                        + " ou impedido de rodar devido a presença de algum ciclo.\n\nImportante: Note que um processos estar livre "
                        + "não significa que ele está obrigatoriamente rodando no momento, só garante que ele não está nem em um ciclo,"
                        + " e nem impedido por um ciclo, portanto, ou ele está rodando ou voltará a rodar em algum dado momento.");
                break;
            case 6:
                tutorialStep = 6;
                tutorialText.setText("Em relação as abas: não se preocupe em gerenciá-las. A troca de abas é feita de forma automática"
                        + " com base na situação dos processos.");
                break;
                     
            case 7:
                tutorial.setOpacity(0);
                changeTab(0);
                tutorialButton.setVisible(false);
                tutorial.setLayoutY(510);
                newProcess.setVisible(true);
                tutorialText.setText("Essa é a area de criação e remoção de processos, e, para entendê-la, nada melhor do que a praticar.\n\n"
                        + "Clique em 'adicionar processo'.");
                 tutorialFade.play();
                break;
            case 8:
                tutorialText.setText("Você deve definir 3 coisas para cada processo: um tempo de solicitação, um tempo de requisição e"
                        + " um ícone. \n\nOs outros campos são preenchidos automaticamente.");
                break;
            case 9:
                 newProcess.setVisible(false);
                tutorialText.setText("Clique em adicionar quando estiver tudo pronto.");
                break;
            case 10:
                tutorial.setOpacity(0);
                tutorialButton.setVisible(true);
                tutorialStep = 10;
                tutorial.setLayoutX(250);
                tutorial.setLayoutY(100);
                tutorialText.setText("O ícone de processo guarda muitas informações. \nSua cor de fundo, por exemplo, representa o estado atual do processo.\n\n"
                        + "Verde: rodando.\nAmarelo: dormindo, aguardando algum recurso.\nVermelho: em um ciclo(deadlock).");
                tutorialFade.play();
                break;
            case 11:
                tutorialStep = 11;
                tutorialText.setText("O número acima do processo representa sua linha temporal de requisições de novos recursos.\n"
                        + "\nA cada ciclo o processo sorteará um novo recurso, dentre os existentes.");
                break;
            case 12:
                tutorialStep = 12;
                tutorialButton.setVisible(false);
                tutorialText.setText("Se você passar o mouse por cima do icone do processo, verá as informações definidas na criação do mesmo.\n\nTente!");
                break;
            case 13:
                tutorial.setOpacity(0);
                tutorialStep = 13;
                tutorialButton.setVisible(true);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(400);
                tutorialText.setText("Isso também funciona para os recursos adicionados.");
                tutorialFade.play();
                break;
            case 14:
                tutorialStep = 14;
                tutorialButton.setVisible(true);
                tutorialText.setText("O número no canto superior esquerdo do recurso representa a linha temporal de utilização de recursos"
                        + " do processo que o possui. Quando esse numero chegar a zero o recurso terminou de ser utilizado e será liberado.");
                break;
                
            case 15:
                tutorialStep = 15;
                tutorialButton.setVisible(true);
                tutorialText.setText("Em relação à posse e espera entre processos e recursos, adota-se a seguinte configuração:\n\n"
                        + "Linha amarela: processo está aguardando o recurso ser liberado.\n"
                        + "Linha verde: processo possui recurso.");
                break;
            case 16:
                removeProcess.setVisible(true);
                tutorial.setOpacity(0);
                tutorialButton.setVisible(false);
                tutorial.setLayoutX(300);
                tutorial.setLayoutY(510);
                tutorialText.setText("Agora vamos remover um processo.\nClique em 'remover processo'.");
                tutorialFade.play();
                break;
            case 17:
                removeProcess.setVisible(false);
                tutorial.setOpacity(0);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(450);
                tutorialText.setText("Para remover um processo basta arrastá-lo para o centro da área escura e soltá-lo na lixeira. \n\n"
                        + "Simples assim!");
                tutorialFade.play();
                break;
            case 18:
                tutorialStep = 17;
                tutorialButton.setVisible(true);
                tutorial.setOpacity(0);
                tutorial.setLayoutX(150);
                tutorial.setLayoutY(300);
                tutorialText.setText("A partir de agora você conhece todas as funcionalidades desta ferramenta e está apto para utiliza-lá. \n\n Faça bom proveito!");
                tutorialFade.play();
                break;
        }
    }
    
    //Logs
    public void newProcessLog(String process, int processId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - Novo processo adicionado! Nome: " + process + " | ID: " + processId);
        });
    }
    public  void requestLog(String process, int processId, String resource, int resourceId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - " + process + "[" + processId +"] solicitou " + resource + "[" + resourceId + "].");
        });
    }

    public void useLog(String process, int processId, String resource, int resourceId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - " + process + "[" + processId +"] está utilizando " + resource + "[" + resourceId + "].");
        });
    }

    public void removedLog(String process, int processId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - " + process + "[" + processId + "] foi removido.");
        });
    }

    public void concludedLog(String process, int processId, String resource, int resourceId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - " + process + "[" + processId +"] terminou de utilizar " + resource + "[" + resourceId + "].");
        });
    }
    
    public void sleepingLog(String process, String processId, String resource, int resourceId) {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - " + process + "[" + processId + "] bloqueado. " + resource + "[" + resourceId + "] ja está sendo usado.");
        });
    }
    
    public void noDeadlockLog() {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - [Sistema Operacional] - Sem deadlock até o momento." );
        });
    }
    public void deadlockLog() {
        Platform.runLater(() -> {
            log.appendText("\n[" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":"
                    + LocalDateTime.now().getSecond() + "] - [Sistema operacional] - DEADLOCK ENCONTRADO! \n             `-> Vide aba 'Situação' para mais detalhes.");
        });
    }
    
    //Move processes' icons, on 'situation tab'
    public void move(int process, int position){
        Platform.runLater(()->{
            if (process == 0) {
                situation0.setLayoutY(position);
            }
            if (process == 1) {
                situation1.setLayoutY(position);
            }
            if (process == 2) {
                situation2.setLayoutY(position);
            }
            if (process == 3) {
                situation3.setLayoutY(position);
            }
            if (process == 4) {
                situation4.setLayoutY(position);
            }
            if (process == 5) {
                situation5.setLayoutY(position);
            }
            if (process == 6) {
                situation6.setLayoutY(position);
            }
            if (process == 7) {
                situation7.setLayoutY(position);
            }
            if (process == 8) {
                situation8.setLayoutY(position);
            }
            if (process == 9) {
                situation9.setLayoutY(position);
            }
        });
    }
    
    //Updade the processes' requesting time labels
    public void updateTimelineRequest(int process, int request) {
        Platform.runLater(()->{
            if (process == 0) {
                process0Label.setText(Integer.toString(request));
            }
            if (process == 1) {
                process1Label.setText(Integer.toString(request));
            }
            if (process == 2) {
                process2Label.setText(Integer.toString(request));
            }
            if (process == 3) {
                process3Label.setText(Integer.toString(request));
            }
            if (process == 4) {
                process4Label.setText(Integer.toString(request));
            }
            if (process == 5) {
                process5Label.setText(Integer.toString(request));
            }
            if (process == 6) {
                process6Label.setText(Integer.toString(request));
            }
            if (process == 7) {
                process7Label.setText(Integer.toString(request));
            }
            if (process == 8) {
                process8Label.setText(Integer.toString(request));
            }
            if (process == 9) {
                process9Label.setText(Integer.toString(request));
            }
        });
    }
    
     //Updade the processes' use time labels
    public void updateTimelineUse(int resource, String use) {
        Platform.runLater(() -> {
            if (resource == 0) {
                resource0Label.setText(use);
            }
            if (resource == 1) {
                resource1Label.setText(use);
            }
            if (resource == 2) {
                resource2Label.setText(use);
            }
            if (resource == 3) {
                resource3Label.setText(use);
            }
            if (resource == 4) {
                resource4Label.setText(use);
            }
            if (resource == 5) {
                resource5Label.setText(use);
            }
            if (resource == 6) {
                resource6Label.setText(use);
            }
            if (resource == 7) {
                resource7Label.setText(use);
            }
            if (resource == 8) {
                resource8Label.setText(use);
            }
            if (resource == 9) {
                resource9Label.setText(use);
            }
        });
    }
    
    //Set the processes visibility
    public void visibility(int process, boolean visible) {
        Platform.runLater(() -> {
            if (process == 0) {
                process0Label.setVisible(visible);
                situation0.setVisible(visible);
                p0Back.setVisible(visible);
            }
            if (process == 1) {
                process1Label.setVisible(visible);
                situation1.setVisible(visible);
                p1Back.setVisible(visible);
            }
            if (process == 2) {
                process2Label.setVisible(visible);
                situation2.setVisible(visible);
                p2Back.setVisible(visible);
            }
            if (process == 3) {
                process3Label.setVisible(visible);
                situation3.setVisible(visible);
                p3Back.setVisible(visible);
            }
            if (process == 4) {
                process4Label.setVisible(visible);
                situation4.setVisible(visible);
                p4Back.setVisible(visible);
            }
            if (process == 5) {
                process5Label.setVisible(visible);
                situation5.setVisible(visible);
                p5Back.setVisible(visible);
            }
            if (process == 6) {
                process6Label.setVisible(visible);
                situation6.setVisible(visible);
                p6Back.setVisible(visible);
            }
            if (process == 7) {
                process7Label.setVisible(visible);
                situation7.setVisible(visible);
                p7Back.setVisible(visible);
            }
            if (process == 8) {
                process8Label.setVisible(visible);
                situation8.setVisible(visible);
                p8Back.setVisible(visible);
            }
            if (process == 9) {
                process9Label.setVisible(visible);
                situation9.setVisible(visible);
                p9Back.setVisible(visible);
            }
        });

    }
    //Set the processes' background color
    public void setBackStyle(int process, double hue, double saturation){
        Platform.runLater(()->{
            if (process == 0) {
                p0Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 1) {
                p1Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 2) {
                p2Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 3) {
                p3Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 4) {
                p4Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 5) {
                p5Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 6) {
                p6Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 7) {
                p7Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 8) {
                p8Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
            if (process == 9) {
                p9Back.setEffect(new ColorAdjust(hue, saturation, 0, 0));
            }
        });
    }
    //Set transitions verticaly
    public void newTransitionY(TranslateTransition transition, Node node, double fromY, double toY, double duration){
        transition.setFromY(fromY);
        transition.setToY(toY);
        transition.setDuration(Duration.millis(duration));
        transition.setNode(node);
        node.setVisible(true);
    }
    //Set fading
    public void setFade(FadeTransition transition, Node node, double from, double to, double duration) {
        transition.setFromValue(from);
        transition.setToValue(to);
        transition.setDuration(Duration.millis(duration));
        transition.setNode(node);
    }
    
    public void setCycleAmountText(String text){
        Platform.runLater(()->{ cycleAmount.setText(text); });
    }
    
    public void changeTab(int tab){
        selectionModel.select(tab);
    }
    public void deadlockPulse(){//Play one red pulse when OS finds a deadlock
        deadlock.setVisible(true);
        deadlockPulse.play();
    }
    public void showInfo(String sentence){//Show information on a balloon
        infoText.setText(sentence);     
        balloon.play();
    }
    
    @FXML
    private void addProcessRelease(MouseEvent event) {//Add process button
        try{
            if (requestField.getText().trim().isEmpty() || useField.getText().trim().isEmpty()) {
                showInfo("Preencha todos os campos.");
            } else if (Integer.parseInt(requestField.getText()) < 1 || Integer.parseInt(useField.getText()) < 1) {
                showInfo("Digite um número maior que zero.");
            } else if (processIcon.getImage() == null) {
                showInfo("Adicione um ícone.");
            } else {
                int np = newProcess();
                if (np > -1) {
                    processAmount++;
                    showInfo("Novo processo adicionado.");
                    System.out.println("Novo processo adicionado: " + process[np].getAlias() + "[" + np + ']');
                    newProcessLog(process[np].getAlias(), Integer.parseInt(process[np].getName()));
                    process[np].start();
                    newProcessPane.setVisible(false);
                    lista.add(process[np].getName());
                    processAlias.setText("-");
                    useField.setText("");
                    requestField.setText("");
                    iconBar.setVisible(false);
                    processIcon.setImage(null);
                    addIconText.setText("Clique para adicionar um ícone");
                } else {
                    System.out.println("Não ha espaco para novos processos");
                }
                if(menu.tutorial){
                    showTutorial(10);
                }
            }
        }catch(Exception e){
            showInfo("Somente números são permitidos.");
        }
    }
    
    private int newProcess(){//Tries to create a new process
        for (int i = 0; i < 10; i++) {
            if (process[i].getState().equals(Thread.State.NEW) || process[i].getState().equals(Thread.State.TERMINATED)) {
                if(process[i].getState().equals(Thread.State.TERMINATED)){
                    process[i] = new Process(tool, resource, this);
                } else{tool.setProcessAmount(tool.getProcessAmount()+1);}
                process[i].setIndex(i);
                process[i].setName(Integer.toString(stepUp));
                process[i].setAlias(processAlias.getText());
                refreshSelector(process[i].getAlias(),false);
                setVisual(i);
                process[i].setRequest(Integer.parseInt(requestField.getText()));
                process[i].setUse(Integer.parseInt(useField.getText()));
                process[i].resetRequest();
                stepUp++;
                return i;
            }
        }
        return -1;
    }
    
    public void refreshSelector(String process, boolean validate){ //Refresh the process selection icon bar
        if(process.equals("Photoshop")){
            photoshop.setVisible(validate);
        }
        if(process.equals("Illustrator")){
            illustrator.setVisible(validate);
        }
        if(process.equals("Chrome")){
            chrome.setVisible(validate);
        }
        if(process.equals("Dropbox")){
            dropbox.setVisible(validate);
        }
        if(process.equals("Reader")){
            reader.setVisible(validate);
        }
        if(process.equals("Steam")){
            steam.setVisible(validate);
        }
        if(process.equals("Utorrent")){
            utorrent.setVisible(validate);
        }
        if(process.equals("Netbeans")){
            netbeans.setVisible(validate);
        }
        if(process.equals("Spotify")){
            spotify.setVisible(validate);
        }
        if(process.equals("Word")){
            word.setVisible(validate);
        }
    }
    
    public void setVisual(int i) {//Sets the added processes visual
        if (i == 0) {
            process0.setImage(processIcon.getImage());
            situation0.setImage(processIcon.getImage());
            process0.setVisible(true);
            process[i].setIcon(process0);
            process[i].setWantArrow(process0Arrow);
        }
        if (i == 1) {
            process1.setImage(processIcon.getImage());
            situation1.setImage(processIcon.getImage());
            process1.setVisible(true);
            process[i].setIcon(process1);
            process[i].setWantArrow(process1Arrow);
        }
        if (i == 2) {
            process2.setImage(processIcon.getImage());
            situation2.setImage(processIcon.getImage());
            process2.setVisible(true);
            process[i].setIcon(process2);
            process[i].setWantArrow(process2Arrow);
        }
        if (i == 3) {
            process3.setImage(processIcon.getImage());
            situation3.setImage(processIcon.getImage());
            process3.setVisible(true);
            process[i].setIcon(process3);
            process[i].setWantArrow(process3Arrow);
        }
        if (i == 4) {
            process4.setImage(processIcon.getImage());
            situation4.setImage(processIcon.getImage());
            process4.setVisible(true);
            process[i].setIcon(process4);
            process[i].setWantArrow(process4Arrow);
        }
        if (i == 5) {
            process5.setImage(processIcon.getImage());
            situation5.setImage(processIcon.getImage());
            process5.setVisible(true);
            process[i].setIcon(process5);
            process[i].setWantArrow(process5Arrow);
        }
        if (i == 6) {
            process6.setImage(processIcon.getImage());
            situation6.setImage(processIcon.getImage());
            process6.setVisible(true);
            process[i].setIcon(process6);
            process[i].setWantArrow(process6Arrow);
        }
        if (i == 7) {
            process7.setImage(processIcon.getImage());
            situation7.setImage(processIcon.getImage());
            process7.setVisible(true);
            process[i].setIcon(process7);
            process[i].setWantArrow(process7Arrow);
        }
        if (i == 8) {
            process8.setImage(processIcon.getImage());
            situation8.setImage(processIcon.getImage());
            process8.setVisible(true);
            process[i].setIcon(process8);
            process[i].setWantArrow(process8Arrow);
        }
        if (i == 9) {
            process9.setImage(processIcon.getImage());
            situation9.setImage(processIcon.getImage());
            process9.setVisible(true);
            process[i].setIcon(process9);
            process[i].setWantArrow(process9Arrow);
        }
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
    private void minimizeWindowOut(MouseEvent event) {
        minimize.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void minimizeWindowIn(MouseEvent event) {
        minimize.setEffect(new ColorAdjust(0, 0, 0, 0.4));
    }

    @FXML
    private void closeWindowOut(MouseEvent event) {
        close.setEffect(new ColorAdjust(0, 0, 0, 0));
    }

    @FXML
    private void closeWindowIn(MouseEvent event) {
        close.setEffect(new ColorAdjust(0, 0, 0, 0.2));
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
    private void selNetbeans(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(netbeans.getImage());
        processAlias.setText("Netbeans");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selSpotify(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(spotify.getImage());
        processAlias.setText("Spotify");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selWord(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(word.getImage());
        processAlias.setText("Word");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selSteam(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(steam.getImage());
        processAlias.setText("Steam");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selUtorrent(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(utorrent.getImage());
        processAlias.setText("Utorrent");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selChrome(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(chrome.getImage());
        processAlias.setText("Chrome");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selDropbox(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(dropbox.getImage());
        processAlias.setText("Dropbox");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selReader(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(reader.getImage());
        processAlias.setText("Reader");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selPhotoshop(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(photoshop.getImage());
        processAlias.setText("Photoshop");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void selIllustrator(MouseEvent event) {
        transitionIconBar(slide, iconBar, 5, 1201, 1000);
        slide.play();
        processIcon.setImage(illustrator.getImage());
        processAlias.setText("Illustrator");
        addIconText.setText("Ícone adicionado!");
    }

    @FXML
    private void p0Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p0Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[0].getName());
            nameInfoP.setText(process[0].getAlias());
            requestInfo.setText(Integer.toString(process[0].getRequest()));
            useInfo.setText(Integer.toString(process[0].getUse()));
            backInfo.setRotate(180);
            processInfo.setLayoutX(64);
            processInfo.setLayoutY(119);
            processInfo.setVisible(true);
        });
        if(menu.tutorial){
            if(tutorialStep == 12){
                showTutorial(13);
            }
        }
    }
    
    @FXML
    private void p1Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p1Enter(MouseEvent event) {
        Platform.runLater(()->{
            idInfoP.setText(process[1].getName());
            nameInfoP.setText(process[1].getAlias());
            requestInfo.setText(Integer.toString(process[1].getRequest()));
            useInfo.setText(Integer.toString(process[1].getUse()));
            backInfo.setRotate(180);
            processInfo.setLayoutX(183);
            processInfo.setLayoutY(104);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p2Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p2Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[2].getName());
            nameInfoP.setText(process[2].getAlias());
            requestInfo.setText(Integer.toString(process[2].getRequest()));
            useInfo.setText(Integer.toString(process[2].getUse()));
            backInfo.setRotate(180);
            processInfo.setLayoutX(305);
            processInfo.setLayoutY(99);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p3Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p3Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[3].getName());
            nameInfoP.setText(process[3].getAlias());
            requestInfo.setText(Integer.toString(process[3].getRequest()));
            useInfo.setText(Integer.toString(process[3].getUse()));
            backInfo.setRotate(180);
            processInfo.setLayoutX(415);
            processInfo.setLayoutY(106);
            processInfo.setVisible(true);
        });
    }
    @FXML
    private void p4Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p4Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[4].getName());
            nameInfoP.setText(process[4].getAlias());
            requestInfo.setText(Integer.toString(process[4].getRequest()));
            useInfo.setText(Integer.toString(process[4].getUse()));
            backInfo.setRotate(180);
            processInfo.setLayoutX(527);
            processInfo.setLayoutY(120);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p5Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p5Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[5].getName());
            nameInfoP.setText(process[5].getAlias());
            requestInfo.setText(Integer.toString(process[5].getRequest()));
            useInfo.setText(Integer.toString(process[5].getUse()));
            backInfo.setRotate(0);
            processInfo.setLayoutX(70);
            processInfo.setLayoutY(411);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p6Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p6Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[6].getName());
            nameInfoP.setText(process[6].getAlias());
            requestInfo.setText(Integer.toString(process[6].getRequest()));
            useInfo.setText(Integer.toString(process[6].getUse()));
            backInfo.setRotate(0);
            processInfo.setLayoutX(187);
            processInfo.setLayoutY(424);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p7Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p7Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[7].getName());
            nameInfoP.setText(process[7].getAlias());
            requestInfo.setText(Integer.toString(process[7].getRequest()));
            useInfo.setText(Integer.toString(process[7].getUse()));
            backInfo.setRotate(0);
            processInfo.setLayoutX(308);
            processInfo.setLayoutY(436);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p8Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p8Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[8].getName());
            nameInfoP.setText(process[8].getAlias());
            requestInfo.setText(Integer.toString(process[8].getRequest()));
            useInfo.setText(Integer.toString(process[8].getUse()));
            backInfo.setRotate(0);
            processInfo.setLayoutX(419);
            processInfo.setLayoutY(424);
            processInfo.setVisible(true);
        });
    }
    
    @FXML
    private void p9Exit(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p9Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoP.setText(process[9].getName());
            nameInfoP.setText(process[9].getAlias());
            requestInfo.setText(Integer.toString(process[9].getRequest()));
            useInfo.setText(Integer.toString(process[9].getUse()));
            backInfo.setRotate(0);
            processInfo.setLayoutX(525);
            processInfo.setLayoutY(408);
            processInfo.setVisible(true);
        });
    }

    @FXML
    private void r0Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r0Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("0");
            nameInfoR.setText(menu.name0.getText());
            resourceInfo.setLayoutX(259);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }

    @FXML
    private void r1Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r1Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("1");
            nameInfoR.setText(menu.name1.getText());
            resourceInfo.setLayoutX(331);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r2Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r2Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("2");
            nameInfoR.setText(menu.name2.getText());
            resourceInfo.setLayoutX(188);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r3Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r3Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("3");
            nameInfoR.setText(menu.name3.getText());
            resourceInfo.setLayoutX(405);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r4Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r4Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("4");
            nameInfoR.setText(menu.name4.getText());
            resourceInfo.setLayoutX(115);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
        @FXML
    private void r5Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r5Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("5");
            nameInfoR.setText(menu.name5.getText());
            resourceInfo.setLayoutX(477);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r6Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r6Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("6");
            nameInfoR.setText(menu.name6.getText());
            resourceInfo.setLayoutX(45);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r7Exit(MouseEvent event) {
        resourceInfo.setVisible(false);
    }

    @FXML
    private void r7Enter(MouseEvent event) {
        Platform.runLater(() -> {
            idInfoR.setText("7");
            nameInfoR.setText(menu.name7.getText());
            resourceInfo.setLayoutX(547);
            resourceInfo.setLayoutY(246);
            resourceInfo.setVisible(true);
        });
    }
    
    @FXML
    private void r8Exit(MouseEvent event) {
        resourceInfoLeft.setVisible(false);
    }

    @FXML
    private void r8Enter(MouseEvent event) {
        Platform.runLater(() -> {
            nameInfoLeft.setText(menu.name8.getText());
            resourceInfoLeft.setVisible(true);
        });
    }
    
    @FXML
    private void r9Exit(MouseEvent event) {
        resourceInfoRight.setVisible(false);
    }

    @FXML
    private void r9Enter(MouseEvent event) {
        Platform.runLater(() -> {
            nameInfoRight.setText(menu.name9.getText());
            resourceInfoRight.setVisible(true);
        });
    }

    @FXML
    private void processIconExit(MouseEvent event) {
    }

    @FXML
    private void processIconEnter(MouseEvent event) {
    }

    @FXML
    private void processIconClick(MouseEvent event) {
    }

    @FXML
    private void newProcessRelease(MouseEvent event) {
        if(menu.tutorial){
            newProcess.setVisible(true);
            showTutorial(8);
        }
        newProcessDown.setVisible(false);
        newProcess.setVisible(true);
        if(processAmount < 10){
            newProcessPane.setVisible(true);
            processId.setText(Integer.toString(stepUp));
        }else{
            showInfo("Numéro máximo de processos atingido.");
        }
    }

    @FXML
    private void newProcessExit(MouseEvent event) {
        newProcess.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void newProcessEnter(MouseEvent event) {
        newProcess.setEffect(new ColorAdjust(0, 0, 0, 0.1));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void newProcessPress(MouseEvent event) {
        newProcessDown.setVisible(true);
        newProcess.setVisible(false);
    }
    
    @FXML
    private void removeProcessRelease(MouseEvent event) {
        trashPane.setVisible(true);
        trashPaneFade.play();
        removePane.setVisible(true);
        removeProcess.setVisible(true);
        removeProcessDown.setVisible(false);
        trash.setVisible(true);
        removing = true;
        if(menu.tutorial){
            showTutorial(17);
        }
    }

    @FXML
    private void removeProcessExit(MouseEvent event) {
        removeProcess.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void removeProcessEnter(MouseEvent event) {
        removeProcess.setEffect(new ColorAdjust(0, 0, 0, 0.1));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void removeProcessPress(MouseEvent event) {
        removeProcess.setVisible(false);
        removeProcessDown.setVisible(true);
    }

    @FXML
    private void addProcessEnter(MouseEvent event) {
        addProcessButton.setEffect(new ColorAdjust(0, 0, 0, 1));
        getStage().getScene().setCursor(Cursor.HAND);
    }


    @FXML
    private void addProcessExit(MouseEvent event) {
        addProcessButton.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void cancelProcessEnter(MouseEvent event) {
        cancelAdd.setEffect(new ColorAdjust(0, 0, 0, 0.04));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void cancelProcessExit(MouseEvent event) {
        cancelAdd.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void cancelRemoveRelease(MouseEvent event) {
        removePane.setVisible(false);
        trashPane.setVisible(false);
        trash.setVisible(false);
        removing = false;
    }

    @FXML
    private void cancelRemoveExit(MouseEvent event) {
        cancelRemove.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void cancelRemoveEnter(MouseEvent event) {
        cancelRemove.setEffect(new ColorAdjust(0, 0, 0, 0.04));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void cancelRemovePress(MouseEvent event) {
    }

    @FXML
    private void pullIcons(MouseEvent event) {
        transitionIconBar(slide, iconBar, 1201, 5, 500);
        slide.play();
    }
    
    private void transitionIconBar(TranslateTransition transition, Node node, double fromX, double toX, double duration){
        transition.setFromX(fromX);
        transition.setToX(toX);
        transition.setDuration(Duration.millis(duration));
        transition.setNode(node);
        node.setVisible(true);
    }

    @FXML
    private void addProcessClick(MouseEvent event) {
    }

    @FXML
    private void cancelProcessClick(MouseEvent event) {
        newProcessPane.setVisible(false);
        processAlias.setText("-");
        useField.setText("");
        requestField.setText("");
        processIcon.setImage(null);
        iconBar.setVisible(false);
        addIconText.setText("Clique para adicionar um ícone");
    }

    @FXML
    private void addProcessPress(MouseEvent event) {
    }

    @FXML
    private void addIconExit(MouseEvent event) {
        addIcon.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void addIconEnter(MouseEvent event) {
        addIcon.setEffect(new ColorAdjust(0, 0, 0.3, 0));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void addIconClick(MouseEvent event) {
        transitionIconBar(slide, iconBar, 1201, 5, 500);
        slide.play();
        if(menu.tutorial){
            showTutorial(9);
        }
    }

    @FXML
    private void confirmExit(MouseEvent event) {
        confirm.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void confirmEnter(MouseEvent event) {
        confirm.setEffect(new ColorAdjust(0, 0, 0, 0.5));
        getStage().getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void confirmClick(MouseEvent event) {
        osFade.play();
        SODelta = Integer.parseInt(OSTime.getText());
        try{
            os = new OS(SODelta, tool, this);
            os.start();
            if(menu.tutorial){
                showTutorial(1);
            }
        }catch(Exception e){
            System.out.println("somente numeros");
        }
    }
    //Set all processes icons background to white
    public void cleanBackStyle(){
        for (int i = 0; i < 10; i++) {
            setBackStyle(i, 0, 0);
        }
    }
    
    public void removeProcess(int index) throws InterruptedException{
        if(tool.findDeadlock()){
            os.cleanBackStyle();
        }
        removePane.setVisible(false);
        Process.removeProcess = Integer.parseInt(process[index].getName());
        refreshSelector(process[index].getAlias(), true);
        Process.ldbr = process[index].getDrawn();
        if (tool.getState(index, process[index].getDrawn()) == 2) {
            do {
                resource[Process.ldbr].safeRelease();
                Process.remover.acquire();
            } while (tool.getState(index, process[index].getDrawn()) == 2);
        }
        showInfo("Processo removido.");
        trash.setVisible(false);
        trashOpen.setVisible(false);
        trashPane.setVisible(false);
        process[index].getIcon().setEffect(null);
        removing = false;
        processAmount--;
        if(menu.tutorial){
            showTutorial(18);
        }
    }
    //Verify if the dragged process to be removed is inside trash
    public boolean insideTrash(ImageView process){
        if((process.getLayoutX() + 25) > 354 && (process.getLayoutX() + 25) < 414 && (process.getLayoutY() + 25) > 292 && (process.getLayoutY() + 25) < 366){
            trash.setVisible(false);
            trashOpen.setVisible(true);
            return true;
        }
        else{
            if(removing){
                trash.setVisible(true);
                trashOpen.setVisible(false);
            }
            return false;
        }
    }
    
    @FXML
    private void p0Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process0.setLayoutX(process0.getLayoutX() + event.getX() - 25);
            process0.setLayoutY(process0.getLayoutY() + event.getY() - 25);
            if (insideTrash(process0)) {
                process0.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 40, 100, 0, 0));
            } else {
                process0.setEffect(null);
            }
        }
    }

    @FXML
    private void p0Press(MouseEvent event) {
        processInfo.setVisible(false);
    }

    @FXML
    private void p0Release(MouseEvent event) throws InterruptedException {
        if(insideTrash(process0)){
            removeProcess(0);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process0.setLayoutX(126);
        process0.setLayoutY(68);
    }
    
    @FXML
    private void p1Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process1.setLayoutX(process1.getLayoutX() + event.getX() - 25);
            process1.setLayoutY(process1.getLayoutY() + event.getY() - 25);
            if (insideTrash(process1)) {
                process1.setEffect(new InnerShadow(BlurType.GAUSSIAN, Color.RED, 100, 40, 0, 0));
            } else {
                process1.setEffect(null);
            }
        }
    }

    @FXML
    private void p1Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    
    @FXML
    private void p1Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process1)) {
            removeProcess(1);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process1.setLayoutX(245);
        process1.setLayoutY(53);
    }
    
    @FXML
    private void p2Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    
    @FXML
    private void p2Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process2)) {
            removeProcess(2);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process2.setLayoutX(367);
        process2.setLayoutY(47);
    }

    @FXML
    private void p2Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process2.setLayoutX(process2.getLayoutX() + event.getX() - 25);
            process2.setLayoutY(process2.getLayoutY() + event.getY() - 25);
            if (insideTrash(process2)) {
                process2.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process2.setEffect(null);
            } 
        }
    }
    
    @FXML
    private void p3Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p3Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process3)) {
            removeProcess(3);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process3.setLayoutX(477);
        process3.setLayoutY(55);
    }

    @FXML
    private void p3Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process3.setLayoutX(process3.getLayoutX() + event.getX() - 25);
            process3.setLayoutY(process3.getLayoutY() + event.getY() - 25);
            if (insideTrash(process3)) {
                process3.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process3.setEffect(null);
            }
        }
        
    }
    
    @FXML
    private void p4Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p4Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process4)) {
            removeProcess(4);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process4.setLayoutX(589);
        process4.setLayoutY(71);
    }

    @FXML
    private void p4Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process4.setLayoutX(process4.getLayoutX() + event.getX() - 25);
            process4.setLayoutY(process4.getLayoutY() + event.getY() - 25);
            if (insideTrash(process4)) {
                process4.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process4.setEffect(null);
            }
        }
    }
    
    @FXML
    private void p5Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p5Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process5)) {
            removeProcess(5);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process5.setLayoutX(130);
        process5.setLayoutY(529);
    }

    @FXML
    private void p5Drag(MouseEvent event) {
        if(removing){
            getStage().getScene().setCursor(Cursor.HAND);
            process5.setLayoutX(process5.getLayoutX() + event.getX() - 25);
            process5.setLayoutY(process5.getLayoutY() + event.getY() - 25);
            if (insideTrash(process5)) {
                process5.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process5.setEffect(null);
            } 
        }
    }
    
    @FXML
    private void p6Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p6Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process6)) {
            removeProcess(6);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process6.setLayoutX(246);
        process6.setLayoutY(540);
    }

    @FXML
    private void p6Drag(MouseEvent event) {
        if (removing) {
            getStage().getScene().setCursor(Cursor.HAND);
            process6.setLayoutX(process6.getLayoutX() + event.getX() - 25);
            process6.setLayoutY(process6.getLayoutY() + event.getY() - 25);
            if (insideTrash(process6)) {
                process6.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process6.setEffect(null);
            }
        }
    }
    
    @FXML
    private void p7Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p7Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process7)) {
            removeProcess(7);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process7.setLayoutX(368);
        process7.setLayoutY(553);
    }

    @FXML
    private void p7Drag(MouseEvent event) {
        if (removing) {
            getStage().getScene().setCursor(Cursor.HAND);
            process7.setLayoutX(process7.getLayoutX() + event.getX() - 25);
            process7.setLayoutY(process7.getLayoutY() + event.getY() - 25);
            if (insideTrash(process7)) {
                process7.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process7.setEffect(null);
            }
        }
    }
    
    @FXML
    private void p8Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p8Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process8)) {
            removeProcess(8);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process8.setLayoutX(480);
        process8.setLayoutY(542);
    }

    @FXML
    private void p8Drag(MouseEvent event) {
        if (removing) {
            getStage().getScene().setCursor(Cursor.HAND);
            process8.setLayoutX(process8.getLayoutX() + event.getX() - 25);
            process8.setLayoutY(process8.getLayoutY() + event.getY() - 25);
            if (insideTrash(process8)) {
                process8.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process8.setEffect(null);
            }
        }
    }

    @FXML
    private void p9Press(MouseEvent event) {
        processInfo.setVisible(false);
    }
    @FXML
    private void p9Release(MouseEvent event) throws InterruptedException {
        if (insideTrash(process9)) {
            removeProcess(9);
        }
        getStage().getScene().setCursor(Cursor.DEFAULT);
        process9.setLayoutX(585);
        process9.setLayoutY(525);
    }

    @FXML
    private void p9Drag(MouseEvent event) {
        if (removing) {
            getStage().getScene().setCursor(Cursor.HAND);
            process9.setLayoutX(process9.getLayoutX() + event.getX() - 25);
            process9.setLayoutY(process9.getLayoutY() + event.getY() - 25);
            if (insideTrash(process9)) {
                process9.setEffect(new InnerShadow(BlurType.THREE_PASS_BOX, Color.RED, 0, 0, 120, 120));
            } else {
                process9.setEffect(null);
            }
        }
    }

    @FXML
    private void iconBarEnter(MouseEvent event) {
        getStage().getScene().setCursor(Cursor.HAND);
    }
    
    @FXML
    private void iconBarExit(MouseEvent event) {
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void nextStep(MouseEvent event) {
        switch(tutorialStep){
            case 1:
                showTutorial(2);
                break;
            case 2:
                showTutorial(3);
                break;
            case 3:
                showTutorial(4);
                break;
            case 4:
                showTutorial(5);
                break;
            case 5:
                showTutorial(6);
                break;
            case 6:
                showTutorial(7);
                break;
            case 10:
                showTutorial(11);
                break;
            case 11:
                showTutorial(12);
                break;
            case 13:
                showTutorial(14);
                break;
            case 14:
                showTutorial(15);
                break;
            case 15:
                showTutorial(16);
                break;
            case 17:
                skipRotation.setByAngle(-360);
                skipRotation.setDuration(Duration.millis(800));
                skipRotation.setNode(skipButton);
                skipMove.setFromX(100);
                skipMove.setToX(0);
                skipMove.setDuration(Duration.millis(800));
                skipMove.setNode(skipButton);
                skipEffect.play();
                
                menu.tutorial = false;
                setFade(tutorialFade, tutorial, 1, 0, 1000);
                tutorialFade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
                    tutorial.setVisible(false);
                });
                tutorialFade.play();
                removeProcess.setVisible(true);
                newProcess.setVisible(true);
                cancelRemove.setVisible(true);
                cancelAdd.setVisible(true);
                break;
        }
    }

    @FXML
    private void OSEntered(KeyEvent event) {
        if (menu.tutorial) {
            showTutorial(0);
        }
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
    private void skipClick(MouseEvent event) {
        skipRotation.setByAngle(-360);
        skipRotation.setDuration(Duration.millis(800));
        skipRotation.setNode(skipButton);
        skipMove.setFromX(100);
        skipMove.setToX(0);
        skipMove.setDuration(Duration.millis(800));
        skipMove.setNode(skipButton);
        skipEffect.play();
        
        menu.tutorial = false;
        setFade(tutorialFade, tutorial, 1, 0, 300);
        tutorialFade.onFinishedProperty().set((EventHandler<ActionEvent>) (ActionEvent actionEvent) -> {
            tutorial.setVisible(false);
        });
        tutorialFade.play();
        removeProcess.setVisible(true);
        newProcess.setVisible(true);
        cancelRemove.setVisible(true);
        cancelAdd.setVisible(true);
    }

    @FXML
    private void nextStepExit(MouseEvent event) {
        tutorialButton.setEffect(new ColorAdjust(0, 0, 0, 0));
        getStage().getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void nextStepEnter(MouseEvent event) {
        tutorialButton.setEffect(new ColorAdjust(0, 0, 0, 0.1));
        getStage().getScene().setCursor(Cursor.HAND);
    }
}
