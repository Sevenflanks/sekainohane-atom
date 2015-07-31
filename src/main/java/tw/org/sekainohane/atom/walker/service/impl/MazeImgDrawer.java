package tw.org.sekainohane.atom.walker.service.impl;

import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.walker.service.MazeDrawer;

public class MazeImgDrawer implements MazeDrawer {
	
	private static final int MARGIN_TOP = 100;
	
	private static final int MARGIN_LEFT = 100;
	
	private static final int AREA_WIDTH = 10;
	
	private static final int AREA_LENGHT = 10;
	
	private Map<AreaType, Image> areaTypeImgs;
	
	public MazeImgDrawer(Map<AreaType, Image> areaTypeImgs) {
		this.areaTypeImgs = areaTypeImgs;
	}

	@Override
	public void draw(Graphics graphics, Maze maze) {
		maze.getAreas().stream().forEach(a -> {
			graphics.drawImage(areaTypeImgs.get(a.getType()), MARGIN_TOP + AREA_WIDTH * a.getPos().getX(), MARGIN_LEFT + AREA_LENGHT * Math.abs(maze.getLenght() - 1 - a.getPos().getY()));
		});
		graphics.flush();
	}

}
