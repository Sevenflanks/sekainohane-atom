package tw.org.sekainohane.atom.maze.service.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.maze.service.AreaBuilder;

import com.google.common.collect.Lists;

public class RegularAreaBuilder implements AreaBuilder {

	private final Maze maze;
	
	private Position build;
	
	public RegularAreaBuilder(final Maze maze) {
		this.maze = Objects.requireNonNull(maze);
	}
	
	@Override
	public RegularAreaBuilder setBuildPosition(final Position build) {
		this.build = build;
		return this;
	}
	
	@Override
	public Area build() {
		List<AreaType> canUseTypes = Lists.newArrayList();
		maze.getRates().entrySet().stream()
			.filter(r -> r.getValue() > 0)
			.filter(r -> maze.getAroundAreas(build).parallelStream().allMatch(isAreaTypeCanUse(r.getKey())))
			.filter(isAreaGoAtMaze())
			.forEach(r -> {
				for (int i = 0; i < r.getValue(); i++) {
					canUseTypes.add(r.getKey());
				}
			});
		
		if (canUseTypes.isEmpty()) {
			return Area.empty(build);
		}
		
		AreaType chooseType = getRandomAreaType(canUseTypes);
		
		return new Area(chooseType, build);
	}
	
	private AreaType getRandomAreaType(List<AreaType> types) {
		int whichToGet = (int)Math.floor(Math.random() * ((double)types.size()));
		return types.get(whichToGet);
	}
	
	/**
	 * 可以用的圖塊型態 1.可互相通行  2.不互相通行
	 */
	private Predicate<Area> isAreaTypeCanUse(AreaType areaType) {
		return new Predicate<Area>() {
			@Override
			public boolean test(Area a) {
				if (a.getPos().isNearBy(build)) {
					if (a.getPos().isAtNorth(build)) {
						return (a.getType().isSouthCanGo() && areaType.isNorthCanGo())
								|| (!a.getType().isSouthCanGo() && !areaType.isNorthCanGo());
						
					} else if (a.getPos().isAtSouth(build)) {
						return (a.getType().isNorthCanGo() && areaType.isSouthCanGo()) 
								|| (!a.getType().isNorthCanGo() && !areaType.isSouthCanGo());
						
					} else if (a.getPos().isAtWest(build)) {
						return (a.getType().isEastCanGo() && areaType.isWestCanGo())
								|| (!a.getType().isEastCanGo() && !areaType.isWestCanGo());
						
					} else if (a.getPos().isAtEast(build)) {
						return a.getType().isWestCanGo() && areaType.isEastCanGo()
								|| (!a.getType().isWestCanGo() && !areaType.isEastCanGo());
					}
				}
				return false;
			}
		};
	}
	
	/**
	 * 預計使用的圖塊, 期可通行方向有任何一面會通往地圖外
	 */
	private Predicate<Entry<AreaType, Integer>> isAreaGoAtMaze() {
		return new Predicate<Entry<AreaType, Integer>>() {
			@Override
			public boolean test(Entry<AreaType, Integer> r) {
				boolean isAtMaze = true;
				
				if (r.getKey().isNorthCanGo()) {
					isAtMaze &= maze.isPosAtMaze(Position.north(build));
				}
					
				if (r.getKey().isSouthCanGo()) {
					isAtMaze &= maze.isPosAtMaze(Position.south(build));
				}
				
				if (r.getKey().isWestCanGo()) {
					isAtMaze &= maze.isPosAtMaze(Position.west(build));
				}
				
				if (r.getKey().isEastCanGo()) {
					isAtMaze &= maze.isPosAtMaze(Position.east(build));
				}
				
				return isAtMaze;
			}
		};
	}
	
}
