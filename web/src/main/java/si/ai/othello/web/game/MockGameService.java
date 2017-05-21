package si.ai.othello.web.game;

import si.ai.othello.game.Game;

import static si.ai.othello.game.Game.WHITE;
import static si.ai.othello.web.Application.BOARD_SERVICE;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class MockGameService implements GameService {


    @Override
    public GameState getGameState() {

        Player player = new Player("Kaziu", WHITE);

        return new GameState(
                BOARD_SERVICE.getBoard(),
                BOARD_SERVICE.getAvailableMoves(),
                player
        );
    }
}
