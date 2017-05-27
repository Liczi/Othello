/**
 * Created by Jakub on 21.05.2017.
 */
export class Player {
    name: string;
    color: boolean;

    constructor(name: string, color: boolean) {
        this.name = name;
        this.color = color;
    }

    toJson(): string {
        return JSON.stringify(this);
    }
}