package TestBoard.Controllers;

import Board.BoardMaker;
import Board.Template;
import GUI.Colors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Test1Controller {
    @FXML
    Pane pane;

    @FXML
    Button button;

    @FXML
    public void initialize() {
        Colors colors = new Colors();

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, pane, 30, 30, 20);
    }

    public void board(){

        Colors colors = new Colors();

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, pane, 30, 30, 20);

    }

}
