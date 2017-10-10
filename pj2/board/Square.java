package board;

public class Square {

	public static final int BLANK = 2;
	public static final int WHITE = 1;
	public static final int BLACK = 0;
	public static final int BLACK_VALID_ONLY = 0;
	public static final int WHITE_VALID_ONLY = 1;
	public static final int BOTH_VALID = 2;
	public static final int NO_VALID = 3;
	public static final int WALL_NO_VALID = 4;
	protected int validity;
	protected Chessman chessman;
	protected int color;
	protected Coordinate coordinate;
	
	public Square(int x, int y){
		color = BLANK;
		validity = NO_VALID;
		coordinate = new Coordinate(x, y);
	}
	
	public Square(int color, int validity, int x, int y) {
		this.color = color;
		this.validity = validity;
		coordinate = new Coordinate(x, y);
	}

	public int getX(){
		return coordinate.x;
	}
	
	public int getY(){
		return coordinate.y;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public Chessman getChessman(){
		return chessman;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

	public int getColor(){
		return color;
	}
	
	public void setValidity(int validity){
		this.validity = validity;
	}
	
	public int getValidity(){
		return validity;
	}
	
	public void add(Chessman chessman){
		this.chessman = chessman;
		color = chessman.color;
		validity = NO_VALID;
	}
	
	public Chessman remove(){
		Chessman temp = chessman;
		chessman = null;
		color = BLANK;
		return temp;
	}
	
	@Override
	public String toString() {
		String string = "(" + coordinate.x + "," + coordinate.y + ") " 
						+ "color=" + color + " validity=" + validity; 
		return string;
	}
}
