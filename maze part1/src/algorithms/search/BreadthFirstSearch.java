package algorithms.search;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * This class inherit BestFirstSearch class how inherit
 * the abstract class ASearchingAlgorithm that implements
 * ISearchingAlgorithm interface that defines the
 * functionality of a searching algorithms.
 * here we will preform a search on a searchable object
 * by the BFS algorithm.
 * Created by ronnie on 4/16/2017.
 */
public class BreadthFirstSearch extends BestFirstSearch {

    /**
     * {@inheritDoc}
     * we preform the search by a breadth pass on the domain.
     * we use the BestFirstSearch solve method.
     */
    @Override
    public Solution solve(ISearchable domain)
    {
        return super.solve(domain);
    }

    /**
     * {@inheritDoc}
     * here we initial the "open" queue to a regular queue.
     */
    @Override
    protected void initialQueue()
    {
        initialSearch();
        this.open = new ConcurrentLinkedDeque<AState>();
    }

    /** {@inheritDoc} */
    @Override
    protected void addNeighbors(ArrayList<AState> neighbors, AState state)
    {
        for(int i=0;i<neighbors.size();i++)
        {
            AState n = neighbors.get(i);
            if (!(all.containsKey(n.toString())))
            {
                open.add(n);
                all.put(n.toString(), n);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getName()
    {
        return "Breadth First Search";
    }
}
