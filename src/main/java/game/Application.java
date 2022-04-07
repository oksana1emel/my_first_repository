package game;

import game.models.Coordinate;
import game.models.Game;
import game.models.Player;
import game.services.GameService;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        GameService gameService = new GameService ();
        Game game  = gameService.createGame();
        Player winner = gameService.playTheGame(game);
        System.out.println( "\nПобедил: " + winner );
    }


    public static void printGame (Queue <Player> players) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append( "\n===========================\n" );
        stringBuilder.append( "Блокнот первого игрока:\n" );
        stringBuilder.append(notebookToString(players.poll().getEmploymentOfCells()));
        stringBuilder.append( "\nБлокнот второго игрока:\n" );
        stringBuilder.append(notebookToString(players.poll().getEmploymentOfCells()));
        stringBuilder.append( "===========================\n" );

        System.out.print(stringBuilder.toString());
    }

    private static StringBuilder notebookToString (Map<Coordinate, Integer> map) {

        StringBuilder stringBuilder = new StringBuilder();
        List< Map.Entry< Coordinate, Integer >> entryList = map.entrySet()
                .stream ()
                // сортируем так, чтобы доска получилась сверху вниз слева направо
                .sorted ( ( entry1, entry2 ) -> {
                    Coordinate c1 = entry1.getKey();
                    Coordinate c2 = entry2.getKey();
                    if ( c1.getY () < c2.getY () )
                        return 1;
                    if ( c1.getY () > c2.getY () )
                        return -1;
                    // одинаковых ячеек просто не иожет быть,но
                    if ( c1.equals ( c2 ) )
                        return 0;
                    return c1.getX () > c2.getX ()? 1 : -1;
                })
                .collect( Collectors.toList () );

        int counter = 0;
        for ( Map.Entry< Coordinate,Integer > cell : entryList ) {

            switch ( cell.getValue() ) {
                case -1 -> stringBuilder.append( "#" );
                case 0 -> stringBuilder.append( "~" );
                case 1 -> stringBuilder.append( "x" );
            }
            stringBuilder.append( " " );
            counter++;
            if ( counter == 14 ) {
                stringBuilder.append( "\n" );
                counter = 0;
            }
        }
        return stringBuilder;
    }

}
