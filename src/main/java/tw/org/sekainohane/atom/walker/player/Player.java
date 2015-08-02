package tw.org.sekainohane.atom.walker.player;

import lombok.Getter;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;

public class Player {
	@Getter
	private int width = 32;
	
	private XMLPackedSheet player;
	
	private Image[] downImages;
	private Image[] leftImages;
	private Image[] rightImages;
	private Image[] upImages;
	
	private Animation up;
	private Animation down;
	private Animation left;
	private Animation right;
	
	private int[] during = new int[]{60,60,60,60};
			
	public Player(String fileName) {
		 try {
			this.player = new XMLPackedSheet("images/actors/" + fileName + ".png", "images/actors/actor.xml");
			this.downImages = new Image[]{player.getSprite("actor_down_1.png"), player.getSprite("actor_down_2.png"), player.getSprite("actor_down_3.png"), player.getSprite("actor_down_2.png")};
			this.leftImages = new Image[]{player.getSprite("actor_left_1.png"), player.getSprite("actor_left_2.png"), player.getSprite("actor_left_3.png"), player.getSprite("actor_left_2.png")};
			this.rightImages = new Image[]{player.getSprite("actor_right_1.png"), player.getSprite("actor_right_2.png"), player.getSprite("actor_right_3.png"), player.getSprite("actor_right_2.png")};
			this.upImages = new Image[]{player.getSprite("actor_up_1.png"), player.getSprite("actor_up_2.png"), player.getSprite("actor_up_3.png"), player.getSprite("actor_up_2.png")};
			
			this.up = new Animation(upImages, during);
			this.down = new Animation(downImages, during);
			this.left = new Animation(leftImages, during);
			this.right = new Animation(rightImages, during);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Image faceUp() {
		return upImages[1];
	}
	
	public Image faceDown() {
		return downImages[1];
	}
	
	public Image faceLeft() {
		return leftImages[1];
	}
	
	public Image faceRight() {
		return rightImages[1];
	}
	
	public Animation moveUp() {
		return up;
	}
	
	public Animation moveDown() {
		return down;
	}
	
	public Animation moveLeft() {
		return left;
	}
	
	public Animation moveRight() {
		return right;
	}
}
