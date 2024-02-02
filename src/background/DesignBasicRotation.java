package background;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Creates and controls the designs that are drawn
 */
public class DesignBasicRotation {
    private GraphicsContext gc = Background.gc;
    private Canvas canvas = Background.canvas;
    private int count = 0;
    
    private int iteration = 10; // Distance between lines
    private int speed = 50; // The speed the circle moves. A lower number is faster
    
    // Origin for cirlce
    private int x1;
    private int y1;
    
    public void start() {
        x1 = Background. WINDOW_WIDTH / 2;
        y1 = Background. WINDOW_HEIGHT / 2;
        
        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(speed),
            ae-> drawShapes(++count%iteration)
        ));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    /**
     * Draws the shapes on the UI
     * @param degrees - The degree to start at. This degree is looped through to
     *      make an endless movement
     */
    private void drawShapes(double degrees) {
        reset();
        gc.setStroke(Color.RED);
        
        // Loops through the circle generating points with distance `iteration`
        for (; degrees < 360; degrees += iteration) {
            double radians = Math.toRadians(degrees);
            // Find point with soh cah toa
            double y2 = y1 - Math.sin(radians) * Background.WINDOW_HEIGHT; 
            double x2 = x1 + Math.cos(radians) * Background.WINDOW_WIDTH;
            gc.strokeLine(x1, y1, x2, y2);
        }        
    }
    
    /**
     * Resets the background
     */
    private void reset() {
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
