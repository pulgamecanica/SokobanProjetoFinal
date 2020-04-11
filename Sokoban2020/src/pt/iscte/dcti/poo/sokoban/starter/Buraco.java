package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Buraco extends AbstractSObject {

	public Buraco(Point2D Point2D){
		super(Point2D, "Buraco", 2, false, true, false, true, false);
	}
	
	@Override
	public void activateLOTROMode() {return;}
	@Override
	public void activateLinkMode() {return;}
	@Override
	public void activateMarioMode() {return;}
	@Override
	public void objectIsOnTheHole() {stepInsideHole();setName("BigStoneBuraco");
	}
}
