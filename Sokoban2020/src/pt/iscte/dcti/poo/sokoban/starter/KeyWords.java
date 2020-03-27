package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.utils.Point2D;

public interface KeyWords {
	String getTheElementKey();
	ElementKey createAnElement(Point2D point, int level);
}
