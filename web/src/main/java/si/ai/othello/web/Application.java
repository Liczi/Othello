package si.ai.othello.web;

import com.google.gson.Gson;
import si.ai.othello.game.Board;
import spark.Spark;

import static si.ai.othello.web.util.JsonUtil.json;
import static si.ai.othello.web.util.JsonUtil.toJson;
import static spark.Spark.*;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Application {
    public static void main(String[] args) {
//        Spark.staticFileLocation("/components/src");

        enableCORS("http://localhost:4200", "ALL", "ALL");
//        after((req, res) -> {
//            res.type("application/json");
//        });



        final Boolean[] tab = {true, false};
//        get("/test", (req, res) -> new TestToJson(1, "string", true, tab), json());
        //todo extract to Board package and BoardController class
        get("/board", (req, res) -> new Boolean[8][8], json());
        get("/title", (req, res) -> "Reversi", json());
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
