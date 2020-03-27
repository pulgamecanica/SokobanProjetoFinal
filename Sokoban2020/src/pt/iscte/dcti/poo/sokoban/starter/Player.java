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
	
	public Player(Point2D initialPosition, int level){
		position = initialPosition;
		imageName = "Empilhadora_D";
		this.level = level;
	}
	public void setElementsInTheMap(ArrayList<ElementKey> elementsInTheMap) {
		this.elementsInTheMap = elementsInTheMap;
	}
	public void updateElementsInTheMap(ElementKey elementInTheMap) {
		for(ElementKey x: elementsInTheMap)
			if(x.equals(elementInTheMap))
				x = elementInTheMap;
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
		return level;
	}
	@Override
	public boolean canMove() {
		return true;
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
	public void updateElement(Point2D point) {
		return;
	}
	
	public void setName(String name){
		imageName = name;
	}
	
	public boolean moveCheck(Point2D newPosition) {
		ImageMatrixGUI.getInstance().setStatusMessage("Moves Left: "+ moves);
		ImageMatrixGUI.getInstance().setName("Level: "+ level);
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) 
			for(ElementKey x: elementsInTheMap) {
				if((!x.canStepOn() && x.getPosition().equals(newPosition))) {
					ImageMatrixGUI.getInstance().setStatusMessage("Something is on your Way!");
					return false;
				}
			}
		if (moves == 0) {
			ImageMatrixGUI.getInstance().dispose();
		}
		return true;
	}

	public void moveUp() {
		Point2D newPosition = new Point2D(position.getX(), position.getY() - 1);
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX(), newPosition.getY() - 1))) {
				x.updateElement(new Point2D(newPosition.getX(), newPosition.getY() - 1));
				updateElementsInTheMap(x);
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
		for(ElementKey x: elementsInTheMap)
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX(), newPosition.getY() + 1)))
				x.updateElement(new Point2D(newPosition.getX(), newPosition.getY() + 1));
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
		for(ElementKey x: elementsInTheMap)
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX() - 1, newPosition.getY())))
				x.updateElement(new Point2D(newPosition.getX() - 1, newPosition.getY()));
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
		for(ElementKey x: elementsInTheMap)
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(new Point2D(newPosition.getX() + 1, newPosition.getY())))
				x.updateElement(new Point2D(newPosition.getX() + 1, newPosition.getY()));
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

}