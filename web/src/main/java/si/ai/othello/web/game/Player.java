package si.ai.othello.web.game;

import lombok.Data;
import lombok.NonNull;

/**
 * @author Jakub Licznerski
 *         Created on 21.05.2017.
 */
@Data
public class Player {
    @NonNull private String name;
    @NonNull private boolean color;

    public Player(si.ai.othello.game.player.Player player) {
        this.name = player.getName();
        this.color = player.getColor();
    }

    public Player(String name, boolean color) {
        this.name = name;
        this.color = color;
    }
}
