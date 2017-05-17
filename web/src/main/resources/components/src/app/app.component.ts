import {Component} from '@angular/core';
import {ApiService} from './api/api.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    title = 'app works!';

    constructor(private apiService: ApiService) {
    }

    ngOnInit() {
        this.loadTitle();
    }

    loadTitle() {
        this.apiService.getTitle()
            .subscribe(title => this.title = title, err => {
                console.log(err)
            });
    }
}