package dad.transiciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.transiciones.fade.FadeTransitionController;
import dad.transiciones.fill.FillTransitionController;
import dad.transiciones.path.PathTransitionController;
import dad.transiciones.pause.PauseTransitionController;
import dad.transiciones.scale.ScaleTransitionController;
import dad.transiciones.translate.TranslateTransitionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class TransicionesController implements Initializable {

	private FadeTransitionController fadeController;
	private FillTransitionController fillController;
	private PathTransitionController pathController;
	private ScaleTransitionController scaleController;
	private TranslateTransitionController translateController;
	private PauseTransitionController pauseController;

	private BorderPane view;

	@FXML
	private Tab fadeTab, fillTab, parallelTab, pathTab, pauseTab, rotateTab, scaleTab, sequentialTab, strokeTab,
			translateTab;

	public TransicionesController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TransicionesView.fxml"));
			loader.setController(this);
			view = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fadeController = new FadeTransitionController();
		fillController = new FillTransitionController();
		pathController = new PathTransitionController();
		scaleController = new ScaleTransitionController();
		translateController = new TranslateTransitionController();
		pauseController = new PauseTransitionController();

		fadeTab.setContent(fadeController.getView());
		fillTab.setContent(fillController.getView());
		pathTab.setContent(pathController.getView());
		scaleTab.setContent(scaleController.getView());
		translateTab.setContent(translateController.getView());
		pauseTab.setContent(pauseController.getView());
	}

	public BorderPane getView() {
		return view;
	}

}
