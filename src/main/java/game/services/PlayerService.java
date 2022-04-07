package game.services;

import game.models.*;
import game.models.rules.Arrangement;

import java.util.*;

import static game.models.ShotResult.MISS;
import static game.models.Situation.*;
import static game.models.TypeOfFigures.*;
import static game.models.ShotResult.HIT;

public class PlayerService {

    private static int counterId = 0;

    public static Map < Coordinate, Integer > createEmptyShellingMap(int size) {
        Map < Coordinate, Integer > shellingMap = new HashMap <> ();
        for ( int line = 0; line < size; line++ )
            for ( int column = 0; column < size; column++ )
                shellingMap.put ( new Coordinate ( line, column ), 0 );
        return shellingMap;
    }

    public Collection < Arrangement > getArrangements () {
        Collection < Arrangement > arrangements = new LinkedList <> ();
        arrangements.add ( new Arrangement( SUBMARINE, new Coordinate( 2, 2 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( BOMB, new Coordinate( 4, 1 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( MINESWEEPER, new Coordinate( 6, 7 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( BOAT, new Coordinate( 0, 0 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( BOAT, new Coordinate( 2, 0 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( BOAT, new Coordinate( 4, 0 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( BOAT, new Coordinate( 6, 0 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( DESTROYER, new Coordinate( 11, 1 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( DESTROYER, new Coordinate( 9, 4 ) , VERTICAL ) );
        arrangements.add ( new Arrangement( DESTROYER, new Coordinate( 12, 7 ) , VERTICAL ) );
        arrangements.add ( new Arrangement( CRUISER, new Coordinate( 5, 2 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( CRUISER, new Coordinate( 3, 8 ) , HORIZONTAL ) );
        arrangements.add ( new Arrangement( CARRIER, new Coordinate( 12, 9 ) , HORIZONTAL ) );
        return arrangements;
    }

    public Coordinate getStep (Player player) {
        List<Coordinate> availableSteps = new ArrayList<>();
        for ( Map.Entry <Coordinate,Integer > entry : player.getEmploymentOfCells().entrySet () ) {
            if (entry.getValue()==0){
                availableSteps.add(entry.getKey());
            }
        }
        // 0 - пусто пока что, не знаю, что это
        // -1 - промах
        // 1 - попадание
        return availableSteps.get(getRandomIntTheRange(availableSteps.size()));
    }

    public Coordinate getPositionShip (Game game, Player player){
        GameBoard gameBoard = game.getGameBoardFor(player);
        Map<Figure, Set<Coordinate>> figure = gameBoard.getFigurePositions();
        for (Map.Entry <Figure, Set<Coordinate>> entry: figure.entrySet()){
            if (entry.getKey().getType() != BOMB && entry.getKey().getType() != MINESWEEPER )
                return entry.getValue().toArray(new Coordinate[0])[0];
        }
        return null;
    }

    public Coordinate getPositionMine (Player player, Game game){
        GameBoard gameBoard = game.getGameBoardFor(player);
        Map<Figure, Set<Coordinate>> figure = gameBoard.getFigurePositions();
        for (Map.Entry <Figure, Set<Coordinate>> entry: figure.entrySet()){
            if (entry.getKey().getType() == BOMB )
                return entry.getValue().toArray(new Coordinate[0])[0];

        }
        return null;
    }

    public void markAStep(Player player, Coordinate coordinate, ShotResult shotResult){
        // 0 - пусто пока что, не знаю, что это
        // -1 - промах
        // 1 - попадание
        switch (shotResult){
            case HIT,KILL -> player.getEmploymentOfCells().put(coordinate, 1);
            case MISS,EXPLODE,DESTROY_MINESWEEPER -> player.getEmploymentOfCells().put(coordinate, -1);
        }

    }

    public List < Player > createPlayers ( final int quantity, final int size ) {
        List < Player > completedPlayers = new LinkedList<>();
        for ( int i = 0; i < quantity; i++ ) {
            completedPlayers.add ( new Player ( counterId++, size ) );
        }
        return  completedPlayers;
    }

    public int getRandomIntTheRange ( int max ) {
        return (int) (Math.random() * max);
    }
}
