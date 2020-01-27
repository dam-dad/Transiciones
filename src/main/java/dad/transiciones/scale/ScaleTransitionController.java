package dad.transiciones.scale;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class ScaleTransitionController implements Initializable {

	private ScaleTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private ImageView emojiImage;
	
	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public ScaleTransitionController() {
		transicion = new ScaleTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ScaleTransitionView.fxml"));
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
		transicion.setDuration(Duration.seconds(0.50));
		transicion.setFromX(1);
		transicion.setToX(0.25);
		transicion.setFromY(1);
		transicion.setToY(0.25);
		transicion.setNode(emojiImage);
		transicion.setInterpolator(Interpolator.EASE_BOTH);
		
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
