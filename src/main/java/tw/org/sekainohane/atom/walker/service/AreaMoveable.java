package tw.org.sekainohane.atom.walker.service;

import java.util.function.Predicate;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Position;

public interface AreaMoveable {
	
	public Predicate<Position> isPosCanGo(AreaType areaType);
	
}
