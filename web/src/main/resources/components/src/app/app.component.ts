import {Component, enableProdMode} from '@angular/core';
import {ApiService} from './api/api.service';
import {Player} from "./util/Player";
import {GameState} from "./util/GameState";
import {isUndefined} from "util";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'app works!';
    gameState: GameState;
    players: Player[];
    winner: Player = null;
    gameStarted = false;

    whiteScore;
    blackScore;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.players = [new Player('', true), new Player('', false)];
    }

    ngOnChanges() {
        // this.resetCurrentScore();
    }

    //todo delete this
    loadTitle() {
        this.apiService.getTitle()
            .subscribe(title => this.title = title, err => {
                console.log(err)
            });
    }

    //todo insert player names from input form
    startNewGame(white: Player, black: Player) {
        this.winner = null;
        this.gameStarted = true;
        this.apiService.startNewGame(white, black)
            .subscribe(gameState => {
                this.gameState = gameState;
                this.boardChangedEvent(gameState);
            }, err => {
                console.log(err)
            });
    }

    gameEndedEvent(winner) {
        //todo implement gameEnd actions
        this.winner = winner;
        this.gameStarted = false;
    }

    playersChangedEvent(players) {
        this.players = players;
        this.startNewGame(players[0], players[1]);
    }

    boardChangedEvent(gameState) {
        this.whiteScore = gameState.whiteScore;
        this.blackScore = gameState.blackScore;
    }

    getWinnerName() {
        if (this.winner != null)
            return this.winner.name;
        else
            return '';
    }
}