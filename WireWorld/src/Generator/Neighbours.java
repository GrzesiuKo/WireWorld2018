package Generator;

public class Neighbours {

    private BoardAdapter adapter;

    private int hight = 0;
    private int width = 0;

    private boolean borders = true;

    public Neighbours(BoardAdapter adapter) {
        this.adapter = adapter;
        this.hight = adapter.getHeight();
        this.width = adapter.getWidth();
    }

    public int getHeadNeighboursNumber(int x, int y) {
        if (borders)
            return neighbours(x, y);
        else
            return borderlessNeighbours(x, y);
    }

    private int neighbours(int x, int y) {
        int heads = 0;
        for (int iy = y - 1; iy <= y + 1; iy++) {
            for (int ix = x - 1; ix <= x + 1; ix++) {
                if( !( ix == x && iy == y) )
                    heads = analyzeCell( ix, iy, heads);
            }
        }
        return heads;
    }

    private int borderlessNeighbours(int x, int y) {
        return 0;
    }

    private int analyzeCell( int x, int y, int heads ){
        if( x>= 0 && x < width)
            if( y >=0 && y < hight)
                if ( adapter.getCellStateAt(x,y) == 3)
                    return (heads+1);
        return heads;
    }
}
