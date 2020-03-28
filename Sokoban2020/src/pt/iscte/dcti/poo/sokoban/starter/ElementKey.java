package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public interface ElementKey extends ImageTile {
	boolean canMove();
	boolean canStepOn();
	ImageTile getImage();
	void updateElementUP();
	void updateElementDOWN();
	void updateElementRIGHT();
	void updateElementLEFT();
	//void setSurfaceUnderneath();
	void objectIsOnTheHole();
}
