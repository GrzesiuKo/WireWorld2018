package GUI;

import Board.BoardMaker;
import Board.Template;
import Board.Templates;
import Generator.BoardAdapter;
import Generator.GeneratorHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class MainScreenController {
    private Templates templates;
    private Template currentTemplate;
    private BoardMaker boardMaker;
    private Stage stage;
    private Colors colors;
    private GeneratorHandler genHandler;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Pane board;

    @FXML
    JFXButton load;

    @FXML
    JFXButton go;

    @FXML
    JFXButton pause;

    @FXML
    JFXButton halt;

    @FXML
    JFXButton clear;

    @FXML
    JFXButton color;

    @FXML
    Button up;

    @FXML
    Button down;

    @FXML
    Button left;

    @FXML
    Button right;


    @FXML
    JFXTextField path;

    @FXML
    public void initialize() {
        colors = new Colors();
        boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, board, 100, 100, 20, 600, 600);

        BoardAdapter adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(500, adapter);
        adapter.setCellStateAt(29,0,3);
        adapter.setCellStateAt(29,29,3);

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


    public void loadFile() {

    }

    public void goAnimation() {

    }

    public void pauseAnimation() {

    }

    public void haltAnimation() {

    }

    public void clear() {
        boardMaker.setBoardColor(0);
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

    public void uncoverDirectionButtons() {
        up.setVisible(true);
        right.setVisible(true);
        down.setVisible(true);
        left.setVisible(true);

        up.toFront();
        down.toFront();
        right.toFront();
        left.toFront();

    }

    public void coverDirectionButtons() {
        up.setVisible(false);
        right.setVisible(false);
        down.setVisible(false);
        left.setVisible(false);
    }

    public void testFigure1() {
        disableNonTemplateButtons(true);
        uncoverDirectionButtons();
        boardMaker.setInsensitiveMode();


        if (boardMaker.getCurrentBoardMode() != 1) {
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
            Template template = new Template(width, height, list);
            currentTemplate = template;

            boardMaker.setCurrentBoardMode(1);
        }


    }

    public void testFigure2() {
        uncoverDirectionButtons();
        disableNonTemplateButtons(true);
        if (boardMaker.getCurrentBoardMode() != 2) {
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
            Template template = new Template(width, height, list);
            currentTemplate = template;

            boardMaker.setCurrentBoardMode(2);
        }

    }

    public void testFigure3() {
        uncoverDirectionButtons();
        disableNonTemplateButtons(true);
        if (boardMaker.getCurrentBoardMode() != 3) {
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
            Template template = new Template(width, height, list);

            currentTemplate = template;

            boardMaker.setCurrentBoardMode(3);
        }

    }

    public void testFigure4() {
        uncoverDirectionButtons();
        disableNonTemplateButtons(true);
        if (boardMaker.getCurrentBoardMode() != 4) {
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
            Template template = new Template(width, height, list);
            currentTemplate = template;

            boardMaker.setCurrentBoardMode(4);
        }


    }

    public void testFigure5() {
    }

    public void directionUp() {
        coverDirectionButtons();
        disableNonTemplateButtons(false);

        boardMaker.setTemplateInsertionMode(currentTemplate, 0, boardMaker.getCurrentBoardMode());

    }

    public void directionRight() {
        coverDirectionButtons();
        disableNonTemplateButtons(false);
        boardMaker.setTemplateInsertionMode(currentTemplate, 1, boardMaker.getCurrentBoardMode());

    }

    public void directionDown() {
        coverDirectionButtons();
        disableNonTemplateButtons(false);
        boardMaker.setTemplateInsertionMode(currentTemplate, 2, boardMaker.getCurrentBoardMode());

    }


    public void directionLeft() {
        coverDirectionButtons();
        disableNonTemplateButtons(false);
        boardMaker.setTemplateInsertionMode(currentTemplate, 3, boardMaker.getCurrentBoardMode());
    }

    public void disableNonTemplateButtons(boolean x) {
        load.setDisable(x);
        go.setDisable(x);
        pause.setDisable(x);
        halt.setDisable(x);
        clear.setDisable(x);
        color.setDisable(x);

    }

}
