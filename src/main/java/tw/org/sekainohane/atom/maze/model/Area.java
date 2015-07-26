package tw.org.sekainohane.atom.maze.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.org.sekainohane.atom.maze.enums.AreaType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Area {
	
	private AreaType type;

	private Position pos;
	
	public Area(AreaType type, int x, int y) {
		this.type = type;
		this.pos = Position.pos(x, y);
	}
	
	public static Area empty(Position pos) {
		return new Area(AreaType.ROOM_EMPTY, pos);
	}

	@Override
	public String toString() {
		return new StringBuilder().append("[").append(type).append("] ").append(pos).toString();
	}
	
}
