import {Component, ElementRef, ViewChild} from '@angular/core';
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

    @ViewChild('whiteText') whiteText: ElementRef;
    @ViewChild('blackText') blackText: ElementRef;

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.players = [new Player('', true, "HUMAN"), new Player('', false, "HUMAN")];
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
        this.resetStyles();
        this.apiService.startNewGame(white, black)
            .subscribe(gameState => {
                this.boardChangedEvent(gameState);
            }, err => {
                console.log(err)
            });
    }

    gameEndedEvent(winner: Player) {
        //todo implement gameEnd actions
        if (winner.color) {
            this.whiteText.nativeElement.style.backgroundColor = "green";
        }
        else {
            this.blackText.nativeElement.style.backgroundColor = "green";
        }

        this.winner = winner;
        //this.gameStarted = false;
    }

    playersChangedEvent(players) {
        this.players = players;
        this.startNewGame(players[0], players[1]);
    }

    boardChangedEvent(gameState: GameState) {
        this.gameState = gameState;
        this.whiteScore = gameState.whiteScore;
        this.blackScore = gameState.blackScore;

        if (gameState.isWinner) {
            this.gameEndedEvent(gameState.player);
        }
        else if (gameState.player.type !== 'HUMAN') {
            this.apiService.moveAI()
                .subscribe(gameState => this.boardChangedEvent(gameState))
        }
    }

    resetStyles() {
        this.whiteText.nativeElement.style.backgroundColor = "rgba(255, 255, 255, 0)";
        this.blackText.nativeElement.style.backgroundColor = "rgba(255, 255, 255, 0)";
    }
}