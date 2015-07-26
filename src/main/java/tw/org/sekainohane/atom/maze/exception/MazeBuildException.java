package tw.org.sekainohane.atom.maze.exception;

import lombok.Getter;

public class MazeBuildException extends RuntimeException {
	
	private static final long serialVersionUID = 2842308601415141512L;
	
	@Getter
	private String message;
	
	public MazeBuildException (String message) {
		this.message = message;
	}
	
}
