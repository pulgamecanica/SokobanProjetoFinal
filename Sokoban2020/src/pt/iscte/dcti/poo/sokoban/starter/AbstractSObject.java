package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class AbstractSObject implements ImageTile {
	private String name;
	private int layer;
	private Point2D position;
	private boolean canMove;
	
	public AbstractSObject(Point2D position, String name, int layer, boolean canMove) {
		this.name  = name;
		this.position = position;
		this.layer = layer;
		this.canMove = canMove;
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
	public void updateElement (Direction dir) {
		Point2D newPosition = position.plus(dir.asVector());
		position = newPosition;
	}
	public boolean canMove() {return canMove;}
	public void setName(String name) {this.name = name;}
	public void setLayer(int layer){this.layer = layer;}
	
	
}
