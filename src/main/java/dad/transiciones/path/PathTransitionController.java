package dad.transiciones.path;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PathTransitionController implements Initializable {

	private PathTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private Path pathShape;
	
	@FXML
	private Button holaButton;

	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public PathTransitionController() {
		transicion = new PathTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PathTransitionView.fxml"));
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
		
		transicion.setAutoReverse(false);
		transicion.setCycleCount(FillTransition.INDEFINITE);
		transicion.setDelay(Duration.ZERO);
		transicion.setDuration(Duration.seconds(5));
		transicion.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		transicion.setRate(1.0);
		transicion.setPath(pathShape);
		transicion.setNode(holaButton);
		transicion.setInterpolator(Interpolator.EASE_BOTH);
	}
	
	public BorderPane getView() {
		return view;
	}
	
	@FXML
	private void onPlayButtonAction(ActionEvent e) {
		System.out.println("iniciando");
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
