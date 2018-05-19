package Board;

import java.util.ArrayList;

public class Template {
    private ArrayList<Integer> template;
    private int width;
    private int height;

    public Template(){
        template = null;
        width = 0;
        height = 0;
    }

    public Template(int width,int  height, ArrayList<Integer> template){
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
}
