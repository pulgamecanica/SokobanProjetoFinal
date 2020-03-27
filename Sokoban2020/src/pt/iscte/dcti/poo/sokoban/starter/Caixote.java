package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Caixote implements ElementKey, ImageTile {
	private Point2D Point2D;
	private int level;
	
	
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
	public void updatePosition(Point2D point) {
		Point2D = point;
	}
	
	@Override
	public void updateElement(Point2D point) {
		System.out.println("Im here");
			Point2D = point;
	}

}