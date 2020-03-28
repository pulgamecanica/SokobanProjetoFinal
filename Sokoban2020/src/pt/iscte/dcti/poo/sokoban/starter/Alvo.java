package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo implements ImageTile, ElementKey {
	private Point2D Point2D;
	private int level;
	
	public Alvo(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	@Override
	public String getName() {
		return "Alvo";
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
		return true;
	}
	@Override
	public ImageTile getImage() {
		return this;
	}

//	@Override
//	public void setSurfaceUnderneath() {
//
//	}
//	
	@Override
	public void updateElementUP() {
		return;
	}
	@Override
	public void updateElementDOWN() {
		return;
	}
	@Override
	public void updateElementRIGHT() {
		return;
	}
	@Override
	public void updateElementLEFT() {
		return;
	}
	@Override
	public void objectIsOnTheHole() {
		// TODO Auto-generated method stub
		
	}

}
