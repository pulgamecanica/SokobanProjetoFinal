package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;

public interface ElementKey extends ImageTile {
	boolean canMove();
	boolean canStepOn();
	ImageTile getImage();
	void updateElementUP(Player p);
	void updateElementDOWN(Player p);
	void updateElementRIGHT(Player p);
	void updateElementLEFT(Player p);
	//void setSurfaceUnderneath();
	void objectIsOnTheHole();
	int level();
	boolean canPlayerStepInsideHole();
}
