package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class CaixoteKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {
		return "C";
	}
	@Override
	public ElementKey createAnElement(Point2D point, int level) {
		return new Caixote(point, level);
	}
}
