package pt.iscte.dcti.poo.sokoban.starter;



import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class BigStone implements ElementKey, ImageTile {

	private Point2D Point2D;
	private int level;
	private boolean canStepHere = false;
	private String name = "BigStone";

	public BigStone(Point2D Point2D, int level){
		this.Point2D = Point2D;
		this.level = level;
	}
	
	@Override
	public String getName() {return name;}
	@Override
	public Point2D getPosition() {return Point2D;}
	@Override
	public int getLayer() {return 3;}
	@Override
	public boolean canMove() {return !canStepHere;}
	@Override
	public boolean canStepOn() {return canStepHere;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);;canStepHere = true;}
	@Override
	public int level() {return level;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {name = "TreasureChest1";}
	@Override
	public boolean isBig() {return true;}
	@Override
	public void activateMarioMode() {name = "MarioBigStone";}
	@Override
	public void updateElement(Direction dir) {Point2D = Point2D.plus(dir.asVector());}
}
