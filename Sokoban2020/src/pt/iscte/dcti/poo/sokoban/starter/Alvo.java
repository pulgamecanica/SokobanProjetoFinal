package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo implements ImageTile, ElementKey {
	private Point2D Point2D;
	private int level;
	private String name;
	
	public Alvo(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
		name = "Alvo";
	}
	@Override
	public String getName() {return name;}
	@Override
	public Point2D getPosition() {return Point2D;}
	@Override
	public int getLayer() {return 1;}
	@Override
	public boolean canMove() {return true;}
	@Override
	public boolean canStepOn() {return true;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {return;}
	@Override
	public int level() {return level;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {name = "Triforce";}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {name = "MarioAlvo";}
	@Override
	public void updateElement(Direction dir) {return;}

}
