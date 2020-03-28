package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Caixote implements ElementKey, ImageTile {
	private Point2D Point2D;
	private int level;
	private boolean canStepOn = false;
	private boolean canMove = true;
	
	
	public Caixote(Point2D point2D, int level){
		this.Point2D = point2D;
		this.level = level;
	}
	@Override
	public String getName() {
		return "Caixote";
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
		return canMove;
	}

	@Override
	public boolean canStepOn() {
		return canStepOn;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}

	@Override
	public void objectIsOnTheHole() {
		System.out.println("Im HERE :D Caixote");
		ImageMatrixGUI.getInstance().removeImage(this);
		canStepOn = true;
		canMove = false;
	}
	@Override
	public void updateElementUP() {
		Point2D p = new Point2D(Point2D.getX(), Point2D.getY() - 1);
		Point2D = p;
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
