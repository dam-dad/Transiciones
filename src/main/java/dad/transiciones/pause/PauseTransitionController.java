package dad.transiciones.pause;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class PauseTransitionController implements Initializable {

	private SequentialTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private ImageView emojiImage;
	
	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public PauseTransitionController() {
		transicion = new SequentialTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PauseTransitionView.fxml"));
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

		RotateTransition rotate1 = new RotateTransition();
		rotate1.setDuration(Duration.seconds(0.25));
		rotate1.setAxis(new Point3D(0, 1, 0));
		rotate1.setFromAngle(90);
		rotate1.setToAngle(0);
		rotate1.setNode(emojiImage);
		rotate1.setInterpolator(Interpolator.EASE_BOTH);

		TranslateTransition translate = new TranslateTransition();
		translate.setDuration(Duration.seconds(0.50));
		translate.setFromX(-200.0);
		translate.setToX(200.0);
		translate.setNode(emojiImage);
		translate.setInterpolator(Interpolator.EASE_BOTH);

		RotateTransition rotate2 = new RotateTransition();
		rotate2.setDuration(Duration.seconds(0.25));
		rotate2.setAxis(new Point3D(0, 1, 0));
		rotate2.setFromAngle(0);
		rotate2.setToAngle(180);
		rotate2.setNode(emojiImage);
		rotate2.setInterpolator(Interpolator.EASE_BOTH);
		
		PauseTransition pause1 = new PauseTransition(Duration.seconds(1));
		
		PauseTransition pause2 = new PauseTransition(Duration.seconds(1));

		transicion = new SequentialTransition();
		transicion.setAutoReverse(true);
		transicion.setCycleCount(Transition.INDEFINITE);
		transicion.getChildren().addAll(rotate1, pause1, translate, rotate2, pause2);
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
