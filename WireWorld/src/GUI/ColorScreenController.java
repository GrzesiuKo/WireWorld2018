package GUI;

import Board.BoardMaker;
import Board.Cell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ColorScreenController {
    private BoardMaker boardMaker;
    private Colors colors;
    private MainScreenController mainScreenController;

    @FXML
    ColorPicker head;
    @FXML
    ColorPicker tail;
    @FXML
    ColorPicker conductor;
    @FXML
    ColorPicker empty;

    @FXML
    Button backButton;

    @FXML
    Button defaultColorsButton;

    @FXML
    Button currentColorsButton;


    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public void setBoardMaker(BoardMaker boardMaker) {
        this.boardMaker = boardMaker;
    }

    public void defaultColors() {

        head.setValue(Color.valueOf("#f9f50e"));
        tail.setValue(Color.valueOf("#f10606"));
        conductor.setValue(Color.valueOf("#000000"));
        empty.setValue(Color.valueOf("#FFFFFF"));
    }
    public void currentColors() {

        head.setValue(Color.valueOf(colors.getHead()));
        tail.setValue(Color.valueOf(colors.getTail()));
        conductor.setValue(Color.valueOf(colors.getConductor()));
        empty.setValue(Color.valueOf(colors.getEmpty()));
    }
    public void back() {

        colors.setHead(head.getValue().toString());
        colors.setTail(tail.getValue().toString());
        colors.setConductor(conductor.getValue().toString());
        colors.setEmpty(empty.getValue().toString());

        mainScreenController.setColors(colors);
        boardMaker.repaintBoard();
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

}
