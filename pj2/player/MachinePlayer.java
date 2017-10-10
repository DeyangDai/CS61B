/* MachinePlayer.java */

package player;

import board.GameBoard;
import board.Square;
import list.List;

/**
 * An implementation of an automatic Network player. Keeps track of moves made
 * by both players. Can select a move for itself.
 */
public class MachinePlayer extends Player {

	private int color;
	private int opponentColor;
	private int searchDepth;
	private GameBoard board;

	// Creates a machine player with the given color. Color is either 0 (black)
	// or 1 (white). (White has the first move.)
	public MachinePlayer(int color) {
		this.color = color;
		opponentColor = 1 - color;
		board = new GameBoard();
	}

	// Creates a machine player with the given color and search depth. Color is
	// either 0 (black) or 1 (white). (White has the first move.)
	public MachinePlayer(int color, int searchDepth) {
		this.color = color;
		opponentColor = 1 - color;
		this.searchDepth = searchDepth;
		board = new GameBoard();
	}

	public void showGameBoard(){
		System.out.print(board);
	}

	// Returns a new move by "this" player. Internally records the move (updates
	// the internal game board) as a move by "this" player.
	public Move chooseMove() {
		// TODO
		List<Move> moves = board.getValidMoves(color);
		int movesSize = moves.length();
		int random = (int) (Math.random() * movesSize + 1);
		Move bestMove = null;
		int count = 0;
		while(moves.hasNext()){
			Move move = moves.next();
			count++;
			if(count == random){
				bestMove = move;
				break;
			}
		}
		board.updateBoard(bestMove, color);
		return bestMove;
	}

	// If the Move m is legal, records the move as a move by the opponent
	// (updates the internal game board) and returns true. If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player. This method allows your opponents to inform you of their moves.
	public boolean opponentMove(Move m) {
		if (board.isValidMove(m, opponentColor)) {
			board.updateBoard(m, opponentColor);
			return true;
		}
		return false;
	}

	// If the Move m is legal, records the move as a move by "this" player
	// (updates the internal game board) and returns true. If the move is
	// illegal, returns false without modifying the internal state of "this"
	// player. This method is used to help set up "Network problems" for your
	// player to solve.
	public boolean forceMove(Move m) {
		if (board.isValidMove(m, board.getNextColor())) {
			board.updateBoard(m, board.getNextColor());
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		MachinePlayer machinePlayer = new MachinePlayer(Square.WHITE);
		MachinePlayer opponentPlayer = new MachinePlayer(Square.BLACK);
		machinePlayer.showGameBoard();
		
		Move machineMove = new Move(1,6);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		Move opponentMove = new Move(1, 4);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(5,1);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(2, 5);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(5,2);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(1,0);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(7,4);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(4,6);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(1,5);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(6,7);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(3,2);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(6,5);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(1,1);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(6,3);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(7,2);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(3,1);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(5,4);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(4,1);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(3,3);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(6,1);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(1,3,1,5);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(4,4,4,1);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(4,5,1,3);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(1,2,2,5);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = new Move(1,5,5,4);
		machinePlayer.forceMove(machineMove);
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = new Move(2,6,6,7);
		opponentPlayer.forceMove(opponentMove);
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		machineMove = machinePlayer.chooseMove();
		System.out.println("machine: " + machineMove);
		opponentPlayer.opponentMove(machineMove);
		machinePlayer.showGameBoard();
		
		opponentMove = opponentPlayer.chooseMove();
		System.out.println("opponent: " + opponentMove);
		machinePlayer.opponentMove(opponentMove);
		machinePlayer.showGameBoard();
		
		
		/*for(int i = 1; i <= 30; i++){
			Move machineMove = machinePlayer.chooseMove();
			//machinePlayer.forceMove(machineMove);
			System.out.println( i + ". machine: " + machineMove);
			opponentPlayer.opponentMove(machineMove);
			machinePlayer.showGameBoard();
			
			Move opponentMove = opponentPlayer.chooseMove();
			//opponentPlayer.forceMove(opponentMove);
			System.out.println( i + ". opponent: " + opponentMove);
			machinePlayer.opponentMove(opponentMove);
			machinePlayer.showGameBoard();
		}*/
		
		/*machinePlayer.forceMove(new Move(1, 2));
		System.out.println("add(1,2)");
		machinePlayer.showGameBoard();
		
		machinePlayer.forceMove(new Move(1, 1));
		System.out.println("add(1,1)");
		machinePlayer.showGameBoard();
		
		machinePlayer.forceMove(new Move(2, 3));
		System.out.println("add(2,3)");
		machinePlayer.showGameBoard();
		
		machinePlayer.forceMove(new Move(3, 2));
		System.out.println("add(3,2)");
		machinePlayer.showGameBoard();
		
		machinePlayer.forceMove(machinePlayer.chooseMove());
		machinePlayer.showGameBoard();*/
	}
}
