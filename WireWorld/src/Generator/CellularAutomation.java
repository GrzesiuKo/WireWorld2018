package Generator;

public class CellularAutomation {

    private BoardAdapter adapter;
    private Rules rules;
    private Neighbours neighbours;

    private int hight;
    private int width;
    private int[][] tmpMatrix;

    private boolean borders = false;

    public CellularAutomation(BoardAdapter adapter) {
        this.adapter = adapter;
        this.hight = adapter.getHeight();
        this.width = adapter.getWidth();
        this.tmpMatrix = new int[width][hight];
        this.neighbours = new Neighbours(adapter);
        this.rules = new Rules();
    }

    public void generateNextFrame() {
        for (int y = 0; y < hight; y++) {
            for (int x = 0; x < width; x++) {
                tmpMatrix[x][y] = rules.getNewState(adapter.getCellStateAt(x, y), neighbours.getHeadNeighboursNumber(x, y, borders));
            }
        }
        updateBoard(tmpMatrix);
    }

    public void setBorders( boolean mode ){
        borders = mode;
    }

    private void updateBoard(int[][] tmp) {
        for (int y = 0; y < hight; y++) {
            for (int x = 0; x < width; x++) {
                adapter.setCellStateAt(x, y, tmp[x][y]);
            }
        }
    }
}
