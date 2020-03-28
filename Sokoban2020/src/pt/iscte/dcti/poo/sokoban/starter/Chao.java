package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Chao implements ImageTile, ElementKey {

	private Point2D Point2D;
	private int level;
	
	public Chao(Point2D Point2D, int level){
		this.Point2D = Point2D;
	}
	
	@Override
	public String getName() {
		return "Chao";
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
		return false;
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
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);;
	}
	@Override
	public void updateElementUP() {
		
	}

	@Override
	public void updateElementDOWN() {
		
	}

	@Override
	public void updateElementRIGHT() {
		
	}

	@Override
	public void updateElementLEFT() {
	
	}



}
