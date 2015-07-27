package tw.org.sekainohane.atom.maze.facade;

import java.util.Optional;

import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.maze.service.AreaBuilder;
import tw.org.sekainohane.atom.maze.service.impl.DymanicBuildPositionSupplier;
import tw.org.sekainohane.atom.maze.service.impl.RegularAreaBuilder;
import tw.org.sekainohane.common.progresser.Progress;

public class AreasGenerator {
	
	private final Maze maze;
	
	public AreasGenerator(final Maze maze) {
		this.maze = maze;
	}
	
	public void creatAreasIntoMaze() {
		DymanicBuildPositionSupplier positionSupplier = new DymanicBuildPositionSupplier(maze);
		
		Optional<Position> nextBuild = positionSupplier.get();
		
		Progress progress = new Progress(maze.getWidth() * maze.getLength());
		
		while (nextBuild.isPresent()) {
			Position currentBuild = nextBuild.get();
			
			AreaBuilder areaBuilder = new RegularAreaBuilder(maze, currentBuild);
			
			Area currentArea = areaBuilder.build();
			
//			Log.info("AreaBulding: {}", currentArea);
			progress.treated(1);
			
			maze.getAreas().add(currentArea);
			
			nextBuild = positionSupplier.get();
		}
		progress.done();
		System.out.println("\n");
	}
	
}
