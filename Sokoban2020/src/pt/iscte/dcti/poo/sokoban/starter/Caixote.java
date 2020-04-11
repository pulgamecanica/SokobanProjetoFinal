package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Caixote extends AbstractSObject{
	
	public Caixote(Point2D point2D){
		super(point2D, "Caixote", 3, true, false, false, true, false);
	}
	
	@Override
	public void activateLinkMode() {setName("Ganon");}
	@Override
	public void activateLOTROMode() {return;}
	@Override
	public void activateMarioMode() {setName("MarioBox");}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);cantStepOnAnymore();}
}
