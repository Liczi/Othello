import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {ApiService} from './api/api.service';
import {BoardComponent} from './board/board.component';
import { ConeComponent } from './cone/cone.component';

@NgModule({
    declarations: [
        AppComponent,
        BoardComponent,
        ConeComponent,
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule
    ],
    providers: [ApiService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
