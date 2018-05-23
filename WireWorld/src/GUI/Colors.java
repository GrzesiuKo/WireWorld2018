package GUI;

import javafx.scene.paint.Color;

public class Colors {
    private String head;
    private String tail;
    private String conductor;
    private String empty;

    public Colors(){
        setHead("#f10606");
        setTail("#f9f50e");
        setConductor("#000000");
        setEmpty("#FFFFFF");
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getTail() {
        return tail;
    }

    public void setTail(String tail) {
        this.tail = tail;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }
}
