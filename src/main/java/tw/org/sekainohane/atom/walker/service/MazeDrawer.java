package tw.org.sekainohane.atom.walker.service;

import org.newdawn.slick.Graphics;

import tw.org.sekainohane.atom.maze.model.Maze;

public interface MazeDrawer {

	public void draw(final Graphics graphics, final Maze maze);
	
}
