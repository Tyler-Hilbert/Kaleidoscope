package background;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


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
