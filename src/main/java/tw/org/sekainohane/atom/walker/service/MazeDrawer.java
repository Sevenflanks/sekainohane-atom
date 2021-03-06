package tw.org.sekainohane.atom.walker.service;

import org.newdawn.slick.Graphics;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;

public interface MazeDrawer {

	public void draw(final Graphics graphics, final Maze maze);

	public MazeDrawer updatePlayer(int x, int y);
	
	public int getActualPlayerX();
	
	public int getActualPlayerY();
	
	public AreaType getCurrentAreaType();
	public AreaType getExpectAreaType(Maze maze, int x, int y);
	
}
