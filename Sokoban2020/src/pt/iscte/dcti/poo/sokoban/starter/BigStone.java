package pt.iscte.dcti.poo.sokoban.starter;



import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class BigStone extends AbstractSObject implements ElementKey {

	private boolean canStepHere = false;
	private Point2D position;

	public BigStone(Point2D Point2D){
		super(Point2D, "BigStone", 3, true);
		position = Point2D;
	}
	
	@Override
	public boolean canStepOn() {return canStepHere;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);;canStepHere = true;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("TreasureChest1");}
	@Override
	public boolean isBig() {return true;}
	@Override
	public void activateMarioMode() {setName("MarioBigStone");}
	@Override
	public void activateLOTROMode() {setName("BigStoneLOTRO");}
}
