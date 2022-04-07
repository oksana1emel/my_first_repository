package game.models.rules;


import game.models.Coordinate;
import game.models.Situation;
import game.models.TypeOfFigures;

/**
 Класс контейнер для того, чтобы хранить тип фигуры, её координаты и положение в пр-ве.
 */

public class Arrangement {

    private TypeOfFigures typeOfFigures;
    private Coordinate coordinate;
    private Situation situation;

    public Arrangement(TypeOfFigures typeOfFigures, Coordinate coordinate, Situation situation ) {

        this.typeOfFigures = typeOfFigures;
        this.coordinate = coordinate;
        this.situation = situation;
    }

    public TypeOfFigures getType () { return typeOfFigures; }

    public Coordinate getCoordinate () { return coordinate; }

    public Situation getSituation () { return situation; }
}