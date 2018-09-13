package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * This class inherit the abstract class AMazeGenerator
 * that implements the IMazeGenerator interface.
 * here we create a maze by specific algorithm.
 * Created by ronnie on 4/15/2017.
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * This method create a new solvable maze by the Randomized Prim's algorithm
     * @param row is the numbers of row requested in the maze
     * @param column is the numbers of column requested in the maze
     * @return Maze
     */
    @Override
    public Maze generate(int row, int column)
    {
        if(row < 2)
            row = 10;
        if(column < 2)
            column = 10;

        Position start = getRandomStart(row);
        Position goal = getRandomGoal(row,column);

        if(start == null)
            start = new Position(0,0);

        if(goal == null)
            goal = new Position(row-1,column-1);

        int[][] data = new int[row][column];
        ArrayList<Position> walls = new ArrayList<Position>();

        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
                data[i][j] = 1;
        }

        data[start.getRow()][start.getColumn()] = 0;
        addNeighbers(data,start,walls);

        int randomIndex;
        Position cell;

        while(walls.size() > 0 && !(walls.contains(goal)) && data[goal.getRow()][goal.getColumn()] == 1)
        {
            randomIndex = (int)(Math.random() * (walls.size()-1));
            cell = walls.remove(randomIndex);

            Position neighbor = getSeparatedCell(data,cell,goal);

            if(neighbor != null)
            {
                data[cell.getRow()][cell.getColumn()] = 0;
                data[neighbor.getRow()][neighbor.getColumn()] = 0;

                addNeighbers(data,neighbor,walls);
            }
        }
        data[goal.getRow()][goal.getColumn()] = 0;
        return new Maze(start,goal,data);
    }

    /**
     * This method find the unvisited passage(if exists) that divide by
     * cell from another visited passage
     * @param data is the maze represented as a matrix of zeros(for passage) and ones(for wall)
     * @param cell is the wall how might divide two passages
     * @param goal is the goal position of the maze
     * @return Position
     */
    private Position getSeparatedCell(int[][] data, Position cell, Position goal)
    {
        int row = cell.getRow();
        int column = cell.getColumn();

        int goalR = goal.getRow();
        int goalC = goal.getColumn();
        if((row==goalR && column==goalC-1) || (column==goalC && (row==goalR-1 || row==goalR+1)))
            return goal;

        if(column!=0 && column != data[0].length-1)
        {
            if(data[row][column+1] == 0 && data[row][column-1] != 0)
                return new Position(row,column-1);
            if(data[row][column-1] == 0 && data[row][column+1] != 0)
                return new Position(row,column+1);
        }

        if(row!=0 && row!=data.length-1)
        {
            if(data[row-1][column] == 0 && data[row+1][column] != 0)
                return new Position(row+1,column);
            if(data[row+1][column] == 0 && data[row-1][column] != 0)
                return new Position(row-1,column);
        }

        return null;
    }

    /**
     * This method add to the wall list the walls that surrounds cell.
     * @param data is the maze represented as a matrix of zeros(for passage) and ones(for wall)
     * @param cell the passage we want to add the walls around it.
     * @param walls a list of walls that we came across by now.
     */
    private void addNeighbers(int[][] data, Position cell, ArrayList<Position> walls)
    {
        Position p;
        if(cell.getRow() > 0 && data[cell.getRow()-1][cell.getColumn()] == 1)
        {
            p = new Position(cell.getRow()-1,cell.getColumn());
            if(!(walls.contains(p)))
                walls.add(p);
        }
        if(cell.getRow() < data.length-1 && data[cell.getRow()+1][cell.getColumn()] == 1)
        {
            p = new Position(cell.getRow()+1,cell.getColumn());
            if(!(walls.contains(p)))
                walls.add(p);
        }
        if(cell.getColumn() > 0 && data[cell.getRow()][cell.getColumn()-1] == 1)
        {
            p = new Position(cell.getRow(),cell.getColumn()-1);
            if(!(walls.contains(p)))
                walls.add(p);
        }
        if(cell.getColumn() < data[0].length-1 && data[cell.getRow()][cell.getColumn()+1] == 1)
        {
            p = new Position(cell.getRow(),cell.getColumn()+1);
            if(!(walls.contains(p)))
                walls.add(p);
        }
    }
}
