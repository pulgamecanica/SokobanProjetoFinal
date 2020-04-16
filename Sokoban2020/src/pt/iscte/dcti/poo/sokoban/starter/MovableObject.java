package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;

public interface MovableObject extends ImageTile {

	boolean canPlayerStepInsideHole();
	boolean isBig();
}
