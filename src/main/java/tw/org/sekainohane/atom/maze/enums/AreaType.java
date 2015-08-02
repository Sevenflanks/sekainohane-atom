package tw.org.sekainohane.atom.maze.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum AreaType {
	ROOM_EMPTY(" ", false, false, false, false, "images/areas/ROOM_EMPTY.png"), // for ecipse console
//	ROOM_EMPTY("　", false, false, false, false, "images/areas/ROOM_EMPTY.png"), // for win console
	ROOM_START("█", true, true, true, true, "images/areas/ROOM_START.png"),
	ROOM_GOAL("█", true, true, true, true, "images/areas/ROOM_GOAL.png"),
	ROOM_NORMAL_1("▒", false, false, true, true, "images/areas/ROOM_NORMAL_1.png"), // for ecipse console
//	ROOM_NORMAL_1("□", false, false, true, true, "images/areas/ROOM_NORMAL_1.png"), // for win console
	
	ROAD_H("═", false, false, true, true, "images/areas/ROAD_H.png"),
	ROAD_V("║", true, true, false, false, "images/areas/ROAD_V.png"),
	
	ROAD_1("╚", true, false, false, true, "images/areas/ROAD_1.png"),
	ROAD_2("╩", true, false, true, true, "images/areas/ROAD_2.png"),
	ROAD_3("╝", true, false, true, false, "images/areas/ROAD_3.png"),
	ROAD_4("╠", true, true, false, true, "images/areas/ROAD_4.png"),
	ROAD_5("╬", true, true, true, true, "images/areas/ROAD_5.png"),
	ROAD_6("╣", true, true, true, false, "images/areas/ROAD_6.png"),
	ROAD_7("╔", false, true, false, true, "images/areas/ROAD_7.png"),
	ROAD_8("╦", false, true, true, true, "images/areas/ROAD_8.png"),
	ROAD_9("╗", false, true, true, false, "images/areas/ROAD_9.png"),
	
	WALL_H("╪", false, false, true, true, "images/areas/WALL_H.png"),
	WALL_V("╫", true, true, false, false, "images/areas/WALL_V.png"),
	
	WALL_2("╨", true, false, false, false, "images/areas/WALL_2.png"),
	WALL_4("╞", false, false, false, true, "images/areas/WALL_4.png"),
	WALL_6("╡", false, false, true, false, "images/areas/WALL_6.png"),
	WALL_8("╥", false, true, false, false, "images/areas/WALL_8.png"),
	
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
