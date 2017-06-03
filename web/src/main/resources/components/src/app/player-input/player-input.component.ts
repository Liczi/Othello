import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Player} from "../util/Player";
import {ApiService} from "../api/api.service";

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
    types: string[];

    whiteType: string = 'HUMAN';
    blackType: string = 'HUMAN';

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.loadPlayerTypes();
    }

    startGame() {
        this.players = [new Player(this.whiteName, true, this.whiteType), new Player(this.blackName, false, this.blackType)];
        this.playersChangeEmitter.emit(this.players)
    }

    loadPlayerTypes() {
        this.apiService.getPlayerTypes()
            .subscribe(types => this.types = types,
                err => {
                    console.log(err)
                });
    }

}
