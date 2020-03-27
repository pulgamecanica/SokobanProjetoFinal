package pt.iscte.dcti.poo.sokoban.starter;

public class ChaoKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {
		return " ";
	}
	
	@Override
	public ElementKey createAnElement(pt.iul.ista.poo.utils.Point2D point, int level) {
		return new Chao(point, level);
	}

}
