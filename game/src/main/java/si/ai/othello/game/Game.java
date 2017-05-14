package si.ai.othello.game;


import si.ai.othello.game.utils.io.Display;
import si.ai.othello.game.player.Player;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public class Game {


    private Player white;
    private Player black;
    private Display display;
    private Board board; // todo [col][row]

    private Player currentPlayer;

    public Game(Player white, Player black, Display display) {
        this.white = white;
        this.black = black;
        this.display = display;
        this.board = new Board(this);

        //todo set currentPlayer (white/black)
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
            currentPlayer.nextMove();
            board.updateCones();
            display.updateBoard(board.getBoard());
        } while (setNextPlayer() != null);

        return currentPlayer;
    }

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
     * @return 0 if current is white, 1 if its black
     */
    public boolean getCurrentColor() {
        return currentPlayer != white;
    }

    private void setCurrentAsOpponent() {
        if (currentPlayer == white) {
            currentPlayer = black;
        } else {
            currentPlayer = white;
        }
    }
}
