package boomerang;


import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Hanish
 */
public class Boomerang extends Application{

        @Override
    public void start(Stage stage){
        StackPane boomerangArea = new StackPane();
        boomerangArea.setStyle("-fx-background-color: white");
        
        FlowPane pane = new FlowPane();
        pane.setStyle("-fx-background-color: white");
        Button btn1 = new Button("Start");
        Button btn2 = new Button("Stop");
        Button btn3 = new Button("Pause");
        Button btn4 = new Button("Exit");
        
        pane.getChildren().addAll(btn1,btn2,btn3,btn4);
        
        BorderPane rootPane = new BorderPane();
        Ellipse ellipse = new Ellipse(200, 100);
        ellipse.setFill(null);
        ellipse.setStroke(Color.BLACK);
        
        Image boom = new Image("pics/boomerang.jpg");
        ImageView boomerang = new ImageView(boom);
        boomerang.setFitWidth(50);
        boomerang.setPreserveRatio(true);
        
        boomerangArea.getChildren().add(boomerang);
        rootPane.setCenter(boomerangArea);
        pane.setAlignment(Pos.CENTER);
        rootPane.setBottom(pane);
        
        btn4.setOnAction(e->{exit();});
        
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(5));
        pt.setPath(ellipse);
        pt.setNode(boomerang);
        pt.setAutoReverse(false);
        pt.setCycleCount(Timeline.INDEFINITE);
        
        RotateTransition rt = new RotateTransition();
        rt.setNode(boomerang);
        rt.setFromAngle(0);
        rt.setToAngle(360);
        rt.setAutoReverse(false);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setDuration(Duration.seconds(0.5));
        
        ParallelTransition ptt = new ParallelTransition();
        ptt.getChildren().addAll(pt,rt);
        
        btn1.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                ptt.play();
            }
        });
        
        btn2.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                ptt.stop();
            }
        });
        
        btn3.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                ptt.pause();
            }
        });
        
        Scene scene = new Scene(rootPane, 600, 400);
        stage.setScene(scene);
        stage.show();
        stage.getIcons().add(boom);
        stage.setTitle("Boomerang");
        
    }
    public void exit(){
        Platform.exit();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
