package si.ai.othello.web.game.player;

import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.player.*;

/**
 * @author Jakub Licznerski
 *         Created on 02.06.2017.
 */
public class PlayerFactory {

    public IPlayer getIPlayer(Player player, EvaluationHeuristic heuristic) {
        PlayerType type = player.getType();

        switch (type) {
            case HUMAN: return new Human(player.getName(), player.isColor());
            case MIN_MAX: return new MinMaxAi(player.getName(), player.isColor(), 4, heuristic);
            //todo fill this
            case ALFA_BETA: return new AlfaBetaAI();
            default: return null;
        }
    }
}
