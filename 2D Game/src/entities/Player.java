package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyBoard;

public class Player extends Entity {
	
	GamePanel gp;
	KeyBoard keyBoard;
	
	public Player(GamePanel gp, KeyBoard keyBoard) {
		this.gp = gp;
		this.keyBoard = keyBoard;
		
		setDefaultValues();
		try {
			getPlayerImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction = 2;	
	}
	
	public void setDefaultValues() {
		
		x = 100;
		y = 100;
		speed = 4;
		
	}
	
	public void getPlayerImage() throws IOException {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyBoard.upPressed == true || keyBoard.downPressed == true ||
				keyBoard.leftPressed == true || keyBoard.rightPressed == true)
		{
		
		if(keyBoard.upPressed == true) {
			direction = 1;
			y -= speed;
		}
		if(keyBoard.downPressed == true) {
			direction = 2;
			y += speed;
		}
		if(keyBoard.leftPressed == true) {
			direction = 3;
			x -= speed;
		}
		if(keyBoard.rightPressed == true) {
			direction = 4;
			x += speed;
		}
		
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNumber == 1) {
				spriteNumber = 2;
			}
			else if (spriteNumber == 2) {
				spriteNumber = 1;
			}
			spriteCounter = 0;
		}
		
		}
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch (direction) {
		case 1: {
			if(spriteNumber == 1) {
				image = up1;
			}
			if(spriteNumber == 2) {
				image = up2;
			}
			
			break;
		}
		case 2: {
			if(spriteNumber == 1) {
				image = down1;
			}
			if(spriteNumber == 2) {
				image = down2;
			}
			
			break;
		}
		case 3: {
			if(spriteNumber == 1) {
				image = left1;
			}
			if(spriteNumber == 2) {
				image = left2;
			}
			
			break;
		}
		case 4: {
			if(spriteNumber == 1) {
				image = right1;
			}
			if(spriteNumber == 2) {
				image = right2;
			}
			
			break;
		}
		
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
