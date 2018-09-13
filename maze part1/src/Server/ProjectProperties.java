package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;

import java.io.*;
import java.util.Properties;

/**
 * Created by ronnie on 29-May-17.
 */
public class ProjectProperties {
    private static boolean firstTime = initialPropertiesFile();
    private static int NumOfThreds;
    private static AMazeGenerator mazeGenerator;
    private static ASearchingAlgorithm searchingAlgorithm;

    private static boolean initialPropertiesFile()
    {
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream(System.getProperty("user.dir") + "\\config.properties");

            prop.setProperty("NumOfThreads","10");
            prop.setProperty("GenerateMazeAlgorithm","MyMazeGenerator");
            prop.setProperty("SearchingAlgorithm","BreadthFirstSearch");

            prop.store(output,null);

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            if(output != null)
            {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            }
        }
        return false;
    }

    public static int getNumOfThreads()
    {
        getProperties();
        if(NumOfThreds < 1)
            return 10;
        return NumOfThreds;
    }

    public static AMazeGenerator getMazeGenerator()
    {
        getProperties();
        if(mazeGenerator == null)
            return new MyMazeGenerator();
        return mazeGenerator;
    }

    public static ASearchingAlgorithm getSearchingAlgorithm()
    {
        getProperties();
        if(searchingAlgorithm == null)
            return new BreadthFirstSearch();
        return searchingAlgorithm;
    }

    private static void getProperties()
    {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
            if(input == null)
                return;
            prop.load(input);
            String s_numOfThreads = prop.getProperty("NumOfThreads");
            String s_mazeGenerator = prop.getProperty("GenerateMazeAlgorithm");
            String s_SearchingAlgorithm = prop.getProperty("SearchingAlgorithm");

            if(s_numOfThreads != null)
            {
                try{
                    NumOfThreds = Integer.parseInt(s_numOfThreads);
                } catch (Exception e){
                    NumOfThreds = 10;
                }
            }

            if(s_mazeGenerator != null)
            {
                if(s_mazeGenerator.equals("SimpleMazeGenerator"))
                    mazeGenerator = new SimpleMazeGenerator();
                else
                    mazeGenerator = new MyMazeGenerator();
            }

            if(s_SearchingAlgorithm != null)
            {
                if(s_SearchingAlgorithm.equals("BestFirstSearch"))
                    searchingAlgorithm = new BestFirstSearch();
                else if(s_SearchingAlgorithm.equals("DepthFirstSearch"))
                    searchingAlgorithm = new DepthFirstSearch();
                else
                    searchingAlgorithm = new BreadthFirstSearch();
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }finally {
            if(input != null)
            {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("IOException: " + e.getMessage());
                }
            }
        }
    }
}
