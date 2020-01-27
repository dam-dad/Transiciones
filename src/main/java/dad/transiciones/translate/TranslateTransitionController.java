package dad.transiciones.translate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class TranslateTransitionController implements Initializable {

	private TranslateTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private ImageView emojiImage;
	
	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public TranslateTransitionController() {
		transicion = new TranslateTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TranslateTransitionView.fxml"));
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
		transicion.setFromX(-200.0);
		transicion.setToX(200);
		transicion.setFromY(-100.0);
		transicion.setToY(100);
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
