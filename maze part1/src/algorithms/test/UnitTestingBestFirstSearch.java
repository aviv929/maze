package algorithms.test;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class preform tests to BestFirstSearch class.
 * Created by ronnie on 4/19/2017.
 */
public class UnitTestingBestFirstSearch {

    @Test
    public void mazeSize() throws Exception
    {
        IMazeGenerator ig = new MyMazeGenerator();
        Maze maze = ig.generate(0,0);
        assertEquals(maze.getData().length,10);
        assertEquals(maze.getData()[0].length,10);
    }

    @Test
    public void notNull1() throws Exception
    {
        IMazeGenerator ig = new MyMazeGenerator();
        Maze maze = ig.generate(0,0);
        assertNotNull(maze);
    }

    @Test
    public void notNull2() throws Exception
    {

        IMazeGenerator ig = new MyMazeGenerator();
        Maze maze = ig.generate(-1,-1);
        assertNotNull(maze);
    }

    @Test
    public void multiplyMazes() throws Exception {
        IMazeGenerator ig = new MyMazeGenerator();
        for(int i=0;i<10;i++)
        {
            Maze maze = ig.generate(i,i*2);
            assertNotNull(maze);
        }
    }

    @Test
    public void testBfsWithNullMaze() throws Exception
    {
        ASearchingAlgorithm bfs = new BestFirstSearch();
        SearchableMaze maze = null;
        Solution sol = bfs.solve(maze);
        assertEquals(sol.getSolutionPath().size(),0);
    }

    @Test
    public void testBfsWithNullSrart() throws Exception
    {
        ASearchingAlgorithm bfs = new BestFirstSearch();
        SearchableMaze maze = new SearchableMaze(new Maze(null,new Position(4,4),new int[5][5]));
        Solution sol = bfs.solve(maze);
        assertEquals(sol.getSolutionPath().size(),0);
    }
}
