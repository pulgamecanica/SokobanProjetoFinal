package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public interface ElementKey extends ImageTile {
	boolean canMove();
	boolean canStepOn();
	ImageTile getImage();
	void updateElement(Point2D point);
}