package controllers;
import Cell.Cell;
import Cell.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RectangleBoardController {
	@FXML
	AnchorPane board;
	
	@FXML
	Button button;
	
	public void display() {
		final Factory factory = new Factory(600, 600, 1000);
		factory.makeBoard(20, 20);

		int i = 0;

		for (Cell c : factory.getBoard()) {
			board.getChildren().add(factory.getBoard().get(i).getCell());
			i++;
		}
	}
	
	
}
