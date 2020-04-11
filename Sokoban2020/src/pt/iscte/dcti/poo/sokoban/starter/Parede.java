package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractSObject implements ElementKey {

	
	public Parede(Point2D Point2D){
		super(Point2D, "Parede", 2, true);
	}
	
	@Override
	public boolean canStepOn() {return false;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("Wall1png");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {setName("MarioFloor");}
	@Override
	public void activateLOTROMode() {
		
	}

}
