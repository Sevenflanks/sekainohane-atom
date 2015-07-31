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
	private final DymanicBuildPositionSupplier positionSupplier;
	private final AreaBuilder areaBuilder;
	
	public AreasGenerator(final Maze maze) {
		this.maze = maze;
		this.positionSupplier = new DymanicBuildPositionSupplier(maze);
		this.areaBuilder = new RegularAreaBuilder(maze);
	}
	
	public void creatAreasIntoMaze() {
		
		
		Optional<Position> nextBuild = positionSupplier.get();
		
		Progress progress = new Progress(maze.getWidth() * maze.getLenght());
		
		while (nextBuild.isPresent()) {
			Position currentBuild = nextBuild.get();
			
			areaBuilder.setBuildPosition(currentBuild);
			
			Area currentArea = areaBuilder.build();
			
			progress.treated(1);
			
			maze.getAreas().add(currentArea);
			
			nextBuild = positionSupplier.get();
		}
		progress.done();
		System.out.println("\n");
	}
	
}
