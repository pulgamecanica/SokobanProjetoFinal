package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;


	public SmallStone(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	@Override
	public String getName() {
		return "SmallStone";
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
		return false;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}


	@Override
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementUP() {
		Point2D = new Point2D(Point2D.getX(), Point2D.getY() - 1);
	}

	@Override
	public void updateElementDOWN() {
		Point2D = new Point2D(Point2D.getX(), Point2D.getY() + 1);
		
	}

	@Override
	public void updateElementRIGHT() {
		Point2D = new Point2D(Point2D.getX() + 1, Point2D.getY());
	}

	@Override
	public void updateElementLEFT() {
		Point2D = new Point2D(Point2D.getX() - 1, Point2D.getY());
		
	}


}
