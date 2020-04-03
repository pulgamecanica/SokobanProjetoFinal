package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class SmallStone implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	private boolean canStepHere = false;
	private String name = "SmallStone";


	public SmallStone(Point2D Point2D, int level){
		this.Point2D = Point2D;
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
		return 2;
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean canStepOn() {
		return canStepHere;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}
	
	@Override
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);;
		canStepHere = true;
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
		name = "TreasureChest2";
	}
	@Override
	public boolean isBig() {
		return false;
	}
}
