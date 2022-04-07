package game.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 Состояние игровой доски. Он знает расположение фигур на ячейках
 */
public class GameBoard {

    private final Map < Figure, Set < Coordinate > > figurePositions = new HashMap <> ();

    public void setPositionToFigure ( Figure figure, Set < Coordinate > coordinates ) { figurePositions.put ( figure, coordinates ); }

    public void removePositionFigure ( Figure figure ) { figurePositions.remove ( figure ); }

    public Map < Figure, Set < Coordinate > > getFigurePositions () { return figurePositions; }

}
