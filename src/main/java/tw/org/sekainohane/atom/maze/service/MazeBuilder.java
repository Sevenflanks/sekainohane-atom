package tw.org.sekainohane.atom.maze.service;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;

public interface MazeBuilder {

	public MazeBuilder setSurface(int width, int length);
	
	public MazeBuilder setStart(int x, int y);
	
	public MazeBuilder setGoal(int x, int y);
	
	public MazeBuilder setAreaTypeRates(Map<AreaType, Integer> rates);
	
	public Maze build();

}
