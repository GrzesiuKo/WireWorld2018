package Board;

import GUI.Colors;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
    Colors colors;
    int status;
    int xMatrixCoordinate;
    int yMatrixCoordinate;
    int prevColor;

    public Cell(Colors c, int x, int y, int width, int height, int xMatrixCoordinate, int yMatrixCoordinate) {
        colors = c;
        prevColor = 0;
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.xMatrixCoordinate = xMatrixCoordinate;
        this.yMatrixCoordinate = yMatrixCoordinate;
    }

    public int getPrevColor() {
        return prevColor;
    }

    public void setPrevColor(int prevColor) {
        this.prevColor = prevColor;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    public int getXCoordinate() {
        return xMatrixCoordinate;
    }


    public int getYCoordinate() {
        return yMatrixCoordinate;
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
            prevColor = 0;
        } else if (current.equals(conductor)) {
            this.setFill(Paint.valueOf(colors.getTail()));
            status = 2;
            prevColor = 1;
        } else if (current.equals(tail)) {
            this.setFill(Paint.valueOf(colors.getHead()));
            status = 3;
            prevColor = 2;
        } else if (current.equals(head)) {
            this.setFill(Paint.valueOf(colors.getEmpty()));
            status = 0;
            prevColor = 3;
        } else {
            this.setFill(Paint.valueOf(colors.getConductor()));
            status = 1;
            prevColor = 0;
        }

    }

    public void setColorAndStatus(int x) {
        switch (x) {
            case 0:
                setFill(Paint.valueOf(colors.getEmpty()));
                setStatus(0);
                break;
            case 1:
                setFill(Paint.valueOf(colors.getConductor()));
                setStatus(1);
                break;
            case 2:
                setFill(Paint.valueOf(colors.getTail()));
                setStatus(2);
                break;
            case 3:
                setFill(Paint.valueOf(colors.getHead()));
                setStatus(3);
                break;
            default:
                setFill(Paint.valueOf(colors.getEmpty()));
                setStatus(0);
                break;
        }
    }

    public void setColor(int x) {
        switch (x) {
            case 0:
                setFill(Paint.valueOf(colors.getEmpty()));
                break;
            case 1:
                setFill(Paint.valueOf(colors.getConductor()));
                break;
            case 2:
                setFill(Paint.valueOf(colors.getTail()));
                break;
            case 3:
                setFill(Paint.valueOf(colors.getHead()));
                break;
            default:
                setFill(Paint.valueOf(colors.getEmpty()));
                break;
        }

    }


}
