package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class inherit ASearchingAlgorithm that implements
 * ISearchingAlgorithm interface that defines the functionality of
 * a searching algorithms.
 * here we will preform a search on a searchable object
 * by the DFS algorithm.
 * Created by ronnie on 4/16/2017.
 */
public class DepthFirstSearch extends ASearchingAlgorithm{

    /**
     * {@inheritDoc}
     * we preform the search by a depth pass on the states.
     */
    @Override
    public Solution solve(ISearchable domain)
    {
        if(!(isLegal(domain)))
            return(new Solution(new ArrayList<AState>()));

        initialSearch();
        Stack<AState> stack = new Stack<AState>();

        stack.push(domain.getInitialState());
        while(!(stack.isEmpty()))
        {
            AState state = stack.pop();
            NodesEvaluated++;

            if(state.equals(domain.getGoalState()))
                return backTrace(state,domain.getInitialState());

            if(!(all.containsKey(state.toString())))
            {
                all.put(state.toString(),state);
                ArrayList<AState> neighbors = domain.getAllPossibleStates(state);

                for(int i=0;i<neighbors.size();i++)
                {
                    AState n = neighbors.get(i);
                    if(!(all.containsKey(n.toString())) && !(stack.contains(n)))
                    {
                        n.setCameFrom(state);
                        stack.push(n);
                    }
                }
            }
        }
        return(new Solution(new ArrayList<AState>()));
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return "Depth First Search";
    }
}
