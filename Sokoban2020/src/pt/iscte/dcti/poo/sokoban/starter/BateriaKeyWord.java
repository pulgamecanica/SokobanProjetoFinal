package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BateriaKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {return "b";}
	@Override
	public ElementKey createAnElement(Point2D point, int level) {return new Bateria(point, level);}
}
