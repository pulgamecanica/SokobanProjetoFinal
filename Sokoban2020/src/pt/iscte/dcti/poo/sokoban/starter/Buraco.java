package pt.iscte.dcti.poo.sokoban.starter;


import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractSObject implements ElementKey {

	private boolean bigStoneInside = false;

	public Buraco(Point2D Point2D){
		super(Point2D, "Buraco", 2, false);
	}
	

	@Override
	public boolean canStepOn() {return true;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() { return;}
	@Override
	public void activateLOTROMode() {return;}
	@Override
	public void activateLinkMode() {return;}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {return;}
	@Override
	public void objectIsOnTheHole() {bigStoneInside = true;setName("BigStoneBuraco");
	}
	@Override
	public boolean canPlayerStepInsideHole() {
		if (bigStoneInside)	return true;
		return false;
	}
}
