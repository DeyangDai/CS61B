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
		
		List<Square> nearbySquares = getNearbySquares(chessman.getCoordinate(), 2);
		
		while(nearbySquares.hasNext()){
			
			Square square = nearbySquares.next();
			
			if(square.getChessman() == null){
				updateValidityAt(square.getCoordinate(), chessman.color);
			} else{
				continue;
			}
			
		}
		
		//if moveKind == STEP, update another color validity.
		if(moveTpye == Move.STEP){
			updateValidityAt(chessman.getCoordinate(), 1 - chessman.color);
		}
	}

	private void updateValidityAt(Coordinate coordinate, int color) {
		List<Square> squares = getNearbySquares(coordinate, 1);
		List<Square> sameColorSquares = getSameColorSquares(squares, color);

		if(sameColorSquares.length() >= 2){
			degradeValidityAt(coordinate, color);
			return;
		}
		if(sameColorSquares.length() == 1){
			Square square = sameColorSquares.next();
			List<Square> nearbySquares = getNearbySquares(square.getCoordinate(), 1);
			if(getSameColorSquares(nearbySquares, color).length() >= 1){
				degradeValidityAt(coordinate, color);
				return;
			}
		}
		upgradeValidityAt(coordinate, color);
	}

	private void upgradeValidityAt(Coordinate coordinate, int color) {
		Square square = squares[coordinate.getX()][coordinate.getY()];
		if(square.isEdge()){
			if(square.getChessman() == null && square.validity == Square.NO_VALID){
				if(color == Square.BLACK && square.isBlackEdge() || color == Square.WHITE && square.isWhiteEdge()){
					square.validity = color;
				}
			}
			return;
		}
		if(square.getChessman() == null && square.validity == Square.NO_VALID){
			square.validity = color;
		}
		if(square.validity == (1 - color)){
			square.validity = 2;
		}
	}

	private void degradeValidityAt(Coordinate coordinate, int color) {
		Square square = squares[coordinate.getX()][coordinate.getY()];
		if(square.validity == Square.BOTH_VALID){
			square.validity = 1 - color;
		}
		if(square.validity == color){
			square.validity = Square.NO_VALID;
		}
	}

	private List<Square> getSameColorSquares(List<Square> squares, int color) {
		List<Square> sameColorSquares = new DList<Square>();
		while(squares.hasNext()){
			Square square = squares.next();
			Chessman chessman = square.getChessman();
			if(null != chessman && chessman.getColor() == color){
				sameColorSquares.insertBack(square);
			}
		}
		return sameColorSquares;
	}

	private List<Square> getNearbySquares(Coordinate coordinate, int layer){
		List<Square> list = new DList<Square>();
		for(int i = -layer; i <= layer; i++){
			for(int j = -layer; j <= layer; j++){
				if(i == 0 && j == 0){
					continue;
				}
				if(squareExist(coordinate.getX()+i, coordinate.getY()+j)){
					Square square = squares[coordinate.getX()+i][coordinate.getY()+j];
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
				+ "       ChessmanInfo\r\n";
		string += "  0  1  2  3  4  5  6  7        0  1  2  3  4  5  6  7\r\n";
		
		for(int y = 0; y < length; y++){
			string += y;
			for(int x = 0; x < length; x++){
				string += "[" + squares[x][y].getValidity() + "]";
			}
			string += y + "    " + y;
			for(int x = 0; x < length; x++){
				int color = squares[x][y].state;
				if(color == Square.BLANK){
					string += "[" + " " + "]";
				} else{
					string += "[" + squares[x][y].state + "]";
				}
			}
			string += y + "\r\n";
		}
		string += "  0  1  2  3  4  5  6  7        0  1  2  3  4  5  6  7\r\n";
		string += "---------------------------------------------------\r\n";
		return string;
	}

}
