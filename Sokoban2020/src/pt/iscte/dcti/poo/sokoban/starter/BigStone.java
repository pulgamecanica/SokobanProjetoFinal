package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;


public class BigStone extends AbstractSObject {

	public BigStone(Point2D Point2D){
		super(Point2D, "BigStone", 3, true, false, false, true, true);
	}

	@Override
	public void activateLinkMode() {setName("TreasureChest1");}
	@Override
	public void activateMarioMode() {setName("MarioBigStone");}
	@Override
	public void activateLOTROMode() {setName("BigStoneLOTRO");}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);cantStepOnAnymore();stayStatic();}
}
