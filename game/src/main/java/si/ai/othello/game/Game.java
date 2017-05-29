package si.ai.othello.game;


import si.ai.othello.game.utils.io.Display;
import si.ai.othello.game.utils.io.player.IPlayer;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public class Game {
    public static final boolean WHITE = true;
    public static final boolean BLACK = false;

    private IPlayer white;
    private IPlayer black;
    private Board board; // [col][row]

    private IPlayer currentPlayer;

    public Game(IPlayer white, IPlayer black) {
        this.white = white;
        this.black = black;
       //this.display = display;
        this.board = new Board(this);
    }

    /**
     * Implements the game loop and returns the winning player;
     *
     * @param isWhiteStarting declare if white cones are starting
     * @return the winner
     */
    public IPlayer startGame(boolean isWhiteStarting) {
        currentPlayer = isWhiteStarting ? white : black;
        return currentPlayer;
//        do {
//            currentPlayer.nextMove(); //todo it could return null or throw exception when no moves can be done
//            board.updateCones();
//           // display.updateBoard(board.getBoard());
//        } while (setNextPlayer() != null);
//
//        return currentPlayer;
    }

    //todo implement EndOfGame as an application event or exception
    public IPlayer setNextPlayer() {
        //change current player
        setCurrentAsOpponent();
        if (board.isEndOfGame()) {
            setCurrentAsOpponent();
            return null;
        } else
            return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * @return 0 if current is black, 1 if its white
     */
    public boolean getCurrentColor() {
        return currentPlayer == white;
    }

    public IPlayer getCurrentPlayer() {
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
