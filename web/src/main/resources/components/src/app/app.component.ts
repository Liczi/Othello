import {Component} from '@angular/core';
import {ApiService} from './api/api.service';
import {Player} from "./util/Player";
import {GameState} from "./util/GameState";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'app works!';
    gameState: GameState;
    //todo test boolean[][] table

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.loadTitle();
        this.startNewGame();
    }

    loadTitle() {
        this.apiService.getTitle()
            .subscribe(title => this.title = title, err => {
                console.log(err)
            });
    }

    //todo start new game creates board component with proper GameState
    //todo then the game is controled from board componenet
    //todo insert player names from input form
    startNewGame() {
        this.apiService.startNewGame(new Player("Zdziś", true), new Player("Miecio", false))
            .subscribe(gameState => this.gameState = gameState, err => {
                console.log(err)
            });
    }


}