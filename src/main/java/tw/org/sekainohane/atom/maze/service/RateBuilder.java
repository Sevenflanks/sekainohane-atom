package tw.org.sekainohane.atom.maze.service;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;

public interface RateBuilder {
	
	public RateBuilder setRate(AreaType areaType, int rate);
	
	public Map<AreaType, Integer> build();
	
}
