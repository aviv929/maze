package algorithms.mazeGenerators;

/**
 * Created by ronnie on 4/15/2017.
 * This abstract class implements the IMazeGenerator interface
 * that defines the functionality of a maze generator.
 * the  measureAlgorithmTimeMillis method is the same for every kind of maze
 * so we implements this method here.
 * However, the generate method is specific for each maze so it abstract and
 * every class that extends this class will need to implements it.
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    /** {@inheritDoc} */
    @Override
    public long measureAlgorithmTimeMillis(int row, int column)
    {
        long startTime = System.currentTimeMillis();
        generate(row,column);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    /**
     * This method create a random start position for the maze
     * @param row is the numbers of rows in the maze
     * @return Position that intended to be the start position
     */
    protected Position getRandomStart(int row)
    {
        int startRow = (int)(Math.random() * (row-1));
        Position start = new Position(startRow,0);
        return start;
    }

    /**
     * This method create a random goal position for the maze
     * @param row is the numbers of rows in the maze
     * @param column is the numbers of columns in the maze
     * @return Position that intended to be the goal position
     */
    protected  Position getRandomGoal(int row, int column)
    {
        int goalRow = (int)(Math.random() * (row-1));
        Position goal = new Position(goalRow,column-1);
        return goal;
    }
}
