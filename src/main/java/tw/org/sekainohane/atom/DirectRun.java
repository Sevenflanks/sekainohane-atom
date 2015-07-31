package tw.org.sekainohane.atom;

import java.util.Map;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularRateBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;

public class DirectRun {
	public static void main(String[] args) {
		Map<AreaType, Integer> rates = new RegularRateBuilder()
				.setRate(AreaType.ROAD_1, 5)
				.setRate(AreaType.ROAD_2, 5)
				.setRate(AreaType.ROAD_3, 5)
				.setRate(AreaType.ROAD_4, 5)
				.setRate(AreaType.ROAD_5, 2)
				.setRate(AreaType.ROAD_6, 5)
				.setRate(AreaType.ROAD_7, 5)
				.setRate(AreaType.ROAD_8, 5)
				.setRate(AreaType.ROAD_9, 5)
				
				.setRate(AreaType.ROAD_H, 30)
				.setRate(AreaType.ROAD_V, 15)
				
				.setRate(AreaType.WALL_2, 1)
				.setRate(AreaType.WALL_4, 1)
				.setRate(AreaType.WALL_6, 1)
				.setRate(AreaType.WALL_8, 1)
				
				.setRate(AreaType.WALL_H, 0)
				.setRate(AreaType.WALL_V, 0)
				
				.setRate(AreaType.ROOM_NORMAL_1, 1)

				.build();

		MazeBuilder mazeBuilder = new RegularMazeBuilder();
		Maze maze = mazeBuilder
				.setSurface(100, 15)
				.setStart(0, 7)
				.setGoal(99, 7)
				.setAreaTypeRates(rates)
				.build();
		maze.draw();
	}
}
