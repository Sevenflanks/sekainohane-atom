package tw.org.sekainohane.atom.maze.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum AreaType {
	ROOM_EMPTY("　", false, false, false, false),
	ROOM_START("█", true, true, true, true),
	ROOM_GOAL("█", true, true, true, true),
	ROOM_NORMAL_1("□", false, false, true, true),
	
	ROAD_H("═", false, false, true, true),
	ROAD_V("║", true, true, false, false),
	
	ROAD_1("╚", true, false, false, true),
	ROAD_2("╩", true, false, true, true),
	ROAD_3("╝", true, false, true, false),
	ROAD_4("╠", true, true, false, true),
	ROAD_5("╬", true, true, true, true),
	ROAD_6("╣", true, true, true, false),
	ROAD_7("╔", false, true, false, true),
	ROAD_8("╦", false, true, true, true),
	ROAD_9("╗", false, true, true, false),
	
	WALL_H("╪", false, false, true, true),
	WALL_V("╫", true, true, false, false),
	
	WALL_2("╨", true, false, false, false),
	WALL_4("╞", false, false, false, true),
	WALL_6("╡", false, false, true, false),
	WALL_8("╥", false, true, false, false),
	
	;
	
	
	private String symbol;
	private boolean northCanGo;
	private boolean southCanGo;
	private boolean westCanGo;
	private boolean eastCanGo;
	
	AreaType(String symbol, boolean northCanGo, boolean southCanGo, boolean westCanGo, boolean eastCanGo) {
		this.symbol = symbol;
		this.northCanGo = northCanGo;
		this.southCanGo = southCanGo;
		this.westCanGo = westCanGo;
		this.eastCanGo = eastCanGo;
	}

	@Override
	public String toString() {
		return this.symbol;
	}
	
	public static List<AreaType> toList() {
		return Arrays.asList(AreaType.values());
	}

	
}
