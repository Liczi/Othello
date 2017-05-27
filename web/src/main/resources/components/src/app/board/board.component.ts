import {Component, Input, OnInit} from '@angular/core';
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
    // availableMoves: Pointer[];
    // currentPlayer: Player;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        // this.loadBoard();
        //this.loadGameState();
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

    // loadBoard() {
    //     this.apiService.getBoard()
    //         .subscribe(board => this.gameState.board = board, err => {
    //             console.log(err)
    //         });
    // }

    // rowCustomTrack(index: number, obj: any) {
    //     return index;
    // }

    isAvailableMove(col: number, row: number) {
        for (let i = 0, l = this.gameState.moves.length; i < l; i++) {
            let pointer = this.gameState.moves[i];
            if (pointer.colIndex === col && pointer.rowIndex === row)
                return true;
        }

        return false;
    }

    gameStateChangeEvent(gameState: GameState) {
        this.gameState = gameState;
        this.board = gameState.board;
    }
}
