package objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class AV extends Car {
	public AV(double x, Lane l) {
		super(x, l);		
		image = "/images/AVimage (1) (1).png";
		acceleration = .1;
		
	}
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}
	
	//front = lane.getFront();
	
	public Image getHDImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(image));
		return i.getImage();
	}
	

public void adjustAccel() {
	
	Lane lane = getLane();
	
	Car frontCar = lane.getNextCar(this); 
    Car backCar = lane.getPrevCar(this);  
    

    
    // Ensure there are cars in both directions
    if (frontCar != null && backCar != null) {
   
        double midpointX = (frontCar.x + backCar.x) / 2.0; // Calculate midpoint

        // Determine the direction of adjustment
        if (this.x < midpointX-100) {
            acceleration = frontCar.acceleration+.0001;
            acceleration = backCar.acceleration-.0001; 
        } else {
            acceleration = frontCar.acceleration; // Already in the midpoint
        }
        
    } else {
    	
    	if (frontCar == null && backCar == null)
    			acceleration = .0001;
    	else if (backCar == null) {
        	acceleration = frontCar.acceleration+.0001;
    	}
        else if (frontCar == null)
        	acceleration = backCar.acceleration-.0001;
      
    }
} 

public void update() {
	//System.out.println("Pos: "+x+" Accel: "+acceleration);
	adjustAccel();
	
	super.update();
	

}

}