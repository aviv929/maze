package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * This class inherit the abstract class AState how
 * defines the functionality of a single state in a domain.
 * here we defines a state in a maze how represent
 * by a two-dimensional matrix.
 * every maze state have position how represent
 * the current coordinates of the state.
 * Created by ronnie on 4/16/2017.
 */
public class MazeState extends AState  {
    private Position position;

    public MazeState(Position position, double cost, AState cameFrom)
    {
        super(position.toString(),cost,cameFrom);
        this.position = position;
    }

    public Position getPosition()
    {
        return position;
    }
}
