package game.models;

import java.util.Objects;


public class Coordinate {

    private int x;

    private int y;


    public Coordinate( int y, int x ) {
        this.y = y;
        this.x = x;
    }


    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass () != o.getClass () ) return false;
        Coordinate that = ( Coordinate ) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public String toString () { return "{" + y + ", "+ x + "}"; }

    @Override
    public int hashCode () { return Objects.hash ( x, y ); }

    public int getX () { return x; }

    public int getY () { return y; }
}
