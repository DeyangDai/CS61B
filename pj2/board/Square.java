package board;

public class Square {

	public static final int BLACK = 0;
	public static final int WHITE = 1;
	public static final int BLANK = 2;

	public static final int BLACK_VALID_ONLY = 0;
	public static final int WHITE_VALID_ONLY = 1;
	public static final int BOTH_VALID = 2;
	public static final int NO_VALID = 3;
	public static final int WALL_NO_VALID = 4;
	
	protected int validity;
	protected Chessman chessman;
	protected int state;
	protected Coordinate coordinate;
	
	public Square(int x, int y){
		state = BLANK;
		validity = NO_VALID;
		coordinate = new Coordinate(x, y);
	}
	
	public Square(int state, int validity, int x, int y) {
		this.state = state;
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
	
	protected void setState(int state) {
		this.state = state;
	}

	public int getState(){
		return state;
	}
	
	protected void setValidity(int validity){
		this.validity = validity;
	}
	
	public int getValidity(){
		return validity;
	}
	
	protected void add(Chessman chessman){
		this.chessman = chessman;
		state = chessman.color;
		validity = NO_VALID;
	}
	
	protected Chessman remove(){
		Chessman temp = chessman;
		chessman = null;
		state = BLANK;
		validity = temp.getColor();
		return temp;
	}
	
	@Override
	public String toString() {
		String string = "(" + coordinate.x + "," + coordinate.y + ") " 
						+ "color=" + state + " validity=" + validity; 
		return string;
	}

	public boolean isEdge() {
		if(isWhiteEdge() || isBlackEdge()){
			return true;
		}
		return false;
	}

	public boolean isBlackEdge() {
		if(coordinate.y == 0 || coordinate.y == 7){
			return true;
		}
		return false;
	}

	public boolean isWhiteEdge() {
		if(coordinate.x == 0 || coordinate.x == 7){
			return true;
		}
		return false;
	}
}
