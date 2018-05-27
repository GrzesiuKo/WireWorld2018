package Tests;

import Board.BoardMaker;
import Board.Template;
import Board.Templates;
import GUI.Colors;
import Generator.BoardAdapter;
import Generator.GeneratorHandler;
import IO.IO;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GeneratorHandlerTest {

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

    public GeneratorHandlerTest(){
        colors = new Colors();
        boardMaker = new BoardMaker();
        board = new Pane();
        boardMaker.makeBoard(colors, board, 100, 100, 20, 800, 600);
        adapter = new BoardAdapter(boardMaker);
        genHandler = new GeneratorHandler(300, adapter);;
    }

    public void test(){
        genHandler.setDelay(600);
    }
}
