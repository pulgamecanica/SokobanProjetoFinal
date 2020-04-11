package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractSObject implements ElementKey{
	private String name;
	private int layer;
	private Point2D position;
	private boolean canMove;
	private boolean canStepOn;
	private boolean canPlayerStepInsideHole;
	private boolean  usedBatery;
	private boolean isBig;
	
	public AbstractSObject(Point2D position, String name, int layer, boolean canMove, boolean canStepOn, boolean canPlayerStepInsideHole, boolean usedBatery, boolean isBig) {
		this.name  = name;
		this.position = position;
		this.layer = layer;
		this.canMove = canMove;
		this.canStepOn = canStepOn;
		this.canPlayerStepInsideHole = canPlayerStepInsideHole;
		this.usedBatery = usedBatery;
		this.isBig = isBig;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Point2D getPosition() {
		return position;
	}
	@Override
	public int getLayer() {
		return layer;
	}
	@Override
	public boolean canStepOn() {
		return canStepOn;
	}
	@Override
	public boolean canPlayerStepInsideHole() {
		return canPlayerStepInsideHole;
	}
	public void objectIsOnTheHole() {
		return;
	}
	@Override
	public void useTheBatery() {
		return;
	}
	@Override
	public boolean usedBatery() {
		return  usedBatery;
	}	
	@Override
	public boolean isBig() {
		return isBig;
	}
	
	public void updateElement (Direction dir) {Point2D newPosition = position.plus(dir.asVector());position = newPosition;}
	public boolean canMove() {return canMove;}
	public void setName(String name) {this.name = name;}
	public void setLayer(int layer){this.layer = layer;}
	public void stayStatic(){canMove = false;}
	public void cantStepOnAnymore() {canStepOn = true;}
	public void stepInsideHole() {canPlayerStepInsideHole = true;}
	public void updateUsedBatery() {usedBatery = true;}
	
}
