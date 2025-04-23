package mainpackage;

import javax.swing.JFrame;

import objects.Lane;

public class MainClass {
	
	public static final int width = 2560;
	public static final int height = 1600;
	
	public static void main(String[] args) {
	
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Sim SIM = new Sim();
		
		frame.add(SIM);
		
		frame.setVisible(true);
		
	}
}
