package si.ai.othello.game;


import si.ai.othello.game.player.IPlayer;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;

import java.util.ArrayList;
import java.util.List;

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

    private List<Long> whiteMovesTime;
    private List<Long> blackMovesTime;

    public Game(IPlayer white, IPlayer black) {
        this.white = white;
        this.black = black;

        this.board = new Board();
        this.display = new ConsoleDisplay();

        this.whiteMovesTime = new ArrayList<>();
        this.blackMovesTime = new ArrayList<>();
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

        if (board.isEndOfGame()) {
            //todo draw implementation
            if (board.getCurrentWhite() > board.getCurrentBlack()) {
                currentPlayer = white;
            } else
                currentPlayer = black;

            return null;
        }

        if (board.getAvailableMoves(getCurrentColor()).length <= 0) {
            if (board.getAvailableMoves(!getCurrentColor()).length <= 0) { //no more moves
                //todo draw implementation
                if (board.getCurrentWhite() > board.getCurrentBlack())
                    currentPlayer = white;
                else
                    currentPlayer = black;
                return null;
            }
            setCurrentAsOpponent();
            return currentPlayer;
        }

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

            //times counting
            if (currentPlayer == white)
                whiteMovesTime.add(time);
            else
                blackMovesTime.add(time);

        } while (setNextPlayer() != null);

        System.out.printf("\nMaximum white move time: %fs", whiteMovesTime.stream().max(Long::compare).get() / 1000f);
        System.out.printf("\nMaximum black move time: %fs\n", blackMovesTime.stream().max(Long::compare).get() / 1000f);
        System.out.printf("\nAverage white moves time: %fs", whiteMovesTime.stream().mapToInt(Long::intValue).average().getAsDouble() / 1000f);
        System.out.printf("\nAverage black moves time: %fs\n", blackMovesTime.stream().mapToInt(Long::intValue).average().getAsDouble() / 1000f);

        return currentPlayer;
    }
}
