package tw.org.sekainohane.atom.maze.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
/**
 * 採數學坐標系, ++為第一象限, -+為第二, --為第三, +-為第四
 * 地圖上只存在第一象限
 */
public class Position {

	private int x;
	
	private int y;
	
	public static Position pos(int x, int y) {
		return new Position(x, y);
	}
	
	public static Position north(Position pos) {
		return new Position(pos.x, pos.y + 1);
	}
	
	public static Position south(Position pos) {
		return new Position(pos.x, pos.y - 1);
	}
	
	public static Position west(Position pos) {
		return new Position(pos.x - 1, pos.y);
	}
	
	public static Position east(Position pos) {
		return new Position(pos.x + 1, pos.y);
	}
	
	// 判定方位處, 因為目前還沒有八方向判定, 因此斜角部分採模糊判定(可以同時吻合兩個)
	// 若有八方向判定, 則需要將>=改為>
	
	public boolean isAtNorth(Position other) {
		return abs(y - other.y) >= abs(x - other.x) && y > other.y;
	}
	
	public boolean isAtSouth(Position other) {
		return abs(y - other.y) >= abs(x - other.x) && y < other.y;
	}
	
	public boolean isAtWest(Position other) {
		return abs(x - other.x) >= abs(y - other.y) && x < other.x;
	}
	
	public boolean isAtEast(Position other) {
		return abs(x - other.x) >= abs(y - other.y) && x > other.x;
	}
	
	/**
	 * 距離只有1
	 */
	public boolean isNearBy(Position other) {
		return (abs(x - other.x) == 1 && abs(y - other.y) == 0) || (abs(x - other.x) == 0 && abs(y - other.y) == 1);
	}
	
	private int abs (int input) {
		return Math.abs(input);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("(").append(x).append(",").append(y).append(")").toString();
	}
	
}
