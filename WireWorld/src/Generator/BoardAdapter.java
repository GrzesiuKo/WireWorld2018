package Generator;

import Board.BoardMaker;
import Board.Cell;

import java.util.ArrayList;

public class BoardAdapter {
    private ArrayList<Cell> list;
    private int hight = 0;
    private int width = 0;

    public BoardAdapter( BoardMaker board ){
        this.list = board.getBoard();
        this.hight = board.getHeight();
        this.width = board.getWidth();
    }

    public int getCellStateAt( int x, int y ){
        return list.get(y*width+x).getStatus();
    }
    public void setCellStateAt( int x, int y, int state){
        list.get(y*width+x).setColorAndStatus(state);
    }

    public int getHeight(){
        return hight;
    }
    public int getWidth(){
        return width;
    }
}
