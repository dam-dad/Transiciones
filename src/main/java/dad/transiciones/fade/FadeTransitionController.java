package dad.transiciones.fade;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class FadeTransitionController implements Initializable {

	private FadeTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private GridPane panel;
	
	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public FadeTransitionController() {
		transicion = new FadeTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FadeTransitionView.fxml"));
			loader.setController(this);
			view = loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		playButton.disableProperty().bind(transicion.statusProperty().isEqualTo(Status.RUNNING));
		pauseButton.disableProperty().bind(transicion.statusProperty().isEqualTo(Status.PAUSED).or(transicion.statusProperty().isEqualTo(Status.STOPPED)));
		stopButton.disableProperty().bind(transicion.statusProperty().isEqualTo(Status.STOPPED));
		estadoLabel.textProperty().bind(transicion.statusProperty().asString());
		
		transicion.setAutoReverse(true);
		transicion.setCycleCount(Transition.INDEFINITE);
		transicion.setDelay(Duration.ZERO);
		transicion.setDuration(Duration.seconds(1));
		transicion.setFromValue(1.0);
		transicion.setToValue(0.0);
		transicion.setRate(2.0);
		transicion.setNode(panel);
		transicion.setInterpolator(Interpolator.LINEAR);
	}
	
	public BorderPane getView() {
		return view;
	}
	
	@FXML
	private void onPlayButtonAction(ActionEvent e) {
		transicion.play();
	}

	@FXML
	private void onPauseButtonAction(ActionEvent e) {
		transicion.pause();
	}

	@FXML
	private void onStopButtonAction(ActionEvent e) {
		transicion.stop();
	}

}
