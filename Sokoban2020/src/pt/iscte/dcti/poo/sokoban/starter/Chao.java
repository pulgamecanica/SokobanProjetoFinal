package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Chao extends AbstractSObject implements ElementKey {
	
	public Chao(Point2D Point2D){
		super(Point2D, "Chao", 0, false);
	}

	@Override
	public boolean canStepOn() {return true;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {ImageMatrixGUI.getInstance().removeImage(this);}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public void activateLinkMode() {setName("Floor");}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void activateMarioMode() {setName("MarioWall");}
	@Override
	public void activateLOTROMode() {setName("Floor_LOTRO");}


}
