import java.util.ArrayList;
import java.util.List;

public class Factory {
	private int x;
	private int y;
	private int i;
	List<Cell> board;

	public Factory(int boardWidth, int boardHight, int cellNumber) {
		this.x = boardWidth;
		this.y = boardHight;
		this.i = cellNumber;
	}

	public void makeBoard(int cellWidth, int cellHeight) {
		board = new ArrayList<Cell>();

		int xc = 1;
		int yc = 1;
		Integer id = 0;

		CellMaker maker = new CellMaker();
		for (int it = 0; it < i; it++) {
			if (it > 0 && board.get(it - 1).getCell().getX() == x - cellWidth + 1) {
				xc = 1;
				yc += cellHeight;
			}
			if (it > 0 && board.get(it - 1).getCell().getY() == y - cellHeight + 1
					&& board.get(it - 1).getCell().getX() == x - cellWidth + 1)
				return;
			maker.makeCell(cellWidth, cellHeight, xc, yc, id.toString());
			board.add(maker.getNewCell());
			xc += cellWidth;
			id++;

		}

	}

	public List<Cell> getBoard() {
		return board;
	}
}