package game.models;

public class Figure {

    private final TypeOfFigures type;
    private final int id;
    private int numberOfLives;

    public Figure ( TypeOfFigures type, int id, int numberOfLives ) {
        this.type = type;
        this.id = id;
        this.numberOfLives = numberOfLives;
    }

    public void removeLives () {
        numberOfLives--;
    }

    @Override
    public String toString() {
        return type + "; lives=" + numberOfLives + "; id=" + id;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        if ( id != figure.id ) return false;
        return type.equals ( figure.type );
    }

    @Override
    public int hashCode () { return id; }

    public TypeOfFigures getType () { return type; }

    public int getNumberOfLives () { return numberOfLives; }
}
