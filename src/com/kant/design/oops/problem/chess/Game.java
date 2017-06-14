/**
 * 
 */
package com.kant.design.oops.problem.chess;

import java.util.List;

/**
 * The basic idea is that Game/Board/etc simply stores the state of the game.
 * You can manipulate them to e.g. set up a position, if that's what you want. I
 * have a class that implements my IGameRules interface that is responsible for:
 * Determining what moves are valid, including castling and en passant.<br/>
 * Determining when players are in check/checkmate/stalemate. <br/>
 * Executing moves. <br/>
 * Separating the rules from the game/board classes also means you can implement
 * variants relatively easily. All methods of the rules interface take a Game
 * object which they can inspect to determine which moves are valid.
 * 
 * 
 * http://massivetechinterview.blogspot.in/2015/07/design-chess-game-using-oo-
 * principles.html
 * 
 * @author shaskant
 *
 */
public class Game {
	private Board board;
	private List<Move> movelist;
	private PieceColor turn;
	private Spot doublePawnPush; // Used for tracking valid en passant captures
	private IChessRules rulesManager;
	private GameStatus status;
	private Player p1, p2;

	private void setTurn(PieceColor color) {
		Player currentPlayer;
		turn = color;
		currentPlayer = (turn == PieceColor.Black) ? p2 : p1;

	}

	private void play() {
		while (checkStatus() != "GameOver") {
			changeTurn();
		}
	}

	private String checkStatus() {
		return null;
	}

	public void changeTurn() {

	}

}
