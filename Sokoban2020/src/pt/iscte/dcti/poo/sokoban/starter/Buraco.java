package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	private boolean bigStoneInside = false;
	
	public Buraco(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	@Override
	public String getName() {
		return "Buraco";
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
		if(bigStoneInside)
			return true;
		return true;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}

	
	@Override
	public void objectIsOnTheHole() {
		System.out.println("Im HERE :D Buraco");
		
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
