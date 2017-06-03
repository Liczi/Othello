/**
 * Created by Jakub on 21.05.2017.
 */
export class Player {
    name: string;
    color: boolean;
    type: string;


    constructor(name: string, color: boolean, type: string) {
        this.name = name;
        this.color = color;
        this.type = type;
    }


    toJson(): string {
        return JSON.stringify(this);
    }
}