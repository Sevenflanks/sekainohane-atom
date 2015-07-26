package tw.org.sekainohane.atom;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RateBuilderBySetter;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;
import junit.framework.TestCase;
public class AppTest extends TestCase {

	public void testApp() {
		
		Map<AreaType, Integer> rates = new RateBuilderBySetter()
		.setRate(AreaType.ROAD_1, 5)
		.setRate(AreaType.ROAD_2, 5)
		.setRate(AreaType.ROAD_3, 5)
		.setRate(AreaType.ROAD_4, 5)
		.setRate(AreaType.ROAD_5, 2)
		.setRate(AreaType.ROAD_6, 5)
		.setRate(AreaType.ROAD_7, 5)
		.setRate(AreaType.ROAD_8, 5)
		.setRate(AreaType.ROAD_9, 5)
		
		.setRate(AreaType.ROAD_H, 15)
		.setRate(AreaType.ROAD_V, 15)
		
		.setRate(AreaType.WALL_2, 2)
		.setRate(AreaType.WALL_4, 2)
		.setRate(AreaType.WALL_6, 2)
		.setRate(AreaType.WALL_8, 2)
		
		.setRate(AreaType.WALL_H, 1)
		.setRate(AreaType.WALL_V, 1)
		
		.setRate(AreaType.ROOM_NORMAL_1, 1)
		
		.build();
		
		MazeBuilder MazeBuilder = new RegularMazeBuilder();
		Maze maze = MazeBuilder.setSurface(50, 10).setStart(0, 4).setAreaTypeRates(rates).build();
		maze.draw();
	}
}
