package pt.iscte.dcti.poo.sokoban.starter;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public enum KeyWord  implements KeyWords{
	
	ALVO{
		@Override
		public String getTheElementKey() {return "X";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Alvo(point);}
	},
	CHAO{
		@Override
		public String getTheElementKey() {return " ";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Chao(point);}
	},
	BATERIA{
		@Override
		public String getTheElementKey() {return "b";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Bateria(point);}
	},
	BIGSTONE{
		@Override
		public String getTheElementKey() {return "P";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new BigStone(point);}
	},
	BURACO {
		@Override
		public String getTheElementKey() {return "O";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Buraco(point);}
	},
	CAIXOTE{
		@Override
		public String getTheElementKey() {return "C";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Caixote(point);}
	},
	PAREDE{
		@Override
		public String getTheElementKey() {return "#";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new Parede(point);}
	},
	SMALLSTONE{
		@Override
		public String getTheElementKey() {return "p";}
		@Override
		public ElementKey createAnElement(Point2D point) {return new SmallStone(point);}
	}
	

}
