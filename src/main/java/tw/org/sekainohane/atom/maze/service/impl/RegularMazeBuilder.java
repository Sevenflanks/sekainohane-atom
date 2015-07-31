package tw.org.sekainohane.atom.maze.service.impl;

import java.util.Map;
import java.util.Objects;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.exception.MazeBuildException;
import tw.org.sekainohane.atom.maze.facade.AreasGenerator;
import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;

public class RegularMazeBuilder implements MazeBuilder {

	Maze maze = new Maze();
	
	@Override
	public MazeBuilder setSurface(int width, int length) {
		maze.setWidth(width);
		maze.setLenght(length);
		return this;
	}

	@Override
	public MazeBuilder setStart(int x, int y) {
		maze.setStart(Position.pos(x, y));
		return this;
	}
	
	@Override
	public MazeBuilder setGoal(int x, int y) {
		maze.setGoal(Position.pos(x, y));
		return this;
	}

	@Override
	public MazeBuilder setAreaTypeRates(Map<AreaType, Integer> rates) {
		maze.setRates(rates);
		return this;
	}

	@Override
	public Maze build() {
		check();
		setStaticPosition();
		AreasGenerator areasGenerator = new AreasGenerator(maze);
		areasGenerator.creatAreasIntoMaze();
		return maze;
	}
	
	private void check() {
		if (maze.getWidth() <= 0) {
			throw new MazeBuildException("Width must > 0");
		}
		if (maze.getLenght() <= 0) {
			throw new MazeBuildException("Length must > 0");
		}
		if (Objects.isNull(maze.getStart())) {
			throw new MazeBuildException("Start position is require");
		}
		if (Objects.isNull(maze.getRates())) {
			throw new MazeBuildException("Rate position is require");
		}
	}
	
	private void setStaticPosition() {
		maze.getAreas().add(new Area(AreaType.ROOM_START, maze.getStart()));
		if (Objects.nonNull(maze.getGoal())) {
			maze.getAreas().add(new Area(AreaType.ROOM_GOAL, maze.getGoal()));
		}
	}

}
