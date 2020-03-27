package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public class BigStoneKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {
		return "P";
	}

	@Override
	public ElementKey createAnElement(Point2D point, int level) {
		return new BigStone(point, level);
	}
}
