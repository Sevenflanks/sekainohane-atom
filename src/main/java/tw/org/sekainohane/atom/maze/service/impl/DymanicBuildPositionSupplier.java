package tw.org.sekainohane.atom.maze.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;

public class DymanicBuildPositionSupplier implements Supplier<Optional<Position>> {

	private final Maze maze;
	
	public DymanicBuildPositionSupplier(final Maze maze) {
		this.maze = Objects.requireNonNull(maze);
	}
	
	@Override
	public Optional<Position> get() {
		return maze.getAreas().parallelStream()
				.map(a -> maze.getAroundBlankAndCanGoPosition(a))
				.flatMap(List::stream)
				.findAny();
	}

}
