
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
		
		
		final Factory factory = new Factory(800, 600, 1000);
		factory.makeBoard(20, 20);

		int i = 0;

		for (Cell c : factory.getBoard()) {
			anchorPane.getChildren().add(factory.getBoard().get(i).getCell());
			i++;
		}
		stage.setScene(scene);

		stage.show();

	}

}
