package algorithms.mazeGenerators;

/**
 * This interface defines the functionality of a maze generator.
 * Created by ronnie on 4/15/2017.
 */
public interface IMazeGenerator {

    /**
     * This method create a new maze by the row and column parameters.
     * @param row is the numbers of row requested in the maze
     * @param column is the numbers of column requested in the maze
     * @return Maze
     */
    public Maze generate(int row, int column);
    /**
     * This method measures the time it takes to build the maze using the
     * generate method.
     * @param row is the numbers of row requested in the maze
     * @param column is the numbers of column requested in the maze
     * @return long the time that take to create a maze by the generate method.
     */
    public long measureAlgorithmTimeMillis(int row, int column);

}
