package Board;

import GUI.Colors;
import GUI.MainScreenController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class BoardMaker {
    private MainScreenController mainScreenController;
    private ArrayList<Cell> board;
    private int width;
    private int height;
    private int currentBoardMode;

    public BoardMaker() {
        this.width = 0;
        this.height = 0;
        board = new ArrayList<Cell>();
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public ArrayList<Cell> getBoard() {
        return board;
    }

    public int getCurrentBoardMode() {
        return currentBoardMode;
    }

    public void setCurrentBoardMode(int currentBoardMode) {
        this.currentBoardMode = currentBoardMode;
    }

    public void makeBoard(Template template, Colors colors, Pane pane, int rectSideLength) {
        currentBoardMode = 0;
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
        currentBoardMode = 0;
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
                        if (event.getButton() == MouseButton.PRIMARY) {
                            rectangle.changeColor();
                        }else if(event.getButton() == MouseButton.SECONDARY){
                            rectangle.setColorAndStatus(0);
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
        currentBoardMode = 0;
        Integer id = 0;
        int x = 0;
        int y = 0;
        while (y < yRows && y * rectSideLength < paneSizeY) {
            Cell rectangle = new Cell(colors, x * rectSideLength, y * rectSideLength, rectSideLength, rectSideLength, x, y);

            if (x < xColumns && x * rectSideLength < paneSizeX) {

                EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            rectangle.changeColor();
                        }else if(event.getButton() == MouseButton.SECONDARY){
                            rectangle.setColorAndStatus(0);
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

    public void setBoardColor(int x) {
        currentBoardMode = 0;
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {

            board.get(lastIndex).setColorAndStatus(x);
            board.get(lastIndex).setPrevColor(x);
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

    public void setTemplateInsertionMode(Template template, int direction, int modeID) {
        currentBoardMode = modeID;
        int lastIndex = width * height - 1;
        int end = 0;
        while (lastIndex >= 0) {

            int finalLastIndex = lastIndex;
            int finalEnd = end;
            BoardMaker boardMaker = this;

            EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton()==MouseButton.PRIMARY) {
                        template.addToBoard(boardMaker, board.get(finalLastIndex), direction);
                        setColorMode();
                        mainScreenController.closeHint();
                        mainScreenController.getScene().setOnScroll(null);
                    } else if (event.getButton() == MouseButton.SECONDARY){
                        setColorMode();
                        boardMaker.repaintBoardOnPrevious();
                        mainScreenController.closeHint();
                    }
                }

            };

            EventHandler<MouseEvent> handler2 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    template.addToBoard(boardMaker, board.get(finalLastIndex), direction);

                }
            };

            EventHandler<MouseEvent> handler3 = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    boardMaker.repaintBoardOnPrevious();

                }
            };

            EventHandler<ScrollEvent> handlerScroll = new EventHandler<ScrollEvent>() {
                int whichDirection = direction;

                @Override
                public void handle(ScrollEvent event) {
                    if (boardMaker.getCurrentBoardMode() != 0) {
                        if (whichDirection == 3) {
                            whichDirection = 0;
                        } else {
                            whichDirection++;
                        }
                        boardMaker.repaintBoardOnPrevious();
                        template.addToBoard(boardMaker, board.get(finalLastIndex), whichDirection);
                    }
                }
            };
            board.get(lastIndex).setOnMouseClicked(handler1);
            board.get(lastIndex).setOnMouseEntered(handler2);
            board.get(lastIndex).setOnMouseExited(handler3);
            board.get(lastIndex).setOnScroll(handlerScroll);


            lastIndex--;
        }
    }

    public void setColorMode() {
        currentBoardMode = 0;
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            int finalLastIndex = lastIndex;
            EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        board.get(finalLastIndex).changeColor();
                    }else if(event.getButton() == MouseButton.SECONDARY){
                        board.get(finalLastIndex).setColorAndStatus(0);
                    }
                }
            };

            mainScreenController.getScene().setOnScroll(null);
            mainScreenController.closeHint();
            board.get(lastIndex).setOnMouseClicked(handler);
            board.get(lastIndex).setOnMouseDragEntered(handler);
            board.get(lastIndex).setOnMouseExited(null);
            board.get(lastIndex).setOnMouseEntered(null);
            lastIndex--;
        }
    }

    public void setInsensitiveMode() {
        currentBoardMode = -1;
        int lastIndex = width * height - 1;
        while (lastIndex >= 0) {
            board.get(lastIndex).setOnMouseClicked(null);
            board.get(lastIndex).setOnMouseDragEntered(null);
            board.get(lastIndex).setOnMouseExited(null);
            board.get(lastIndex).setOnMouseEntered(null);
            lastIndex--;
        }
    }
}

