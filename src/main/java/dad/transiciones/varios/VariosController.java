package dad.transiciones.varios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class VariosController implements Initializable {
	
	private Animation animation;
	
    @FXML
    private VBox view;

    @FXML
    private Label holaLabel;

    @FXML
    private Button animacion1Button, animacion2Button, animacion3Button;

	public VariosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VariosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		view.sceneProperty().addListener((o, ov, nv) -> {
			iniciarTransicion();
		});
		
	}
	
	private void iniciarTransicion() {
    	FadeTransition fade = new FadeTransition();
    	fade.setDuration(Duration.seconds(4));
    	fade.setFromValue(0.0);
    	fade.setToValue(1.0);
    	fade.setNode(view);
    	fade.play();
	}

    @FXML
    void onAnimacion1Action(ActionEvent event) {
    	if (this.animation != null && this.animation.getStatus().equals(Status.RUNNING)) {
    		this.animation.stop();
    	}

    	KeyValue kv1 = new KeyValue(holaLabel.opacityProperty(), 0.0);
    	KeyValue kv2 = new KeyValue(holaLabel.translateXProperty(), 100.0);
    	KeyValue kv3 = new KeyValue(animacion1Button.rotateProperty(), 360.0);
    	KeyFrame kf1 = new KeyFrame(Duration.seconds(1), kv1, kv2, kv3);
    	
    	Timeline timeline = new Timeline();
    	timeline.setAutoReverse(true);
    	timeline.setCycleCount(2);
    	timeline.getKeyFrames().addAll(kf1);
    	timeline.play();
    	
    	this.animation = timeline;
    }
    	
    @FXML
    void onAnimacion2Action(ActionEvent event) {
    	if (this.animation != null && this.animation.getStatus().equals(Status.RUNNING)) {
    		this.animation.stop();
    	}

    	FadeTransition fade = new FadeTransition();
    	fade.setDuration(Duration.seconds(1));
    	fade.setFromValue(1.0);
    	fade.setToValue(0.0);
    	
    	TranslateTransition translate = new TranslateTransition();
    	translate.setDuration(Duration.seconds(1));
    	translate.setFromY(0.0);
    	translate.setToY(-50.0);

    	SequentialTransition secuencia = new SequentialTransition();
    	secuencia.setAutoReverse(true);
    	secuencia.setCycleCount(1);
    	secuencia.setNode(holaLabel);
    	secuencia.getChildren().addAll(translate, fade);
    	secuencia.play();
    	
    	this.animation = secuencia;    	
    }
    
    @FXML
    void onAnimacion3Action(ActionEvent event) {
    	if (this.animation != null && this.animation.getStatus().equals(Status.RUNNING)) {
    		this.animation.stop();
    	}

    	TranslateTransition translate = new TranslateTransition();
    	translate.setDuration(Duration.seconds(0.5));
    	translate.setFromY(50.0);
    	translate.setToY(-50.0);
    	translate.setAutoReverse(true);
    	translate.setCycleCount(TranslateTransition.INDEFINITE);
    	translate.setNode(holaLabel);
    	translate.setInterpolator(Interpolator.EASE_BOTH);
    	translate.play();
    	
    	this.animation = translate;
    }	
    
    public VBox getView() {
		return view;
	}
    
}
