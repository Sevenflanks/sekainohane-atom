package tw.org.sekainohane.atom;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameStarter extends BasicGame {

	public GameStarter(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		graphics.drawString("Hello, World!", 100, 100);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "target/classes/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new GameStarter("Hello World"));
        app.setDisplayMode(800, 600, false);
        app.start();
	}
	
}
