package board;

import player.Move;

public class Chessman {
	
	int color;
	int x;
	int y;
	
	/**
	 * constructs a Chessman by move whose moveKind == Move.ADD.
	 * @param move
	 * @param color
	 */
	public Chessman(Move move, int color) {
		// TODO Auto-generated constructor stub
		if(move.moveKind == Move.ADD){
			x = move.x1;
			y = move.y1;
			this.color = color;
		}
	}
	
	public void moveTo(Move move){
		if(move.moveKind == Move.STEP){
			x = move.x1;
			y = move.y1;
		}
	}
}
