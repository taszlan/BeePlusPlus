package model;

/**
 * Created by atticus on 3/18/16.
 */
public class BeehiveCoordinates{
    private int y;
    private int x;

    public BeehiveCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}