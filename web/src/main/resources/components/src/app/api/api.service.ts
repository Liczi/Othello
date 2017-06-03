import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {Headers, URLSearchParams} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import {AppSettings} from "../app.settings";
import {GameState} from "../util/GameState";
import {Player} from "../util/Player";
import {Pointer} from "../util/Pointer";

@Injectable()
export class ApiService {

    constructor(private http: Http) {
    }

    getTitle(): Observable<string> {
        return this.http.get(AppSettings.API_ENDPOINT + "title")
            .map(res => res.json());
        //.catch((error: any) => Observable.throw(error.json().error || 'Unknown server error'));
    };

    getBoard(): Observable<Boolean[][]> {
        return this.http.get(AppSettings.API_ENDPOINT + "board")
            .map(res => res.json());
        //.catch((error: any) => Observable.throw(error.json().error || 'Unknown server error'));
    };

    getGameState(): Observable<GameState> {
        return this.http.get(AppSettings.API_ENDPOINT + "game")
            .map(res => res.json());
        //.catch((error: any) => Observable.throw(error.json().error || 'Unknown server error'));
    }

    startNewGame(white: Player, black: Player): Observable<GameState> {
        let params: URLSearchParams = new URLSearchParams();
        params.set('whiteName', white.name);
        params.set('blackName', black.name);
        params.set('whiteType', white.type);
        params.set('blackType', black.type);

        return this.http.get(AppSettings.API_ENDPOINT + 'new', {
            // headers: this.getAcceptJsonHeader(),
            search: params
        })
            .map(res => res.json());
    }

    moveAt(at: Pointer): Observable<GameState> {
        return this.http.get(AppSettings.API_ENDPOINT + "move", {
            // headers: this.getAcceptJsonHeader(),
            search: this.objToSearchParams(at)
        })
            .map(res => res.json());
    }

    moveAI(): Observable<GameState> {
        return this.http.get(AppSettings.API_ENDPOINT + "moveAI")
            .map(res => res.json());
    }

    getPlayerTypes(): Observable<string[]> {
        return this.http.get(AppSettings.API_ENDPOINT + "player_types")
            .map(res => res.json());
    }

    objToSearchParams(obj): URLSearchParams {
        let params: URLSearchParams = new URLSearchParams();
        for (let key in obj) {
            if (obj.hasOwnProperty(key))
                params.set(key, obj[key]);
        }
        return params;
    }



    // getAcceptJsonHeader(): Headers {
    //     let headers = new Headers();
    //     headers.append('Content-Type', 'application/json');
    //     return headers;
    // }

}
