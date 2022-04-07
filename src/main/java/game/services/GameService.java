package game.services;

import game.Application;
import game.models.*;
import game.models.rules.Rules;

import java.util.*;

import static game.models.TypeOfFigures.*;
import static game.models.ShotResult.*;


public class GameService {


    private static final GameBoardService gameBoardService = new GameBoardService ();

    private static final FigureService figureService = new FigureService ();

    private static final PlayerService playerService = new PlayerService ();

    public Player getEnemyFor (Game game, Player player ) {
        List < Player > players = game.getPlayers();
        for (Player player1: players){
            if ( !player.equals ( player1 ) )
                return player1;
        }
        return null;
    }

    public Game createGame () {
        // создаются необходимые модели
        GameBoard gameBoardForPlayer_1 = gameBoardService.createGameBoard();
        GameBoard gameBoardForPlayer_2 = gameBoardService.createGameBoard();
        List < Figure > figuresForPlayer_1 = figureService.createFigures ( Rules.getTeamComposition () );
        List < Figure > figuresForPlayer_2 = figureService.createFigures ( Rules.getTeamComposition () );
        List < Player > players = playerService.createPlayers ( 2 , Rules.getFieldSize());
        // игровые доски присваиваются игрокам
        Map < Player, GameBoard > belongingOfGameBoard = new HashMap <> ();
        belongingOfGameBoard.put ( players.get ( 0 ), gameBoardForPlayer_1 );
        belongingOfGameBoard.put ( players.get ( 1 ), gameBoardForPlayer_2 );
        // фигуры расставляются на игровые доски
        gameBoardService.arrangeFigures ( gameBoardForPlayer_1, figuresForPlayer_1, playerService.getArrangements () );
        gameBoardService.arrangeFigures ( gameBoardForPlayer_2, figuresForPlayer_2, playerService.getArrangements () );
        return new Game ( belongingOfGameBoard );
    }

    public Player playTheGame ( Game game ) {
        Queue < Player > players = new LinkedList<> ( game.getPlayers () );
        Coordinate coordinateOfShipEnemy = null;
        while ( true ) {
            Application.printGame(new LinkedList<>( game.getPlayers()));
            // получаем шаг у игрока
            Player player_1 = players.poll ();
            Coordinate step;
            if (coordinateOfShipEnemy != null){
                step = coordinateOfShipEnemy;
                coordinateOfShipEnemy = null;
            } else
                step = playerService.getStep(player_1);
            players.add ( player_1 );
            // смотрим куда попали
            ShotResult shotResult = fireOnPosition ( game.getGameBoardFor ( getEnemyFor ( game, player_1 ) ), step );
            // проверка на окончание игры
            if ( playerWin ( player_1, game ) )
                return player_1;
            if ( shotResult == EXPLODE ) {
                coordinateOfShipEnemy = playerService.getPositionShip(game, player_1);//логика,где мы говорим любую позицию корабля
            }
            if ( shotResult == DESTROY_MINESWEEPER ) {
                Coordinate coordinateOfMineEnemy = playerService.getPositionMine(player_1,game);//логика,где мы говорим позицию своей мины
                playerService.markAStep(getEnemyFor(game, player_1), coordinateOfMineEnemy, MISS);
            }
            // если попали или убили корабль или лодку противника
            // в другом случае делать ничего не надо
            // делаем ход,пока будем попадать или убивать
            while ( shotResult == HIT || shotResult == KILL ) {
                playerService.markAStep(player_1,step, shotResult);
                Application.printGame(new LinkedList<>( game.getPlayers()));
                step = playerService.getStep(player_1);
                shotResult = fireOnPosition ( game.getGameBoardFor ( getEnemyFor ( game, player_1 ) ), step );
                // проверка на окончание игры
                if ( playerWin ( player_1 , game) )
                    return player_1;
            }
            //проверка на то почему прекратили стрелять (промах или попали в мину или тральщик)
            if ( shotResult == EXPLODE ) {
                coordinateOfShipEnemy = playerService.getPositionShip(game, player_1);
            }
            else
                if ( shotResult == DESTROY_MINESWEEPER ) {
                    Coordinate coordinateOfMineEnemy = playerService.getPositionMine(player_1,game);//логика,где мы говорим позицию своей мины
                    playerService.markAStep(getEnemyFor(game, player_1), coordinateOfMineEnemy, MISS);
                }
                // если мы тут, то закончили делать ходы из-за промаха и делать ничего не нужно
            playerService.markAStep(player_1,step, shotResult);
            Application.printGame(new LinkedList<>( game.getPlayers()));
            // точно то же самое для 2го игрока
            // получаем шаг у игрока
            Player player_2 = players.poll ();
            if (coordinateOfShipEnemy != null){
                step = coordinateOfShipEnemy; //при этом я не должна попасть на мину если знаю ее координаты
                coordinateOfShipEnemy = null;
            } else
                step = playerService.getStep(player_2);
            players.add ( player_2 );
            // смотрим куда попали
            shotResult = fireOnPosition ( game.getGameBoardFor ( getEnemyFor ( game, player_2 ) ), step );
            // проверка на окончание игры
            if ( playerWin ( player_2, game ) )
                return player_2;
            if ( shotResult == EXPLODE ) {
                coordinateOfShipEnemy = playerService.getPositionShip(game, player_2);
            }
            if ( shotResult == DESTROY_MINESWEEPER ) {
                Coordinate coordinateOfMineEnemy = playerService.getPositionMine(player_2,game);//логика,где мы говорим позицию своей мины
                playerService.markAStep(getEnemyFor(game, player_2), coordinateOfMineEnemy, MISS);
            }
            // если попали или убили корабль или лодку противника
            // в другом случае делать ничего не надо
            // делаем ход,пока будем попадать или убивать
            while ( shotResult == HIT || shotResult == KILL ) {
                playerService.markAStep(player_2,step, shotResult);
                Application.printGame(new LinkedList<>( game.getPlayers()));
                step = playerService.getStep(player_2);
                shotResult = fireOnPosition ( game.getGameBoardFor ( getEnemyFor ( game, player_2 ) ), step );
                // проверка на окончание игры
                if ( playerWin ( player_2, game ) )
                    return player_2;
            }
            //проверка на то почему прекратили стрелять (промах или попали в мину или тральщик)
            if ( shotResult == EXPLODE ) {
                coordinateOfShipEnemy = playerService.getPositionShip(game, player_2);
            }
            else
            if (shotResult == DESTROY_MINESWEEPER) {
                Coordinate coordinateOfMineEnemy = playerService.getPositionMine(player_2,game);//логика,где мы говорим позицию своей мины
                playerService.markAStep(getEnemyFor(game, player_2), coordinateOfMineEnemy, MISS);
            }
                // если мы тут, то закончили делать ходы из-за промаха и делать ничего не нужно
            playerService.markAStep(player_2,step, shotResult);
        }
    }


    public boolean playerWin ( Player player, Game game) {
        Player enemy = getEnemyFor(game, player);
        GameBoard bord =  game.getGameBoardFor(enemy);
        Map < Figure, Set < Coordinate > > figure =  bord.getFigurePositions() ;
        for ( Figure figure1: figure.keySet ()) {
            if (figure1.getType() != BOMB && figure1.getType() != MINESWEEPER)// если всретили хотя бы один корабль то продолжаем игру, ну то есть выходим из этого метода
                return false;
        }
        return true;
    }


    public ShotResult fireOnPosition ( final GameBoard gameBoard, final Coordinate pos ) {
        Map < Figure, Set < Coordinate > > figurePositions = gameBoard.getFigurePositions ();
        // идём по всем фигурам и их позициям
        for ( Map.Entry < Figure, Set < Coordinate > > entry : figurePositions.entrySet () ) {
            Set < Coordinate > coordinatesFigure = entry.getValue ();
            // если попали по фигуре
            if ( coordinatesFigure.contains ( pos ) ) {
                Figure figureOnPosition = entry.getKey ();
                TypeOfFigures typeOfFigures = figureOnPosition.getType();
                if ( typeOfFigures == BOMB )
                    return EXPLODE;
                if ( typeOfFigures == MINESWEEPER )
                    return DESTROY_MINESWEEPER;
                // если попали по фигуре, попадание по которой её не уничтожит
                if ( figureOnPosition.getNumberOfLives () > 1 ) {
                    if ( typeOfFigures == DESTROYER || typeOfFigures == CRUISER || typeOfFigures == CARRIER )
                        coordinatesFigure.remove ( pos );
                    figureOnPosition.removeLives ();
                    return HIT;
                }
                else { // т.е. попадание уничтожит фигуру
                    gameBoard.removePositionFigure(figureOnPosition);
                    // тут это просто так, по идее больше не используем эту фигуру, но установим ей кол-во жизней на 0
                    figureOnPosition.removeLives ();
                    return KILL;
                }
            }
        }
        // т.е. фигур на этой позиции нет
        return MISS;
    }

}
