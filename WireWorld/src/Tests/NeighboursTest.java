package Tests;

import Board.BoardMaker;
import Board.Template;
import Board.Templates;
import GUI.Colors;
import Generator.BoardAdapter;
import Generator.CellularAutomation;
import Generator.GeneratorHandler;
import Generator.Neighbours;
import IO.IO;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static org.assertj.core.api.Assertions.assertThat;

public class NeighboursTest {

    private Scene scene;
    private Templates templates;
    private Template currentTemplate;
    private BoardMaker boardMaker;
    private Stage stage;
    private Colors colors;
    private Pane board;
    private BoardAdapter adapter;
    private GeneratorHandler genHandler;
    private IO io;
    private CellularAutomation gen;
    private Neighbours neighbours;

    public NeighboursTest(){
        colors = new Colors();
        boardMaker = new BoardMaker();
        board = new Pane();
        boardMaker.makeBoard(colors, board, 100, 100, 20, 800, 600);
        adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(300, adapter);;
        gen = new CellularAutomation(adapter);
        neighbours = new Neighbours(adapter);
    }

    public void test() {
        for (int y = 0; y < 5; y++)
            for (int x = 0; x < 5; x++)
                adapter.setCellStateAt(x, y, 0);

        adapter.setCellStateAt(0, 0, 3);
        adapter.setCellStateAt(1, 0, 2);
        adapter.setCellStateAt(0, 1, 2);
        adapter.setCellStateAt(1, 1, 1);
        adapter.setCellStateAt(1, 2, 3);
        adapter.setCellStateAt(2, 2, 3);

        assertThat( neighbours.getHeadNeighboursNumber(0,0, false) ).as("Checking if counting head neibhours works properly 1.").isEqualTo(0);
        assertThat( neighbours.getHeadNeighboursNumber(0,0, true) ).as("Checking if counting head neibhours works properly 2.").isEqualTo(0);

        assertThat( neighbours.getHeadNeighboursNumber(0,1, false) ).as("Checking if counting head neibhours works properly 3.").isEqualTo(2);
        assertThat( neighbours.getHeadNeighboursNumber(0,1, true) ).as("Checking if counting head neibhours works properly 4.").isEqualTo(2);

        assertThat( neighbours.getHeadNeighboursNumber(1,1, false) ).as("Checking if counting head neibhours works properly 5.").isEqualTo(3);
        assertThat( neighbours.getHeadNeighboursNumber(1,1, true) ).as("Checking if counting head neibhours works properly 6.").isEqualTo(3);

        assertThat( neighbours.getHeadNeighboursNumber(1,2, false) ).as("Checking if counting head neibhours works properly 7.").isEqualTo(1);
        assertThat( neighbours.getHeadNeighboursNumber(1,2, true) ).as("Checking if counting head neibhours works properly 8.").isEqualTo(1);

        assertThat( neighbours.getHeadNeighboursNumber(2,0, false) ).as("Checking if counting head neibhours works properly 9.").isEqualTo(0);
        assertThat( neighbours.getHeadNeighboursNumber(2,0, true) ).as("Checking if counting head neibhours works properly 10.").isEqualTo(0);
    }

    public static void main( String[] args){
        NeighboursTest test = new NeighboursTest();
        test.test();
    }
}
