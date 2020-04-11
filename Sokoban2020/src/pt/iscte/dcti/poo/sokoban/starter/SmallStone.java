package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractSObject implements ElementKey {

	private boolean canStepHere = false;
	private Point2D position;

	public SmallStone(Point2D Point2D){
		super(Point2D, "SmallStone", 2, true);
		position = Point2D;
	}
	

	@Override
	public boolean canStepOn() {return canStepHere;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);canStepHere = true;}

	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("TreasureChest2");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {setName("MarioSmallStone");}
	@Override
	public void activateLOTROMode() {setName("SmallStoneLOTRO");}
	 
}
