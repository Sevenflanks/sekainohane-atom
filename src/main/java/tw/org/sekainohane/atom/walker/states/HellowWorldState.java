package tw.org.sekainohane.atom.walker.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HellowWorldState extends BasicGameState {

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame)
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {
		graphics.drawString("WaitForProcessing", 200, 200);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return 2;
	}

}
