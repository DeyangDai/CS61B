/* MachinePlayer.java */

package player;

import board.GameBoard;
import list.InvalidNodeException;
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

	// Returns a new move by "this" player. Internally records the move (updates
	// the internal game board) as a move by "this" player.
	public Move chooseMove() {
		// TODO
		List<Move> moves = board.getValidMoves(color);
		Move bestMove = null;
		try {
			bestMove = moves.front().item();
		} catch (InvalidNodeException e) {
			e.printStackTrace();
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
		if (board.isValidMove(m, color)) {
			board.updateBoard(m, color);
			return true;
		}
		return false;
	}

}
