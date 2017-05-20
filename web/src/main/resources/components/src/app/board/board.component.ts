import {Component, OnInit} from '@angular/core';
import {ApiService} from "../api/api.service";

@Component({
    selector: 'app-board',
    templateUrl: './board.component.html',
    styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

    board: Boolean[][];
    table;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.table = [
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8],
            [1, 2, 3, 4, 5, 6, 7, 8]
        ];

        this.loadBoard();
    }

    loadBoard() {
        this.apiService.getBoard()
            .subscribe(board => this.board = board, err => {
                console.log(err)
            });
    }

}
