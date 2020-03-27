package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {
 	
	private Player player; 
	private ArrayList<ElementKey> elementsInTheMap = new ArrayList<>();
	
	public SokobanGame(){
		
		//ArrayList<ImageTile> tiles = buildSampleLevel(0);
		ArrayList<ImageTile> tiles = buildRealLevel(4);
		tiles.add(player);
		player.setElementsInTheMap(elementsInTheMap);
		ImageMatrixGUI.getInstance().addImages(tiles);
		
	}
	
	private ArrayList<ImageTile> buildSampleLevel(int level){
		
		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
		
		// Build 10x10 floor tiles
		for (int y=0; y!=10; y++)
			for (int x=0; x!=10 ; x++)
				if(y != 1)
					sampleLevelTiles.add(new Chao(new Point2D(x,y), level));
				else
					sampleLevelTiles.add(new Parede(new Point2D(x,y), level));
				
		return sampleLevelTiles;	
	}
	
	//Read all the file and build the level :D
	private ArrayList<ImageTile> buildRealLevel(int level){
		File file = new File("levels/level" + level + ".txt");
		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
		try {
			int row = 0;
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				sampleLevelTiles.addAll(buildLine(scanner.nextLine(), row, level));
				row++;
			}
		}catch (FileNotFoundException e) {
			System.err.println("ficheiro nao encontrado");
		}
		return sampleLevelTiles;	
	}
	
	//Read each line and save objects for each iteration
	private ArrayList<ImageTile> buildLine(String line, int row, int level){
		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
		ArrayList<KeyWords> possibleObjects = getPossibleObjects(level);
		String[] elements = line.split("");
		for (int i = 0; i < elements.length; i++) {
			ElementKey elementKey = new Chao(new Point2D (i, row), level);
			ImageTile imageTile = elementKey;
			sampleLevelTiles.add(imageTile);
			for(KeyWords x: possibleObjects) {
				if (elements[i].equals(x.getTheElementKey())) {
					ElementKey mapElementFounded = x.createAnElement(new Point2D(i, row), level);
					ImageTile mapImageTileFounded = mapElementFounded;
					if (!elements[i].equals("E")) {
						sampleLevelTiles.add(mapImageTileFounded);
						elementsInTheMap.add(mapElementFounded);
					}
					else
						sampleLevelTiles.add(new Chao(new Point2D(i, row), level));
				}
				if (elements[i].equals("E")) {
					player = new Player(new Point2D(i, row), level);
				}
			}
		}
		return sampleLevelTiles;
	}
	
	private ArrayList<KeyWords> getPossibleObjects(int level){
		ArrayList<KeyWords> array = new ArrayList<>();
		array.add(new AlvoKeyWord());
		array.add(new BateriaKeyWord());
		array.add(new BigStoneKeyWord());
		array.add(new BuracoKeyWord());
		array.add(new CaixoteKeyWord());
		array.add(new ChaoKeyWord());
		array.add(new ParedeKeyWord());
		array.add(new PlayerKeyWord());
		array.add(new SmallStoneKeyWord());;
		return array;
	}
	
	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		System.out.println("Key pressed " + lastKeyPressed);
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
		if (lastKeyPressed == KeyEvent.VK_UP || lastKeyPressed == 87) {
			if (player != null) 
				player.moveUp();
			ImageMatrixGUI.getInstance().update();
		}
		else if (lastKeyPressed == KeyEvent.VK_DOWN || lastKeyPressed == 83) {
			if (player != null) 
				player.moveDown();
			ImageMatrixGUI.getInstance().update();
		}	
		else if (lastKeyPressed == KeyEvent.VK_LEFT || lastKeyPressed == 65) {
			if (player != null) 
				player.moveLeft();
			ImageMatrixGUI.getInstance().update();
		}	
		else if (lastKeyPressed == KeyEvent.VK_RIGHT || lastKeyPressed == 68) {
			if (player != null) 
				player.moveRight();
			ImageMatrixGUI.getInstance().update();
		}	
		else if (lastKeyPressed == KeyEvent.VK_ENTER || lastKeyPressed == 68) {
			if (player != null)
				player.move();
			ImageMatrixGUI.getInstance().update();
		}
	}
}