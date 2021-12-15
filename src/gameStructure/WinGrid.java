package gameStructure;

import States.CellState;
import States.StateGraph;

public class WinGrid {
    MiniGrid mini;
    int eval;
    PlayPoint point;

    public void setMini(MiniGrid mini) {
        this.mini = mini;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    public PlayPoint getPoint() {
        return point;
    }

    public void setPoint(PlayPoint point) {
        this.point = point;
    }

    public MiniGrid getMini() {
        return mini;
    }

    public int getEval() {
        return eval;
    }

    public WinGrid(MiniGrid mini) {
        this.mini = mini;
    }
    public WinGrid(Grid grid){
        this.mini=new MiniGrid(grid);
    }
    public WinGrid evaluate(int ii, int jj, String name,int eval,PlayPoint p){
this.point=p;
            int rows = 1, cols = 1, d1 = 1, d2 = 1;
            for (int i = 0; i < 3; i++) {
                if (!(mini.getCells()[ii][i].getState() == CellState.valueOf(name) ||
                        (mini.getCells()[ii][i].getState() == CellState.Nothing))) {
                    rows = 0;
                }

                if (!(mini.getCells()[i][jj].getState() == CellState.valueOf(name) ||
                        (mini.getCells()[i][jj].getState() == CellState.Nothing))) {
                    cols = 0;
                }
                if (ii == jj) {

                    if (!(mini.getCells()[i][i].getState() == CellState.valueOf(name) ||
                            (mini.getCells()[i][i].getState() == CellState.Nothing))) {
                        d1 = 0;

                    }
                }else{
                    d1=0;
                }
                if (ii== 2 - jj) {

                    if (!(mini.getCells()[i][2 - i].getState() == CellState.valueOf(name) ||
                            (mini.getCells()[i][2 - i].getState() == CellState.Nothing))) {
                        d2 = 0;
                    }

                }else{
                    d2=0;
                }


            }
            this.eval=(rows + cols + d1 + d2)*eval;
            return this;
           


    }
}
