
import controllers.RectangleBoardController;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/GUI/rectangleBoard.fxml"));
		AnchorPane anchorPane = loader.load();
		Scene scene = new Scene(anchorPane, 800, 600);
		stage.setTitle("Wire World 2018");
		stage.setScene(scene);

		stage.show();
	}

}
