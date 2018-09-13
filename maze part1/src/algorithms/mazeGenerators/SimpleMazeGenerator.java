package algorithms.mazeGenerators;

/**
 * This class inherit the abstract class AMazeGenerator
 * that implements the interface IMazeGenerator.
 * here we create a maze by specific algorithm.
 * Created by ronnie on 4/15/2017.
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * This method create a new solvable maze by random injection of walls.
     * @param row is the numbers of row requested in the maze
     * @param column is the numbers of column requested in the maze
     * @return Maze
     */
    @Override
    public Maze generate(int row, int column)
    {
        if(row < 1)
            row = 10;
        if(column < 1)
            column = 10;

        Position start = getRandomStart(row);
        Position goal = getRandomGoal(row,column);

        int[][] data = new int[row][column];

        for(int wallRow=0;wallRow<row;wallRow=wallRow+2)
        {
            for(int wallColumn=0;wallColumn<column;wallColumn=wallColumn+2)
            {
                if((wallRow==start.getRow() && wallColumn == 0) || (wallRow==goal.getRow() && wallColumn==column-1))
                    continue;
                if(Math.random() > 0.5)
                    data[wallRow][wallColumn] = 1;
            }
        }

        Maze maze = new Maze(start,goal,data);
        return maze;
    }
}
