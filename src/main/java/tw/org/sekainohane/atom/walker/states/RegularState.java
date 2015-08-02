package tw.org.sekainohane.atom.walker.states;

import java.util.EnumMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularRateBuilder;
import tw.org.sekainohane.atom.walker.service.AreaMoveable;
import tw.org.sekainohane.atom.walker.service.MazeDrawer;
import tw.org.sekainohane.atom.walker.service.impl.AreaMoveableV1;
import tw.org.sekainohane.atom.walker.service.impl.MazeRangeDrawer;

public class RegularState extends BasicGameState implements InputProviderListener {
	
	private InputProvider provider;
	private Command walkNorth = new BasicCommand("walkNorth");
	private Command walkSouth = new BasicCommand("walkSouth");
	private Command walkWest = new BasicCommand("walkWest");
	private Command walkEast = new BasicCommand("walkEast");
	private Command godMode = new BasicCommand("godMode");
	private Command runMode = new BasicCommand("runMode");
	
	private boolean isWalkingNorth;
	private boolean isWalkingSouth;
	private boolean isWalkingWest;
	private boolean isWalkingEast;
	private boolean isRunMode;
	private boolean isGodMode;
	
	private final Map<AreaType, Image> areaTypeImgs = new EnumMap<>(AreaType.class);

	private MazeDrawer mazeDrawer;
	private AreaMoveable areaMoveable;
	
	private Maze maze;
	
	private boolean isMazeBuilded;
	
	private int actualPlayerX;
	private int actualPlayerY;
	private int expectPlayerX;
	private int expectPlayerY;
	private AreaType expectAreaType;
	
	private String message = "";
	
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

		mazeDrawer = new MazeRangeDrawer(areaTypeImgs, Position.pos(0, 9), 2, 1);
		areaMoveable = new AreaMoveableV1();
		actualPlayerX = mazeDrawer.getActualPlayerX();
		actualPlayerY = mazeDrawer.getActualPlayerY();
		expectPlayerX = actualPlayerX;
		expectPlayerY = actualPlayerY;

		provider = new InputProvider(gameContainer.getInput());
		provider.addListener(this);
		provider.bindCommand(new KeyControl(Input.KEY_UP), walkNorth);
		provider.bindCommand(new KeyControl(Input.KEY_DOWN), walkSouth);
		provider.bindCommand(new KeyControl(Input.KEY_LEFT), walkWest);
		provider.bindCommand(new KeyControl(Input.KEY_RIGHT), walkEast);
		provider.bindCommand(new KeyControl(Input.KEY_W), walkNorth);
		provider.bindCommand(new KeyControl(Input.KEY_S), walkSouth);
		provider.bindCommand(new KeyControl(Input.KEY_A), walkWest);
		provider.bindCommand(new KeyControl(Input.KEY_D), walkEast);
		provider.bindCommand(new KeyControl(Input.KEY_LCONTROL), godMode);
		provider.bindCommand(new KeyControl(Input.KEY_LSHIFT), runMode);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		graphics.setColor(Color.gray);
		if (isMazeBuilded) {
			mazeDrawer.draw(graphics, maze);
		} else {
			graphics.drawString("WaitForLoading", 670, 350);
		}
		graphics.drawString(message, 100, 150);
		graphics.drawString("PlayerX: " + actualPlayerX, 100, 170);
		graphics.drawString("PlayerY: " + actualPlayerY, 100, 190);
		graphics.drawString("ShowX: " + (actualPlayerX % 500 - 250), 100, 210);
		graphics.drawString("ShowY: " + (actualPlayerY % 500 - 250), 100, 230);
		graphics.drawString("GoX: " + (expectPlayerX % 500 - 250), 100, 250);
		graphics.drawString("GoY: " + (expectPlayerY % 500 - 250), 100, 270);
		graphics.drawString("ExpectArea: " + expectAreaType, 100, 290);
		
		graphics.setColor(Color.red);
		graphics.drawOval(640, 350, 10, 10);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int arg2) throws SlickException {
		if (!isMazeBuilded) {
			maze = makeMaze();
			maze.draw();
			isMazeBuilded = true;
		}
		
		expectPlayerX = actualPlayerX;
		expectPlayerY = actualPlayerY;
		if (isRunMode) {
			if (isWalkingNorth) {
				expectPlayerY = actualPlayerY - 5;
			}
			if (isWalkingSouth) {
				expectPlayerY = actualPlayerY + 5;
			}
			if (isWalkingWest) {
				expectPlayerX = actualPlayerX - 5;
			}
			if (isWalkingEast) {
				expectPlayerX = actualPlayerX + 5;
			}
		} else {
			if (isWalkingNorth) {
				expectPlayerY = actualPlayerY - 1;
			}
			if (isWalkingSouth) {
				expectPlayerY = actualPlayerY + 1;
			}
			if (isWalkingWest) {
				expectPlayerX = actualPlayerX - 1;
			}
			if (isWalkingEast) {
				expectPlayerX = actualPlayerX + 1;
			}
		}
		
		if (!isGodMode) {
			expectAreaType = mazeDrawer.getExpectAreaType(maze, expectPlayerX, actualPlayerY);
			if (areaMoveable.isPosCanGo(expectAreaType).test(Position.pos(expectPlayerX % 500 - 250, actualPlayerY % 500 - 250))) {
				actualPlayerX = expectPlayerX;
			}
			
			expectAreaType = mazeDrawer.getExpectAreaType(maze, actualPlayerX, expectPlayerY);
			if (areaMoveable.isPosCanGo(expectAreaType).test(Position.pos(actualPlayerX % 500 - 250, expectPlayerY % 500 - 250))) {
				actualPlayerY = expectPlayerY;
			}
		} else {
			actualPlayerX = expectPlayerX;
			actualPlayerY = expectPlayerY;
		}
		
		mazeDrawer.updatePlayer(actualPlayerX, actualPlayerY);
	}

	@Override
	public int getID() {
		return 3;
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
		return mazeBuilder.setSurface(50, 20).setStart(0, 9)
				.setGoal(49, 9).setAreaTypeRates(rates).build();
	}

	@Override
	public void controlPressed(Command command) {
		message = "Pressed: " + command;
		if (command.equals(walkNorth)) {
			isWalkingNorth = true;
		} else if (command.equals(walkSouth)) {
			isWalkingSouth = true;
		} else if (command.equals(walkWest)) {
			isWalkingWest = true;
		} else if (command.equals(walkEast)) {
			isWalkingEast = true;
		} else if (command.equals(godMode)) {
			isGodMode = true;
		} else if (command.equals(runMode)) {
			isRunMode = true;
		}
	}

	@Override
	public void controlReleased(Command command) {
		message = "Released: " + command;
		if (command.equals(walkNorth)) {
			isWalkingNorth = false;
		} else if (command.equals(walkSouth)) {
			isWalkingSouth = false;
		} else if (command.equals(walkWest)) {
			isWalkingWest = false;
		} else if (command.equals(walkEast)) {
			isWalkingEast = false;
		} else if (command.equals(godMode)) {
			isGodMode = false;
		} else if (command.equals(runMode)) {
			isRunMode = false;
		}
	}
	
}
