package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Player implements ImageTile, ElementKey{

	private Point2D position;
	private String imageName;
	private int level;
	private ArrayList<ElementKey> elementsInTheMap = new ArrayList<>();
	private int moves = 100;
	private SokobanGame game;
	private int movesDone = 0;

	
	public Player(Point2D initialPosition, int level){
		position = initialPosition;
		imageName = "Empilhadora_D";
		this.level = level;
	}
	public int getMoves() {
		return moves;
	}
	public void addGame(SokobanGame game) {
		this.game = game;
	}
	
	public void setElementsInTheMap(ArrayList<ElementKey> elementsInTheMap) {
		this.elementsInTheMap = elementsInTheMap;
	}
	
	@Override
	public String getName() {
		return imageName;
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}
	
	@Override
	public int getLayer() {
		return 3;
	}
	
	@Override
	public boolean canMove() {
		return false;
	}
	
	@Override
	public boolean canStepOn() {
		return false;
	}

	@Override
	public ImageTile getImage() {
		return this;
	}
	
	@Override
	public void objectIsOnTheHole() {
	
	}

	@Override
	public void updateElementUP(Player p) {
		
	}

	@Override
	public void updateElementDOWN(Player p) {
		
	}

	@Override
	public void updateElementRIGHT(Player p) {

	}

	@Override
	public void updateElementLEFT(Player p) {
		
	}

	@Override
	public int level() {
		return level;
	}
	
	@Override
	public boolean canPlayerStepInsideHole() {
		return false;
	}
	@Override
	public boolean usedBatery() {
		return true;
	}

	@Override
	public void useTheBatery() {
		
	}
	
	public int movesDone() {
		return movesDone;
	}
	
	public void gotTheBatrty() {
		moves = 100;
	}
	
	public void setName(String name){
		imageName = name;
	}
	
	public void restartLevel() {
		if (moves == -1) {
			game.advnceToNextLevel(level);
		}
	}
	
	public void buracoHere(Point2D newPosition, ElementKey object) {
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) 
			for(ElementKey x: elementsInTheMap) {
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) && x.getImage().getName().equals("Buraco") && !x.canPlayerStepInsideHole()) {
					object.objectIsOnTheHole();
				}
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) && x.getImage().getName().equals("Buraco") && object.getName().equals("BigStone")) {
					x.objectIsOnTheHole();
				}
					
			}
	}
	
	private boolean moveCheck(Point2D newPosition) {
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) {
			for(ElementKey x: elementsInTheMap) {
				if((!x.canStepOn() && x.getPosition().equals(newPosition))) {
					ImageMatrixGUI.getInstance().setStatusMessage("Something is on your Way!");
					return false;
				}	
				if(!x.usedBatery() && x.getPosition().equals(newPosition)) {
					gotTheBatrty();
					x.useTheBatery();
				}
			}
		}	
		return true;
	}
	

	public void moveUp() {
		Point2D newPosition = new Point2D(position.getX(), position.getY() - 1);
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX(), newPosition.getY() - 1))) {
				x.updateElementUP(this);
				buracoHere(new Point2D(newPosition.getX(), newPosition.getY() - 1), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		//Move Player
		if (moveCheck(newPosition)) {
			moves--;
			movesDone++;
			position = newPosition;
		}
		setName("Empilhadora_U");
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	public void moveDown() {
		Point2D newPosition = new Point2D(position.getX(), position.getY() + 1);
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX(), newPosition.getY() + 1))) {
				x.updateElementDOWN(this);
				buracoHere(new Point2D(newPosition.getX(), newPosition.getY() + 1), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			position = newPosition;
			moves--;
			movesDone++;
		}
		setName("Empilhadora_D");
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	public void moveLeft() {
		Point2D newPosition = new Point2D(position.getX() - 1, position.getY());
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX() - 1, newPosition.getY()))) {
				x.updateElementLEFT(this);
				buracoHere(new Point2D(newPosition.getX() - 1, newPosition.getY()), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			moves--;
			position = newPosition;
			movesDone++;
		}
		setName("Empilhadora_L");
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	public void moveRight() {
		Point2D newPosition = new Point2D(position.getX() + 1, position.getY());
		//Check for movableObjectsa
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX() + 1, newPosition.getY()))) {
				x.updateElementRIGHT(this);
				buracoHere(new Point2D(newPosition.getX() + 1, newPosition.getY()), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			moves--;
			movesDone++;
			position = newPosition;
		}
		setName("Empilhadora_R");
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	
	public void move() {
		Direction randomDirection = Direction.UP;
		Point2D newPosition = position.plus(randomDirection.asVector());
		if (newPosition.getX()>=0 && newPosition.getX()<10 && newPosition.getY()>=0 && newPosition.getY()<10 ){
			position = newPosition;
		}
		ImageMatrixGUI.getInstance().update();
	}
	
}
