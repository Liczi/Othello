package si.ai.othello.web;

import lombok.Data;
import lombok.NonNull;

/**
 * @author Jakub Licznerski
 *         Created on 17.05.2017.
 */
@Data
public class TestToJson {
    @NonNull private int integer;
    @NonNull private String string;
    @NonNull private Boolean aBoolean;
    @NonNull private Boolean[] tablBoolean;
}
