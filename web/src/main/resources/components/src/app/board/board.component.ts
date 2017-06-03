import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ApiService} from "../api/api.service";
import {Pointer} from "../util/Pointer";
import {Player} from "../util/Player";
import {GameState} from "../util/GameState";
import {isUndefined} from "util";

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    @Input() gameState: GameState;
    board: Boolean[][];
    @Output() gameEndedEvent: EventEmitter<Player> = new EventEmitter();
    @Output() boardChangedEvent: EventEmitter<GameState> = new EventEmitter();


    test: boolean = false;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
    }

    ngOnChanges() {
        this.board = isUndefined(this.gameState) ? [] : this.gameState.board;
    }

    loadGameState() {
        this.apiService.getGameState()
            .subscribe(state => {
                this.gameState = state;
            }, err => {
                console.log(err)
            });
    }

    // async gameLoop() {
    //     while(isUndefined(this.gameState) || !this.gameState.isWinner) {
    //         if (!isUndefined(this.gameState) && this.gameState.player.type != "HUMAN")
    //             await this.apiService.moveAI(this.gameState).take(1).toPromise();
    //     }
    // }

    isAvailableMove(col: number, row: number) {
        for (let i = 0, l = this.gameState.moves.length; i < l; i++) {
            let pointer = this.gameState.moves[i];
            if (pointer.colIndex === col && pointer.rowIndex === row)
                return true;
        }

        return false;
    }

    //called when cone is set by player
    gameStateChangeEvent(gameState: GameState) {
        this.gameState = gameState;
        this.board = gameState.board;

        //emit game ended event with winner object
        if (gameState.isWinner) {
            this.boardChangedEvent.emit(gameState);
        }
        if (gameState.player.type !== "HUMAN") {
            //todo add opponent move here and update gameState
            this.apiService.moveAI()
                .subscribe(newGameState => this.gameStateChangeEvent(newGameState))
            //todo add in server side: dont return movesAvailable when player is not human!
        }
        else {
            this.boardChangedEvent.emit(gameState);
        }
    }
}
