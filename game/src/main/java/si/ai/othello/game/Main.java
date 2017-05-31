package si.ai.othello.game;

import si.ai.othello.game.heuristics.BasicTestHeuristic;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.player.IPlayer;
import si.ai.othello.game.player.MinMaxAi;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;

import static si.ai.othello.game.Game.BLACK;
import static si.ai.othello.game.Game.WHITE;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Main {

    public static void main(String[] args) {
        EvaluationHeuristic heuristic = new BasicTestHeuristic();
        Display display = new ConsoleDisplay();
        Game game = new Game(
                new MinMaxAi("Minmax1", WHITE, 4, heuristic, display),
                new MinMaxAi("Minmax2", BLACK, 2, heuristic, display)
        );

        IPlayer winner = game.testStartGame(true);
        System.out.println("White: " + game.getBoard().getCurrentWhite() + ", Black: " + game.getBoard().getCurrentBlack());
        System.out.println("Wygrywa " + winner.getName());
    }
}
