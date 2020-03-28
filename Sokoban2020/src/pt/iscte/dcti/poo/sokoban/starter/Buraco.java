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
		return level;
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

	public void restartLevel() {
		ImageMatrixGUI.getInstance().clearImages();
		ImageMatrixGUI.setSize(10, 10);
		SokobanGame s = new SokobanGame(level);
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();
	}
	
	@Override
	public void objectIsOnTheHole() {
		System.out.println("Im HERE :D Buraco");
		
	}
	@Override
	public void updateElementUP() {
		if (bigStoneInside) 
			restartLevel();
	}
	@Override
	public void updateElementDOWN() {
		if (bigStoneInside) 
			restartLevel();
	}
	@Override
	public void updateElementRIGHT() {
		if (bigStoneInside) 
			restartLevel();
	}
	@Override
	public void updateElementLEFT() {
		if (bigStoneInside) 
			restartLevel();
	}

}
