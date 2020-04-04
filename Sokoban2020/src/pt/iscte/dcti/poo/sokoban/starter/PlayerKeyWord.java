package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class PlayerKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {return "E";}
	@Override
	public ElementKey createAnElement(Point2D point, int level) {return new Player(point, level);}

}
