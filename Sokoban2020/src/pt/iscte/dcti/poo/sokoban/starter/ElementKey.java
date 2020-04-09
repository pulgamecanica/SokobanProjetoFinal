package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;

public interface ElementKey extends ImageTile {
	boolean canMove();
	boolean canStepOn();
	ImageTile getImage();
	boolean canPlayerStepInsideHole();
	boolean usedBatery();
	void useTheBatery();
	void objectIsOnTheHole();
	int level();
	void updateElement(Direction dir);
	boolean isBig();
	void activateLinkMode();
	void activateMarioMode();
	void activateLOTROMode();

}
