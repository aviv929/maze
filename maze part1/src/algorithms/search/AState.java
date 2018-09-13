package algorithms.search;

/**
 * This abstract class defines the functionality of a
 * single state in a domain.
 * every state can be compare to other state in a domain so
 * this abstract class implements Comparable.
 * every state represent by a string.
 * every state have a cost that represent the investment needed
 * to get to this state
 * every state have a came from state how represent the
 * preceding state.
 * Created by ronnie on 4/16/2017.
 */
public abstract class AState implements Comparable {
    private String state;
    private double cost;
    private AState cameFrom;

    public AState(String state, double cost, AState cameFrom)
    {
        this.state = state;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    protected void setCost(double cost)
    {
        this.cost = cost;
    }

    protected void setCameFrom(AState cameFrom)
    {
        this.cameFrom = cameFrom;
    }

    public String getState()
    {
        return state;
    }

    public double getCost()
    {
        return cost;
    }

    public AState getCameFrom()
    {
        return cameFrom;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == null || this.getClass() != obj.getClass())
            return false;
        AState objS = (AState)obj;
        return state.equals(objS.state);
    }

    @Override
    public String toString()
    {
        return state;
    }

    @Override
    public int compareTo(Object o)
    {
        if(o == null || this.getClass() != o.getClass())
            return 1;

        if(this.getCost() < ((AState)o).getCost())
            return -1;
        if(this.getCost() > ((AState)o).getCost())
            return 1;
        return 0;
    }

    @Override
    public int hashCode()
    {
        if(state == null)
            return 0;
        return state.hashCode();
    }
}
