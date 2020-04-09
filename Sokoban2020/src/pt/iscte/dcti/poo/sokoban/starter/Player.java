package pt.iscte.dcti.poo.sokoban.starter;

import java.util.ArrayList;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

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
	public int getMoves() {return moves;}
	
	public void addGame(SokobanGame game) {this.game = game;}
	
	public void setElementsInTheMap(ArrayList<ElementKey> elementsInTheMap) {this.elementsInTheMap = elementsInTheMap;}
	
	@Override
	public String getName() {return imageName;}
	@Override
	public Point2D getPosition() {return position;}
	@Override
	public int getLayer() {return 3;}
	@Override
	public boolean canMove() {return false;}
	@Override
	public boolean canStepOn() {return false;}
	@Override
	public ImageTile getImage() {return this;}
	@Override
	public void objectIsOnTheHole() {return;}
	@Override
	public int level() {return level;}
	@Override
	public boolean canPlayerStepInsideHole() {return false;}
	@Override
	public boolean usedBatery() {return true;}
	@Override
	public void useTheBatery() {return;}
	@Override
	public boolean isBig() {return false;}
	@Override
	public void updateElement(Direction dir) {return;}
	@Override
	public void activateMarioMode() {
		imageName = "Mario_D";
		empilhadora_D = "Mario_D";
		empilhadora_U = "Mario_U";
		empilhadora_R = "Mario_R";
		empilhadora_L = "Mario_L";
	}
	@Override
	public void activateLinkMode() {
		imageName = "Link_U";
		empilhadora_U = "Link_U";
		empilhadora_D = "Link_D";
		empilhadora_L = "Link_L";
		empilhadora_R = "Link_R";
	}
	@Override
	public void activateLOTROMode() {
		imageName = "Gandalf_U";
		empilhadora_U = "Gandalf_U";
		empilhadora_D = "Gandalf_D";
		empilhadora_L = "Gandalf_R";
		empilhadora_R = "Gandalf_L";
		
	}
	public void activateSauronMode() {
		imageName = "Sauron_D";
		empilhadora_U = "Sauron_U";
		empilhadora_D = "Sauron_D";
		empilhadora_L = "Sauron_L";
		empilhadora_R = "Sauron_R";
		
	}
	public void activateGimliMode() {
		imageName = "Gimli_U";
		empilhadora_U = "Gimli_U";
		empilhadora_D = "Gimli_D";
		empilhadora_L = "Gimli_L";
		empilhadora_R = "Gimli_R";
		
	}
	public void activateGandalfMode() {
		imageName = "Gandalf_U";
		empilhadora_U = "Gandalf_U";
		empilhadora_D = "Gandalf_D";
		empilhadora_L = "Gandalf_R";
		empilhadora_R = "Gandalf_L";
		
	}
	public int movesDone() {return movesDone;}
	
	public void gotTheBatrty() {moves = 100;}
	
	public void setName(Direction dir){
		if(dir.asVector().getX() == 0 && dir.asVector().getY() == 1)
			imageName = empilhadora_D;
		else if(dir.asVector().getX() == 0&& dir.asVector().getY() == -1)
			imageName = empilhadora_U;
		else if(dir.asVector().getX() == 1 && dir.asVector().getY() == 0)
			imageName = empilhadora_R;
		else if(dir.asVector().getX() == -1 && dir.asVector().getY() == 0)
			imageName = empilhadora_L;
		}
	
	public void restartLevel() {if (moves == -1) game.advnceToNextLevel(level);}
	
	public void buracoHere(Point2D newPosition, ElementKey object) {
		if (newPosition.getX()>=0 && newPosition.getX()<ImageMatrixGUI.getInstance().getGridDimension().width && newPosition.getY()>=0 && newPosition.getY()<ImageMatrixGUI.getInstance().getGridDimension().height) { 
			for(ElementKey x: elementsInTheMap) {
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) && !x.canPlayerStepInsideHole() && x instanceof Buraco) {
					object.objectIsOnTheHole();
				}
				if ((x.canStepOn() && x.getPosition().equals(newPosition)) &&  x instanceof Buraco && object.isBig()) {
					x.objectIsOnTheHole();
				}	
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
	
	public void move(Direction dir) {
		Point2D newPosition = position.plus(dir.asVector());
		for(ElementKey x: elementsInTheMap) {
			if(x.canMove() && x.getPosition().equals(newPosition) && moveCheck(newPosition.plus(dir.asVector()))) {
				x.updateElement(dir);
				buracoHere(newPosition.plus(dir.asVector()), x);
			}
		}
		ImageMatrixGUI.getInstance().update();
		if (moveCheck(newPosition)) {
			moves--;
			movesDone++;
			position = newPosition;
		}
	    setName(dir);
		ImageMatrixGUI.getInstance().update();
		restartLevel();
	}

	public void deactivateMode() {
		imageName = "Empilhadora_D";
		empilhadora_D = "Empilhadora_D";
		empilhadora_U = "Empilhadora_U";
		empilhadora_R = "Empilhadora_R";
		empilhadora_L = "Empilhadora_L";
	}
	
}
