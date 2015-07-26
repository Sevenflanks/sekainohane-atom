package tw.org.sekainohane.atom.maze.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import tw.org.sekainohane.atom.maze.enums.AreaType;

import com.google.common.collect.Lists;

@Getter
@EqualsAndHashCode
public class Maze {
	
	private Set<Area> areas = new TreeSet<>(Comparator.<Area>comparingInt(a -> a.getPos().getY()).thenComparingInt(a -> a.getPos().getX()));
	
	@Setter
	private int width; // x向長度
	
	@Setter
	private int length; // y向長度
	
	@Setter
	private Position start; // 起始點
	
	@Setter
	private Map<AreaType, Integer> rates; // 圖塊產生權重
	
	public Optional<Area> getArea(int x, int y) {
		return getArea(Position.pos(x, y));
	}
	
	public Optional<Area> getArea(Position pos) {
		return areas.parallelStream().filter(a -> a.getPos().equals(pos)).findFirst();
	}
	
	public List<Area> getAroundAreas(int x, int y) {
		return getAroundAreas(Position.pos(x, y));
	}
	
	/**
	 * 取得四周的Area(若存在)
	 */
	public List<Area> getAroundAreas(Position pos) {
		return areas.parallelStream().filter(a -> a.getPos().isNearBy(pos)).collect(Collectors.toList());
	}
	
	/**
	 * 取得四周沒有Area的Postiion
	 */
	public List<Position> getAroundBlankPosition(Position pos) {
		List<Position> aroundPositions = 
				Lists.newArrayList(Position.north(pos), Position.south(pos), Position.west(pos), Position.east(pos));
		return aroundPositions.parallelStream().filter(p -> !getArea(p).isPresent()).collect(Collectors.toList());
	}
	
	/**
	 * 取得四周沒有Area並且是本Area可以通行的Position
	 */
	public List<Position> getAroundBlankAndCanGoPosition(Area area) {
		return getAroundBlankPosition(area.getPos()).stream().filter(p -> {
			if (p.isAtNorth(area.getPos())) {
				return area.getType().isNorthCanGo();
			} else if (p.isAtSouth(area.getPos())) {
				return area.getType().isSouthCanGo();
			} else if (p.isAtWest(area.getPos())) {
				return area.getType().isWestCanGo();
			} else if (p.isAtEast(area.getPos())) {
				return area.getType().isEastCanGo();
			} else {
				return false; 
			}
		}).collect(Collectors.toList());
	}
	
	/**
	 * 座標是否在迷宮設定的範圍內
	 */
	public boolean isPosAtMaze(Position pos) {
		return pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < width && pos.getY() < length;
	}
	
	/**
	 * 以AreaType設定的Symbol畫出地圖
	 */
	public String getMapBySymbol() {
		StringBuilder sb = new StringBuilder();
		
		for (int y = length - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				Optional<Area> area = getArea(x, y);
				if (area.isPresent()) {
					sb.append(area.get().getType().getSymbol());
				} else {
					sb.append(AreaType.ROOM_EMPTY);
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void draw() {
		System.out.println(getMapBySymbol());
	}

	@Override
	public String toString() {
		return getMapBySymbol();
	}
	
	
}
