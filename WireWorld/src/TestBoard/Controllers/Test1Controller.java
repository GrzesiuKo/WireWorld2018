package TestBoard.Controllers;

import Board.BoardMaker;
import GUI.Colors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Test1Controller {
    @FXML
    Pane pane;

    @FXML
    Button button;

   /* doesn't work @FXML
    public void initialize() {

        Colors colors = new Colors();

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, pane, 30, 30, 20);

        System.out.println("Elo");

    }*/

    public void board(){

        Colors colors = new Colors();
/*
        Template template = new Template();
*/

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(colors, pane, 30, 30, 20);

    }

}
