package Cell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class CellMaker {
	Cell newCell;

	public void makeCell(int w, int h, int x, int y, String id) {
		Cell cell = new Cell(w, h, x, y, id);

		EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				/*if(event.isPrimaryButtonDown())*/
				cell.changeColor();
			}

		};
		cell.getCell().setOnMouseEntered(handler);

		newCell = cell;

	}

	public Cell getNewCell() {
		return newCell;
	}

	public void setNewCell(Cell newCell) {
		this.newCell = newCell;
	}

}
