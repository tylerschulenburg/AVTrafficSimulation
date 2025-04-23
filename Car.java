package objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import mainpackage.GlobalPosition;
import mainpackage.Sim;
import mainpackage.MainClass;
import objects.Lane;

public abstract class Car extends GlobalPosition {
    public String image;
    public double acceleration;
    public double speed;
    protected int speedLimit = 6;  // Speed limit for the car
    protected final double fricAccel = -0.004;  // Friction-based acceleration
    public Lane lane;
    
    public Rectangle2D hitbox;  // For collision detection
    protected double lanePosition; // The Y-position of the car within a lane (should be constant for each lane)
    
    // Constructor
    public Car(double x, Lane l) {
        super(x, l.assignYPosition());
        this.lane =l;
        this.acceleration = 0;
        this.speed = 0;
        this.hitbox = new Rectangle((int)x, (int)y, 80, 50);  // Example size
        
        updateHitbox();  // Update the hitbox with the current position
    }

    // Abstract method to define how each car should update itself (to be implemented in subclasses)
    public void setLane(Lane l) {
    	lane = l;
    }
    public Lane getLane() {
    	return lane;
    }

    // Method to handle drawing the car on screen
    public void draw(Graphics2D g2d) {
        int imgX = (int)(relX);
        g2d.drawImage(getTheImage(), imgX, (int)y, 100, 50, null);
        imgX = (int)(100 + relX);
    }

    // Method to update the general state of the car (e.g., position, speed, etc.)
    public void update() {
        speed += acceleration;
        if (speed < 0 && acceleration < 0) {
            speed = 0;
            acceleration = 0;
        }
        if (speed > speedLimit) {
            acceleration = fricAccel;
            speed = speedLimit;
        }
        x += speed - Sim.Pspeed;

        updateHitbox();
        super.update();
    }

    // Method to update the hitbox with the car's current position
    public void updateHitbox() {
        hitbox.setRect(x, y, 80, 50);
    }

    // Getter for the hitbox (for collision detection)
    public Rectangle2D getHitbox() {
        return hitbox;
    }

    // Abstract method to handle user input for the HDcar class (e.g., arrow keys)

    // Helper method to get the image associated with the car (can be customized for each subclass)
    public Image getTheImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(image));
		return i.getImage();
	}
    public String toString() {
		return "car at " + X+" ";
	}
}
	
	
	
	




