import {Player} from "./Player";
import {Pointer} from "./Pointer";

export class GameState {
    board: Boolean[][];
    moves: Pointer[];
    player: Player;
}