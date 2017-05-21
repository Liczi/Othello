package si.ai.othello.web.game;

import lombok.Data;
import lombok.NonNull;
import si.ai.othello.game.utils.Pointer;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
@Data
public class GameState {
    @NonNull
    private Boolean[][] board;
    @NonNull
    private Pointer[] moves;
    @NonNull
    private Player player;
}
