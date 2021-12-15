package Players;

import gameStructure.PlayPoint;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name , String ch) {
        super(name,ch);
    }

    public void set_minis(int x,int y){
        miniX= x;
        miniY=y;
    }
}
