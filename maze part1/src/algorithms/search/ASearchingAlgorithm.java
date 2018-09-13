package algorithms.search;

import java.util.*;

/**
 * This abstract class implements the ISearchingAlgorithm interface
 * that defines the functionality of a searching algorithms.
 * every searching algorithms have different implementation so
 * we measure every algorithm with the numbers of nodes evaluated
 * during the search.
 * Created by ronnie on 4/16/2017.
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int NodesEvaluated;
    HashMap<String,AState> all;

    /** {@inheritDoc} */
    @Override
    public int getNumberOfNodesEvaluated() {
        return NodesEvaluated;
    }

    /**
     * This method find and return the path from start state
     * to the goal state.
     * @param goal is the goal state of the domain
     * @param start is the start state of the domain
     * @return Solution
     */
    protected Solution backTrace(AState goal, AState start)
    {
        if(goal == null || start == null)
            return new Solution(new ArrayList<AState>());

        Stack<AState> stack = new Stack<AState>();
        stack.push(goal);

        AState next = goal.getCameFrom();
        while(!(next.equals(start)) && next != null)
        {
            stack.push(next);
            next = next.getCameFrom();
        }
        stack.push(start);

        ArrayList<AState> list = new ArrayList<AState>();
        while(!(stack.isEmpty()))
            list.add(stack.pop());

        Solution sol = new Solution(list);
        return sol;
    }

    /**
     * This method initial the "all" hash map that
     * keep all the states the have been Evaluated
     * here we also initial the numbers of nodes evaluated to 0.
     * we call this method in the start of the solve method.
     */
    protected void initialSearch()
    {
        this.NodesEvaluated = 0;
        this.all = new HashMap<String,AState>();
    }

    /**
     * This methos check if the domain is legal
     * @param domain is the domain on which the search is performed
     * @return true if the domain is legal, else return false.
     */
    protected boolean isLegal(ISearchable domain)
    {
        if(domain == null || domain.getGoalState() == null || domain.getInitialState() == null)
            return false;
        return true;
    }
}
