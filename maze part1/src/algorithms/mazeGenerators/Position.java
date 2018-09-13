package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * This class defines and implements the functionality of a point
 * in two-dimensional matrix.
 * every point have 2 coordinate:
 * row is the row number of the point.
 * column is the column number of the point.
 * Created by ronnie on 4/15/2017.
 */
public class Position implements Serializable{
    private int row;
    private int column;

    public Position(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public Position()
    {
        this.row = 0;
        this.column = 0;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }

    @Override
    public String toString() {
        return "{" + row + "," + column + "}";
    }

    /**
     * This method compere between 2 points
     * @param obj is an other point
     * @return true if the 2 points are equal otherwise false
     */
    @Override
    public boolean equals(Object obj)
    {

        Position objP = (Position)obj;
        if(this.row == objP.row && this.column == objP.column)
            return true;

        return false;
    }
}
