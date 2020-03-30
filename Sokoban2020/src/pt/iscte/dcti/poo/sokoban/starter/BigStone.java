package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class BigStone implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	private boolean canStepHere = false;
	private String name = "BigStone";

	public BigStone(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Point2D getPosition() {
		return Point2D;
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canStepOn() {
		return canStepHere;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}

	@Override
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);;
		canStepHere = true;
	}

	@Override
	public void updateElementUP(Player p) {
		Point2D = new Point2D(Point2D.getX(), Point2D.getY() - 1);
	}

	@Override
	public void updateElementDOWN(Player p) {
		Point2D = new Point2D(Point2D.getX(), Point2D.getY() + 1);
		
	}

	@Override
	public void updateElementRIGHT(Player p) {
		Point2D = new Point2D(Point2D.getX() + 1, Point2D.getY());
	}

	@Override
	public void updateElementLEFT(Player p) {
		Point2D = new Point2D(Point2D.getX() - 1, Point2D.getY());
		
	}

	@Override
	public int level() {
		return level;
	}

	@Override
	public boolean canPlayerStepInsideHole() {
		return false;
	}

}
