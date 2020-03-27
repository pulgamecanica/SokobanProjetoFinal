package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;

	public BigStone(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	
	@Override
	public String getName() {
		return "BigStone";
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
		return true;
	}

	@Override
	public boolean canStepOn() {
		//BIGSTONEACTIVATED
		return false;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}

	@Override
	public void updateElement(Point2D point) {
		Point2D = point;
	}

}
