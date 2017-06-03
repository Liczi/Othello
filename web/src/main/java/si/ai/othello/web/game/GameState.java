package si.ai.othello.web.game;

import lombok.*;
import si.ai.othello.game.utils.Pointer;
import si.ai.othello.web.game.player.Player;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
@Data
@AllArgsConstructor
public class GameState {
    @NonNull
    private Boolean[][] board;
    @NonNull
    private Pointer[] moves;
    private Player player;
    private boolean isWinner;
    private int whiteScore;
    private int blackScore;
}
