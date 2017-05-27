package si.ai.othello.web.game;

import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
public interface GameService {
    GameState getGameState();
    GameState newGame(Player white, Player black);
    GameState move(Pointer at);
}
