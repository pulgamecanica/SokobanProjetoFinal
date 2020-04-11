package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractSObject implements ElementKey {

	private boolean usedBatery = false;
	
	public Bateria(Point2D Point2D){
		super(Point2D, "Bateria", 1, true);
	}

	@Override
	public boolean canStepOn() {return true;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {return;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return usedBatery;}
	@Override
	public void useTheBatery() {usedBatery = true; setLayer(-1);}
	@Override
	public void activateLinkMode() {setName("Ocarina");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {setName("MarioMushroom");}
	@Override
	public void activateLOTROMode() {setName("TheEYE");}
}