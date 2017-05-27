import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ApiService} from "../api/api.service";
import {Pointer} from "../util/Pointer";
import {GameState} from "../util/GameState";

@Component({
    selector: 'app-cone',
    templateUrl: './cone.component.html',
    styleUrls: ['./cone.component.css']
})
export class ConeComponent implements OnInit {

    //currentColor represents the current cone color: null - no cone, true - white, false - black
    @Input() currentColor: Boolean;
    @Input() col: number;
    @Input() row: number;
    @Input() isAvailable: boolean;
    @Output() gameStateEvent: EventEmitter<GameState> = new EventEmitter();

    constructor(private api: ApiService) {
    }

    ngOnInit() {
    }

    move() {
        //todo emit gameState change (to board - check if update)
        this.api.moveAt(new Pointer(this.col, this.row))
            .subscribe(gameState => this.gameStateEvent.emit(gameState), err => {
                console.log(err)
            })
    }
}
