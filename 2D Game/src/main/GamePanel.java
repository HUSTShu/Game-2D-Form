package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // the size of a block is 16x16 
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // the size of the real size is 48x48
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; // the window's width is 16 blocks
	final int screenHeight = tileSize * maxScreenRow; // the window's height is 12 blocks
	
	int FPS = 60; // 60 flames per seconds

	TileManager tileManager = new TileManager(this);
	KeyBoard keyBoard = new KeyBoard();
	Thread gameThread;
	
	Player knight = new Player(this, keyBoard);

	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of this class
		this.setBackground(Color.white); // set the color of background
		this.setDoubleBuffered(true); // set the buffer of the screen
		this.addKeyListener(keyBoard);
		this.setFocusable(true);
		
	}
	
// start the thread of the game
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
			
			// 1. UPDATE
			update();
			
			// 2. DRAW
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; // sleep only accepts milliseconds
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime); // sleep Thread
				
				nextDrawTime += drawInterval; // wake up Thread
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		
		knight.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		tileManager.draw(g2); // Tile is drew before draw player
		
		knight.draw(g2);
		
		g2.dispose(); // release system resources that it is using
		
		
	}
	
}
