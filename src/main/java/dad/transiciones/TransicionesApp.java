package dad.transiciones;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransicionesApp extends Application {

	private TransicionesController transicionesController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		transicionesController = new TransicionesController();

		primaryStage.setTitle("Transiciones Demo");
		primaryStage.setScene(new Scene(transicionesController.getView(), 640, 480));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
