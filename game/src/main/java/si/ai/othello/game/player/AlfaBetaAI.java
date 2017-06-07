package si.ai.othello.game.player;

import si.ai.othello.game.Board;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.utils.Pointer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static si.ai.othello.game.utils.BoardUtils.*;

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

        Pointer[] moves = board.getAvailableMoves(this.color);
        double[] values = new double[moves.length];
        for (int i = 0; i < moves.length; i++) {
            values[i] = minimize(
                    moveAt(board.cloneBoard(), moves[i], this.color),
                    !this.color,
                    1,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE);
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
        //leaf - return value
        if (currentDepth >= this.maxTreeDepth) {
            return heuristic.evaluate(currentBoard, this.color); //todo consider passing currentColor
        }

        List<Pointer> moves = getAvailableMoves(currentBoard, currentColor)
                .collect(Collectors.toList());

        //if no moves -> its a leaf
        if (moves.size() <= 0) {
            return heuristic.noAvailableMovesEvaluation(currentBoard, this.color, currentColor);
        }

        //initialize
        double currentValue = Integer.MIN_VALUE;

        double values[] = new double[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            if (currentValue > beta) //prune
                return currentValue;

            values[i] = minimize(
                    moveAt(copyBoard(currentBoard), moves.get(i), currentColor),
                    !currentColor,
                    currentDepth + 1,
                    alfa,
                    beta
            );

            if (values[i] > currentValue) {
                currentValue = values[i];
                alfa = currentValue;
            }
        }
        return currentValue;
    }

    private double minimize(Boolean[][] currentBoard, boolean currentColor, int currentDepth, double alfa, double beta) {
        //leaf - return value
        if (currentDepth >= this.maxTreeDepth) {
            return heuristic.evaluate(currentBoard, this.color); //todo consider passing currentColor
        }

        List<Pointer> moves = getAvailableMoves(currentBoard, currentColor)
                .collect(Collectors.toList());

        //if no moves -> its a leaf
        if (moves.size() <= 0) {
            return heuristic.noAvailableMovesEvaluation(currentBoard, this.color, currentColor);
        }

        //initialize
        double currentValue = Integer.MAX_VALUE;

        double values[] = new double[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            if (currentValue < alfa) //prune
                return currentValue;

            values[i] = maximize(
                    moveAt(copyBoard(currentBoard), moves.get(i), currentColor),
                    !currentColor,
                    currentDepth + 1,
                    alfa,
                    beta
            );

            if (values[i] < currentValue) {
                currentValue = values[i];
                beta = currentValue;
            }
        }
        return currentValue;
    }
}
