package si.ai.othello.web.game;

import si.ai.othello.game.Game;
import si.ai.othello.game.heuristics.BasicTestHeuristic;
import si.ai.othello.game.heuristics.EvaluationHeuristic;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.game.utils.io.ConsoleDisplay;
import si.ai.othello.game.utils.io.Display;
import si.ai.othello.web.game.player.Player;

import static si.ai.othello.game.player.PlayerType.HUMAN;
import static si.ai.othello.web.Application.PLAYER_FACTORY;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public class BasicGameService implements GameService {

    private Game game;

    //todo check if needed
    @Override
    public GameState getGameState() {
        return getCurrentGameState(false);
    }

    @Override
    public GameState newGame(Player white, Player black) {

        //todo customize it ?
        EvaluationHeuristic heuristic = new BasicTestHeuristic();

        game = new Game(
                PLAYER_FACTORY.getIPlayer(white, heuristic),
                PLAYER_FACTORY.getIPlayer(black, heuristic)
        );
        game.startGame(true);

        //white starts
        return getCurrentGameState(false);
    }

    @Override
    public GameState move(Pointer at) {
        boolean isWinner = false;

        game.getBoard().moveAt(at, game.getCurrentColor());
        game.getBoard().updateCones();
        if (game.setNextPlayer() == null) {
            isWinner = true;
        }

        //todo delete
        Display display = new ConsoleDisplay();
        display.updateBoard(game.getBoard().getBoard());

        return getCurrentGameState(isWinner);
    }

    @Override
    public GameState moveAI() {
        boolean isWinner = false;

        //AI move
        Pointer nextMove = game.getCurrentPlayer().nextMove(game.getBoard());
        game.getBoard().moveAt(nextMove, game.getCurrentColor());
        game.getBoard().updateCones();
        if (game.setNextPlayer() == null) {
            isWinner = true;
        }

        //todo delete
        Display display = new ConsoleDisplay();
        display.updateBoard(game.getBoard().getBoard());

        return getCurrentGameState(isWinner);
    }

    private GameState getCurrentGameState(boolean isWinner) {
        Player current = new Player(game.getCurrentPlayer());

        return new GameState(
                game.getBoard().getBoard(),
                current.getType() == HUMAN ? game.getBoard().getAvailableMoves(current.isColor()) : new Pointer[0],
                current,
                isWinner,
                game.getBoard().getCurrentWhite(),
                game.getBoard().getCurrentBlack()
        );
    }
}