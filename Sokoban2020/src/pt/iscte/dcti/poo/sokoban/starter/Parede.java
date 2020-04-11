package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Parede extends AbstractSObject{

	public Parede(Point2D Point2D){
		super(Point2D, "Parede", 2, false, false, false, true, false);
	}

	@Override
	public void activateLinkMode() {setName("Wall1png");}
	@Override
	public void activateMarioMode() {setName("MarioFloor");}
	@Override
	public void activateLOTROMode() {
		
	}
}
