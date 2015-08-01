package tw.org.sekainohane.atom.walker.service.impl;

import java.util.Map;

import lombok.Getter;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import tw.org.sekainohane.atom.maze.enums.AreaType;
import tw.org.sekainohane.atom.maze.model.Area;
import tw.org.sekainohane.atom.maze.model.Maze;
import tw.org.sekainohane.atom.maze.model.Position;
import tw.org.sekainohane.atom.walker.service.MazeDrawer;

public class MazeRangeDrawer implements MazeDrawer {
	
	private static final int SCREEN_WIDTH = 1280;
	private static final int SCREEN_LENGHT = 700;
	
	private static final int AREA_WIDTH = 500;
	private static final int AREA_LENGHT = 500;
	
	private Map<AreaType, Image> areaTypeImgs;
	
	@Getter
	private int actualPlayerX;
	@Getter
	private int actualPlayerY;
	
	private int showX;
	private int showY;
	
	private final int DRAW_RANGE_X;
	private final int DRAW_RANGE_Y;
	private final int SCREEN_CENTER_X;
	private final int SCREEN_CENTER_Y;

	public MazeRangeDrawer(Map<AreaType, Image> areaTypeImgs, Position startPos, int drawRangex, int drawRangeY) {
		// 算出起始點實際位置(某個位置的圖的中心點)
		this.actualPlayerX = (startPos.getX() * AREA_WIDTH) + (AREA_WIDTH / 2);
		this.actualPlayerY = (startPos.getY() * AREA_LENGHT) + (AREA_LENGHT / 2);
		this.SCREEN_CENTER_X = SCREEN_WIDTH / 2;
		this.SCREEN_CENTER_Y = SCREEN_LENGHT /2;
		this.DRAW_RANGE_X = drawRangex;
		this.DRAW_RANGE_Y = drawRangeY;
		this.areaTypeImgs = areaTypeImgs;
	}
	
	@Override
	public MazeRangeDrawer updatePlayer(int x, int y) {
		this.actualPlayerX = x;
		this.actualPlayerY = y;
		this.showX = x % AREA_WIDTH - AREA_WIDTH / 2;
		this.showY = y % AREA_LENGHT - AREA_LENGHT / 2;
		return this;
	}
	
	@Override
	public void draw(Graphics graphics, Maze maze) {
		// 計算最左下角的點 從左下角掃上來
		// 畫面中心 -> 取得畫面中心的圖的左上角 -> 取的左下角的點
		int showX = (SCREEN_CENTER_X - AREA_WIDTH / 2) - (AREA_WIDTH * DRAW_RANGE_X);
		int showY = (SCREEN_CENTER_Y - AREA_LENGHT / 2) - (AREA_LENGHT * DRAW_RANGE_Y);
		AreaType[][] areas = needToView(maze);
		
		for (int x = 0; x < areas.length; x++) {
			for (int y = areas[x].length - 1; y >= 0; y--) {
				graphics.drawImage(areaTypeImgs.get(areas[x][y]), showX - this.showX, showY + y * AREA_LENGHT - this.showY);
			}
			showX += AREA_WIDTH;
		}
		
		
//		needToView(maze).forEach(a -> {
//			graphics.drawImage(areaTypeImgs.get(a.getType()), AREA_WIDTH * a.getPos().getX(), AREA_LENGHT * Math.abs(maze.getLenght() - 1 - a.getPos().getY()));
//		});
		graphics.flush();
	}
	
	private AreaType[][] needToView(Maze maze) {
		AreaType[][] mappedAreas = new AreaType[DRAW_RANGE_X*2+1][DRAW_RANGE_Y*2+1];
		int playerPosX = actualPlayerX / 500;
		int playerPosY = actualPlayerY / 500;
//		List<Area> areas = maze.getAreas().parallelStream().filter(a -> {
//			return a.getPos().getX() >= playerPosX - DRAW_RANGE_X && a.getPos().getX() <= playerPosX + DRAW_RANGE_X
//					&& a.getPos().getY() >= playerPosY - DRAW_RANGE_Y && a.getPos().getY() <= playerPosY + DRAW_RANGE_Y;
//		})
//		.collect(Collectors.toList());
		 
		for (int x = -DRAW_RANGE_X; x <= DRAW_RANGE_X ; x++) {
			for (int y = -DRAW_RANGE_Y; y <= DRAW_RANGE_Y ; y++) {
				mappedAreas[x + DRAW_RANGE_X][y + DRAW_RANGE_Y] = maze.getArea(playerPosX + x, playerPosY + y).orElse(Area.empty(Position.pos(playerPosX + x, playerPosY + y))).getType();
			}
		}
		 
		return mappedAreas;
	}
	
}
