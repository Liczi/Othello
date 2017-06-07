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
        EvaluationHeuristic heuristic = new AdvStaticBoardHeuristic();
        EvaluationHeuristic pmcsHeuristic = new PMCSHeuristic();

        IPlayer minmaxW = new MinMaxAi("MinmaxW", WHITE, 5, heuristic);
        IPlayer minmaxB = new MinMaxAi("MinmaxB", BLACK, 4, heuristic);

        IPlayer alfabetaW = new AlfaBetaAI("AlfaBetaW", WHITE, 6, heuristic);
        IPlayer alfabetaB = new AlfaBetaAI("AlfaBetaB", BLACK, 4, heuristic);

        IPlayer newHeuristicW = new AlfaBetaAI("AlfaBeta+HeurW", WHITE, 4, pmcsHeuristic);
        IPlayer newHeuristicB = new AlfaBetaAI("AlfaBeta+HeurB", BLACK, 6, pmcsHeuristic);

        Game game = new Game(
                newHeuristicW,
                alfabetaB
        );


        IPlayer winner = game.testStartGame(false);
        System.out.println("White: " + game.getBoard().getCurrentWhite() + ", Black: " + game.getBoard().getCurrentBlack());
        System.out.println("Wins: " + winner.getName());


    }
}
