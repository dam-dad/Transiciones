package dad.transiciones.varios;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VariosApp extends Application {

	private VariosController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new VariosController();
		
		primaryStage.setTitle("Fade Transition");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
