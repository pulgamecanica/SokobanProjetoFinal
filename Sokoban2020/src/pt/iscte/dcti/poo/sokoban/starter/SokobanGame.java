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
	
	public SokobanGame(int level){
		this.level = level;
		ArrayList<ImageTile> tiles = buildRealLevel(level);
		tiles.add(player);
		ImageMatrixGUI.getInstance().addImages(tiles);
		for (ImageTile x: tiles)
			if(x.getName().equals("Alvo")) 
				alvos.add(x.getPosition());
			else if(x.getName().equals("Caixote")) 
				caixas.add(x);
			
		bS = new BestScores(level);
		bS.searchFile();
		messages();
	}
	public ArrayList<AbstractSObject> getElementsInTheMap(){
		ArrayList<AbstractSObject> array =  new ArrayList<>();
		for(ElementKey x : elementsInTheMap)
			array.add((AbstractSObject)x);
		return array;
	}
	public ArrayList<ElementKey> getElementElementsInTheMap(){return elementsInTheMap;}
	public int getLevel(){return level;}
	//Verify If the level is completed
	private void checkAlvosAndCaixas () {
		int counter = 0;
		for (Point2D x: alvos)
			for(ImageTile y: caixas) {
				if(x.equals(y.getPosition())) {
					counter++;
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
		ImageMatrixGUI.getInstance().setStatusMessage("LEVEL: " + (level+1) + " ** MOVES: " + player.movesDone() + " ** ENERGY: "+ player.getMoves() + " ** BestScore: " + (bS.getTopOne()==0? "NULL":bS.getTopOne()) +  " ** >>>>'r'-->RESTART - 'n'-->NEXTLEVEL - 'b'-->BACK - 'q'-->QUITGAME ** - 'h'??? - 'm'??? <<<<");
		ImageMatrixGUI.getInstance().setName("Level: "+ (level+1));
	}
	//Start a level from scratch
	public void advnceToNextLevel(int levelToStart) {
		elementsInTheMap.clear();
		alvos.clear();
		caixas.clear();
		player = null;
		ImageMatrixGUI.getInstance().clearImages();
		startLevel(levelToStart);
		ImageMatrixGUI.getInstance().update();
		ImageMatrixGUI.getInstance().go();
	}
	public void startLevel(int level) {
		this.level = level;
		ArrayList<ImageTile> tiles = buildRealLevel(level);
		tiles.add(player);
		ImageMatrixGUI.getInstance().addImages(tiles);
		for (ImageTile x: tiles)
			if(x.getName().equals("Alvo")) 
				alvos.add(x.getPosition());
			else if(x.getName().equals("Caixote")) 
				caixas.add(x);
		bS = new BestScores(level);
		bS.searchFile();
		//System.out.println(bS.getTopOne());
		messages();
	}

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
//			ImageTile element = new Chao(new Point2D (i, row), 0);
//			sampleLevelTiles.add(element);
			for(KeyWords x: possibleObjects) {
				if (elements[i].equals(x.getTheElementKey())) {
					AbstractSObject mapElementFounded = (AbstractSObject)x.createAnElement(new Point2D(i, row));
					sampleLevelTiles.add(mapElementFounded);
					elementsInTheMap.add((ElementKey)mapElementFounded);
				}
				if (elements[i].equals("E")) {
					player = new Player(new Point2D(i, row), this);
				}
			}
		}
		return sampleLevelTiles;
	}
	private ArrayList<KeyWords> getPossibleObjects(int level){
		ArrayList<KeyWords> array2 = new ArrayList<KeyWords>();
		for(KeyWord x: KeyWord.values())
			array2.add(x);
		return array2;
	}
	private void checkForBuraco() {
		for(ElementKey x: elementsInTheMap)
			if(player.getPosition().equals(x.getPosition()) && x.getName().equals("Buraco") && !x.canPlayerStepInsideHole()) {
				advnceToNextLevel(level);
				return;
			}
	}
	public boolean moveCheck(Point2D newPosition) {
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width &&newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) {
			for(ElementKey x: elementsInTheMap) {
				if((!x.canStepOn() && x.getPosition().equals(newPosition))) {
					ImageMatrixGUI.getInstance().setStatusMessage("Something is on your Way!");
					return false;
				}	
				if(!x.usedBatery() && x.getPosition().equals(newPosition)) {
					player.gotTheBatrty();
					x.useTheBatery();
				}
			}
		}	
		return true;
	}
	@Override
	public void update(Observed arg0) {
		messages();
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		//System.out.println("Key pressed " + lastKeyPressed);
		// VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
		if (lastKeyPressed == KeyEvent.VK_UP || lastKeyPressed == KeyEvent.VK_DOWN || lastKeyPressed == KeyEvent.VK_LEFT || lastKeyPressed == KeyEvent.VK_RIGHT) {
			if (player != null) 
				player.move(Direction.directionFor(lastKeyPressed));
		}
		// "n" next level
		else if (lastKeyPressed == KeyEvent.VK_N) {
			if (player != null)
				advnceToNextLevel(level + 1);
		}
		// "r" restart level
		else if (lastKeyPressed == KeyEvent.VK_R) {
			if (player != null)
				advnceToNextLevel(level);
		}
		// "q" quit
		else if (lastKeyPressed == KeyEvent.VK_Q) {
			if (player != null)
				ImageMatrixGUI.getInstance().dispose();
		}
		// "b" go back
		else if (lastKeyPressed == KeyEvent.VK_B) {
			if (player != null)
				advnceToNextLevel(level - 1);
		}
		// "h" activate TLOZ mode
		else if (lastKeyPressed == KeyEvent.VK_H) {
			if (player != null) {
				for(ElementKey x: elementsInTheMap)
					x.activateLinkMode();
				player.activateLinkMode();
			}
			floorTiles(1);
			ImageMatrixGUI.getInstance().update();
		}
		// "m" activate MArio mode
		else if (lastKeyPressed == KeyEvent.VK_M) {
			if (player != null) {
				for(ElementKey x: elementsInTheMap)
					x.activateMarioMode();
				player.activateMarioMode();
			}
			floorTiles(2);
			ImageMatrixGUI.getInstance().update();
		}
		// "z" activate LOTRO mode
		else if (lastKeyPressed == KeyEvent.VK_Z) {
			if (player != null) {
				for(ElementKey x: elementsInTheMap)
					x.activateLOTROMode();
				int random = (int)(Math.random() * 3) +1;
				if (random == 1) 
					player.activateGandalfMode();
				if (random == 2)
					player.activateGimliMode();
				if (random == 3)
					player.activateSauronMode();
				floorTiles(3);
				ImageMatrixGUI.getInstance().update();
			}
		}
		// 'p' Change player :D
		else if (lastKeyPressed == KeyEvent.VK_P) {
			if (player != null) {
				int random = (int)(Math.random() * 6) +1;
				//System.out.println(random);
				if (random == 1)
					player.activateMarioMode();
				if (random == 2)
					player.activateLinkMode();
				if (random == 3)
					player.deactivateMode();
				if (random == 4)
					player.activateGimliMode();
				if (random == 5)
					player.activateSauronMode();
				if (random == 6)
					player.activateGandalfMode();
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
	public void floorTiles (int style) {
		ArrayList<ImageTile> tiles = new ArrayList<>();
		for(int i = 0; i < ImageMatrixGUI.getInstance().getGridDimension().getHeight(); i++) {
			for(int j = 0; j < ImageMatrixGUI.getInstance().getGridDimension().getWidth(); j++) {
				tiles.add(new Chao(new Point2D (i, j), style));
			}
		}
		ImageMatrixGUI.getInstance().addImages(tiles);
	}
}
