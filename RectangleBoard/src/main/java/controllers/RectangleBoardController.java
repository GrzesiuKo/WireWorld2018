package controllers;
import com.jfoenix.controls.JFXButton;

import Cell.Cell;
import Cell.Factory;
import Cell.MouseTracker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

public class RectangleBoardController {
	MouseTracker tracker;
	@FXML
	AnchorPane board;
	
	@FXML
	JFXButton button;
	
	public RectangleBoardController() {
		MouseTracker tracker = new MouseTracker();
		this.tracker = tracker;

	
	}
	
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
