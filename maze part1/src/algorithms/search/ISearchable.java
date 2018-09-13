package algorithms.search;

import java.util.ArrayList;

/**
 * This interface defines the functionality of a searchable object.
 * Created by ronnie on 4/16/2017.
 */
public interface ISearchable {

    /**
     * This method return the initial situation from which we want to begin to search.
     * @return AState
     */
    public AState getInitialState();

    /**
     * This method return the goal situation we want to reach.
     * @return AState
     */
    public AState getGoalState();

    /**
     * This method return a list of legal states that can be reached from the current state.
     * @param s is the current state.
     * @return ArrayList<AState>
     */
    public ArrayList<AState> getAllPossibleStates(AState s);

}
