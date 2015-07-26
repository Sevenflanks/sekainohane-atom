package tw.org.sekainohane.atom;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RateBuilderBySetter;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
		Map<AreaType, Integer> rates = new RateBuilderBySetter()
			.setRate(AreaType.ROAD_1, 3)
			.setRate(AreaType.ROAD_2, 2)
			.setRate(AreaType.ROAD_3, 1)
			.setRate(AreaType.ROAD_4, 2)
			.setRate(AreaType.ROAD_5, 1)
			.setRate(AreaType.ROAD_6, 1)
			.setRate(AreaType.ROAD_7, 3)
			.setRate(AreaType.ROAD_8, 2)
			.setRate(AreaType.ROAD_9, 1)
			
			.setRate(AreaType.ROAD_H, 2)
			.setRate(AreaType.ROAD_V, 10)
			
			.setRate(AreaType.WALL_2, 1)
			.setRate(AreaType.WALL_4, 1)
			.setRate(AreaType.WALL_6, 1)
			.setRate(AreaType.WALL_8, 1)
			
			.setRate(AreaType.WALL_H, 0)
			.setRate(AreaType.WALL_V, 0)
			
			.setRate(AreaType.ROOM_NORMAL_1, 1)
			
			.build();
		
		MazeBuilder MazeBuilder = new RegularMazeBuilder();
		Maze maze = MazeBuilder.setSurface(40, 5).setStart(0, 2).setAreaTypeRates(rates).build();
		maze.draw();
	}
}
