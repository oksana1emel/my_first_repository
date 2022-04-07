package game.services;

import game.models.Figure;
import game.models.TypeOfFigures;
import game.models.rules.Order;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static game.models.TypeOfFigures.*;

/**
 Методы для работы с объектами фигур(создание И ТД)
 */

public class FigureService {

    private static int counterId = 0;

    public List < Figure > createFigures ( final Collection <Order> orders ) {
        List < Figure > completedFigures = new LinkedList<>();
        for ( Order order : orders ) {
            TypeOfFigures type = order.getType ();
            for ( int counter = 0; counter < order.getQuantity (); counter++ ) {
                switch ( type ) {
                    case BOMB -> completedFigures.add ( new Figure ( BOMB, counterId++, 1 ) );
                    case MINESWEEPER -> completedFigures.add ( new Figure ( MINESWEEPER, counterId++, 1 ) );
                    case SUBMARINE -> completedFigures.add ( new Figure ( SUBMARINE, counterId++, 1 ) );
                    case BOAT -> completedFigures.add ( new Figure ( BOAT, counterId++, 1 ) );
                    case DESTROYER -> completedFigures.add ( new Figure ( DESTROYER, counterId++, 2 ) );
                    case CRUISER -> completedFigures.add ( new Figure ( CRUISER, counterId++, 3 ) );
                    case CARRIER -> completedFigures.add ( new Figure ( CARRIER, counterId++, 4 ) );
                };
            }
        }
        return completedFigures;
    }
}
