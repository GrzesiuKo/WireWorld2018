package Generator;

public class CellularAutomaton {

    Rules rules;
    Neighbours neighbours;

    private int hight = 0;
    private int width = 0;
    private int[][] tmpMatrix;

    public CellularAutomaton(BoardAdapter adapter){
        this.hight = adapter.getHeight();
        this.width = adapter.getWidth();
        tmpMatrix = new int[width][hight];
        this.neighbours = new Neighbours( adapter.getWidth(), adapter.getHeight() );
        rules = new Rules();
    }
}
