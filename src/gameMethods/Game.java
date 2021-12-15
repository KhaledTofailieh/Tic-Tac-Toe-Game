package gameMethods;

import Players.Player;
import gameStructure.Grid;
import gameStructure.PlayPoint;

public class Game {
    private Grid grid;
    private  Player[] players;
    private GameLogicController controller;
    private GameType type;
    private int current;

    public Game(GameType type,Player[] players) {
        grid=new Grid();
        controller=new GameLogicController();
        this.type=type;
        this.players=players;
        current=0;
    }

    public Grid play(PlayPoint played){


        controller.update_cell_state(grid,played);
        controller.update_mini_state(grid,played);

        grid.print();
        grid.print_win_grid();

     return new Grid(grid);
    }
    public Grid play(PlayPoint played,Grid grid){

        controller.update_cell_state(grid,played);
        controller.update_mini_state(grid,played);

        return new Grid(grid);
    }


    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public GameLogicController getController() {
        return controller;
    }

    public void setController(GameLogicController controller) {
        this.controller = controller;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
