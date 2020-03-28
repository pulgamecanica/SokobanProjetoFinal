package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	
	public Bateria(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	@Override
	public String getName() {
		return "Bateria";
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
//		ImageMatrixGUI.getInstance().removeImage(this);;
//	}
	
	@Override
	public void updateElementUP() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementDOWN() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementRIGHT() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementLEFT() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void objectIsOnTheHole() {
		// TODO Auto-generated method stub
		
	}

}
