package board;

import list.List;
import player.Move;
import list.DList;

public class GameBoard {

	private Square[][] board;
	private int nextColor = Square.WHITE; //white first.
	private List<Chessman> blackChessmen; 
	private List<Chessman> whiteChessmen;
	private int length; //GameBoard is a length * length chess box, 
						//and contains Square[length][length].  
	
	public GameBoard() {
		this(8);
	}

	public GameBoard(int length) {
		this.length = length;
		blackChessmen = new DList<Chessman>();
		whiteChessmen = new DList<Chessman>();
		
		initBoard();
	}

	private void initBoard() {
		board = new Square[length][length];
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				board[x][y] = new Square(Square.BLANK);
			}
		}
		board[0][0].setColor(Square.WALL);
		board[0][length - 1].setColor(Square.WALL);
		board[length - 1][0].setColor(Square.WALL);
		board[length - 1][length - 1].setColor(Square.WALL);
	}

	public int getCount(int color){
		if(color == Square.BLACK){
			return getBlackCount();
		}
		if(color == Square.WHITE){
			return getWhiteCount();
		}
		return -1;
	}
	
	public int getWhiteCount(){
		return whiteChessmen.length();
	}
	
	public int getBlackCount(){
		return blackChessmen.length();
	}
	
	public boolean isValidMove(Move move, int color) {
		if(nextColor == color){
			switch (move.moveKind) {
			case Move.ADD:
			case Move.STEP:
				return board[move.x1][move.y1].isValidFor(color);
			case Move.QUIT:
				//TODO
				return false;
			}
		}
		return false;
	}

	public List<Move> getValidMoves(int color) {
		// depend on moveKind???
		// TODO
		List<Move> moves = new DList<Move>();
		if(getCount(color) < 10){
			for(int x = 0; x < length; x++){
				for(int y = 0; y < length; y++){
					if(board[x][y].isValidFor(color)){
						moves.insertBack(new Move(x, y));
					}
				}
			}
		}
		if(getCount(color) == 10){
			//TODO
		}
		return null;

	}

	private List<Square> findConnections(Square square) {
		// TODO
		return null;
	}

	private boolean hasNetwork() {
		// TODO
		return false;
	}

	public int evaluation() {
		return 0;
		// TODO

	}

	/**
	 * update isValidForBlack and isValidForWhite 
	 * in every square of "this" GameBoard.
	 */
	private void updateValidity(){
		//TODO
	}
	
	/**
	 * updates "this" GameBoard, m should be examined by
	 * isValidMove(m, color) before update(m, color).
	 * Then changes nextColor and updates isValidForBlack & isValidForWhite 
	 * in every square of "this" GameBoard.
	 * 
	 * @param m is a valid Move.
	 * @param color is the color of the Move m, 0 == black, 1 == white.
	 */
	public void updateBoard(Move move, int color) {
		Chessman chessman;
		switch (move.moveKind) {
		case Move.ADD:
			chessman = new Chessman(move, color);
			board[move.x1][move.y1].add(chessman);
			this.listAdd(chessman);
			break;
		case Move.STEP:
			chessman = board[move.x2][move.y2].remove();
			board[move.x1][move.y1].add(chessman);
			break;
		case Move.QUIT:
			//TODO
			break;
		}
		this.nextColor = 1 - color;
		updateValidity();
	}
	
	public void listAdd(Chessman chessman){
		if(chessman.color == Square.BLACK){
			blackChessmen.insertBack(chessman);
		}
		if(chessman.color == Square.WHITE){
			whiteChessmen.insertBack(chessman);
		}
	}
}
