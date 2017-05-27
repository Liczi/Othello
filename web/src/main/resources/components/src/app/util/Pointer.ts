/**
 * Created by Jakub on 21.05.2017.
 */
export class Pointer {
    public colIndex: number;
    public rowIndex: number;

    constructor(col: number, row: number) {
        this.colIndex = col;
        this.rowIndex = row;
    }

    toJson(): string {
        return JSON.stringify(this);
    }
}