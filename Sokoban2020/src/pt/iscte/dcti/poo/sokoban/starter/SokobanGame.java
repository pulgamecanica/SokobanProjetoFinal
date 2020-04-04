package pt.iscte.dcti.poo.sokoban.starter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
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
			if(x.getName().equals("Alvo")) 
				alvos.add(x.getPosition());
			else if(x.getName().equals("Caixote")) 
				caixas.add(x);
			
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
			bS.setBestScore(player.movesDone() - 1);
			bS.createOrAddScore();
			advnceToNextLevel(level + 1);
		}
	}
	//Messages
	private void messages() {
		ImageMatrixGUI.getInstance().setStatusMessage("LEVEL: " + (level+1) + " ** MOVES: " + player.movesDone() + " ** ENERGY: "+ player.getMoves() + " ** BestScore: " + (bS.getTopOne()==0? "NULL":bS.getTopOne()) +  " ** >>>>'r'-->RESTART - 'n'-->NEXTLEVEL - 'b'-->BACK - 'l'-->QUITGAME ** - 'h'??? - 'm'??? <<<<");
		ImageMatrixGUI.getInstance().setName("Level: "+ (level+1));
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
//	private ArrayList<ImageTile> buildSampleLevel(int level){
//		
//		ArrayList<ImageTile> sampleLevelTiles = new ArrayList<ImageTile>();
//		
//		// Build 10x10 floor tiles
//		for (int y=0; y!=10; y++)
//			for (int x=0; x!=10 ; x++)
//				if(y != 1)
//					sampleLevelTiles.add(new Chao(new Point2D(x,y), level));
//				else
//					sampleLevelTiles.add(new Parede(new Point2D(x,y), level));
//				
//		return sampleLevelTiles;	
//	}
//	
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
			elementsInTheMap.add(elementKey);
			sampleLevelTiles.add(imageTile);
			for(KeyWords x: possibleObjects) {
				if (elements[i].equals(x.getTheElementKey())) {
					ElementKey mapElementFounded = x.createAnElement(new Point2D(i, row), level);
					ImageTile mapImageTileFounded = mapElementFounded;
					if (!elements[i].equals("E")) {
						sampleLevelTiles.add(mapImageTileFounded);
						elementsInTheMap.add(mapElementFounded);
					}
					else {
						ElementKey elementKey_a = new Chao(new Point2D (i, row), level);
						sampleLevelTiles.add(elementKey_a);
						elementsInTheMap.add(elementKey_a);
					}
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
		if (lastKeyPressed == KeyEvent.VK_UP || lastKeyPressed == KeyEvent.VK_DOWN || lastKeyPressed == KeyEvent.VK_LEFT || lastKeyPressed == KeyEvent.VK_RIGHT) {
			if (player != null) 
				player.move(Direction.directionFor(lastKeyPressed));
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
		// "l" quit
		else if (lastKeyPressed == 76) {
			if (player != null)
				ImageMatrixGUI.getInstance().dispose();
		}
		// "b" go back
		else if (lastKeyPressed == 66) {
			if (player != null)
				advnceToNextLevel(level - 1);
		}
		else if (lastKeyPressed == 72) {
			if (player != null) {
				for(ElementKey x: elementsInTheMap)
					x.activateLinkMode();
				player.activateLinkMode();
			}
		}
		else if (lastKeyPressed == 77) {
			if (player != null) {
				for(ElementKey x: elementsInTheMap)
					x.activateMarioMode();
				player.activateMarioMode();
			}
		}
		// 'p' Change player :D
		else if (lastKeyPressed == 80) {
			if (player != null) {
				int random = (int)(Math.random() * 3) +1;
				System.out.println(random);
				if (random == 1)
					player.activateMarioMode();
				if (random == 2)
					player.activateLinkMode();
				if (random == 3)
					player.deactivateMode();
				ImageMatrixGUI.getInstance().update();
			}
		}
		//Check gameStatus
		if(player != null) {
			ImageMatrixGUI.getInstance().update();
			checkAlvosAndCaixas();
			checkForBuraco();
		}	
	}

}
