package si.ai.othello.web.game;

import spark.Route;

import static si.ai.othello.web.Application.GAME_SERVICE;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class GameController {

    public static Route getGameState() {
        return ((request, response) -> GAME_SERVICE.getGameState());
    }
}
