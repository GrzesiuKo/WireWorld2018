package Board;

import GUI.Colors;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.beans.EventHandler;

public class Cell extends Rectangle {
    Colors colors;
    int status;

    public Cell(Colors c, int x, int y, int width, int height) {
        colors = c;
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }


    public Cell(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    public void changeColor() {
        Paint current = this.getFill();
        Paint empty = Paint.valueOf(colors.getEmpty());
        Paint conductor = Paint.valueOf(colors.getConductor());
        Paint tail = Paint.valueOf(colors.getTail());
        Paint head = Paint.valueOf(colors.getHead());

        if (current.equals(empty)) {
            this.setFill(Paint.valueOf(colors.getConductor()));
            status = 1;
        } else if (current.equals(conductor)) {
            this.setFill(Paint.valueOf(colors.getTail()));
            status = 2;
        } else if (current.equals(tail)) {
            this.setFill(Paint.valueOf(colors.getHead()));
            status = 3;
        } else if (current.equals(head)) {
            this.setFill(Paint.valueOf(colors.getEmpty()));
            status = 0;
        } else {
            this.setFill(Paint.valueOf(colors.getConductor()));
            status = 1;
        }

    }
}
