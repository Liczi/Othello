package si.ai.othello.game;


import si.ai.othello.game.utils.io.Display;
import si.ai.othello.game.player.Player;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public class Game {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    private Player white;
    private Player black;
    private Display display;
    private Board board; // [col][row]

    private Player currentPlayer;

    public Game(Player white, Player black, Display display) {
        this.white = white;
        this.black = black;
        this.display = display;
        this.board = new Board(this);
    }

    /**
     * Implements the game loop and returns the winning player;
     *
     * @param isWhiteStarting declare if white cones are starting
     * @return the winner
     */
    public Player startGame(boolean isWhiteStarting) {
        currentPlayer = isWhiteStarting ? white : black;

        do {
            currentPlayer.nextMove(); //todo it could return null or throw exception when no moves can be done
            board.updateCones();
            display.updateBoard(board.getBoard());
        } while (setNextPlayer() != null);

        return currentPlayer;
    }

    //todo implement EndOfGame as an application event or exception
    private Player setNextPlayer() {
        //change current player
        setCurrentAsOpponent();
        if (board.isEndOfGame()) {
            setCurrentAsOpponent();
            return null;
        } else
            return currentPlayer;
    }

    /**
     * @return 0 if current is black, 1 if its white
     */
    public boolean getCurrentColor() {
        return currentPlayer == white;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentAsOpponent() {
        if (currentPlayer == white) {
            currentPlayer = black;
        } else {
            currentPlayer = white;
        }
    }
}
