package si.ai.othello.web.board;

import spark.Route;

import static si.ai.othello.web.Application.BOARD_SERVICE;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class BoardController {

    public static Route getBoard() {
        return (req, res) -> BOARD_SERVICE.getBoard();
    }

}
