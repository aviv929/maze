package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

import java.io.*;

/**
 * Created by ronnie on 18-May-17.
 */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try {
            ObjectInputStream in = new ObjectInputStream(inFromClient);
            ObjectOutputStream out = new ObjectOutputStream(outToClient);
            out.flush();

            Maze maze = (Maze) in.readObject();
            int c = maze.hashCode();
            String mazeName = c + "";
            Solution sol;

            File solution = new File(tempDirectoryPath + "/" + mazeName);

            if(solution.exists())
            {
                ObjectInputStream inFromFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "/" + mazeName));
                sol = (Solution) inFromFile.readObject();
            }
            else
            {
                SearchableMaze sm = new SearchableMaze(maze);
                BreadthFirstSearch bfs = new BreadthFirstSearch();
                sol = bfs.solve(sm);
                ObjectOutputStream outToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "/" + mazeName));
                outToFile.writeObject(sol);
                outToFile.flush();
            }
            out.writeObject(sol);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
