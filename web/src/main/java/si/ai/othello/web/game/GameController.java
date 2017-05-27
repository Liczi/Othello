package si.ai.othello.web.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.web.util.JsonUtil;
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

    public static Route newGame() {
        return (request, response) -> GAME_SERVICE.newGame(
                new Player(request.queryParams("whiteName"), true),
                new Player(request.queryParams("blackName"), false)
        );
    }

    public static Route move() {
        return (request, response) -> GAME_SERVICE.move(new Pointer(
                Integer.parseInt(request.queryParams("colIndex")),
                Integer.parseInt(request.queryParams("rowIndex"))
        ));
    }
}
