package background;

import static java.lang.Math.random;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class Background extends Application {
    final static int WINDOW_HEIGHT = 1000;
    final static int WINDOW_WIDTH = 1000;
    
    static GraphicsContext gc = null;
    static Canvas canvas = null;
   
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        
        DesignMovingRotation design = new DesignMovingRotation();
        design.start();
       

        root.getChildren().add(canvas);
        primaryStage.initStyle(StageStyle.UNDECORATED); // Removes windows border
        primaryStage.setScene(new Scene(root));        
        primaryStage.show();
    }
        
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
