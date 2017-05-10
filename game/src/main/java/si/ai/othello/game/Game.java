package si.ai.othello.game;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Jakub Licznerski
 *         Created on 10.05.2017.
 */
public class Game {
    public static final int BOARD_SIZE = 8;

    private Player white;
    private Player black;
    private Boolean[][] board; // todo [col][row]
    private int currentWhite;
    private int currentBlack;

    private Player currentPlayer;

    public Game(Player white, Player black) {
        this.white = white;
        this.black = black;

        board = new Boolean[BOARD_SIZE][BOARD_SIZE];
        setInitialCones();
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
            updateCones();
            displayGameState();
        } while ((currentPlayer = nextPlayer()) != null);

        return currentPlayer;
    }


    private void updateCones() {
        //todo update number of current cones of each player
        throw new NotImplementedException();
    }

    private Player nextPlayer() {

        //todo check the state of the game, and return null if the game has ended

        //todo implement body
        throw new NotImplementedException();

//        return currentPlayer == white ? black : white;
    }

    private void setInitialCones() {
        currentWhite = 2;
        currentBlack = 2;

        throw new NotImplementedException();
        //todo implement initial cones positions in the center (2 cones of each color)
    }

    private void displayGameState() {
        //todo signal web module to display current game state

        throw new NotImplementedException();
    }

    public Boolean[][] getBoard() {
        return board;
    }

    /**
     *
     * @return 0 if current is white, 1 if its black
     */
    public boolean getCurrentColor() {
        return currentPlayer == white ? false : true;
    }
}
