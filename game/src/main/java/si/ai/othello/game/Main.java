package si.ai.othello.game;

import si.ai.othello.game.heuristics.AdvStaticBoardHeuristic;
import si.ai.othello.game.heuristics.PMCSHeuristic;
import si.ai.othello.game.heuristics.StaticBoardHeuristic;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.player.AlfaBetaAI;
import si.ai.othello.game.player.IPlayer;
import si.ai.othello.game.player.MinMaxAi;

import static si.ai.othello.game.Game.BLACK;
import static si.ai.othello.game.Game.WHITE;

/**
 * @author Jakub Licznerski
 *         Created on 06.05.2017.
 */
public class Main {

    public static void main(String[] args) {
        EvaluationHeuristic staticHeuristic = new StaticBoardHeuristic();
        EvaluationHeuristic advStaticHeuristic = new AdvStaticBoardHeuristic();
        EvaluationHeuristic pmcsHeuristic = new PMCSHeuristic();

        IPlayer minmaxW = new MinMaxAi("MinmaxW", WHITE, 6, staticHeuristic);
        IPlayer minmaxB = new MinMaxAi("MinmaxB", BLACK, 6, staticHeuristic);

        IPlayer alfabetaW = new AlfaBetaAI("AlfaBetaW", WHITE, 7, staticHeuristic);
        IPlayer alfabetaB = new AlfaBetaAI("AlfaBetaB", BLACK, 7, staticHeuristic);

        IPlayer newHeuristicW = new AlfaBetaAI("advStaticHeuristic", WHITE, 5, advStaticHeuristic);
        IPlayer newHeuristicB = new AlfaBetaAI("static", BLACK, 5, staticHeuristic);

        Game game = new Game(
                newHeuristicW,
                newHeuristicB
        );


        IPlayer winner = game.testStartGame(false);
        System.out.println("White: " + game.getBoard().getCurrentWhite() + ", Black: " + game.getBoard().getCurrentBlack());
        System.out.println("Wins: " + winner.getName());


    }
}
