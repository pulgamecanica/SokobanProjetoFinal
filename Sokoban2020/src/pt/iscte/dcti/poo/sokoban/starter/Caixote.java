package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Caixote extends AbstractSObject implements ElementKey {
	private boolean canStepOn = false;
	private boolean canMove = true;
	private Point2D position;
	
	public Caixote(Point2D point2D){
		super(point2D, "Caixote", 3, true);
		position = point2D;
	}
	
	@Override
	public boolean canStepOn() {return canStepOn;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("Ganon");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateLOTROMode() {return;}
	@Override
	public void activateMarioMode() {setName("MarioBox");}
	@Override
	public void objectIsOnTheHole() {
		ImageMatrixGUI.getInstance().removeImage(this);
		canStepOn = true;
		canMove = false;
	}
}
