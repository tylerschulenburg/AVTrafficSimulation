package objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;


public class HDcar extends Car implements KeyListener {
	public class HDCar extends Car implements KeyListener {
	    private boolean movingForward;
	    private Lane lane;

	    public HDCar(double x, Lane lane) {
	        super(x, lane);  // Automatically assigns Y position
	        this.lane = lane;
	        movingForward = false;
	    }

	    @Override
	    public void update() {
	        if (movingForward) {
	            acceleration = 0.02;  // Accelerate forward
	        } else {
	            acceleration = -0.02; // Gradually slow down
	        }
	    }

	    public void switchLane(Lane newLane) {
	        if (newLane != lane) {
	            lane = newLane;
	            y = lane.assignYPosition();  // Snap to new lane position
	        }
	    }

	    @Override
	    public void handleInput() {
	        // HDCar doesn't use AI logic for acceleration
	    }

	    @Override
	    public Image getTheImage() {
	        return null; // Replace with actual car image
	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_UP) {
	            movingForward = true;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	            movingForward = false;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	            switchLane(YourGame.getLeftLane(lane));
	        }
	        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	            switchLane(YourGame.getRightLane(lane));
	        }
	    }

	    @Override
	    public void keyReleased(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_UP) {
	            movingForward = false;
	        }
	    }

	    @Override
	    public void keyTyped(KeyEvent e) {}
	}

	
		
}

