package algorithms.test;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze[] mazes = new Maze[10];
        for(int i=0;i<10;i++)
        {
            mazes[i] =  mazeGenerator.generate(300, 300); //Generate new maze
        }
        try {
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new FileOutputStream(mazeFileName));
            for(int i=0;i<10;i++)
            {
                out.write(mazes[i].toByteArray());
                out.flush();
                out.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        byte[][] savedMazeBytes = new byte[10][0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new FileInputStream(mazeFileName));
            for(int i=0;i<10;i++)
            {
                savedMazeBytes[i] = new byte[mazes[i].toByteArray().length];
                in.read(savedMazeBytes[i]);
                in.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=0;i<10;i++)
        {
            Maze loadedMaze = new Maze(savedMazeBytes[i]);
            boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(),mazes[i].toByteArray());
            System.out.println(String.format("Mazes " + i + " equal: %s",areMazesEquals)); //maze should be equal to loadedMaze
        }

    }
}

