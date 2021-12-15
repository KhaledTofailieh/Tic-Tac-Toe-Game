package gameStructure;

import States.CellState;

public class MiniGrid {
    private int x,y,selected_cells;
    private CellState state;

    private Cell[][] cells;

    public MiniGrid(int x, int y) {
        this.x = x;
        this.y = y;
        this.selected_cells=0;
        state =CellState.Nothing;
        cells =new Cell[3][];
        for(int i=0;i<3;i++){
            cells[i]=new Cell[3];
            for (int j=0;j<3;j++){
                cells[i][j]=new Cell(i,j);
            }
        }
    }
    public MiniGrid(MiniGrid grid){
        this.x = grid.x;
        this.y = grid.y;
        this.selected_cells=grid.selected_cells;
        state =grid.state;
        cells =new Cell[3][];
        for(int i=0;i<3;i++){
            cells[i]=new Cell[3];
            for (int j=0;j<3;j++){
                cells[i][j]=new Cell(grid.cells[i][j]);
            }
        }
    }
   public  MiniGrid(Grid grid){

        this.cells=new Cell[3][];
        for(int i=0;i<3;i++){
            this.cells[i]=new Cell[3];
            for(int j=0;j<3;j++){
                this.cells[i][j]=new Cell(i,j);
                this.getCells()[i][j].setState(grid.getCells()[i][j].getState());

            }
        }
   }
    public void print(int k){
        for (int i=0;i<3;i++){
           cells[k][i].print();
        }
        System.out.print("  ");
    }

    public Cell[][] getCells() {
        return cells;
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

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getSelected_cells() {
        return selected_cells;
    }

    public void setSelected_cells(int selected_cells) {
        this.selected_cells = selected_cells;
    }

    public void print_state(){
        if(state==CellState.Nothing){
            System.out.print(" ");
        }
        else{
            System.out.print("  "+state);
        }
    }
}


