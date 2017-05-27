import {Directive, ElementRef, EventEmitter, HostListener, Output} from '@angular/core';

@Directive({
    selector: '[available-move]'
})
export class AvailableMoveDirective {

    @Output() move: EventEmitter<any> = new EventEmitter();

    constructor(el: ElementRef) {
        el.nativeElement.style.backgroundColor = "rgba(255,255,255, 0.2)"
    }

    @HostListener('click', ['$event']) onClick($event) {
        this.move.emit({});
    }
}
