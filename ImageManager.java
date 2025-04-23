package mainpackage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.ImageIcon;

public class ImageManager {
	
	/*

	public static BufferedImage rotate(BufferedImage img, double theta)
    {
 
        // Getting Dimensions of image
        int width = img.getWidth();
 
        // Creating a new buffered image
        BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
 
        // creating Graphics in buffered image
        Graphics2D g2 = newImage.createGraphics();
 
        // Rotating image by degrees using toradians()
        // method
        // and setting new dimension t it
        g2.rotate(theta, width / 2, height / 2);
        g2.drawImage(img, null, 0, 0);
 
        // Return rotated buffer image
        return newImage;
    }
    
    */
	
	public static Image getPlayerImage(Object p, String im) {
		ImageIcon i = new ImageIcon(p.getClass().getResource(im));
		return i.getImage();
	}
	
	//Works but resizes
	
	/*public static BufferedImage rotate(BufferedImage bimg, Double angle) {
	    double sin = Math.abs(Math.sin(angle)),
	           cos = Math.abs(Math.cos(angle));
	    int w = bimg.getWidth();
	    int h = bimg.getHeight();
	    int neww = (int) Math.floor(w*cos + h*sin),
	        newh = (int) Math.floor(h*cos + w*sin);
	    BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.translate((neww-w)/2, (newh-h)/2);
	    graphic.rotate(angle, w/2, h/2);
	    graphic.drawRenderedImage(bimg, null);
	    graphic.dispose();
	    return rotated;
	}*/
	
	///New Function
	
	public static BufferedImage rotate(BufferedImage originalImage, double degrees) {
        // Convert degrees to radians
        double radians = degrees;

        // Get the original image dimensions
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Create a new image of the same size as the original
        BufferedImage rotatedImage = new BufferedImage(width, height, originalImage.getType());

        // Create a Graphics2D object for rendering
        Graphics2D g2d = rotatedImage.createGraphics();

        // Set rendering hints for high quality
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Rotate around the center of the original image
        g2d.translate(width / 2.0, height / 2.0); // Move origin to the center
        g2d.rotate(radians);                     // Rotate around the new origin
        g2d.translate(-width / 2.0, -height / 2.0); // Move origin back

        // Draw the original image onto the rotated context
        g2d.drawImage(originalImage, 0, 0, null);

        // Release resources
        g2d.dispose();

        return rotatedImage;
    }
	
	
	public static BufferedImage Buffer(Image image) throws IllegalArgumentException {
	    int width = image.getWidth(null);
	    int height = image.getHeight(null);
	    
	    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    bufferedImage.getGraphics().drawImage(image, 0, 0, null);
	    return bufferedImage;
	}
}
