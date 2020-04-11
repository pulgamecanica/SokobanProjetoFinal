package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;

public interface ElementKey extends ImageTile {
	
	boolean canStepOn();
	ImageTile getImage();
	boolean canPlayerStepInsideHole();
	boolean usedBatery();
	void useTheBatery();
	void objectIsOnTheHole();
	boolean isBig();
	void activateLinkMode();
	void activateMarioMode();
	void activateLOTROMode();

}
