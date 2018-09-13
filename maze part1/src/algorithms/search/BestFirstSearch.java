package algorithms.search;

import java.util.*;

/**
 * This class inherit the abstract class ASearchingAlgorithm that implements
 * ISearchingAlgorithm interface that defines the functionality of
 * a searching algorithms.
 * Best First Search work the same as Breadth First Search except that
 * the first one use Priority Queue.
 * Created by ronnie on 4/17/2017.
 */
public class BestFirstSearch extends ASearchingAlgorithm {
    protected java.util.Queue<AState> open;
    protected HashSet<AState> close;

    /**
     * {@inheritDoc}
     * here we search for the best path from start state to goal state.
     */
    @Override
    public Solution solve(ISearchable domain)
    {
        if(!(isLegal(domain)))
            return(new Solution(new ArrayList<AState>()));

        this.initialQueue();
        this.close = new HashSet<AState>();

        open.add(domain.getInitialState());
        all.put(domain.getInitialState().toString(),domain.getInitialState());

        while(open.size() > 0)
        {
            AState state = open.poll();
            NodesEvaluated++;
            close.add(state);

            if(state.equals(domain.getGoalState()))
                return backTrace(state,domain.getInitialState());

            ArrayList<AState> neighbors = domain.getAllPossibleStates(state);
            this.addNeighbors(neighbors,state);
        }
        return(new Solution(new ArrayList<AState>()));
    }

    /**
     * This method add the neighbors of state to the open queue.
     * @param neighbors is a list of state neighbors.
     * @param state is the current state.
     */
    protected void addNeighbors(ArrayList<AState> neighbors, AState state)
    {
        for(int i=0;i<neighbors.size();i++)
        {
            AState n = neighbors.get(i);
            if(!(all.containsKey(n.toString())))
            {
                open.add(n);
                all.put(n.toString(),n);
            }
            else
            {
                AState nIn = all.get(n.toString());
                if(nIn.getCost() > n.getCost())
                {
                    if(close.contains(nIn))
                        close.remove(nIn);
                    else
                        open.remove(nIn);
                    open.add(n);
                }
            }
        }
    }

    /**
     * here we initial the open queue to a Priority Queue
     * so the solve algorithm will pull the states by
     * priority
     */
    protected void initialQueue()
    {
        initialSearch();
        this.open = new PriorityQueue<AState>();
    }

    /** {@inheritDoc} */
    @Override
    public String getName()
    {
        return "Best First Search";
    }
}
