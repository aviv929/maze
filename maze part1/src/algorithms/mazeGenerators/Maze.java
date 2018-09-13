package algorithms.mazeGenerators;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class defines and implements the functionality of a maze.
 * every maze have start and goal positions.
 * the maze represent by a two-dimensional matrix.
 * passage represent with 0 and wall represent with 1
 * Created by ronnie on 4/15/2017.
 */
public class Maze implements Serializable{
    private Position start;
    private Position goal;
    private int[][] data;

    /**
     * The constructor create a new Maze with the following parameters
     * @param start is the start position of the maze
     * @param goal is the goal position of the maze
     * @param data is a two-dimensional int array of zeros(for passage) and ones(for wall)
     */
    public Maze(Position start, Position goal, int[][] data)
    {
        this.start = start;
        this.goal = goal;
        this.data = data;
    }

    public Maze()
    {
        this.start = new Position(0,0);
        this.goal = new Position(9,9);
        this.data = new int[10][10];
    }

    public Maze(byte[] byteArray)
    {
        if(byteArray == null || byteArray.length < 12)
            return;

        int[] initialData = new int[6];

        int index = 0;
        for(int i=0;i<6;i++)
        {
            initialData[i] = (byteArray[index] & 0xff)*255 + (byteArray[index+1] & 0xff);
            index = index + 2;
        }

        data = new int[initialData[0]][initialData[1]];
        start = new Position(initialData[2],initialData[3]);
        goal = new Position(initialData[4],initialData[5]);

        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[0].length;j++)
            {
                data[i][j] = byteArray[index];
                index++;
            }
        }
    }

    /**
     * This method print the maze where every passage is
     * a empty space and every wall is a "X".
     * the start mark with S
     * the goal mark with E
     */
    public void print()
    {
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[i].length;j++)
            {
                if(i == start.getRow() && j == start.getColumn())
                    System.out.print("S");

                else if(i == goal.getRow() && j == goal.getColumn())
                    System.out.print("E");

                else if(data[i][j] == 0)
                    System.out.print(" ");
                else if(data[i][j] == 1)
                    System.out.print("X");
            }
            System.out.println("");
        }
    }

    /**
     * This method return the start of the maze
     * @return Position
     */
    public Position getStartPosition()
    {
        return start;
    }

    /**
     * This method return the goal of the maze
     * @return Position
     */
    public Position getGoalPosition()
    {
        return goal;
    }

    /**
     * This method return the maze as a matrix of zeros(for passage) and ones(for wall)
     * @return int[][]
     */
    public int[][] getData()
    {
        return data;
    }

    public void setStart(Position start)
    {
        this.start = start;
    }

    public void setGoal(Position goal)
    {
        this.goal = goal;
    }

    public void setData(int[][] data)
    {
        this.data = data;
    }

    public byte[] toByteArray()
    {
        if(data == null || data[0] == null || start == null || goal == null)
            return new byte[0];

        int[] initialData = new int[6];
        initialData[0] = data.length;
        initialData[1] = data[0].length;
        initialData[2] = start.getRow();
        initialData[3] = start.getColumn();
        initialData[4] = goal.getRow();
        initialData[5] = goal.getColumn();

        byte[] byteArray = new byte[data.length*data[0].length + 12];

        int index = 0;
        for(int i=0;i<6;i++)
        {
            int c = 0;
            while(initialData[i] >= 0)
            {
                if(initialData[i] >= 255)
                {
                    c++;
                    initialData[i] = initialData[i] - 255;
                }
                else
                {
                    byteArray[index] = (byte)c;
                    byteArray[index+1] = (byte)initialData[i];
                    index = index + 2;
                    initialData[i] = -1;
                }
            }
        }
        for(int i=0;i<data.length;i++)
        {
            for(int j=0;j<data[0].length;j++)
            {
                byteArray[index] = (byte)data[i][j];
                index++;
            }
        }
        return byteArray;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(this.toByteArray());
    }
}
