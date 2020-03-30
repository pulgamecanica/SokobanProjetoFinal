package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
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
		return 2;
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
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementUP(Player p) {
		
	}

	@Override
	public void updateElementDOWN(Player p) {
		
	}

	@Override
	public void updateElementRIGHT(Player p) {
		
	}

	@Override
	public void updateElementLEFT(Player p) {

	}

	@Override
	public int level() {
		return level;
	}

}
