package Board;

import java.util.ArrayList;

public class Template {
    private ArrayList<Integer> template;
    private int width;
    private int height;

    public Template() {
        template = null;
        width = 0;
        height = 0;
    }

    public Template(int width, int height, ArrayList<Integer> template) {
        this.template = template;
        this.width = width;
        this.height = height;
    }

    public ArrayList<Integer> getTemplate() {
        return template;
    }

    public void setTemplate(ArrayList<Integer> template) {

        this.template = template;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void addToBoard(BoardMaker boardMaker, Cell cell, int direction) {
        switch (direction) {
            case 0:
                addUp(boardMaker, cell);
                break;
            case 1:
                addRight(boardMaker, cell);
                break;
            case 2:
                addDown(boardMaker, cell);
                break;
            case 3:
                addLeft(boardMaker, cell);
                break;
            default:
                addRight(boardMaker, cell);
                break;
        }
    }

    public void addUp(BoardMaker boardMaker, Cell cell) {
    }

    public int addRight(BoardMaker boardMaker, Cell cell) {
        if (cell.getXCoordinate() + this.width <= boardMaker.getWidth()) {
            if (cell.getYCoordinate() + this.height <= boardMaker.getHeight()) {
                Cell currentCell = cell;
                int templateIndex = 0;
                int ix = cell.getXCoordinate();
                int iy = cell.getYCoordinate();
                while (iy < cell.getYCoordinate() + this.height) {

                    while (ix < cell.getXCoordinate() + this.width) {
                        currentCell.setColorAndStatus(template.get(templateIndex));
                        if (Integer.valueOf(currentCell.getId()) + 1 < boardMaker.getWidth() * boardMaker.getHeight())
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + 1);

                        templateIndex++;
                        ix++;
                    }
                    if (Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() - this.width < boardMaker.getWidth() * boardMaker.getHeight())
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() - this.width);
                    ix = cell.getXCoordinate();
                    iy++;
                }
            } else {
                System.out.println("Template wystaje na dole poza tablice.");
                return 1;
            }
        } else {
            System.out.println("Template wystaje na po prawej poza tablice.");
            return 1;
        }
        return 0;
    }

    public void addDown(BoardMaker boardMaker, Cell cell) {
    }


    public int addLeft(BoardMaker boardMaker, Cell cell) {
        if (cell.getXCoordinate() - this.width + 1 >= 0) {
            if (cell.getYCoordinate() + this.height <= boardMaker.getHeight()) {
                Cell currentCell = cell;
                int exception = 0;
                int templateIndex = 0;
                int ix = cell.getXCoordinate();
                int iy = cell.getYCoordinate();

                if (Integer.valueOf(cell.getId()) == width - 1) {
                    exception++;
                }
                while (iy < cell.getYCoordinate() + height) {
                    while (ix > cell.getXCoordinate() - width) {

                        System.out.println("ix =" + ix + " iy =" + iy);
                        System.out.println(currentCell.getId());


                        currentCell.setColorAndStatus(template.get(templateIndex));
                        if (Integer.valueOf(currentCell.getId()) - 1 >= 0)
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) - 1);

                        templateIndex++;

                        ix--;
                    }
                    ix = cell.getXCoordinate();
                    if (exception == 1) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width - 1);
                        exception = 0;
                    } else if (Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width <= boardMaker.getHeight() * boardMaker.getWidth()) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width);
                    }
                    iy++;

                }

            } else {
                System.out.println("Template wystaje na dole poza tablice.");
                return 1;
            }

        } else {
            System.out.println("Template wystaje po lewej poza tablice.");
            return 1;
        }
        return 0;


    }
}