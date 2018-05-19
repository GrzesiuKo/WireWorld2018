package TestBoard;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Test2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/Test2.fxml"));
        Scene scene = new Scene (loader.load());

        scene.addEventFilter(MouseDragEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                scene.startFullDrag();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String args){
        launch(args);
    }
}

