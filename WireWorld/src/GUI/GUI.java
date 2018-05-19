package GUI;

import Board.BoardMaker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/MainScreen.fxml"));
        primaryStage.setTitle("Wire World 2018");
        Scene scene = new Scene(root);
        scene.addEventFilter(MouseDragEvent.DRAG_DETECTED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                scene.startFullDrag();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

        public static void main (String[]args){
            launch(args);
        }
}

