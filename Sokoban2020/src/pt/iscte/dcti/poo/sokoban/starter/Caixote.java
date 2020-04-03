package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Caixote implements ElementKey, ImageTile {
	private Point2D Point2D;
	private int level;
	private boolean canStepOn = false;
	private boolean canMove = true;
	private String name = "Caixote";
	
	
	public Caixote(Point2D point2D, int level){
		this.Point2D = point2D;
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
	public void updateElementUP(Player p) {
		Point2D = Point2D.plus(new Vector2D(0,-1));;
	}

	@Override
	public void updateElementDOWN(Player p) {
		Point2D = Point2D.plus(new Vector2D(0,1));;
		
	}

	@Override
	public void updateElementRIGHT(Player p) {
		Point2D = Point2D.plus(new Vector2D(1,0));;
	}

	@Override
	public void updateElementLEFT(Player p) {
		Point2D = Point2D.plus(new Vector2D(-1, 0));;
		
	}
	@Override
	public int level() {
		return level;
	}
	@Override
	public boolean canPlayerStepInsideHole() {
		return false;
	}
	@Override
	public boolean usedBatery() {
		return true;
	}
	@Override
	public void useTheBatery() {
	
	}
	@Override
	public void activateLinkMode() {
		name = "Ganon";
	}
	@Override
	public boolean isBig() {
		// TODO Auto-generated method stub
		return false;
	}


}
