import java.util.ArrayList; 

/**
 * Abstract class for the Search Problem
 * @author Home
 *
 */
public abstract class SearchProblem { 
	
	State initialState;
    abstract public int pathCostFunc(SearchTreeNode n);
    abstract public boolean goalTestFunc(SearchTreeNode n);
    abstract public ArrayList<SearchTreeNode> transitionFunction(SearchTreeNode n);
    abstract public int heuristicFunctionOne(SearchTreeNode n);
    abstract public int heuristicFunctionTwo(SearchTreeNode n);

}
