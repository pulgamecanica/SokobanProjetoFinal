package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

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
	private String empilhadora_D, empilhadora_U, empilhadora_R, empilhadora_L;

	
	public Player(Point2D initialPosition, int level){
		position = initialPosition;
		this.level = level;
		imageName = "Empilhadora_D";
		empilhadora_D = "Empilhadora_D";
		empilhadora_U = "Empilhadora_U";
		empilhadora_R = "Empilhadora_R";
		empilhadora_L = "Empilhadora_L";
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
		return;
	}

	@Override
	public void updateElementUP(Player p) {
		return;
	}

	@Override
	public void updateElementDOWN(Player p) {
		return;
	}

	@Override
	public void updateElementRIGHT(Player p) {
		return;
	}

	@Override
	public void updateElementLEFT(Player p) {
		return;
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
		return;
	}
	
	@Override
	public void activateLinkMode() {
		empilhadora_U = "Link_U";
		empilhadora_D = "Link_D";
		empilhadora_L = "Link_L";
		empilhadora_R = "Link_R";
	}
	@Override
	public boolean isBig() {
		return false;
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
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) && !x.canPlayerStepInsideHole() && x instanceof Buraco) {
					object.objectIsOnTheHole();
				}
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) &&  x instanceof Buraco && object.isBig()) {
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
		Point2D newPosition = position.plus(new Vector2D(0, -1));
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(newPosition.plus(new Vector2D(0, -1)))) {
				x.updateElementUP(this);
				buracoHere(newPosition.plus(new Vector2D(0, -1)), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		//Move Player
		if (moveCheck(newPosition)) {
			moves--;
			movesDone++;
			position = newPosition;
		}
		setName(empilhadora_U);
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	
	public void moveDown() {
		Point2D newPosition = position.plus(new Vector2D(0, 1));
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(newPosition.plus(new Vector2D(0, 1)))) {
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
		setName(empilhadora_D);
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	
	public void moveLeft() {
		Point2D newPosition = position.plus(new Vector2D(-1, 0));
		//Check for movableObjects
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(newPosition.plus(new Vector2D(-1, 0)))) {
				x.updateElementLEFT(this);
				buracoHere(newPosition.plus(new Vector2D(-1, 0)), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			moves--;
			position = newPosition;
			movesDone++;
		}
		setName(empilhadora_L);
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}
	
	public void moveRight() {
		Point2D newPosition = position.plus(new Vector2D(1, 0));
		//Check for movableObjectsa
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(newPosition.plus(new Vector2D(1, 0)))) {
				x.updateElementRIGHT(this);
				buracoHere(newPosition.plus(new Vector2D(1, 0)), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			moves--;
			movesDone++;
			position = newPosition;
		}
		setName(empilhadora_R);
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
