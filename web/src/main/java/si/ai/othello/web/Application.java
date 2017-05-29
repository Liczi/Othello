package si.ai.othello.web;

import si.ai.othello.web.board.BoardController;
import si.ai.othello.web.board.BoardService;
import si.ai.othello.web.board.MockBoardService;
import si.ai.othello.web.game.GameController;
import si.ai.othello.web.game.GameService;
import si.ai.othello.web.game.BasicGameService;
import si.ai.othello.game.player.PlayerType;
import si.ai.othello.web.game.player.PlayerFactory;

import static si.ai.othello.web.util.JsonUtil.json;
import static spark.Spark.*;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Application {
    public static BoardService BOARD_SERVICE;
    public static GameService GAME_SERVICE;
    public static PlayerFactory PLAYER_FACTORY;

    public static final String ACCEPT_TYPE_JSON = "application/json";

    public static void main(String[] args) {
        initializeServices();

        enableCORS("http://localhost:4200", "ALL", "ALL");

        final Boolean[] tab = {true, false};
//        get("/test", (req, res) -> new TestToJson(1, "string", true, tab), json());
        get("/board", BoardController.getBoard(), json());
        get("/game", GameController.getGameState(), json());
        get("/new", ACCEPT_TYPE_JSON, GameController.newGame(), json());
//        get("new", "application/json", (request, response) -> request.queryParams("param1"));
        get("/move", ACCEPT_TYPE_JSON, GameController.move(), json());
        get("/title", (req, res) -> "Reversi", json());
        get("/player_types", (request, response) ->  PlayerType.values(), json());
        get("moveAI", GameController.moveAI(), json());
    }

    private static void initializeServices() {
        BOARD_SERVICE = new MockBoardService();
        GAME_SERVICE = new BasicGameService();
        PLAYER_FACTORY = new PlayerFactory();
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
