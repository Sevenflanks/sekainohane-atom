package tw.org.sekainohane.atom.maze.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum AreaType {
	ROOM_EMPTY(" ", false, false, false, false, "images/maze/ROOM_EMPTY.png"), // for ecipse console
//	ROOM_EMPTY("　", false, false, false, false, "images/maze/ROOM_EMPTY.png"), // for win console
	ROOM_START("█", true, true, true, true, "images/maze/ROOM_START.png"),
	ROOM_GOAL("█", true, true, true, true, "images/maze/ROOM_GOAL.png"),
	ROOM_NORMAL_1("▒", false, false, true, true, "images/maze/ROOM_NORMAL_1.png"), // for ecipse console
//	ROOM_NORMAL_1("□", false, false, true, true, "images/maze/LOGO.png"), // for win console
	
	ROAD_H("═", false, false, true, true, "images/maze/ROAD_H.png"),
	ROAD_V("║", true, true, false, false, "images/maze/ROAD_V.png"),
	
	ROAD_1("╚", true, false, false, true, "images/maze/ROAD_1.png"),
	ROAD_2("╩", true, false, true, true, "images/maze/ROAD_2.png"),
	ROAD_3("╝", true, false, true, false, "images/maze/ROAD_3.png"),
	ROAD_4("╠", true, true, false, true, "images/maze/ROAD_4.png"),
	ROAD_5("╬", true, true, true, true, "images/maze/ROAD_5.png"),
	ROAD_6("╣", true, true, true, false, "images/maze/ROAD_6.png"),
	ROAD_7("╔", false, true, false, true, "images/maze/ROAD_7.png"),
	ROAD_8("╦", false, true, true, true, "images/maze/ROAD_8.png"),
	ROAD_9("╗", false, true, true, false, "images/maze/ROAD_9.png"),
	
	WALL_H("╪", false, false, true, true, "images/maze/WALL_H.png"),
	WALL_V("╫", true, true, false, false, "images/maze/WALL_V.png"),
	
	WALL_2("╨", true, false, false, false, "images/maze/WALL_2.png"),
	WALL_4("╞", false, false, false, true, "images/maze/WALL_4.png"),
	WALL_6("╡", false, false, true, false, "images/maze/WALL_6.png"),
	WALL_8("╥", false, true, false, false, "images/maze/WALL_8.png"),
	
	;
	
	
	private String symbol;
	private boolean northCanGo;
	private boolean southCanGo;
	private boolean westCanGo;
	private boolean eastCanGo;
	private String path;
	
	AreaType(String symbol, boolean northCanGo, boolean southCanGo, boolean westCanGo, boolean eastCanGo, String path) {
		this.symbol = symbol;
		this.northCanGo = northCanGo;
		this.southCanGo = southCanGo;
		this.westCanGo = westCanGo;
		this.eastCanGo = eastCanGo;
		this.path = path;
	}

	public static List<AreaType> toList() {
		return Arrays.asList(AreaType.values());
	}

	
}
