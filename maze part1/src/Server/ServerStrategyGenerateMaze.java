package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;

/**
 * Created by ronnie on 18-May-17.
 */
public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient)
    {
        try {
            ObjectInputStream in = new ObjectInputStream(inFromClient);
            ObjectOutputStream out = new ObjectOutputStream(new MyCompressorOutputStream(outToClient));
            out.flush();

            int[] dataInitial = (int[]) in.readObject();
            if(dataInitial.length < 2)
                dataInitial = new int[2];

            MyMazeGenerator mg = new MyMazeGenerator();
            Maze maze = mg.generate(dataInitial[0],dataInitial[1]);

            ByteArrayOutputStream b = new ByteArrayOutputStream(dataInitial[0]*dataInitial[1]+12);
            OutputStream compressor = new MyCompressorOutputStream(b);
            compressor.write(maze.toByteArray());

            out.writeObject(b.toByteArray());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
