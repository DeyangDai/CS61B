package board;

import list.List;
import player.Move;

import list.DList;

public class GameBoard {

	private Square[][] squares;
	private int nextColor = Square.WHITE; // white first.
	private List<Chessman> blackChessmen;
	private List<Chessman> whiteChessmen;
	private int length; // GameBoard is a length * length chess box,
						// and contains Square[length][length].

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
		squares = new Square[length][length];
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				squares[x][y] = new Square(Square.BLANK, Square.WALL_NO_VALID, x, y);
				if( 0 < x && x < length-1 && 0 < y && y < length-1){	//inner squares
					squares[x][y].setValidity(Square.BOTH_VALID);
				} else{
					 if( 0 < y && y < length-1){		//squares of left and right edge
						 squares[x][y].setValidity(Square.WHITE_VALID_ONLY);
					 }
					 if( 0 < x && x < length-1){		//squares of top and bottom edge
						 squares[x][y].setValidity(Square.BLACK_VALID_ONLY);
					 }
				}
			}
		}
	}
	
	private List<Square> findConnections(Square square) {
		// TODO
		return null;
	}

	private boolean hasNetwork() {
		// TODO
		return false;
	}

	/**
	 * updates the validity of two layers near the chessman.
	 * @param chessman
	 */
	private void updateNearbyValidity(Chessman chessman, int moveTpye) {
		
		List<Square> nearbySquares = getNearbySquares(chessman, 2);
		
		while(nearbySquares.hasNext()){
			
			Square square = nearbySquares.next();
			Chessman nearbyChessman;
			
			if(square.chessman != null){
				nearbyChessman = square.chessman;
			} else{
				continue;
			}
			
			if(nearbyChessman.color == chessman.color){
				int type = calculateType(nearbyChessman, chessman);
				if(moveTpye == Move.ADD){
					modifyValidity(nearbyChessman, chessman, type, moveTpye);
				}
				if(moveTpye == Move.STEP){
					modifyValidity(nearbyChessman, chessman, type, moveTpye);
				}
			}
			
			
			/*if(moveTpye == Move.STEP){
				//TODO
				Chessman nearbyChessman;
				if(square.chessman != null){
					nearbyChessman = square.chessman;
				} else{
					continue;
				}
				if(nearbyChessman.color == chessman.color){
					int type = calculateType(nearbyChessman, chessman);
					degradeValidity(nearbyChessman, chessman, type);
				}
			}*/
		}
	}

	private void modifyValidity(Chessman nearbyChessman, Chessman chessman,
								int type, int moveType) {
		
		List<Square> squares1 = getNearbySquares(nearbyChessman, 1);
		List<Square> squares2 = getNearbySquares(chessman, 1);
		List<Square> result = null;
		
		switch (type) {
		case 1:
		case 2:	
			result = union(nearbyChessman, squares1, chessman, squares2);
			break;
		case 4:
		case 5:
		case 8:
			result = intersect(squares1, squares2);
			break;
		}
		
		if(result == null){
			return;
		}

		while(result.hasNext()){
			Square square = result.next();
			modifyValidityAt(square.getX(), square.getY(), chessman.color, moveType);
		}
		
		if(moveType == Move.STEP){
			modifyValidityAt(chessman.getX(), chessman.getY(), chessman.color, moveType);
		}
	}

	/*private void degradeValidity(Chessman nearbyChessman, Chessman chessman, int type) {
		switch (type) {
		case 1:
			degradeValidityType1(nearbyChessman, chessman);
			break;
		case 2:
			degradeValidityType2(nearbyChessman, chessman);
			break;
		case 8:
			degradeValidityType3(nearbyChessman, chessman);
			break;
		case 5:
			degradeValidityType4(nearbyChessman, chessman);
			break;
		case 4:
			degradeValidityType5(nearbyChessman, chessman);
			break;
		}
		
	}*/
	
	/*private void degradeValidityType5(Chessman nearbyChessman, Chessman chessman) {
		// TODO Auto-generated method stub
		int x = chessman.x;
		int y = chessman.y;
		int x1 = nearbyChessman.x;
		int y1 = nearbyChessman.y;
		
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //1
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //2
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //3
	}*/
	
	/*private void degradeValidityType4(Chessman nearbyChessman, Chessman chessman) {
		// TODO Auto-generated method stub
		int x = chessman.x;
		int y = chessman.y;
		int x1 = nearbyChessman.x;
		int y1 = nearbyChessman.y;
		
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //1
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //2
	}*/
	
	/*private void degradeValidityType3(Chessman nearbyChessman, Chessman chessman) {
		int x = chessman.x;
		int y = chessman.y;
		int x1 = nearbyChessman.x;
		int y1 = nearbyChessman.y;
		
		degradeValidityAt((x+x1)/2, (y+y1)/2, chessman.color); //1
	}*/
	
	/*private void degradeValidityType2(Chessman nearbyChessman, Chessman chessman) {
		// TODO Auto-generated method stub
		int x = chessman.x;
		int y = chessman.y;
		int x1 = nearbyChessman.x;
		int y1 = nearbyChessman.y;
		
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //1
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //2
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //3
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //4
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //5
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //6
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //7
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //8
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //9
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //10
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //11
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //12
	}*/
	
	/*private void degradeValidityType1(Chessman nearbyChessman, Chessman chessman) {
		int x = chessman.x;
		int y = chessman.y;
		int x1 = nearbyChessman.x;
		int y1 = nearbyChessman.y;
		
		degradeValidityAt(2*x1-x, 2*y1-y, chessman.color); //1
		degradeValidityAt(x-y+y1, y-x1+x, chessman.color); //2
		degradeValidityAt(2*x-x1, 2*y-y1, chessman.color); //3
		degradeValidityAt(x1-y+y1, y1-x1+x, chessman.color); //4
		degradeValidityAt(x1+y-y1, y1+x1-x, chessman.color); //5
		degradeValidityAt(x+y-y1, y+x1-x, chessman.color); //6
		degradeValidityAt(2*x1-x+y-y1, 2*y1-y+x1-x, chessman.color); //7
		degradeValidityAt(2*x1-x-y+y1, 2*y1-y-x1+x, chessman.color); //8
		degradeValidityAt(2*x+y-y1-x1, 2*y+x1-x-y1, chessman.color); //9
		degradeValidityAt(2*x-x1-y+y1, 2*y-y1-x1+x, chessman.color); //10
		
	}*/
	
	private void modifyValidityAt(int x, int y, int color, int moveType){
		if(squareExist(x, y)){
			Square square = squares[x][y];
			if(moveType == Move.ADD){
				degradeValidity(square, color);
			}
			if(moveType == Move.STEP){
				upgradeValidity(square, color);
			}
		}
	}

	private void upgradeValidity(Square square, int color) {
		if(square.validity == (1 - color)){
			square.validity = 2;
		}
		if(square.getChessman() == null && square.validity == Square.NO_VALID){
			square.validity = color;
		}
	}

	private void degradeValidity(Square square, int color) {
		if(square.validity == 2){
			square.validity = 1 - color;
		}
		if(square.validity == color){
			square.validity = Square.NO_VALID;
		}
	}

	private List<Square> union(Chessman nearbyChessman, List<Square> squares1, Chessman chessman,
			List<Square> squares2) {
		
		List<Square> result = new DList<Square>();
		while(squares1.hasNext()){
			Square square = squares1.next();
			if(square.getChessman() != chessman){
				result.insertBack(square);
			}
		}
		while(squares2.hasNext()){
			Square square = squares2.next();
			if(square.getChessman() == nearbyChessman){
				continue;
			}
			boolean findSquare = false;
			while(result.hasNext()){
				if(result.next() == square){
					findSquare = true;
				}
			}
			if(!findSquare){
				result.insertBack(square);
			}
		}
		return result;
	}

	private List<Square> intersect(List<Square> squares1, List<Square> squares2) {
		List<Square> result = new DList<Square>();
		while(squares1.hasNext()){
			Square square1 = squares1.next();
			while(squares2.hasNext()){
				Square square2 = squares2.next();
				if(square1 == square2){
					result.insertBack(square2);
				}
			}
		}
		return result;
	}

	private int calculateType(Chessman nearbyChessman, Chessman chessman) {
		int deltaX = nearbyChessman.getX() - chessman.getX();
		int deltaY = nearbyChessman.getY() - chessman.getY();
		return deltaX * deltaX + deltaY * deltaY;
	}

	private List<Square> getNearbySquares(Chessman chessman, int layer){
		List<Square> list = new DList<Square>();
		for(int i = -layer; i <= layer; i++){
			for(int j = -layer; j <= layer; j++){
				if(i == 0 && j == 0){
					continue;
				}
				if(squareExist(chessman.getX()+i, chessman.getY()+j)){
					Square square = squares[chessman.getX()+i][chessman.getY()+j];
					list.insertBack(square);
				}
			}
		}
		return list; 
	}

	private boolean squareExist(int x, int y) {
		if(0 <= x && x < length && 0 <= y && y < length){
			//if the square is a wall, return false
			if((x==0 && y==0) || (x==0 && y ==length-1) ||	
					(x==length-1 && y==0) || (x==length-1 && y==length-1)){
				return false;
			}
			return true;
		}
		return false;
	}

	private void listAdd(Chessman chessman) {
		if (chessman.color == Square.BLACK) {
			blackChessmen.insertBack(chessman);
		}
		if (chessman.color == Square.WHITE) {
			whiteChessmen.insertBack(chessman);
		}
	}

	public Square getSquareAt(int x, int y){
		return squares[x][y];
	}

	public int getCount(int color) {
		if (color == Square.BLACK) {
			return getBlackCount();
		}
		if (color == Square.WHITE) {
			return getWhiteCount();
		}
		return -1;
	}

	public int getWhiteCount() {
		return whiteChessmen.length();
	}

	public int getBlackCount() {
		return blackChessmen.length();
	}

	public List<Move> getValidMoves(int color) {
		List<Move> moves = new DList<Move>();
		if (getCount(color) < 10) {
			for (int x = 0; x < length; x++) {
				for (int y = 0; y < length; y++) {
					Move addMove = new Move(x, y);
					if (isValidMove(addMove, color)) {
						moves.insertBack(addMove);
					}
				}
			}
		}
		if (getCount(color) == 10) {
			// TODO
			List<Chessman> chessmen = getChessmen(color);
			while(chessmen.hasNext()){
				Chessman chessman = chessmen.next();
				for (int x = 0; x < length; x++) {
					for (int y = 0; y < length; y++) {
						Move stepMove = new Move(x, y, chessman.getX(), chessman.getY());
						if (isValidMove(stepMove, color)) {
							moves.insertBack(stepMove);
						}
					}
				}
			}
		}
		return moves;
	
	}

	public List<Chessman> getChessmen(int color) {
		if(color == Square.BLACK)
			return blackChessmen;
		if(color == Square.WHITE)
			return whiteChessmen;
		return null;
	}

	public int getNextColor(){
		return nextColor;
	}

	public boolean isValidMove(Move move, int color) {
		if (nextColor == color) {
			switch (move.moveKind) {
			case Move.ADD:
				Square newPosition = squares[move.x1][move.y1];
				if(newPosition.validity == Square.BOTH_VALID || newPosition.validity == color){
					return true;
				}
				return false;
			case Move.STEP:
				if(move.x1 == move.x2 && move.y1 == move.y2){
					return false;
				}
				//First step, removes the chessman, then updates validity.
				Chessman chessman = squares[move.x2][move.y2].remove();
				updateNearbyValidity(chessman, Move.STEP);
				//Second step, checks whether the ADD move is valid.
				Boolean isValid = isValidMove(new Move(move.x1, move.y1), color);
				//Third step, puts the chessman back, then updates validity.
				squares[move.x2][move.y2].add(chessman);
				updateNearbyValidity(chessman, Move.ADD);
				return isValid;
			case Move.QUIT:
				// TODO
				return false;
			}
		}
		return false;
	}

	/**
	 * updates "this" GameBoard, m should be examined by isValidMove(m, color)
	 * before update(m, color). Then changes nextColor and updates
	 * isValidForBlack & isValidForWhite in every square of "this" GameBoard.
	 * 
	 * @param m
	 *            is a valid Move.
	 * @param color
	 *            is the color of the Move m, 0 == black, 1 == white.
	 */
	public void updateBoard(Move move, int color) {
		Chessman chessman = null;
		switch (move.moveKind) {
		case Move.ADD:
			chessman = new Chessman(move, color);
			this.listAdd(chessman);
			break;
		case Move.STEP:
			chessman = squares[move.x2][move.y2].remove();
			updateNearbyValidity(chessman, Move.STEP);
			chessman.doMove(move);
			break;
		case Move.QUIT:
			// TODO
			return;
		}
		squares[move.x1][move.y1].add(chessman);
		this.nextColor = 1 - color;
		updateNearbyValidity(chessman, Move.ADD);
	}

	public int evaluation(GameBoard board) {
		return 0;
		// TODO
	
	}

	@Override
	public String toString() {
		String string = "      SquareValidity         "
				+ "    ChessmanInfo\r\n";
		for(int y = 0; y < length; y++){
			for(int x = 0; x < length; x++){
				string += "[" + squares[x][y].getValidity() + "]";
			}
			string += "   ";
			for(int x = 0; x < length; x++){
				int color = squares[x][y].color;
				if(color == Square.BLANK){
					string += "[" + " " + "]";
				} else{
					string += "[" + squares[x][y].color + "]";
				}
			}
			string += "\r\n";
		}
		string += "-------------------------------------"
				+ "--------------\r\n";
		return string;
	}

	public static void main(String[] args) {
		GameBoard board = new GameBoard();
		System.out.println(board);
	}
}
