import {Component, Input, OnInit} from '@angular/core';

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

    constructor() {
    }

    ngOnInit() {
    }

}
