package game.models.rules;


import java.util.Collection;
import java.util.LinkedList;

import static game.models.TypeOfFigures.*;


public class Rules {

    private final static int fieldSize = 14;

    private final static Collection <Order> teamComposition = new LinkedList <> ();

    static {
        // заполняем состав команды
        teamComposition.add ( new Order ( SUBMARINE, 1 ) );
        teamComposition.add ( new Order ( BOMB, 1 ) );
        teamComposition.add ( new Order ( MINESWEEPER, 1 ) );
        teamComposition.add ( new Order ( BOAT, 4 ) );
        teamComposition.add ( new Order ( DESTROYER, 3 ) );
        teamComposition.add ( new Order ( CRUISER, 2 ) );
        teamComposition.add ( new Order ( CARRIER, 1 ) );

    }

    public static int getFieldSize () { return fieldSize; }

    public static Collection<Order> getTeamComposition () { return teamComposition; }
}
