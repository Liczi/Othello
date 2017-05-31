package si.ai.othello.web.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import si.ai.othello.game.player.IPlayer;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
@Data
@AllArgsConstructor
public class Player {
    @NonNull private String name;
    private boolean color;

    public Player(IPlayer player) {
        this.name = player.getName();
        this.color = player.getColor();
    }
}
