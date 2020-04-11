package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class SmallStone extends AbstractSObject {
	
	public SmallStone(Point2D Point2D){
		super(Point2D, "SmallStone", 2, true, false, false, true, false);
	}
	
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);cantStepOnAnymore();}
	@Override
	public void activateLinkMode() {setName("TreasureChest2");}
	@Override
	public void activateMarioMode() {setName("MarioSmallStone");}
	@Override
	public void activateLOTROMode() {setName("SmallStoneLOTRO");}
	 
}
