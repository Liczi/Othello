package si.ai.othello.web.game;

import si.ai.othello.game.Game;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;
import si.ai.othello.game.player.Human;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class MockGameService implements GameService {

    private Game game;

    //todo check if needed
    @Override
    public GameState getGameState() {
        return getCurrentGameState(false);
    }

    @Override
    public GameState newGame(Player white, Player black) {
        //todo add AI player handling
        game = new Game(
                new Human(white.getName(), white.isColor()),
                new Human(black.getName(), black.isColor())
        );
        game.startGame(true);

        //white starts
        return getCurrentGameState(false);
    }

    @Override
    public GameState move(Pointer at) {
        boolean isWinner = false;

        game.getBoard().moveAt(at, game.getCurrentColor());
        game.getBoard().updateCones();
        if (game.setNextPlayer() == null) {
            isWinner = true;
        };

        //todo delete
        Display display = new ConsoleDisplay();
        display.updateBoard(game.getBoard().getBoard());

        return getCurrentGameState(isWinner);
    }

    private GameState getCurrentGameState(boolean isWinner) {
        Player current = new Player(game.getCurrentPlayer());

        return new GameState(
                game.getBoard().getBoard(),
                game.getBoard().getAvailableMoves(current.isColor()),
                current,
                isWinner,
                game.getBoard().getCurrentWhite(),
                game.getBoard().getCurrentBlack()
        );
    }
}
