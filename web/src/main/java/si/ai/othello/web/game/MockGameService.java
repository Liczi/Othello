package si.ai.othello.web.game;

import si.ai.othello.game.Game;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.game.utils.io.player.Human;

import static si.ai.othello.game.Game.WHITE;
import static si.ai.othello.web.Application.BOARD_SERVICE;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class MockGameService implements GameService {

    private Game game;

    @Override
    public GameState getGameState() {
        return getCurrentGameState();
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
        return getCurrentGameState();
    }

    @Override
    public GameState move(Pointer at) {
        game.getBoard().moveAt(at, game.getCurrentColor());
        game.getBoard().updateCones();

        return getCurrentGameState();
    }

    private GameState getCurrentGameState() {
        Player current = new Player(game.getCurrentPlayer());

        return new GameState(
                game.getBoard().getBoard(),
                game.getBoard().getAvailableMoves(current.isColor()),
                current
        );
    }
}
