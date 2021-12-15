package States;
import gameMethods.GameLogicController;
import gameStructure.Grid;
import gameStructure.PlayPoint;
public class StateGraph {
    private Grid grid;
    private PlayPoint played;
    private int eval;
    private int min= 1000;
    private int max=-1000;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Grid getGrid() {
        return grid;
    }

    public PlayPoint getPlayed() {
        return played;
    }

    public int getEval() {
        return eval;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    public StateGraph(Grid grid, PlayPoint played) {
        this.grid = grid;
        this.played = played;
    }

    public void print() {
        this.grid.print();
    }

    public StateGraph(Grid grid) {
        this.grid = grid;
    }

    public int evaluate() {
        int rows = 1, cols = 1, d1 = 1, d2 = 1;
        for (int i = 0; i < 3; i++) {
            if (!(grid.getCells()[played.getX()][played.getY()].getCells()[played.getI()][i].getState() == CellState.valueOf(played.getName()) ||
                    (grid.getCells()[played.getX()][played.getY()].getCells()[played.getI()][i].getState() == CellState.Nothing))) {
                rows = 0;
            }

            if (!(grid.getCells()[played.getX()][played.getY()].getCells()[i][played.getJ()].getState() == CellState.valueOf(played.getName()) ||
                    (grid.getCells()[played.getX()][played.getY()].getCells()[i][played.getJ()].getState() == CellState.Nothing))) {
                cols = 0;
            }
            if (played.getI() == played.getJ()) {

                if (!(grid.getCells()[played.getX()][played.getY()].getCells()[i][i].getState() == CellState.valueOf(played.getName()) ||
                        (grid.getCells()[played.getX()][played.getY()].getCells()[i][i].getState() == CellState.Nothing))) {
                    d1 = 0;

                }
            }else{
                d1=0;
            }
            if (played.getI() == 2 - played.getJ()) {

                if (!(grid.getCells()[played.getX()][played.getY()].getCells()[i][2 - i].getState() == CellState.valueOf(played.getName()) ||
                        (grid.getCells()[played.getX()][played.getY()].getCells()[i][2 - i].getState() == CellState.Nothing))) {
                    d2 = 0;
                }

            }else{
                d2=0;
            }


        }
        this.eval=rows + cols + d1 + d2;
        GameLogicController g=new GameLogicController();
        if(g.is_win(grid.getCells()[played.getX()][played.getY()],played.getName())!=CellState.Nothing)
            eval+=10;
        return this.eval;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setPlayed(PlayPoint played) {
        this.played = played;
    }
}
