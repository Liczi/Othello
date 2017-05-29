import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Player} from "../util/Player";

@Component({
    selector: 'app-player-input',
    templateUrl: './player-input.component.html',
    styleUrls: ['./player-input.component.css']
})
export class PlayerInputComponent implements OnInit {

    whiteName = 'Nazwa1';
    blackName = 'Nazwa2';
    @Output() playersChangeEmitter: EventEmitter<Player[]> = new EventEmitter();
    players: Player[];

    constructor() {
    }

    ngOnInit() {
    }

    startGame() {
        this.players = [new Player(this.whiteName, true), new Player(this.blackName, true)];
        this.playersChangeEmitter.emit(this.players)
    }

}
