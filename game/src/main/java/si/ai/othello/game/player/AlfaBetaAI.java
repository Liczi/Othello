package si.ai.othello.game.player;

import si.ai.othello.game.Board;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.utils.Pointer;

import static si.ai.othello.game.utils.BoardUtils.moveAt;

/**
 * @author Jakub Licznerski
 *         Created on 11.05.2017.
 */
public class AlfaBetaAI implements IPlayer {
    private String name;
    private boolean color;
    private int maxTreeDepth;
    private EvaluationHeuristic heuristic;

    public AlfaBetaAI(String name, boolean color, int maxTreeDepth, EvaluationHeuristic heuristic) {
        this.name = name;
        this.color = color;
        this.maxTreeDepth = maxTreeDepth;
        this.heuristic = heuristic;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getColor() {
        return color;
    }

    @Override
    public PlayerType getPlayerType() {
        return PlayerType.ALFA_BETA;
    }

    @Override
    public Pointer nextMove(Board board) {
        Pointer result = null;

        Pointer [] moves = board.getAvailableMoves(this.color);
        double [] values = new double[moves.length];
        for (int i = 0; i < moves.length; i++) {
            values[i] = maximize(
                    moveAt(board.cloneBoard(), moves[i], this.color),
                    !this.color,
                    1);
        }

      double maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxVal) {
                maxVal = values[i];
                result = moves[i];
            }
        }
        return result;
    }


    private double maximize(Boolean[][] currentBoard, boolean currentColor, int currentDepth, double alfa, double beta) {
        //todo consider renaming to currentAlfa and currentBeta
        double currentValue = Integer.MIN_VALUE;

    }

    private double minimize() {

    }
}
