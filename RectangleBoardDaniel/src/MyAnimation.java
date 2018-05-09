

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class MyAnimation extends Thread {
    private HashMap<String, Rectangle> recMap;
    int delay;
    static boolean play = true;

    public MyAnimation(HashMap<String, Rectangle> map, int ddelay) {
        recMap = map;
        delay = ddelay;
    }

    static public void pauseAnim(){
        play = false;
    }

    static public void playAnim(){
        play = true;
    }

    public void run() {
        Rectangle rec;
        boolean faze = true;
        int x = 0, y = 0;
        while(true) {
            while (y < 10 && play) {
                while (x < 10 && play) {
                    try {
                        sleep(delay);
                    } catch (InterruptedException e) {
                        return;
                    }
                    rec = recMap.get("" + x + y);
                    if (faze)
                        rec.setFill(Color.GREEN);
                    else
                        rec.setFill(Color.BLACK);
                    x++;
                }
                y++;
                x = 0;
                if (y == 10) {
                    y = 0;
                    faze = !faze;
                }
            }
            try {
                x = y = 0;
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


