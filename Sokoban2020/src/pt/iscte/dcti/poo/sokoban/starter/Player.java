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
	private int moves = 20;
	private int targets;
	private SokobanGame game;
	
	public Player(Point2D initialPosition, int level){
		position = initialPosition;
		imageName = "Empilhadora_D";
		this.level = level;
	}
	public void addGame(SokobanGame game) {
		this.game = game;
	}
	
	public void setElementsInTheMap(ArrayList<ElementKey> elementsInTheMap) {
		this.elementsInTheMap = elementsInTheMap;
	}

	public void addTargets(int targets) {
		this.targets += targets;
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
	
	public void gotTheBatrty() {
		moves = 100;
	}
	
	public void setName(String name){
		imageName = name;
	}
	
	public void restartLevel() {
		game.advnceToNextLevel(level);
	}
	public void buracoHere(Point2D newPosition, ElementKey object) {
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) 
			for(ElementKey x: elementsInTheMap) {
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) && x.getImage().getName().equals("Buraco")) {
					object.objectIsOnTheHole();
				}
					
			}
	}
	public boolean moveCheck(Point2D newPosition) {
		ImageMatrixGUI.getInstance().setStatusMessage("Moves Left: "+ moves + "Targets Done!: " + targets);
		ImageMatrixGUI.getInstance().setName("Level: "+ level);
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) {
			for(ElementKey x: elementsInTheMap) {
				if((!x.canStepOn() && x.getPosition().equals(newPosition))) {
					ImageMatrixGUI.getInstance().setStatusMessage("Something is on your Way!");
					return false;
				}	
			}
		}
		if (targets == 0) {
			//ImageMatrixGUI.setSize(10, 10);
			//ImageMatrixGUI.setSize(20, 20);
			SokobanGame s = new SokobanGame(level + 1);
			//ImageMatrixGUI.getInstance().go();
		}
			
		if (moves == 0) {
			//SokobanGame s = new SokobanGame(level);
			ImageMatrixGUI.getInstance().dispose();
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
		if (moveCheck(newPosition)) {
			moves--;
			position = newPosition;
		}
		setName("Empilhadora_U");
		ImageMatrixGUI.getInstance().update();
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
		}
		setName("Empilhadora_D");
		ImageMatrixGUI.getInstance().update();
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
		}
		setName("Empilhadora_L");
		ImageMatrixGUI.getInstance().update();
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
			position = newPosition;
		}
		setName("Empilhadora_R");
		ImageMatrixGUI.getInstance().update();
	}
	
	public void move() {
		
		Direction randomDirection = Direction.UP;

		Point2D newPosition = position.plus(randomDirection.asVector());
		if (newPosition.getX()>=0 && newPosition.getX()<10 && newPosition.getY()>=0 && newPosition.getY()<10 ){
			position = newPosition;
		}
		ImageMatrixGUI.getInstance().update();
	}
	
	
	@Override
	public void objectIsOnTheHole() {
		System.out.println("Im HERE :D Player");
		ImageMatrixGUI.getInstance().removeImage(this);;
	}

	@Override
	public void updateElementUP(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateElementDOWN(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateElementRIGHT(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateElementLEFT(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int level() {
		return level;
	}

}
