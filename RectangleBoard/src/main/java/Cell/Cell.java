package Cell;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell {
	int color;
	int width;
	int height;
	int x;
	int y;
	Integer id;
	Rectangle cell;

	@Override
	public int hashCode() {
		return id;
	}
	
	public Cell(int w, int h, int x, int y, Integer id) {
		color = 0;
		Rectangle cell = new Rectangle();
		cell.setId(id.toString());
		this.cell = cell;
		width = w;
		height = h;
		this.x = x;
		this.y = y;
		cell.setX(x);
		cell.setY(y);
		cell.setWidth(w);
		cell.setHeight(h);
		cell.setStroke(Paint.valueOf("#000000"));
		cell.setFill(Paint.valueOf("#fffdfd"));
	}

	public Rectangle getCell() {
		return this.cell;
	}

	public void changeColor() {
		switch (this.color) {
		case 3:
			this.setEmpty();
			this.color = 0;
			break;

		case 2:
			this.setHead();
			this.color = 3;
			break;

		case 1:
			this.setTail();
			this.color = 2;
			break;

		case 0:
			this.setConduct();
			this.color = 1;
			break;

		default:
			break;
		}

	}

	public void setConduct() {
		cell.setFill(Paint.valueOf("#000000"));
	};

	public void setHead() {
		cell.setFill(Paint.valueOf("#f6ff1e"));
	};

	public void setTail() {
		cell.setFill(Paint.valueOf("#ff1e1e"));
	};

	public void setEmpty() {
		cell.setFill(Paint.valueOf("#fffdfd"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	};

}
