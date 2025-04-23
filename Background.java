package objects;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {
    private Image bgImage;
    private int bgX;  // Background X position
    private double smallBgX;
    private final int screenWidth = 2560;
    private final int bgWidth = 2560;
    
    public Background(String imagePath) {
    	System.out.println(" Image loaded from "+imagePath);
        bgImage = new ImageIcon(imagePath).getImage();
        bgX = 0;
    }

    public void update(double playerSpeed) {
    	smallBgX-=playerSpeed;
    	
        bgX =(int)(smallBgX+.5);  // Move background backwards

        // Loop background when it reaches the end
        if (bgX <= -bgWidth) {
            bgX += bgWidth;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(bgImage, bgX, 350, null);
        g.drawImage(bgImage, bgX + bgWidth-200, 350, null);  // Draw second instance for looping effect
    }
}