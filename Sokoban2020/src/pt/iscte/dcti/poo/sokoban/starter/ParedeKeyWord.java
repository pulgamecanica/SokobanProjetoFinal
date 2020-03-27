package pt.iscte.dcti.poo.sokoban.starter;

public class ParedeKeyWord implements KeyWords {

	@Override
	public String getTheElementKey() {
		return "#";
	}
	
	@Override
	public ElementKey createAnElement(pt.iul.ista.poo.utils.Point2D point, int level) {
		return new Parede(point, level);
	}

}
