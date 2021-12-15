package GameControllers;

import Players.ComputerPlayer;
import Players.HumanPlayer;
import Players.Player;

import java.util.Scanner;

public abstract class GamePlayingController {
    public int getNextPlayer(int current){
        if(current ==0){
            return 1;
        }
        else {
            return 0;
        }
    }
    public  Player[] init_HumanHuman(){
        Player[] players = new Player[2];
        players[0]=new HumanPlayer("Human1","X");
        players[1]=new HumanPlayer("Human 2","O");
        return players;
    }
    public  Player[] init_HumanComputer(int depth){
        Player[] players = new Player[2];
        players[0]=new HumanPlayer("Human1","X");
        players[1]=new ComputerPlayer("Computer","O",depth);
        return players;
    }
}
