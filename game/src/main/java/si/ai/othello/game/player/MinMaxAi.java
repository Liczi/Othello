package si.ai.othello.game.player;

import si.ai.othello.game.Board;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static si.ai.othello.game.utils.BoardUtils.*;

/**
 * @author Jakub Licznerski
 *         Created on 11.05.2017.
 */
public class MinMaxAi implements IPlayer {

    private String name;
    private boolean color;
    private int maxTreeDepth;
    private EvaluationHeuristic heuristic;

    public MinMaxAi(String name, boolean color, int maxTreeDepth, EvaluationHeuristic heuristic) {
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
        return PlayerType.MIN_MAX;
    }

    @Override
    public Pointer nextMove(Board board) {
        Pointer[] availableMoves = board.getAvailableMoves(this.color);
        Pointer result = null;

        //recursion
        double[] values = new double[availableMoves.length];
        for (int i = 0; i < availableMoves.length; i++) {
            values[i] = evaluateNode(
                    moveAt(board.cloneBoard(), availableMoves[i], this.color),
                    !this.color,
                    1
            );
        }

        double maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxVal) {
                maxVal = values[i];
                result = availableMoves[i];
            }
        }
        return result;
    }

    private double evaluateNode(Boolean[][] currentBoard, boolean currentColor, int currentDepth) {
        //todo evaluate heuristics for the currentColor?
        if (currentDepth >= maxTreeDepth) {
            return heuristic.evaluate(currentBoard, this.color);
        }


        List<Pointer> moves = getAvailableMoves(currentBoard, currentColor)
                .collect(Collectors.toList());

        //if no moves, the opponent moves
        if (moves.size() <= 0) {
            return evaluateNode(currentBoard, !currentColor, currentDepth + 1);
        }

        //todo performance improvement: dont store it in array, evaluate in double[2] or on the run
        //recursion
        double[] values = new double[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            values[i] = evaluateNode(
                    moveAt(copyBoard(currentBoard), moves.get(i), currentColor),
                    !currentColor,
                    currentDepth + 1
            );
        }


        //minimize values
        if (currentColor != this.color) {
            return Arrays.stream(values).min().getAsDouble();
        }
        //maximize values
        else {
            return Arrays.stream(values).max().getAsDouble();
        }
    }
}
