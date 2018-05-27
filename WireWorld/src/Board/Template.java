package Board;

import java.util.ArrayList;

public class Template {
    private ArrayList<Integer> template;
    private int width;
    private int height;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int addUp(BoardMaker boardMaker, Cell cell) {
        if (cell.getXCoordinate() + height <= boardMaker.getWidth()) {
            if (cell.getYCoordinate() - width + 1 >= 0) {
                Cell currentCell = cell;
                boolean exception = false;
                int templateIndex = 0;
                int ix = cell.getXCoordinate();
                int iy = cell.getYCoordinate();
                while (ix < cell.getXCoordinate() + height) {
                    while (iy > cell.getYCoordinate() - width) {
                        currentCell.setColorAndStatus(template.get(templateIndex));
                        if (Integer.valueOf(currentCell.getId())- boardMaker.getWidth()>=0) {
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) - boardMaker.getWidth());
                        }else{
                            exception = true;

                        }
                        templateIndex++;
                        iy--;
                    }
                    if (exception) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + (width-1) * boardMaker.getWidth() + 1);
                    }else {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + width * boardMaker.getWidth() + 1);
                    }
                    iy = cell.getYCoordinate();
                    ix++;
                }

            } else {

                return 1;
            }
        } else {

            return 1;
        }
        return 0;
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
                return 1;
            }
        } else {
            return 1;
        }
        return 0;
    }

    public int addDown(BoardMaker boardMaker, Cell cell) {
        if (cell.getXCoordinate() - height + 1 >= 0) {
            if (cell.getYCoordinate() + width <= boardMaker.getHeight()) {
                Cell currentCell = cell;
                int ix = cell.getXCoordinate();
                int iy = cell.getYCoordinate();
                int templateIndex = 0;
                boolean exception1 = false;
                boolean exception2 = false;

                while (ix > cell.getXCoordinate() - height) {
                    while (iy < cell.getYCoordinate() + width) {
                        currentCell.setColorAndStatus(template.get(templateIndex));
                        templateIndex++;
                        if (Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() < boardMaker.getWidth() * boardMaker.getHeight()) {
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth());
                        } else {
                            exception1 = true;
                        }
                        iy++;
                    }
                    iy = cell.getYCoordinate();
                    if (exception1) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) - (width - 1) * boardMaker.getWidth() - 1);

                    } else {
                        if (exception2) {
                            return 0;

                        } else {
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) - width * boardMaker.getWidth() - 1);
                        }
                        if (Integer.valueOf(currentCell.getId()) == 0) {
                            exception2 = true;
                        }
                    }
                    ix--;
                }


            } else {
                return 1;
            }

        } else {
            return 1;
        }
        return 0;
    }


    public int addLeft(BoardMaker boardMaker, Cell cell) {
        if (cell.getXCoordinate() - this.width + 1 >= 0) {
            if (cell.getYCoordinate() + this.height <= boardMaker.getHeight()) {
                Cell currentCell = cell;
                boolean exception = false;
                int templateIndex = 0;
                int ix = cell.getXCoordinate();
                int iy = cell.getYCoordinate();

                if (Integer.valueOf(cell.getId()) == width - 1) {
                    exception = true;
                }
                while (iy < cell.getYCoordinate() + height) {
                    while (ix > cell.getXCoordinate() - width) {

                        currentCell.setColorAndStatus(template.get(templateIndex));
                        if (Integer.valueOf(currentCell.getId()) - 1 >= 0)
                            currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) - 1);

                        templateIndex++;

                        ix--;
                    }
                    ix = cell.getXCoordinate();
                    if (exception) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width - 1);
                        exception = false;
                    } else if (Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width <= boardMaker.getHeight() * boardMaker.getWidth()) {
                        currentCell = boardMaker.getBoard().get(Integer.valueOf(currentCell.getId()) + boardMaker.getWidth() + width);
                    }
                    iy++;

                }

            } else {
                return 1;
            }

        } else {
            return 1;
        }
        return 0;


    }
}