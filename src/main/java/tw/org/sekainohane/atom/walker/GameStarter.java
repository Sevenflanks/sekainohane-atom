package tw.org.sekainohane.atom.walker;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import tw.org.sekainohane.atom.walker.states.DummyState;

/**
 * To export runnable jar, first disable System.setProperty("org.lwjgl.librarypath"...
 * then run maven build -> compile assembly:single, ignore test
 * @author Sevenflanks
 */
public class GameStarter extends StateBasedGame {

	public GameStarter(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException {
		// For Eclipse Run, !MUST BE! disable when export jar
		System.setProperty("org.lwjgl.librarypath", ClassLoader.getSystemResource("natives").getPath());
		AppGameContainer app = new AppGameContainer(new GameStarter("Hello World"));
        app.setDisplayMode(1280, 700, false);
        app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new DummyState());
	}
	
}
