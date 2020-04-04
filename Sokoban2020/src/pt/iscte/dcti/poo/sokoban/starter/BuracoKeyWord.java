package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BuracoKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {return "O";}

	@Override
	public ElementKey createAnElement(Point2D point, int level) {return new Buraco(point, level);}
}
