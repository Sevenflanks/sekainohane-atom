package tw.org.sekainohane.atom;

import java.util.Map;
import java.util.Scanner;

import com.google.common.primitives.Ints;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.service.MazeBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularRateBuilder;
import tw.org.sekainohane.atom.maze.service.impl.RegularMazeBuilder;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("===================");
		System.out.println("Welcome Maze drawer");
		System.out.println("===================");
		System.out.println("");
		System.out.println("　　　　┌───────────────────────────────┐╮");
		System.out.println("　　　　│　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　││");
		System.out.println("　　　　│　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　││");
		System.out.println("　　　　│　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　│Length");
		System.out.println("　　　　│　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　││");
		System.out.println("　　　　│　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　││");
		System.out.println("　　　↗└───────────────────────────────┘╯");
		System.out.println("(0.0) 　╰───────────────Width─────────────╯");
		System.out.println("");
		System.out.println("Please enter the following settings");
		System.out.println("[Map Width]: Number >= 1");
		int width = waitForKeyInInt(scan);
		System.out.println("[Map Length]: Number >= 1");
		int length = waitForKeyInInt(scan);
		
		System.out.println("[StartPoint X]: Number >= 0 & < Width");
		int startX = waitForKeyInInt(scan);
		System.out.println("[StartPoint Y]: Number >= 0 & < Length");
		int startY = waitForKeyInInt(scan);
		
		System.out.println("[GoalPoint]: Has a goal?");
		boolean hasGoal = waitForKeyInYN(scan);
		int goalX = 0, goalY = 0;
		if (hasGoal) {
			System.out.println("[GoalPoint X]: Number >= 0 & < Width");
			goalX = waitForKeyInInt(scan);
			System.out.println("[GoalPoint Y]: Number >= 0 & < Length");
			goalY = waitForKeyInInt(scan);
		}
		
		System.out.println("[Rate]: Use default rate");
		boolean useDefaultRate = waitForKeyInYN(scan);
		
		Map<AreaType, Integer> rates = null;
		if (useDefaultRate) {
			rates = new RegularRateBuilder()
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
		} else {
			RegularRateBuilder rateBuilder = new RegularRateBuilder();
			AreaType.toList().stream()
				.filter(at -> !at.equals(AreaType.ROOM_START) || !at.equals(AreaType.ROOM_EMPTY) || !at.equals(AreaType.ROOM_GOAL))
				.forEach(at -> {
					System.out.println("[Rate]-[" + at.getSymbol() + "]: number");
					rateBuilder.setRate(at, waitForKeyInInt(scan));
				});
			rates = rateBuilder.build();
		}
		
		System.out.println("[Builder]: All setting has done, press enter to start...");
		scan.hasNextLine();
		scan.close();
		
		MazeBuilder mazeBuilder = new RegularMazeBuilder();
		mazeBuilder.setSurface(width, length).setStart(startX, startY).setAreaTypeRates(rates);
		if (hasGoal) {
			mazeBuilder.setGoal(goalX, goalY);
		}
		Maze maze = mazeBuilder.build();
		maze.draw();
	}
	
	private static int waitForKeyInInt(Scanner scan) {
		int result = -999;
		while (scan.hasNextLine()) {
			try {
				result = Ints.tryParse(scan.nextLine());
			} catch (Exception e) {
				System.out.println("Must key in a Number");
			}
			if (result != -999) {
				break;
			}
		}
		return result;
	}
	
	private static boolean waitForKeyInYN(Scanner scan) {
		String result = null;
		while (scan.hasNextLine()) {
			result = scan.nextLine();
			if (!result.toUpperCase().equals("Y") && !result.toUpperCase().equals("N")) {
				System.out.println("Must key in a Y/N");
			} else {
				break;
			}
		}
		return result.toUpperCase().equals("Y");
	}
	
}
