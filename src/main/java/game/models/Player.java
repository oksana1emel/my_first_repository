package game.models;

import game.services.PlayerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Player {

    private final int id;
    private final Map<Coordinate, Integer> employmentOfCells;

    public Player ( int id, int size) {
        this.id = id;
        this.employmentOfCells= PlayerService.createEmptyShellingMap(size);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass () != o.getClass () )
            return false;
        Player player = ( Player ) o;
        return id == player.id;
    }

    @Override
    public int hashCode () {
        return Objects.hash ( id );
    }

    public Map<Coordinate, Integer> getEmploymentOfCells() {
        return employmentOfCells;
    }


}
