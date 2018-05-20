package Board;

import GUI.Colors;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BoardMaker {
    private ArrayList<Rectangle> board;
    private int width;
    private int height;

    public BoardMaker() {
        this.width = 0;
        this.height = 0;
        board = new ArrayList<Rectangle>();
    }

    public ArrayList<Rectangle> getBoard() {
        return board;
    }

    public void makeBoard(Template template, Colors colors, Pane pane, int rectSideLength) {
         int xColumns = template.getWidth();
         int yRows = template.getHeight();

         makeBoard(colors, pane,xColumns,yRows, rectSideLength);
        int lastIndex = width*height-1;
        while(lastIndex>=0) {
            switch(template.getTemplate().get(lastIndex)){
                case 0:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getEmpty()));
                    break;
                case 1:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getConductor()));
                    break;
                case 2:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getTail()));
                    break;
                case 3:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getHead()));
                    break;
                default:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getEmpty()));
                    break;
            }
            lastIndex--;
        }

    }

    public void makeBoard(Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength) {
        Integer id = 1;
        int x = 0;
        int y = 0;
        this.width = xColumns;
        this.height = yRows;
        while (y < yRows && y * rectSideLength < pane.getHeight()) {
            Rectangle rectangle = new Rectangle(x * rectSideLength, y * rectSideLength, rectSideLength, rectSideLength);

            if (x < xColumns && x * rectSideLength < pane.getWidth()) {

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Paint current = rectangle.getFill();
                        Paint empty = Paint.valueOf(colors.getEmpty());
                        Paint conductor = Paint.valueOf(colors.getConductor());
                        Paint tail = Paint.valueOf(colors.getTail());
                        Paint head = Paint.valueOf(colors.getHead());

                        if (current.equals(empty)){
                            rectangle.setFill(Paint.valueOf(colors.getConductor()));
                        }else if(current.equals(conductor)){
                            rectangle.setFill(Paint.valueOf(colors.getTail()));
                        }else if(current.equals(tail)){
                            rectangle.setFill(Paint.valueOf(colors.getHead()));
                        }else if(current.equals(head)){
                            rectangle.setFill(Paint.valueOf(colors.getEmpty()));
                        }else{
                            rectangle.setFill(Paint.valueOf(colors.getConductor()));
                        }

                    }
                };

                rectangle.setOnMouseDragEntered(handler);
                rectangle.setOnMouseClicked(handler);
                rectangle.setStroke(Paint.valueOf("#000000"));
                rectangle.setFill(Paint.valueOf(colors.getEmpty()));
                rectangle.setId(id.toString());
                id++;
                board.add(rectangle);
                pane.getChildren().add(rectangle);

                x += 1;
            } else if (x * rectSideLength > pane.getWidth()){
                this.width = x-1;
                x = 0;
                y += 1;
            }else if (x * rectSideLength == pane.getWidth()) {
                this.width = x;
                x = 0;
                y += 1;
            }else{
                x = 0;
                y += 1;
            }

        }
        if(y * rectSideLength > pane.getHeight()){
            this.height = y-1;
            System.out.println("pane.getHeight() = "+pane.getHeight());
        }else if(y * rectSideLength == pane.getHeight()){
            this.height=y;
            System.out.println(" y = pane.getHeight() = "+pane.getHeight());
        }

    }

/*Wersja poniżej jest dla metody initialize w kontrolerze, ponieważ pane.getHeight() zwraca tam zawsze zero, nie wiedzieć czemu?*/
    public void makeBoard(Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength, int paneSizeX, int paneSizeY) {
        Integer id = 1;
        int x = 0;
        int y = 0;
        this.width = xColumns;
        this.height = yRows;
        while (y < yRows && y * rectSideLength < paneSizeY) {
            Rectangle rectangle = new Rectangle(x * rectSideLength, y * rectSideLength, rectSideLength, rectSideLength);

            if (x < xColumns && x * rectSideLength < paneSizeX) {

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Paint current = rectangle.getFill();
                        Paint empty = Paint.valueOf(colors.getEmpty());
                        Paint conductor = Paint.valueOf(colors.getConductor());
                        Paint tail = Paint.valueOf(colors.getTail());
                        Paint head = Paint.valueOf(colors.getHead());

                        if (current.equals(empty)){
                            rectangle.setFill(Paint.valueOf(colors.getConductor()));
                        }else if(current.equals(conductor)){
                            rectangle.setFill(Paint.valueOf(colors.getTail()));
                        }else if(current.equals(tail)){
                            rectangle.setFill(Paint.valueOf(colors.getHead()));
                        }else if(current.equals(head)){
                            rectangle.setFill(Paint.valueOf(colors.getEmpty()));
                        }else{
                            rectangle.setFill(Paint.valueOf(colors.getConductor()));
                        }

                    }
                };

                rectangle.setOnMouseDragEntered(handler);
                rectangle.setOnMouseClicked(handler);
                rectangle.setStroke(Paint.valueOf("#000000"));
                rectangle.setFill(Paint.valueOf(colors.getEmpty()));
                rectangle.setId(id.toString());
                id++;
                board.add(rectangle);
                pane.getChildren().add(rectangle);

                x += 1;
            } else if (x * rectSideLength > paneSizeX){
                this.width = x-1;
                x = 0;
                y += 1;
            }else if (x * rectSideLength == paneSizeX) {
                this.width = x;
                x = 0;
                y += 1;
            }else{
                x = 0;
                y += 1;
            }

        }
        if(y * rectSideLength > paneSizeY){
            this.height = y-1;

        }else if(y * rectSideLength == paneSizeY){
            this.height=y;

        }

    }
}
