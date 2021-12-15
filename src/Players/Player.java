package Players;

import States.PlayerState;
import gameStructure.PlayPoint;

public abstract class Player {
    protected String name;
    protected PlayerState state;
    protected String XO;
    protected int miniX,miniY;

    public Player(String name , String ch) {
        this.name = name;
        this.state=PlayerState.nothing;
        this.XO=ch;
        this.miniX=-1;
        this.miniY=-1;
    }

    public void setMiniX(int miniX) {
        this.miniX = miniX;
    }

    public void setMiniY(int miniY) {
        this.miniY = miniY;
    }

    public PlayPoint play(PlayPoint played)
    {
     return played;
    }
    public void print(){
        System.out.println("Player: "+XO);
    }

    public int getMiniX() {
        return miniX;
    }

    public int getMiniY() {
        return miniY;
    }

    public String getXO() {
        return XO;
    }

    public void setXO(String XO) {
        this.XO = XO;
    }
}
