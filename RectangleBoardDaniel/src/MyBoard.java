

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;


public class MyBoard extends Application {

    private Color color = new Color(0, 1, 0, 1);

    public void start( Stage stage )  {

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(1000, 1000);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(0);
        gridPane.setHgap(0);

        HashMap<String, Rectangle> recMap = new HashMap<String, Rectangle>();
        ArrayList<Rectangle> recList = new ArrayList<Rectangle>();

        for( int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                Rectangle tmpRec = new Rectangle(30, 30);
                tmpRec.setStroke(Color.GRAY);

                gridPane.add(tmpRec, x, y);

                recMap.put("" + x + y, tmpRec);
                recList.add(tmpRec);
                EventHandler<MouseEvent> eventHandler = e -> {
                    tmpRec.setFill(color);
                };
                tmpRec.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
            }
        }

        ColorPicker colp = new ColorPicker(Color.GREEN);
        gridPane.add(colp, 10, 0);
        colp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                color = colp.getValue();
            }
        });

        Button btnS = new Button("Start");
        gridPane.add(btnS, 10,1);
        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyAnimation.playAnim();
            }
        });

        Button btnSS = new Button("Stop");
        gridPane.add(btnSS, 10,2);
        btnSS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyAnimation.pauseAnim();
            }
        });

        Scene scene = new Scene(gridPane, 400, 400);
        stage.setScene(scene);
        stage.show();
        MyAnimation animate = new MyAnimation(recMap, 100);
        animate.start();
    }

    public static void main ( String []args){
        launch(args);
    }
}
