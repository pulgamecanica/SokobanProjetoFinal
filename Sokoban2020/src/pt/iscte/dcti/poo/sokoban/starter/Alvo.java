package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Alvo extends AbstractSObject {
	
	public Alvo(Point2D Point2D){
		super(Point2D, "Alvo", 1, false, true, false, true, false);
	}
	
	@Override
	public void activateLinkMode() {setName("Triforce");}
	@Override
	public void activateMarioMode() {setName("MarioAlvo");}
	@Override
	public void activateLOTROMode() {setName("TheRing");}

}
