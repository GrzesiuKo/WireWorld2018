package GUI;

import Board.BoardMaker;
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

public class MainScreenController {
    private Stage stage;
    private Colors colors;

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
    Button ﬁgure1;

    @FXML
    Button ﬁgure2;

    @FXML
    Button ﬁgure3;

    @FXML
    Button ﬁgure4;

    @FXML
    Button ﬁgure5;

    @FXML
    Button ﬁgure6;
    @FXML
    JFXTextField path;

    @FXML
    public void initialize() {
        colors = new Colors();

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, board, 100, 100, 20, 600, 600);
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

}
