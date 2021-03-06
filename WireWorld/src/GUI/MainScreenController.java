package GUI;

import Board.BoardMaker;
import Board.Template;
import Board.Templates;
import Generator.BoardAdapter;
import Generator.GeneratorHandler;
import IO.IO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class MainScreenController {
    private Scene scene;
    private Templates templates;
    private Template currentTemplate;
    private BoardMaker boardMaker;
    private Stage stage;
    private Colors colors;

    private BoardAdapter adapter;
    private GeneratorHandler genHandler;
    private IO io;

    @FXML
    Label stateLabel;

    @FXML
    JFXTextArea hint;

    @FXML
    JFXTextArea hint2;

    @FXML
    Circle stateCircle;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Pane board;
    @FXML
    JFXButton loadTemplate;

    @FXML
    JFXButton load;

    @FXML
    JFXButton go;

    @FXML
    JFXButton pause;


    @FXML
    JFXButton clear;

    @FXML
    JFXButton color;


    @FXML
    Button figure1;

    @FXML
    Button figure2;

    @FXML
    Button figure3;

    @FXML
    Button figure4;
    @FXML
    Button figure5;

    @FXML
    Button figure6;

    @FXML
    JFXTextField path;


    @FXML
    public void initialize() {
        colors = new Colors();
        boardMaker = new BoardMaker();
        boardMaker.setMainScreenController(this);
        boardMaker.makeBoard(colors, board, 100, 100, 20, 800, 600);

        adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(300, adapter);

        genHandler.start();
        io = new IO();
        templates = new Templates();

    }

    public JFXTextArea getHint() {
        return hint;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public GeneratorHandler getGenHandler() {
        return genHandler;
    }

    public void loadFile() {
        genHandler.pauseGenerator();
        isAnimationRunningSignal(false);
        disableTemplateButtons(false);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            boardMaker.setBoardColor(0);
            boardMaker.setColorMode();
            io.readBoardConfiguration(file, adapter);
        }
    }

    public void loadTemplate(){
        genHandler.pauseGenerator();
        isAnimationRunningSignal(false);
        disableTemplateButtons(false);
        Template template = null;

        if(templates.getKeyList().size()==3)
            return;

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);


        if( file != null){
                Board.Template templ = io.readTemplate( file );
                template = templ ;
                if( templ != null)
                    templates.addTemplate( templ.getName(), templ);
            }

        if (template!=null) {
            if (figure4.getText().compareTo("-") == 0) {
                figure4.setText(template.getName());
                return;
            } else if (figure5.getText().compareTo("-") == 0) {
                figure5.setText(template.getName());
                return;
            } else if (figure6.getText().compareTo("-") == 0) {
                figure6.setText(template.getName());
                return;
            }
        }
    }

    public void saveToFile() {
        genHandler.pauseGenerator();
        isAnimationRunningSignal(false);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",  "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);

        if (file != null)
            io.saveBoard(file, adapter);
    }

    public void goAnimation() {
        boardMaker.setInsensitiveMode();
        disableTemplateButtons(true);
        genHandler.playGenerator();
        isAnimationRunningSignal(true);
    }

    public void pauseAnimation() {
        boardMaker.setColorMode();
        disableTemplateButtons(false);
        genHandler.pauseGenerator();
        isAnimationRunningSignal(false);
    }

    public void clear() {
        genHandler.pauseGenerator();
        isAnimationRunningSignal(false);
        boardMaker.setBoardColor(0);
        boardMaker.setColorMode();
        disableTemplateButtons(false);

    }

    public void colorMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/ColorScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ColorScreenController colorScreenController = loader.getController();
        colorScreenController.setColors(colors);
        colorScreenController.setMainScreenController(this);
        colorScreenController.setBoardMaker(boardMaker);

        Scene scene = new Scene(pane);

        Stage newStage = new Stage();

        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setTitle("Color Menu");
        newStage.setResizable(false);
        newStage.initOwner(stage);
        newStage.setScene(scene);
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.show();


    }


    public void manageTemplateInsertion(Template template, int insertionBoardMode) {
        hint.setVisible(true);
        if(template != null && (template.getHeight()>= boardMaker.getHeight()-(boardMaker.getHeight()/3) ||
                template.getWidth()>= boardMaker.getWidth()-(boardMaker.getWidth()/3))){
            hint.setVisible(false);
            hint2.setVisible(true);
        }
        boardMaker.repaintBoard();
        boardMaker.setInsensitiveMode();
        if (boardMaker.getCurrentBoardMode() != insertionBoardMode) {

            if (template == null) {
                if (insertionBoardMode == 1) {
                    ArrayList<Integer> list = new ArrayList<>();
                    int height = 5;
                    int width = 5;
                    int amount = height * width;
                    Integer color = 0;
                    while (amount > 0) {
                        list.add(color);
                        amount--;
                    }
                    list.set(0, 1);
                    list.set(20, 1);
                    list.set(6, 1);
                    list.set(16, 1);
                    list.set(12, 1);
                    list.set(13, 1);
                    list.set(14, 1);

                    currentTemplate = new Template(width, height, list);
                } else if (insertionBoardMode == 2) {
                    ArrayList<Integer> list = new ArrayList<>();
                    int height = 3;
                    int width = 4;
                    int amount = height * width;
                    Integer color = 0;
                    while (amount > 0) {
                        list.add(color);
                        amount--;
                    }
                    list.set(4, 1);
                    list.set(5, 1);
                    list.set(1, 1);
                    list.set(2, 1);
                    list.set(7, 1);
                    list.set(9, 1);
                    list.set(10, 1);

                    currentTemplate = new Template(width, height, list);
                } else if (insertionBoardMode == 3) {
                    ArrayList<Integer> list = new ArrayList<>();
                    int height = 5;
                    int width = 9;
                    int amount = height * width;
                    Integer color = 0;
                    while (amount > 0) {
                        list.add(color);
                        amount--;
                    }
                    list.set(0, 1);
                    list.set(1, 1);
                    list.set(2, 1);
                    list.set(3, 1);
                    list.set(13, 1);
                    list.set(21, 1);
                    list.set(22, 1);
                    list.set(23, 1);
                    list.set(24, 1);
                    list.set(25, 1);
                    list.set(26, 1);
                    list.set(31, 1);
                    list.set(36, 1);
                    list.set(37, 1);
                    list.set(38, 1);
                    list.set(39, 1);

                    currentTemplate = new Template(width, height, list);

                } else {
                   ArrayList<Integer> list = new ArrayList<>();
                    int height = 4;
                    int width = 4;
                    int amount = height * width;
                    Integer color = 0;
                    while (amount > 0) {
                        color = 1 + amount % 3;
                        list.add(color);
                        amount--;
                    }
                    currentTemplate = new Template(width, height, list);
                }
            } else if (template != null) {
                currentTemplate = template;
            }

            boardMaker.setTemplateInsertionMode(currentTemplate, 1, boardMaker.getCurrentBoardMode());

            scene.setOnScroll(new EventHandler<ScrollEvent>() {
                int whichDirection = 1;

                @Override
                public void handle(ScrollEvent event) {
                    if (whichDirection == 3) {
                        whichDirection = 0;
                    } else {
                        whichDirection++;
                    }
                    boardMaker.setTemplateInsertionMode(currentTemplate, whichDirection, boardMaker.getCurrentBoardMode());


                }
            });
            boardMaker.setCurrentBoardMode(insertionBoardMode);

        }

    }

    public void testFigure1() {
        manageTemplateInsertion(null, 1);
    }

    public void testFigure2() {
        manageTemplateInsertion(null, 2);
    }

    public void testFigure3() {
        manageTemplateInsertion(null, 3);
    }

    public void testFigure4() {
        if (templates.getKeyList().size() > 0) {
            String key = templates.getKeyList().get(0);
            manageTemplateInsertion(templates.getTemplate(key), 4);
        }else{
            manageTemplateInsertion(null, 4);

        }
    }

    public void testFigure5() {
        if (templates.getKeyList().size() > 1) {
            String key = templates.getKeyList().get(1);
            manageTemplateInsertion(templates.getTemplate(key), 5);
        }else{
            manageTemplateInsertion(null, 5);

        }
    }

    public void testFigure6() {
        if (templates.getKeyList().size() > 2) {
            String key = templates.getKeyList().get(2);
            manageTemplateInsertion(templates.getTemplate(key), 6);
        }else{
            manageTemplateInsertion(null, 6);

        }
    }


    public void disableNonTemplateButtons(boolean x) {
        load.setDisable(x);
        go.setDisable(x);
        pause.setDisable(x);
        clear.setDisable(x);
        color.setDisable(x);

    }

    public void disableTemplateButtons(boolean x) {
        figure1.setDisable(x);
        figure2.setDisable(x);
        figure3.setDisable(x);
        figure4.setDisable(x);
        figure5.setDisable(x);
        figure6.setDisable(x);

    }

    public void isAnimationRunningSignal(boolean x) {
        if (x) {
            stateCircle.setFill(Paint.valueOf("#31ff21"));
            stateLabel.setText("Running!");
        } else {
            stateCircle.setFill(Paint.valueOf("#f00202"));
            stateLabel.setText("Stopped");
        }
    }

    public void closeHint() {
        hint.setVisible(false);
        hint2.setVisible(false);
    }
}
