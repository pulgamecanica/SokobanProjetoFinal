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
 	//Attributes	
	private Player player; 
	
	private ArrayList<ElementKey> elementsInTheMap = new ArrayList<>();
	
	private ArrayList<Point2D> alvos = new ArrayList<>(); 
	
	private ArrayList<ImageTile> caixas = new ArrayList<>(); 
	
	private int level;
	
	private BestScores bS;
	
	//Start the game Add all components
	public SokobanGame(int level){
		this.level = level;
		//ArrayList<ImageTile> tiles = buildSampleLevel(0);
		ArrayList<ImageTile> tiles = buildRealLevel(level);
		tiles.add(player);
		player.setElementsInTheMap(elementsInTheMap);
		player.addGame(this);
		ImageMatrixGUI.getInstance().addImages(tiles);
		for (ImageTile x: tiles)
			if(x.getName().equals("Alvo")) {
				System.out.println("Founded a" + x.getName() +  "at" + " " + x.getPosition().getX() + " " + x.getPosition().getY());
				alvos.add(x.getPosition());
			}
		for (ImageTile x: tiles)
			if(x.getName().equals("Caixote")) {
				System.out.println("Founded a" + x.getName() +  "at" + " " + x.getPosition().getX() + " " + x.getPosition().getY());
				caixas.add(x);
			}
		bS = new BestScores(level);
		bS.searchFile();
		System.out.println(bS.getTopOne());
		messages();
		//SET SCORES
	}
	//Verify If the level is completed
	private void checkAlvosAndCaixas () {
		int counter = 0;
		for (Point2D x: alvos)
			for(ImageTile y: caixas) {
				if(x.equals(y.getPosition())) {
					counter++;
					System.out.println("One Caixa Done! :D");
				}
			}
		if (counter == alvos.size()) {
			bS.setBestScore(player.movesDone());
			bS.createOrAddScore();
			advnceToNextLevel(level + 1);
		}
	}
	//Messages
	private void messages() {
		ImageMatrixGUI.getInstance().setStatusMessage("Level: " + (level+1) + " Moves: " + player.movesDone() + " Energy: "+ player.getMoves() + " BestScore" + (bS.getTopOne()==0? "NULL":bS.getTopOne()) +  " ----------->>>>'r'-->RESTART 'n'-->NEXTLEVEL 'b'-->BACK 'l'-->QUITGAME<<<<----------");
		ImageMatrixGUI.getInstance().setName("Level: "+ level);
	}
	
	//Start a level from scratch
	public void advnceToNextLevel(int levelToStart) {
		elementsInTheMap.clear();
		alvos.clear();
		caixas.clear();
		player = null;
		ImageMatrixGUI.getInstance().clearImages();
		ImageMatrixGUI.getInstance().unregisterObserver(this);
		ImageMatrixGUI.getInstance().registerObserver(new SokobanGame(levelToStart));
		ImageMatrixGUI.getInstance().update();
		ImageMatrixGUI.getInstance().go();
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
			scanner.close();
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
	//List with all the objects that can Exist in the game :D
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
	private void checkForBuraco() {
		for(ElementKey x: elementsInTheMap)
			if(player.getPosition().equals(x.getPosition()) && x.getName().equals("Buraco") && !x.canPlayerStepInsideHole()) {
				advnceToNextLevel(level);
				return;
			}
	}
	@Override
	public void update(Observed arg0) {
		messages();
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		System.out.println("Key pressed " + lastKeyPressed);
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
		if (lastKeyPressed == KeyEvent.VK_UP || lastKeyPressed == 87) {
			if (player != null) 
				player.moveUp();
		}
		else if (lastKeyPressed == KeyEvent.VK_DOWN || lastKeyPressed == 83) {
			if (player != null) 
				player.moveDown();
		}	
		else if (lastKeyPressed == KeyEvent.VK_LEFT || lastKeyPressed == 65) {
			if (player != null) 
				player.moveLeft();
		}	
		else if (lastKeyPressed == KeyEvent.VK_RIGHT || lastKeyPressed == 68) {
			if (player != null) 
				player.moveRight();
		}	
		else if (lastKeyPressed == KeyEvent.VK_ENTER) {
			if (player != null)
				player.move();
		}
		// "n" next level
		else if (lastKeyPressed == 78) {
			if (player != null)
				advnceToNextLevel(level + 1);
		}
		// "r" restart level
		else if (lastKeyPressed == 82) {
			if (player != null)
				advnceToNextLevel(level);
		}
		else if (lastKeyPressed == 76) {
			if (player != null)
				ImageMatrixGUI.getInstance().dispose();
		}
		else if (lastKeyPressed == 66) {
			if (player != null)
				advnceToNextLevel(level - 1);
		}
		if(player != null) {
			ImageMatrixGUI.getInstance().update();
			checkAlvosAndCaixas();
			checkForBuraco();
		}	
//		else if (lastKeyPressed == KeyEvent.VK_ENTER || lastKeyPressed == 68) {
//			if (player != null)
//				player.move();
//			ImageMatrixGUI.getInstance().update();
//		}
//////		checkAlvosAndCaixas();
//		int counter = 0;
//		for(ImageTile x: elementsInTheMap) {
//			counter++;
//			System.out.println(counter + " This is: " + x.getName() + " At the position X: " + x.getPosition().getX() + ", Y: " + x.getPosition().getY());
//		}
	}

}
