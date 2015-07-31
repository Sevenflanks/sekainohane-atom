package tw.org.sekainohane.atom.maze.service;

import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Position;

public interface AreaBuilder {
	
	public Area build();

	public AreaBuilder setBuildPosition(final Position build);
	
}
