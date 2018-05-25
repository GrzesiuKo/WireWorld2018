package Generator;

public class Neighbours {

    private BoardAdapter adapter;

    private int hight = 0;
    private int width = 0;

    public Neighbours(BoardAdapter adapter) {
        this.adapter = adapter;
        this.hight = adapter.getHeight();
        this.width = adapter.getWidth();
    }

    public int getHeadNeighboursNumber(int x, int y, boolean borders) {
        if (adapter.getCellStateAt(x, y) == StatusIndicators.EMPTY)
            return 0;
        else if (borders)
            return neighbours(x, y);
        else
            return borderlessNeighbours(x, y);
    }

    private int neighbours(int x, int y) {
        int heads = 0;
        for (int iy = y - 1; iy <= y + 1; iy++) {
            for (int ix = x - 1; ix <= x + 1; ix++) {
                if (!(ix == x && iy == y))
                    heads = analyzeCell(ix, iy, heads);
            }
        }
        return heads;
    }

    int borderlessNeighbours( int x, int y){
        int tmpx, tmpy, ix, iy, heads = 0;
        for( ix = x-1; ix <= x+1; ix++){
            tmpx = ix;
            if( ix == -1)
                ix = adapter.getWidth() -1;
            else if( ix == adapter.getWidth() )
                ix = 0;
            for( iy = y-1; iy <= y+1; iy++){
                tmpy = iy;
                if( iy == -1)
                    iy = adapter.getHeight() -1;
                else if( iy == adapter.getHeight() )
                    iy = 0;
                if( !(ix == x && iy == y) )
                    heads = analyzeCell( ix, iy, heads);
                iy = tmpy;
            }
            ix = tmpx;
        }
        return heads;
    }

    private int analyzeCell(int x, int y, int heads) {
        if (x >= 0 && x < width)
            if (y >= 0 && y < hight)
                if (adapter.getCellStateAt(x, y) == 3)
                    return (heads + 1);
        return heads;
    }
}
