package gameMethods;

import States.CellState;
import gameStructure.Grid;
import gameStructure.MiniGrid;
import gameStructure.PlayPoint;


public class GameLogicController {


    public CellState is_win(MiniGrid grid,String player){
        int r=0,c=0,d1=0,d2=0;

        for(int i=0;i<3;i++){
            if(grid.getCells()[i][i].getState()==CellState.valueOf(player)) {
                d1++;
            }
            if(grid.getCells()[i][2-i].getState()==CellState.valueOf(player)){
                d2++;
            }

            for(int j=0;j<3;j++){
             if(grid.getCells()[i][j].getState()==CellState.valueOf(player)){
                 r++;
             }
             if(grid.getCells()[j][i].getState()==CellState.valueOf(player)) {
                 c++;
             }

             }
            if(r==3||c==3||d1==3||d2==3){
               // System.out.println(" Win ");
                return CellState.valueOf(player);
            }else{
               // System.out.println(" Win jjjj"+c+r);
                r=0;
                c=0;

            }
        }



        return CellState.Nothing;
    }
    public void update_mini_state(Grid grid,PlayPoint played){
        CellState iswin=is_win(grid.getCells()[played.getX()][played.getY()],played.getName());
          grid.getCells()[played.getX()][played.getY()].setState(iswin);
    }

    public void update_cell_state(Grid grid , PlayPoint played){
        grid.getCells()[played.getX()][played.getY()].getCells()[played.getI()][played.getJ()].
              setState(CellState.valueOf(played.getName()));
    }
    public boolean isAvailable_forPlay(Grid grid,PlayPoint played){
        return grid.getCells()[played.getX()][played.getY()].getSelected_cells()<9 && grid.getCells()[played.getX()][played.getY()].getState()==CellState.Nothing;
    }
    public boolean isAvailable_cell(Grid grid , PlayPoint played){
        return grid.getCells()[played.getX()][played.getY()].getCells()[played.getI()][played.getJ()].getState()== CellState.Nothing;
    }
}
