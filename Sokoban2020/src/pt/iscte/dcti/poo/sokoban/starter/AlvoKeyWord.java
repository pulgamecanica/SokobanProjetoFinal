package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class AlvoKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {return "X";}
	@Override
	public ElementKey createAnElement(Point2D point, int level) {return new Alvo(point, level);}

}