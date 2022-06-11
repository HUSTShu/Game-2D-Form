package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame(); //  create a new window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // create X to close
		window.setResizable(false); // 
		window.setTitle("2D Adventure"); // create title
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel); // add the GamePanel to the screen
		window.pack(); // show the screen
		
		window.setLocationRelativeTo(null); // put the window in the center
		window.setVisible(true); // show the window
		
		gamePanel.startGameThread();
		
	}
}
