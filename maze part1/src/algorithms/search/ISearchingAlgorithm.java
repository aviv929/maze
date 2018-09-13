package algorithms.search;

/**
 * This interface defines the functionality of a searching algorithms.
 * Created by ronnie on 4/16/2017.
 */
public interface ISearchingAlgorithm {

    /**
     * This method search for a legal sequence of states from start state to goal state on domain.
     * @param domain is a searchable object on which we will run a search
     * @return Solution
     */
    public Solution solve(ISearchable domain);

    /**
     * This method return the name of the searching algorithm.
     * @return String
     */
    public String getName();

    /**
     * This method return the numbers of nodes that have been evaluated during the search.
     * @return int
     */
    public int getNumberOfNodesEvaluated();
}
