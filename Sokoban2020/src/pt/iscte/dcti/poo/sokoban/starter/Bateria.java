package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Bateria extends AbstractSObject {

	
	public Bateria(Point2D Point2D){
		super(Point2D, "Bateria", 1, false, true, false, false, false);
	}
	
	@Override
	public void activateLinkMode() {setName("Ocarina");}
	@Override
	public void activateMarioMode() {setName("MarioMushroom");}
	@Override
	public void activateLOTROMode() {setName("TheEYE");}
	@Override
	public void useTheBatery() {updateUsedBatery(); setLayer(-1);}
}