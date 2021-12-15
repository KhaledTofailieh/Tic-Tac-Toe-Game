package gameStructure;

public class PlayPoint {
    private int x,y,i,j;
    private String name;

    public PlayPoint(int x, int y, int i, int j, String name) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.name = name;
    }
    public PlayPoint(PlayPoint playPoint){
        this.x = playPoint.x;
        this.y = playPoint.y;
        this.i = playPoint.i;
        this.j = playPoint.j;
        this.name = playPoint.name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getName() {
        return name;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public void setName(String name) {
        this.name = name;
    }

}
