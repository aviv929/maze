package algorithms.search;

import java.util.ArrayList;

/**
 * This class defines result of a search in a way
 * that we can save and go over the states that bring us
 * to the goal state.
 * in other words solution is a legal sequence of states
 * from start state to goal state.
 * Created by ronnie on 4/16/2017.
 */
public class Solution {
    private ArrayList<AState> sol;

    public Solution(ArrayList<AState> sol)
    {
        this.sol = sol;
    }

    public ArrayList<AState> getSolutionPath()
    {
        return sol;
    }
}
