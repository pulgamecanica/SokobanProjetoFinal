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
		return 1;
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
	
	
	@Override
	public void updateElementUP(Player p) {
		ImageMatrixGUI.getInstance().removeImage(this);
		p.gotTheBatrty();
	}
	@Override
	public void updateElementDOWN(Player p) {
		ImageMatrixGUI.getInstance().removeImage(this);
		p.gotTheBatrty();
	}
	@Override
	public void updateElementRIGHT(Player p) {
		ImageMatrixGUI.getInstance().removeImage(this);
		p.gotTheBatrty();
	}
	@Override
	public void updateElementLEFT(Player p) {
		ImageMatrixGUI.getInstance().removeImage(this);
		p.gotTheBatrty();
	}
	@Override
	public void objectIsOnTheHole() {
		return;
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
