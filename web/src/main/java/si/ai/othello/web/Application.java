package si.ai.othello.web;

import spark.Spark;

import static spark.Spark.get;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Application {
    public static void main(String[] args) {


        Spark.staticFileLocation("/public");




        get("/hello_world", (req, res) -> "Hello World");
    }
}
