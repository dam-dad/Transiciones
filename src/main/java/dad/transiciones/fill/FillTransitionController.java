package dad.transiciones.fill;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class FillTransitionController implements Initializable {

	private FillTransition transicion;
	
	private BorderPane view;
	
	@FXML
	private Polygon poligonoShape;
	
	@FXML
	private Button playButton, pauseButton, stopButton;
	
	@FXML
	private Label estadoLabel;
	
	public FillTransitionController() {
		transicion = new FillTransition();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FillTransitionView.fxml"));
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
		transicion.setCycleCount(FillTransition.INDEFINITE);
		transicion.setDelay(Duration.ZERO);
		transicion.setDuration(Duration.seconds(1));
		transicion.setFromValue((Color)poligonoShape.getFill());
		transicion.setToValue(Color.YELLOW);
		transicion.setRate(1.0);
		transicion.setShape(poligonoShape);
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
