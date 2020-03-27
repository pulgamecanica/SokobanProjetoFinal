package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Parede implements ImageTile, ElementKey {
	private Point2D Point2D;
	private int level;
	
	public Parede(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	
	@Override
	public String getName() {
		return "Parede";
	}

	@Override
	public Point2D getPosition() {
		return Point2D;
	}

	@Override
	public int getLayer() {
		return level;
	}

	@Override
	public boolean canMove() {
		return false;
	}

	@Override
	public boolean canStepOn() {
		return false;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}

	@Override
	public void updateElement(Point2D point) {
		return;
	}
}
