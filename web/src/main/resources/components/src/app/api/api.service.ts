import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import {AppSettings} from "../app.settings";
import {Test} from "../../test/test";

@Injectable()
export class ApiService {

    constructor(private http: Http) {
    }

    getTitle(): Observable<string> {
        return this.http.get(AppSettings.API_ENDPOINT + "title")
            .map(res => res.json());
            //.catch((error: any) => Observable.throw(error.json().error || 'Unknown server error'));
    };

    getTestData(): Observable<Test> {
        return this.http.get(AppSettings.API_ENDPOINT + "test")
            .map(res => res.json());
            //.catch((error: any) => Observable.throw(error.json().error || 'Unknown server error'));
    };

}
