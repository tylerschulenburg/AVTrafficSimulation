package mainpackage;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import inputs.KeyInput;
import objects.Car;
//import objects.HDcar;
import objects.Lane;
import objects.Player;
import objects.AV;
import objects.Background;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Sim extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private String background = "/Users/tylerschulenburg/Desktop/CSlll/AVSimProject/src/images/allRoad (1).png";
	
	Player p;
	
	ArrayList<Car> others;
	

	
	

	
	public static double score = 10.0; // Collision score
	public static int flowScore = 0;
	
	private Lane lane1, lane2, lane3;
    private ArrayList<Car> allCars;  // This can be used to keep track of all cars in the simulation
    private Player playerCar;
	private Background bg;
	
	public static double Pspeed;
	public static double Px;


	Timer simlooptimer;
	
	public Sim() {
		
		
		setFocusable(true);
		bg = new Background(background);
		

	    // Constructor to initialize the lanes, the player car, and the AV cars
	        lane1 = new Lane(665, 755);   // Top lane (Y: 665-755)
	        lane2 = new Lane(755, 845);   // Middle lane (Y: 755-845)
	        lane3 = new Lane(845, 935);   // Bottom lane (Y: 845-935)
	        allCars = new ArrayList<Car>();
	        

	        // Initialize the player car
	       // playerCar = new Player(100, 700);  // Example position
	        //allCars.add(playerCar);

	        // Initialize some AV cars for each lane
	        
	        for (int i = 0; i < 5; i++) {
	            AV avCar1 = new AV(Math.random()*20+2, lane1);  // Example positions in different lanes
	            AV avCar2 = new AV(Math.random()*20+2, lane2);
	            AV avCar3 = new AV(Math.random()*20+2, lane3);
	            allCars.add(avCar1);
	            allCars.add(avCar2);
	            allCars.add(avCar3);
	        }
	        playerCar = new Player(100, lane2, bg);
	        allCars.add(playerCar);
	    

		
		
		
		
		others = new ArrayList<Car>();
		
		
		
		
		
		simlooptimer = new Timer(10, this);
		simlooptimer.start();
		
		addKeyListener(new KeyInput(playerCar));
		
	}
	public void paint(Graphics g) {
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		//g2d.drawImage(getBackgroundImage(), 0, 0, this);
		bg.draw(g2d);		
		g2d.setFont(new Font("TAHOMA",Font.BOLD,18));
		
		g2d.drawString("Score = "+score,40,40);
		for (Car c: allCars) {
			c.draw(g2d);
			
		}
		
		
	}
	public Image getBackgroundImage() {
		ImageIcon i = new ImageIcon(getClass().getResource(background));
		return i.getImage();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	        // Update the position of all cars
	        // Update the position of all cars
		
		 playerCar.update();  // Assuming there's a method to handle arrow key input
	        
	        Pspeed=playerCar.speed;
	        Px=playerCar.x;
	        bg.update(Pspeed);
	        
	        for (Car car : allCars) {
	        	if (car!= playerCar) {
	            	car.update();  // Calls the update method for each car
	            	car.x -= Pspeed;
	        	}
	            
	            // Handle car lane assignment and removal
	            Lane currentLane = car.getLane();
	            if (lane1.isInLane(car)) {
	                if (currentLane != lane1) {
	                    if (currentLane != null) {
	                        currentLane.removeCar(car);  // Remove from previous lane
	                    }
	                    lane1.insertCar(car);  // Insert into new lane
	                    car.setLane(lane1);
	                }
	            } else if (lane2.isInLane(car)) {
	                if (currentLane != lane2) {
	                    if (currentLane != null) {
	                        currentLane.removeCar(car);  // Remove from previous lane
	                    }
	                    lane2.insertCar(car);  // Insert into new lane
	                    car.setLane(lane2);
	                }
	            } else if (lane3.isInLane(car)) {
	                if (currentLane != lane3) {
	                    if (currentLane != null) {
	                        currentLane.removeCar(car);  // Remove from previous lane
	                    }
	                    lane3.insertCar(car);  // Insert into new lane
	                    car.setLane(lane3);
	                }
	            }

	            // Autonomous vehicle logic: update their behavior
	            if (car instanceof AV) {
	                AV avCar = (AV) car;
	                avCar.update();  // Autonomous vehicles might do something different here
	            }
	            
	        }



	        // Handle the player's car movement (based on user input)
	       
	        
	        // Optionally, update lanes based on some additional logic or events
	        lane1.update();
	        lane2.update();
	        lane3.update();
	    

		repaint();
		
		
	}

public void UpdateScore() {
	
	for (Car o: others) {
	if (p.hitbox.intersects(o.hitbox)) {
		score-=.1;
		score=(int)(score*10)/10.0;
	}
	}
}
}
