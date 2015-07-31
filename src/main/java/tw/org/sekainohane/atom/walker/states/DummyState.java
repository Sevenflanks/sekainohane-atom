package tw.org.sekainohane.atom.walker.states;

import java.util.EnumMap;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularRateBuilder;
import tw.org.sekainohane.atom.walker.service.MazeDrawer;
import tw.org.sekainohane.atom.walker.service.impl.MazeImgDrawer;

public class DummyState extends BasicGameState {

	private final Map<AreaType, Image> areaTypeImgs = new EnumMap<>(AreaType.class);

	private MazeDrawer mazeDrawer;
	
	private Maze maze;
	
	private boolean isMazeBuilded;

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		AreaType.toList()
				.forEach(at -> {
					try {
						areaTypeImgs.put(at, new Image(ResourceLoader.getResourceAsStream(at.getPath()), at.toString(), false));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		mazeDrawer = new MazeImgDrawer(areaTypeImgs);

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {		
		if (isMazeBuilded) {
			mazeDrawer.draw(graphics, maze);
		} else {
			graphics.drawString("WaitForLoading", 200, 200);
		}
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
		if (!isMazeBuilded) {
			maze = makeMaze();
			isMazeBuilded = true;
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	private Maze makeMaze() {
		Map<AreaType, Integer> rates = new RegularRateBuilder()
				.setRate(AreaType.ROAD_1, 5).setRate(AreaType.ROAD_2, 5)
				.setRate(AreaType.ROAD_3, 5).setRate(AreaType.ROAD_4, 5)
				.setRate(AreaType.ROAD_5, 2).setRate(AreaType.ROAD_6, 5)
				.setRate(AreaType.ROAD_7, 5).setRate(AreaType.ROAD_8, 5)
				.setRate(AreaType.ROAD_9, 5)

				.setRate(AreaType.ROAD_H, 30).setRate(AreaType.ROAD_V, 15)

				.setRate(AreaType.WALL_2, 1).setRate(AreaType.WALL_4, 1)
				.setRate(AreaType.WALL_6, 1).setRate(AreaType.WALL_8, 1)

				.setRate(AreaType.WALL_H, 1).setRate(AreaType.WALL_V, 1)

				.setRate(AreaType.ROOM_NORMAL_1, 1)

				.build();

		MazeBuilder mazeBuilder = new RegularMazeBuilder();
		return mazeBuilder.setSurface(100, 20).setStart(0, 9)
				.setGoal(99, 9).setAreaTypeRates(rates).build();
	}

}
