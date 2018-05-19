package TestBoard.Controllers;

import Board.BoardMaker;
import Board.Template;
import GUI.Colors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Test2Controller {
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

        ArrayList<Integer> list = new ArrayList<>();
        int height = 30;
        int width = 5000;
        int amount = height*width;
        Integer color = 0;
        while(amount>0){
            color = amount%4;
            list.add(color);
            amount--;
        }

        Template template = new Template(width, height, list);

        BoardMaker boardMaker = new BoardMaker();

        boardMaker.makeBoard(template, colors, pane,20);

    }

}
