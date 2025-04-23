package mainpackage;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import objects.Player;

public class GlobalPosition {
	public static int PposX = 100; //Tracks the global position of the Player
	
	public static String img = "/images/bg (2) (1).png";
	//public static String img = "/images/bg.png";
	
	public double x; //relative position of each object
	public double y;
	
	public int X;
	public int Y;
	
	public double relX;
	

	
	/*public GlobalPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}*/
	public GlobalPosition(double x, double y) {
		this.x = x;
		this.y = y;
		//System.out.print(" glob ");
	}
	
	public void update() {
		
		if (y>MainClass.height-50)
			y=MainClass.height-50;
		else if (y<0)
			y = 0;
		
		X=(int)(x+.5);
		Y=(int)(y+.5);

	}
	
	
	
}
