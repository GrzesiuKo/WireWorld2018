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
import Board.Cell;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardAdapterTest {

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

    public BoardAdapterTest(){
        colors = new Colors();
        boardMaker = new BoardMaker();
        board = new Pane();
        boardMaker.makeBoard(colors, board, 100, 100, 20, 800, 600);
        adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(300, adapter);;
        gen = new CellularAutomation(adapter);
    }

    public void test(){
        ArrayList<Cell> list = boardMaker.getBoard();

        assertThat(adapter.getCellStateAt(0,0)).as("Testing if board addapter works properly 1").isEqualTo(list.get(0).getStatus());
        assertThat(adapter.getCellStateAt(10,0)).as("Testing if board addapter works properly 2").isEqualTo(list.get(10).getStatus());
        list.get(10).setColorAndStatus(1);
        assertThat(adapter.getCellStateAt(10,0)).as("Testing if board addapter works properly 3").isEqualTo(list.get(10).getStatus());
        adapter.setCellStateAt(14,0,3);
        assertThat(adapter.getCellStateAt(14,0)).as("Testing if board addapter works properly 4").isEqualTo(list.get(14).getStatus());
    }

    public static void main( String[] args ){
        BoardAdapterTest boardt = new BoardAdapterTest();
        boardt.test();
    }
}
