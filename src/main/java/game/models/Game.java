package game.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Game {

    private final Map < Player, GameBoard > belongingOfGameBoard; //принадлежность доске
    public Game ( Map < Player, GameBoard > belongingOfGameBoard ) {
        this.belongingOfGameBoard = belongingOfGameBoard;
    }

    public List < Player > getPlayers () {
        List < Player > players = new LinkedList <> ();
       // идём по всей мапе и записываем всех игроков
        for ( Map.Entry < Player,GameBoard > entry : belongingOfGameBoard.entrySet () )
            players.add ( entry.getKey () );
        return players;
    }

    public GameBoard getGameBoardFor ( Player player ) { return belongingOfGameBoard.get ( player ); }
}


