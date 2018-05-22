package Board;

import GUI.Colors;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class BoardMaker {
    private ArrayList<Cell> board;
    private int width;
    private int height;

    public BoardMaker() {
        this.width = 0;
        this.height = 0;
        board = new ArrayList<Cell>();
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public void makeBoard(Template template, Colors colors, Pane pane, int rectSideLength) {
        int xColumns = template.getWidth();
        int yRows = template.getHeight();

        makeBoard(colors, pane, xColumns, yRows, rectSideLength);
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            switch (template.getTemplate().get(lastIndex)) {
                case 0:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getEmpty()));
                    board.get(lastIndex).setStatus(0);
                    break;
                case 1:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getConductor()));
                    board.get(lastIndex).setStatus(1);
                    break;
                case 2:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getTail()));
                    board.get(lastIndex).setStatus(2);
                    break;
                case 3:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getHead()));
                    board.get(lastIndex).setStatus(3);
                    break;
                default:
                    board.get(lastIndex).setFill(Paint.valueOf(colors.getEmpty()));
                    board.get(lastIndex).setStatus(0);
                    break;
            }
            lastIndex--;
        }

    }

    public void makeBoard(Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength) {
        Integer id = 0;
        int x = 0;
        int y = 0;
        this.width = xColumns;
        this.height = yRows;
        while (y < yRows && y * rectSideLength < pane.getHeight()) {
            Cell rectangle = new Cell(x * rectSideLength, y * rectSideLength, rectSideLength, rectSideLength, x, y);

            if (x < xColumns && x * rectSideLength < pane.getWidth()) {

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        rectangle.changeColor();

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
            } else if (x * rectSideLength > pane.getWidth()) {
                this.width = x - 1;
                x = 0;
                y += 1;
            } else if (x * rectSideLength == pane.getWidth()) {
                this.width = x;
                x = 0;
                y += 1;
            } else {
                x = 0;
                y += 1;
            }

        }
        if (y * rectSideLength > pane.getHeight()) {
            this.height = y - 1;
            System.out.println("pane.getHeight() = " + pane.getHeight());
        } else if (y * rectSideLength == pane.getHeight()) {
            this.height = y;
            System.out.println(" y = pane.getHeight() = " + pane.getHeight());
        }

    }

    /*Wersja poniżej jest dla metody initialize w kontrolerze, ponieważ pane.getHeight() zwraca tam zawsze zero, nie wiedzieć czemu?*/
    public void makeBoard(Colors colors, Pane pane, int xColumns, int yRows, int rectSideLength, int paneSizeX, int paneSizeY) {
        Integer id = 0;
        int x = 0;
        int y = 0;
        while (y < yRows && y * rectSideLength < paneSizeY) {
            Cell rectangle = new Cell(colors, x * rectSideLength, y * rectSideLength, rectSideLength, rectSideLength, x, y);

            if (x < xColumns && x * rectSideLength < paneSizeX) {

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        rectangle.changeColor();
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
            } else if (x * rectSideLength > paneSizeX) {
                this.width = x - 1;
                x = 0;
                y += 1;
            } else if (x * rectSideLength == paneSizeX) {
                this.width = x;
                x = 0;
                y += 1;
            } else {
                x = 0;
                y += 1;
            }
        }

        if (y * rectSideLength > paneSizeY) {
            this.height = y - 1;
        } else if (y * rectSideLength == paneSizeY) {
            this.height = y;
        }
    }


    public void repaintBoardOnPrevious() {
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            switch (board.get(lastIndex).getPrevColor()) {
                case 0:
                    board.get(lastIndex).setColorAndStatus(0);
                    break;
                case 1:
                    board.get(lastIndex).setColorAndStatus(1);
                    break;
                case 2:
                    board.get(lastIndex).setColorAndStatus(2);
                    break;
                case 3:
                    board.get(lastIndex).setColorAndStatus(3);
                    break;
                default:
                    board.get(lastIndex).setColorAndStatus(0);
                    break;
            }
            lastIndex--;
        }
    }
    public void repaintBoard() {
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            switch (board.get(lastIndex).getStatus()) {
                case 0:
                    board.get(lastIndex).setColorAndStatus(0);
                    board.get(lastIndex).setPrevColor(0);
                    break;
                case 1:
                    board.get(lastIndex).setColorAndStatus(1);
                    board.get(lastIndex).setPrevColor(1);
                    break;
                case 2:
                    board.get(lastIndex).setColorAndStatus(2);
                    board.get(lastIndex).setPrevColor(2);
                    break;
                case 3:
                    board.get(lastIndex).setColorAndStatus(3);
                    board.get(lastIndex).setPrevColor(3);
                    break;
                default:
                    board.get(lastIndex).setColorAndStatus(0);
                    board.get(lastIndex).setPrevColor(0);
                    break;
            }
            lastIndex--;
        }
    }
    public void setBoardColor(Paint paint) {
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {

            board.get(lastIndex).setFill(paint);

            lastIndex--;
        }
    }

    public void setBoardStatus(int status) {
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {

            board.get(lastIndex).setStatus(status);

            lastIndex--;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setTemplateInsertionMode(Template template, int direction) {
        this.repaintBoard();
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            int finalLastIndex = lastIndex;
            BoardMaker finalBoardMaker = this;
            EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    template.addToBoard(finalBoardMaker, board.get(finalLastIndex), direction);
                    setColorMode();
                }
            };

            EventHandler<MouseEvent> handler2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    template.addToBoard(finalBoardMaker, board.get(finalLastIndex), direction);

                }
            };

            EventHandler<MouseEvent> handler3 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    finalBoardMaker.repaintBoardOnPrevious();

                }
            };
            board.get(lastIndex).setOnMouseClicked(handler1);
            board.get(lastIndex).setOnMouseEntered(handler2);
            board.get(lastIndex).setOnMouseExited(handler3);


            lastIndex--;
        }
    }

    public void setColorMode() {

        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            int finalLastIndex = lastIndex;
            BoardMaker finalBoardMaker = this;
            EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    board.get(finalLastIndex).changeColor();
                }
            };
            board.get(lastIndex).setOnMouseClicked(handler);
            board.get(lastIndex).setOnMouseDragEntered(handler);
            board.get(lastIndex).setOnMouseExited(null);
            board.get(lastIndex).setOnMouseEntered(null);
            lastIndex--;
        }
    }

}

