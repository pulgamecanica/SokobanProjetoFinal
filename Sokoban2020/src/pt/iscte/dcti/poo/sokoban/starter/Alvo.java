package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractSObject implements ElementKey {
	
	public Alvo(Point2D Point2D){
		super(Point2D, "Alvo", 1, false);
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
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("Triforce");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {setName("MarioAlvo");}
	@Override
	public void activateLOTROMode() {setName("TheRing");}

}
