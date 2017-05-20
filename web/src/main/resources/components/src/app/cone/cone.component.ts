import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'app-cone',
    templateUrl: './cone.component.html',
    styleUrls: ['./cone.component.css']
})
export class ConeComponent implements OnInit {

    @Input() value: Boolean;

    constructor() {
    }

    ngOnInit() {
    }

}
