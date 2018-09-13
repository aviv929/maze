package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * This class implements the ISearchable interface that
 * defines the functionality of a searchable object.
 * this class defines a maze that can be searched from
 * start state to goal state.
 * Created by ronnie on 4/16/2017.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState start;
    private MazeState goal;

    public SearchableMaze(Maze maze)
    {
        if(maze != null && maze.getStartPosition() != null & maze.getGoalPosition() != null)
        {
            this.maze = maze;
            this.start = new MazeState(maze.getStartPosition(),0,null);
            this.goal = new MazeState(maze.getGoalPosition(),0,null);
        }
    }

    /** {@inheritDoc} */
    @Override
    public AState getInitialState()
    {
        return start;
    }

    /** {@inheritDoc} */
    @Override
    public AState getGoalState()
    {
        return goal;
    }

    /** {@inheritDoc} */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s)
    {
        ArrayList<AState> result = new ArrayList<AState>();

        if(maze == null || maze.getMaze() == null || s == null ||  ((MazeState)s).getPosition() == null)
            return result;

        Position p = ((MazeState)s).getPosition();
        int[][] data = maze.getMaze();

        if(p.getRowIndex() < 0 || p.getColumnIndex() < 0 || p.getRowIndex() > data.length - 1 || p.getColumnIndex() > data[0].length - 1)
            return result;

        if(p.getRowIndex() > 0 && data[p.getRowIndex()-1][p.getColumnIndex()] == 0)
        {
            MazeState state = new MazeState(new Position(p.getRowIndex()-1,p.getColumnIndex()),s.getCost()+1,s);
            result.add(state);
        }
        if(p.getRowIndex() > 0 && p.getColumnIndex() < data[0].length-1 && data[p.getRowIndex()-1][p.getColumnIndex()+1] == 0)
        {
            if(data[p.getRowIndex()-1][p.getColumnIndex()] == 0 || data[p.getRowIndex()][p.getColumnIndex()+1] == 0 )
            {
                MazeState state = new MazeState(new Position(p.getRowIndex()-1,p.getColumnIndex()+1),s.getCost()+1.5,s);
                result.add(state);
            }
        }
        if(p.getColumnIndex() < data[0].length-1 && data[p.getRowIndex()][p.getColumnIndex()+1] == 0)
        {
            MazeState state = new MazeState(new Position(p.getRowIndex(),p.getColumnIndex()+1),s.getCost()+1,s);
            result.add(state);
        }
        if(p.getRowIndex() < data.length-1 && p.getColumnIndex() < data[0].length-1 && data[p.getRowIndex()+1][p.getColumnIndex()+1] == 0)
        {
            if(data[p.getRowIndex()+1][p.getColumnIndex()] == 0 || data[p.getRowIndex()][p.getColumnIndex()+1] == 0)
            {
                MazeState state = new MazeState(new Position(p.getRowIndex()+1,p.getColumnIndex()+1),s.getCost()+1.5,s);
                result.add(state);
            }
        }
        if(p.getRowIndex() < data.length-1 && data[p.getRowIndex()+1][p.getColumnIndex()] == 0)
        {
            MazeState state = new MazeState(new Position(p.getRowIndex()+1,p.getColumnIndex()),s.getCost()+1,s);
            result.add(state);
        }
        if(p.getRowIndex() < data.length-1 && p.getColumnIndex() > 0 && data[p.getRowIndex()+1][p.getColumnIndex()-1] == 0)
        {
            if(data[p.getRowIndex()+1][p.getColumnIndex()] == 0 || data[p.getRowIndex()][p.getColumnIndex()-1] == 0)
            {
                MazeState state = new MazeState(new Position(p.getRowIndex()+1,p.getColumnIndex()-1),s.getCost()+1.5,s);
                result.add(state);
            }
        }
        if(p.getColumnIndex() > 0 && data[p.getRowIndex()][p.getColumnIndex()-1] == 0)
        {
            MazeState state = new MazeState(new Position(p.getRowIndex(),p.getColumnIndex()-1),s.getCost()+1,s);
            result.add(state);
        }
        if(p.getColumnIndex() > 0 && p.getRowIndex() > 0 && data[p.getRowIndex()-1][p.getColumnIndex()-1] == 0)
        {
            if(data[p.getRowIndex()-1][p.getColumnIndex()] == 0 || data[p.getRowIndex()][p.getColumnIndex()-1] == 0)
            {
                MazeState state = new MazeState(new Position(p.getRowIndex()-1,p.getColumnIndex()-1),s.getCost()+1.5,s);
                result.add(state);
            }
        }
        return result;
    }
}
