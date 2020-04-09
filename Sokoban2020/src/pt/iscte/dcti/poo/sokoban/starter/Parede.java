package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Parede implements ImageTile, ElementKey {
	private Point2D Point2D;
	private int level;
	private String name = "Parede";
	
	public Parede(Point2D Point2D, int level){
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
	public boolean canMove() {return false;}
	@Override
	public boolean canStepOn() {return false;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);;}
	@Override
	public int level() {return level;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {name = "Wall1png";}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {name = "MarioFloor";}
	@Override
	public void updateElement(Direction dir) {return;}
	@Override
	public void activateLOTROMode() {
		// TODO Auto-generated method stub
		
	}

}
