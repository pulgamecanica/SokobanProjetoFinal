package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageTile;

public interface ElementKey extends ImageTile {
	
	boolean canStepOn();
	boolean canPlayerStepInsideHole();
	boolean usedBatery();
	void useTheBatery();
	void objectIsOnTheHole();
	boolean isBig();
	//OTHER
	void activateLinkMode();
	void activateMarioMode();
	void activateLOTROMode();

}
