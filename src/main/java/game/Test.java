package game;

import game.models.*;
import game.services.FigureService;
import game.services.GameBoardService;
import game.services.GameService;
import game.services.PlayerService;

import java.util.Map;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        FigureService figureService = new FigureService ();
        GameBoardService gameBoardService = new GameBoardService ();
        PlayerService playerService = new PlayerService ();
        GameService gameService = new GameService ();
        Game game  = gameService.createGame();
        Player winner = gameService.playTheGame(game);
        System.out.println(winner);

        /*
        List < Figure > figures = figureService.createFigures ( Rules.getTeamComposition () );
        GameBoard gameBoard = gameBoardService.createAGameBoard ( Rules.getFieldSize () );
        Collection < Arrangement > arrangements = playerService.getArrangements ();


        System.out.println ("Фигуры");
        for ( Figure figure : figures ) {
            System.out.println( figure );
        }

        System.out.println("\n\n Игровое Поле");
        System.out.println( outputMap ( gameBoard.DEL_getMap () ) );

        System.out.println("\n\n Игровое Поле с расставленными фигурами");
        gameService.arrangeFigures ( gameBoard, figures, arrangements );
        System.out.println ( outputMap (gameBoard.DEL_getMap () ) );

     */
        /*Game game = gameService.createGame ();
        GameBoard gameBoard_1 = game.getGameBoardFor ( new Player ( 0 ) );
        GameBoard gameBoard_2 = game.getGameBoardFor ( new Player ( 1 ) );

        Map < Figure, Set < Coordinate > > positionFiguresForPlayer_1 = gameBoard_1.getFigurePositions ();
        Map < Figure, Set < Coordinate > > positionFiguresForPlayer_2 = gameBoard_2.getFigurePositions ();

        System.out.println ( "Позиции фигур,расставленных на игровой доске" );
        System.out.println ( "\nПозиции на доске 1го игрока" );
        System.out.println ( outputMap ( positionFiguresForPlayer_1 ) );
        System.out.println( "\nПозиции на доске 2го игрока" );
        System.out.println ( outputMap ( positionFiguresForPlayer_2 ) );*/
    }


    /*private static String outputMap ( Map < Figure, Set < Coordinate > > map ) {

        String line = "";

        for ( Map.Entry < Figure, Set < Coordinate > > entry : map.entrySet () ) {

            line += entry.getKey () + " ->" + entry.getValue () + "\n";
        }

        return line;
    }*/

}
