import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";
import {Pointer} from "../util/Pointer";
import {Player} from "../util/Player";

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    board: Boolean[][];
    availableMoves: Pointer[];
    currentPlayer: Player;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        // this.loadBoard();
        this.loadGameState();
    }

    loadGameState() {
        this.apiService.getGameState()
            .subscribe(state => {
                this.board = state.board;
                this.availableMoves = state.moves;
                this.currentPlayer = state.player;
            }, err => {
                console.log(err)
            });
    }

    loadBoard() {
        this.apiService.getBoard()
            .subscribe(board => this.board = board, err => {
                console.log(err)
            });
    }

    rowCustomTrack(index: number, obj: any) {
        return index;
    }

    isAvailableMove(col: number, row: number) {
        for (let i = 0, l = this.availableMoves.length; i < l; i++) {
            let pointer = this.availableMoves[i];
            if (pointer.colIndex === col && pointer.rowIndex === row)
                return true;
        }

        return false;
    }
}
