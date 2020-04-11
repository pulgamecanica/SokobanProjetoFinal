package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Main {

	public static void main(String[] args) {
		createTenByTen();
	}

	public static void createTenByTen() {
		ImageMatrixGUI.setSize(20, 20);
		SokobanGame s = new SokobanGame(0);
		ImageMatrixGUI.getInstance().registerObserver(s);
		ImageMatrixGUI.getInstance().go();
	}

}
