package tw.org.sekainohane.atom.maze.service.impl;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;

public class RegularMazeBuilder implements MazeBuilder {

	Maze maze = new Maze();
	
	@Override
	public MazeBuilder setSurface(int width, int length) {
		maze.setWidth(width);
		maze.setLength(length);
		return this;
	}

	@Override
	public MazeBuilder setStart(int x, int y) {
		maze.setStart(Position.pos(x, y));
		return this;
	}

	@Override
	public MazeBuilder setAreaTypeRates(Map<AreaType, Integer> rates) {
		maze.setRates(rates);
		return this;
	}

	@Override
	public Maze build() {
		setStaticPosition();
		AreasGenerator areasGenerator = new AreasGenerator(maze);
		areasGenerator.creatAreasIntoMaze();
		return maze;
	}
	
	private void setStaticPosition() {
		maze.getAreas().add(new Area(AreaType.ROOM_START, maze.getStart()));
	}

}
