package Tests;

import Board.BoardMaker;
import Board.Template;
import Board.Templates;
import GUI.Colors;
import Generator.BoardAdapter;
import Generator.CellularAutomation;
import Generator.GeneratorHandler;
import IO.IO;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static org.assertj.core.api.Assertions.*;

public class CellularAutomationTest {

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

    public CellularAutomationTest(){
        colors = new Colors();
        boardMaker = new BoardMaker();
        board = new Pane();
        boardMaker.makeBoard(colors, board, 100, 100, 20, 800, 600);
        adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(300, adapter);;
        gen = new CellularAutomation(adapter);

    }

    public void test(){
        for( int y =0; y < 3; y++)
            for( int x =0; x < 3; x++)
                adapter.setCellStateAt(x,y,0);

        adapter.setCellStateAt(0,0, 3);
        adapter.setCellStateAt(1,0, 2);
        adapter.setCellStateAt(0,1, 2);
        adapter.setCellStateAt(1,1,1);

        gen.generateNextFrame();

        assertThat( adapter.getCellStateAt(0,0)).as("Checking if the generator works properly 1.").isEqualTo(2);
        assertThat( adapter.getCellStateAt(1,0)).as("Checking if the generator works properly 2.").isEqualTo(1);
        assertThat( adapter.getCellStateAt(0,1)).as("Checking if the generator works properly 3.").isEqualTo(1);
        assertThat( adapter.getCellStateAt(1,1)).as("Checking if the generator works properly 4.").isEqualTo(3);
    }

    public static void main( String[] args){
        CellularAutomationTest test = new CellularAutomationTest();
        test.test();

    }
}
