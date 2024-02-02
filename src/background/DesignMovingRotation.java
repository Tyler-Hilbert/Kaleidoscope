package background;


import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

/**
 * Creates and controls the designs that are drawn
 */
public class DesignMovingRotation {
    private GraphicsContext gc = Background.gc;
    private Canvas canvas = Background.canvas;
    private int count = 0;
    private int iteration = 10; // Distance between lines

    // Color changing 
    public static boolean changingColor = true;
    private boolean multicolor = true;
    private ChangingColor color1 = new ChangingColor(170, 70, 0);
    private ChangingColor color2 = new ChangingColor(90, 100, 110);
    private ChangingColor color3 = new ChangingColor(255, 0, 255);
    
    // The speed the circle moves. A lower number is faster
    // Is only used when edgeBouncing = false
    private int speed = 50; 
    
    // Bouncing variables
    private boolean edgeBouncing = false;
    private int bounceX = 20;
    private int bounceY = 7;
    private int bounceMargin = 20;
    
    // Origin for cirlce
    private int x1;
    private int y1;
    
    // Where the origin is moving to
    private int xd;
    private int yd;
    
    // Changing direction
    private boolean changingDirection = true;
    private int changeDirectionSpeed = 5;
    private int direction = 1; // DON'T MANUALLY CHANGE THIS. It determines the direction of rotation.

    
    public void start() {
        x1 = Background.WINDOW_WIDTH / 2;
        y1 = Background.WINDOW_HEIGHT / 2;
        
        generateDestination();

        Timeline timeline = new Timeline(new KeyFrame(
            Duration.millis(speed),
            ae-> drawShapes()
        ));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    /**
     * Draws the shapes on the UI
     */
    private void drawShapes() {
        reset();
        
        setColor();

        if (edgeBouncing) {
            moveOriginBounce();
        } else {
            moveOriginRandom();
        }
        
        if (changingDirection) {
            Random rand = new Random();
            if (rand.nextInt(1000) <=  changeDirectionSpeed) {
                direction *= -1;
            }
        }
        count += direction;
        double degrees = count % iteration;
        
        
        // Loops through the circle generating points with distance `iteration`
        for (; degrees < 360; degrees += iteration) {
            double radians = Math.toRadians(degrees);
            // Find point with soh cah toa
            double y2 = y1 - Math.sin(radians) * Background.WINDOW_HEIGHT * 1.5; 
            double x2 = x1 + Math.cos(radians) * Background.WINDOW_WIDTH * 1.5;
            gc.strokeLine(x1, y1, x2, y2);
        }        
    }
    
    /**
     * Sets the color for the lines drawn
     */
    private void setColor() {
        if (multicolor) {
            gc.setStroke(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
                   CycleMethod.REFLECT,
                   new Stop(0.0, color1.getUpdatedColor()),
                   new Stop(1.0, color2.getUpdatedColor()),
                   new Stop(0.0, color3.getUpdatedColor())));
        } else {
            gc.setStroke(color1.getUpdatedColor());
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
    
    /**
     * Generates xd, yd: the points that the origin is moving to
     */
    private void generateDestination() {
        Random rand = new Random();
        xd = rand.nextInt(Background.WINDOW_WIDTH);
        yd = rand.nextInt(Background.WINDOW_HEIGHT);
        
        if (edgeBouncing) {
            int wall = rand.nextInt(4);
            if (wall == 0)
                xd = 20;
            else if (wall == 1)
                xd = (int)canvas.getWidth() - 20;
            else if (wall == 2)
                yd = 20;
            else
                yd = (int)canvas.getHeight() - 20;
        }
    }
    
    /**
     * Moves the origin towards the random destination
     */
    private void moveOriginRandom() {
        if (xd == x1 || yd == y1) {
            generateDestination();
        }
        
        if (xd > x1) {
            x1++;
        } else {
            x1--;
        }
        
        if (yd > y1) {
            y1++;
        } else {
            y1--;
        }
    }
    
    /**
     * Continues to move the origin on the path
     */
    private void moveOriginBounce() {        
        x1 += bounceX;
        y1 += bounceY;
        if ((x1 <  bounceMargin && bounceX < 0) || (x1 > canvas.getWidth() - bounceMargin && bounceX > 0)) {
            bounceX *= -1;
        }
        
        if ((y1 < bounceMargin && bounceY < 0) || (y1 > canvas.getHeight() - bounceMargin && bounceY > 0)) {
            bounceY *= -1;
        }
    }
}
