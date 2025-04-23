package objects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;



import mainpackage.GlobalPosition;
import mainpackage.ImageManager;
import mainpackage.MainClass;
import mainpackage.Sim;

public class Player extends Car {
    private String playerImage = "/images/playercar.png";
    private double acceleration = 0.0;
    public double speed = 0.0;
    private double heading = 0.0; // Car rotation (in degrees)

    private final int speedLimit = 10;
    private final int accelMax = 2;
    private final int accelMin = -1;
    private final double fricAccel = 0; //-0.004;

    private Image rotated;
    private Background background; // Reference to Background

    public Player(int x, Lane l, Background bg) {
        super(x, l);
        this.background = bg;
    }

    public void setAcceleration(int a) {
        acceleration = a;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_RIGHT) {
            heading += 0.05; // Slight turn right
        } else if (key == KeyEvent.VK_LEFT) {
            heading -= 0.05; // Slight turn left
        } else if (key == KeyEvent.VK_DOWN) {
            if (acceleration > accelMin)
                acceleration -= 0.001;
        } else if (key == KeyEvent.VK_UP) {
            if (acceleration < accelMax)
                acceleration += 0.001;
        }

        rotated = (Image) ImageManager.rotate(ImageManager.Buffer(ImageManager.getPlayerImage(this, playerImage)), heading);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            acceleration = 0; // Stop accelerating when key is released
        }
    }

    public void updateHitbox() {
        int pos = PposX;
        double radianAngle = Math.toRadians(heading);
        double rotatedWidth = Math.abs(37 * Math.cos(radianAngle)) + Math.abs(60 * Math.sin(radianAngle));
        double rotatedHeight = Math.abs(37 * Math.sin(radianAngle)) + Math.abs(60 * Math.cos(radianAngle));

        double centerX = pos + 80 / 2;
        double centerY = y + 50 / 2;

        hitbox = new Rectangle2D.Double(
            centerX - rotatedWidth / 2,
            centerY - rotatedHeight / 2,
            rotatedWidth,
            rotatedHeight
        );
    }

    public void update() {
        // Update speed based on acceleration and friction
        double friction = (speed > 0) ? fricAccel : 0;
        double netAccel = acceleration + friction;
        speed += netAccel;

        // Speed limits
        if (speed < 0.0) {
            speed = 0;
            acceleration = 0;
        }
        if (speed > speedLimit) {
            acceleration = fricAccel;
            speed = speedLimit;
        }

        // Instead of moving the player, move the background backward
        double Vx = speed* Math.cos(Math.toRadians(heading));
        background.update(Vx);
        Sim.Pspeed=Vx;

        y += speed * Math.sin(Math.toRadians(heading));
        
        if (y>MainClass.height-50)
			y=MainClass.height-50;
		else if (y<0)
			y = 0;
        
        
		X=(int)(x+.5);
		Y=(int)(y+.5);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(rotated, X, Y, 80, 50, null);
    }
}
