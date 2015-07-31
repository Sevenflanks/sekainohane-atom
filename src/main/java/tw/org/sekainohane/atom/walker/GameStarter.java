package tw.org.sekainohane.atom.walker;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tw.org.sekainohane.atom.walker.states.DummyState;

public class GameStarter extends StateBasedGame {

	public GameStarter(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "target/classes/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		AppGameContainer app = new AppGameContainer(new GameStarter("Hello World"));
        app.setDisplayMode(1280, 700, false);
        app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new DummyState());
	}
	
}
