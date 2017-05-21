import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
    selector: '[available-move]'
})
export class AvailableMoveDirective {

    constructor(el: ElementRef) {
        el.nativeElement.style.backgroundColor = "rgba(255,255,255, 0.2)"
    }

    @HostListener('click', ['$event']) onClick($event) {
        //todo add move() post request
        console.info('clicked: ' + $event);
    }
}
