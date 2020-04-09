package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	private boolean bigStoneInside = false;
	private String name = "Buraco";
	
	public Buraco(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	
	@Override
	public String getName() {return name;}
	@Override
	public Point2D getPosition() {return Point2D;}
	@Override
	public int getLayer() {return 2;}
	@Override
	public boolean canMove() {return true;}
	@Override
	public boolean canStepOn() {return true;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() { return;}
	@Override
	public void activateLOTROMode() {return;}
	@Override
	public void activateLinkMode() {return;}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {return;}
	@Override
	public void updateElement(Direction dir) {return;}
	@Override
	public int level() {return level;}
	@Override
	public void objectIsOnTheHole() {
		System.out.println("Im HERE :D Buraco");
		bigStoneInside = true;
		name = "BigStoneBuraco";
	}
	@Override
	public boolean canPlayerStepInsideHole() {
		if (bigStoneInside)
			return true;
		return false;
	}
}
