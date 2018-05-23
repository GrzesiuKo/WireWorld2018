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
import javafx.stage.WindowEvent;

import java.awt.event.MouseEvent;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainScreen.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Wire World 2018");
        Scene scene = new Scene(root);
        scene.addEventFilter(MouseDragEvent.DRAG_DETECTED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                scene.startFullDrag();
            }
        });
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainScreenController mainScreenController = loader.getController();
        mainScreenController.setStage(primaryStage);
        mainScreenController.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                    mainScreenController.getGenHandler().terminate();


            }
        });

    }

    public static void main(String[] args) {
        launch(args);


    }
}

