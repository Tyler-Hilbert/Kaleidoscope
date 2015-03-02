package background;

import static background.DesignMovingRotation.changingColor;
import java.util.Random;
import javafx.scene.paint.Color;

/**
 *  An RGB value that is changing to another random color
 */
public class ChangingColor {
    // Current colors
    private int r;
    private int g;
    private int b;
    // Destination colors. These are generated randomly
    private int rD;
    private int gD;
    private int bD;
    
    public ChangingColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        
        Random rand = new Random();
        rD = rand.nextInt(246) + 10;
        gD = rand.nextInt(246) + 10;
        bD = rand.nextInt(246) + 10;
            
    }
    
    public Color getUpdatedColor() {
        if (DesignMovingRotation.changingColor) {
            Random rand = new Random();
            if (r < rD) 
                r++;
            else if (r > rD)
                r--;
            else
                rD = rand.nextInt(246) + 10;
           
            if (g < gD) 
                g++;
            else if (g > gD)
                g--;
            else
                gD = rand.nextInt(246) + 10;
            
            if (b < bD) 
                b++;
            else if (b > bD)
                b--;
            else
                bD = rand.nextInt(246) + 10;
        }
        
        return Color.web("rgb(" + r + "," + g + "," + b + ")");
    }
}
