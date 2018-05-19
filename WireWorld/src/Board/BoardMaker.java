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
        this.width = 30;
        this.height = 30;
        board = new ArrayList<Rectangle>();
    }

    public ArrayList<Rectangle> getBoard() {
        return board;
    }

    public void makeBoard(Template template, Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength) {

    }

    public void makeBoard(Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength) {
        Integer id = 1;
        int x = 0;
        int y = 0;
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
            } else {
                x = 0;
                y += 1;
            }

        }

    }
}
