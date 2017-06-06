package si.ai.othello.game;


import si.ai.othello.game.player.IPlayer;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;

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
    private Display display;

    public Game(IPlayer white, IPlayer black) {
        this.white = white;
        this.black = black;

        this.board = new Board();
        this.display = new ConsoleDisplay();
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
    }

    //todo implement EndOfGame as an application event or exception
    public IPlayer setNextPlayer() {
        //change current player
        setCurrentAsOpponent();
        if (board.isEndOfGame(getCurrentColor())) {
            if (board.getCurrentBlack() + board.getCurrentWhite() >= 64) {
                if (board.getCurrentWhite() == board.getCurrentBlack())
                    throw new UnsupportedOperationException();
                else if (board.getCurrentWhite() > board.getCurrentBlack()) {
                    currentPlayer = white;
                } else
                    currentPlayer = black;
            } else {
                setCurrentAsOpponent();
            }
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

    public IPlayer testStartGame(boolean isWhiteStarting) {
        currentPlayer = isWhiteStarting ? white : black;
        //show initial state
        //System.out.printf("%s's turn", isWhiteStarting ? "white" : "black"); //todo vulnerable for isWhiteStarting variable change
        display.updateBoard(getBoard().getBoard());
        System.out.println();
        do {
            long now = System.currentTimeMillis();
            System.out.printf("%s's turn", currentPlayer.getColor() ? "white" : "black");
            board.moveAt(currentPlayer.nextMove(board), getCurrentColor());
            long time = System.currentTimeMillis() - now;
            board.updateCones();
            display.updateBoard(board.getBoard());
            System.out.printf("Computed in %fs\n\n", time / 1000d);
        } while (setNextPlayer() != null);

        return currentPlayer;
    }
}
