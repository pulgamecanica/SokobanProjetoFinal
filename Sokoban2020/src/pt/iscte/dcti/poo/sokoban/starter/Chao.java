package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Chao implements ImageTile{
	
	private Point2D position;	
	private String name = "Chao";
	
	public Chao(Point2D Point2D, int style){
		position = Point2D;
		checkStyle(style);
	}
	
	@Override
	public String getName() {return name;}
	@Override
	public Point2D getPosition() {return position;}
	@Override
	public int getLayer() {return 0;}
	//*************************************************************************************************************************************************************//
	public void checkStyle(int style) {
		if(style == 1)
			activateLinkMode();
		else if(style == 2)
			activateMarioMode();
		else if (style == 3)
			activateLOTROMode();
	}
	public void activateLinkMode() {name = "Floor";}
	public void activateMarioMode() {name = "MarioWall";}
	public void activateLOTROMode() {name = "Floor_LOTRO";}


}
