package gameStructure;

import States.CellState;

public class Cell {
    private int x,y;
    private CellState state;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;

        state=CellState.Nothing;
    }
    public Cell(Cell cell) {
        this.x = cell.x;
        this.y = cell.y;

        state=cell.state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void print(){
        if(this.state==CellState.Nothing){
            System.out.print("-"+" ");
        }else{
            System.out.print(this.state+" ");
        }

    }
}
