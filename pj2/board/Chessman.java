package board;

import player.Move;

public class Chessman {
	
	int color;
	Coordinate coordinate;
	
	/**
	 * constructs a Chessman by move whose moveKind == Move.ADD.
	 * @param move
	 * @param color
	 */
	public Chessman(Move move, int color) {
		if(move.moveKind == Move.ADD){
			coordinate = new Coordinate(move.x1, move.y1);
			this.color = color;
		}
	}
	
	public void doMove(Move move){
		if(move.moveKind == Move.STEP){
			coordinate.x = move.x1;
			coordinate.y = move.y1;
		}
	}
	
	public Coordinate getCoordinate(){
		return coordinate;
	}
	
	public int getColor(){
		return color;
	}
	
	public int getX(){
		return coordinate.x;
	}

	public int getY(){
		return coordinate.y;
	}
	
	@Override
	public String toString() {
		String string = "[x=" + coordinate.x + ",y=" + coordinate.y + "," + color + "]";
		return string;
	}
}
