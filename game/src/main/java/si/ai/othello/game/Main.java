package si.ai.othello.game;

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
        EvaluationHeuristic heuristic = new StaticBoardHeuristic();
        Game game = new Game(
                new MinMaxAi("Minmax1", WHITE, 4, heuristic),
                new MinMaxAi("Minmax2", BLACK,4, heuristic)
        );

//        Game game = new Game(
//                new AlfaBetaAI("AlfaBeta1", WHITE, 6, heuristic),
//                new AlfaBetaAI("AlfaBeta2", BLACK, 7, heuristic)
//        );

        IPlayer winner = game.testStartGame(true);
        System.out.println("White: " + game.getBoard().getCurrentWhite() + ", Black: " + game.getBoard().getCurrentBlack());
        System.out.println("Wins: " + winner.getName());


    }
}
