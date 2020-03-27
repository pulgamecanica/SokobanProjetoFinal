package pt.iscte.dcti.poo.sokoban.starter;

public class SmallStoneKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {
		return "p";
	}
	
	@Override
	public ElementKey createAnElement(pt.iul.ista.poo.utils.Point2D point, int level) {
		return new SmallStone(point, level);
	}

}
