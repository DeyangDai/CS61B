package board;

public class Square {

	public static final int BLANK = 2;
	public static final int WHITE = 1;
	public static final int BLACK = 0;
	public static final int WALL = -1;
	protected boolean isValidForBlack;
	protected boolean isValidForWhite;
	protected Chessman chessman;
	private int color = BLANK;
	
	public Square(int color) {
		this.color = color;
		isValidForBlack = true;
		isValidForWhite = true;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

	public boolean isValidFor(int color){
		if(this.color != BLANK){
			return false;
		}
		if(color == BLACK){
			return isValidForBlack;
		}
		if(color == WHITE){
			return isValidForWhite;
		}
		return false;
	}
	
	public void add(Chessman chessman){
		this.chessman = chessman;
		color = chessman.color;
		isValidForBlack = false;
		isValidForWhite = false;
	}
	
	public Chessman remove(){
		Chessman temp = chessman;
		chessman = null;
		color = BLANK;
		return temp;
	}
}
