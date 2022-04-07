package game.services;


import game.models.*;
import game.models.rules.Arrangement;


import java.util.*;

import static game.models.TypeOfFigures.*;
import static game.models.TypeOfFigures.CARRIER;


public class GameBoardService {

    public GameBoard createGameBoard () {
        return new GameBoard ();
    }

    public void arrangeFigures ( GameBoard gameBoard, final List < Figure > inputFigures, final Collection < Arrangement > arrangements ) {
        // создаём список фигур,которые мы будем расставлять
        // (он дублируется с переданным т.к. из него мы будем удалять расставленные фигуры)
        List < Figure > figures = new LinkedList<>( inputFigures );
        for ( Arrangement arrange : arrangements ) {
            TypeOfFigures type = arrange.getType ();
            int counter = 0;
            // ищем фигуру того же типа, что и в расстановке
            for ( Figure figure: figures ) {
                if ( figure.getType () == type ) {
                    // тут мы смотрим что за тип фигуры и ставим его
                    arrangeFigure ( gameBoard, figure, arrange );
                    break;
                }
                counter++;
            }
            figures.remove ( counter );
        }
    }


    private void arrangeFigure ( GameBoard gameBoard, Figure figure, Arrangement arrangement ) {
        Coordinate coordinate = arrangement.getCoordinate ();
        TypeOfFigures type = figure.getType ();
        Set < Coordinate > positions = new HashSet <> ();
        // если фигура занимает не 1 ячейку
        if ( type == DESTROYER || type == CRUISER || type == CARRIER ) {
            int y = coordinate.getY();
            int x = coordinate.getX();
            Situation situation = arrangement.getSituation();
            int numberOfCells = figure.getNumberOfLives ();
            // в зависимости от того, как расположена фигура, расставляем её на ячейки
            switch ( situation ) {
                case VERTICAL -> {
                    for ( int counter = y; numberOfCells > 0; counter++, numberOfCells-- )
                        positions.add ( new Coordinate ( counter, x ) );
                }
                case HORIZONTAL -> {
                    for ( int counter = x; numberOfCells > 0; counter++, numberOfCells-- )
                        positions.add ( new Coordinate ( y, counter ) );
                }
            }
        }
        else // т.е. фигура занимает 1 ячейку
            positions.add ( coordinate );
        gameBoard.setPositionToFigure ( figure, positions );
    }

}
