package gameStructure;

public class Grid {
    private MiniGrid [][] cells;

    public Grid() {

        cells=new MiniGrid[3][];
        for(int i=0;i<3;i++){
           cells[i]=new MiniGrid[3];
           for (int j=0;j<3;j++){
               cells[i][j]=new MiniGrid(i,j);
           }
        }
    }
    public Grid(Grid grid){
        this.cells=new MiniGrid[3][];
        for(int i=0;i<3;i++){
            cells[i]=new MiniGrid[3];
            for (int j=0;j<3;j++){
                cells[i][j]=new MiniGrid(grid.cells[i][j]);
            }
        }
    }
    public void print(){
        for(int i=0;i<3;i++){
            for (int k=0;k<3;k++){
            for (int j=0;j<3;j++){

                cells[i][j].print(k);
            }
                System.out.println();
            }
            System.out.println();
        }
    }
    public void print_win_grid(){
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
             cells[i][j].print_state();
            }
            System.out.println();
        }
    }
    public MiniGrid[][] getCells() {
        return cells;
    }

}
