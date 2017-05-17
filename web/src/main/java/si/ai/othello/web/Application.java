package si.ai.othello.web;

import spark.Spark;

import static si.ai.othello.web.util.JsonUtil.json;
import static spark.Spark.after;
import static spark.Spark.get;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Application {
    public static void main(String[] args) {
        Spark.staticFileLocation("/components/src");

        after((req, res) -> {
            res.type("application/json");
        });



        final Boolean[] tab = {true, false};
        get("/pointer", (req, res) -> new TestToJson(1, "string", true, tab), json());
    }
}
