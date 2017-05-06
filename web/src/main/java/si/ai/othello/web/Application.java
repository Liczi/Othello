package si.ai.othello.web;

import static spark.Spark.get;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Application {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
