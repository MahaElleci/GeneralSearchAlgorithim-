/**
 * Search tree node definition
 * @author Home
 *
 */

public class SearchTreeNode implements Comparable<SearchTreeNode>{
	
	State state; 
	SearchTreeNode parentNode;  
	int cost;
	Operation operator; 
	int depth;  
	int heuristicValue;  
	int heuristicAndCost;
	 
    public static SearchStrategies evaluation; 
    
	
	public SearchTreeNode(State state, Operation operation,SearchTreeNode parent,int c,int d){
		this.state = state;
		this.operator = operation; 
		this.parentNode = parent;
		this.cost = c; 
		this.depth = d;
	
	} 
	
	
	

	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}

    

	public void setHeuristicAndCost(int heuristicAndCost) {
		this.heuristicAndCost = heuristicAndCost;
	}




	public SearchTreeNode getParentNode() {
		return parentNode;
	}



	public void setParentNode(SearchTreeNode parentNode) {
		this.parentNode = parentNode;
	}
  
	


	public void setCost(int cost) {
		this.cost = cost;
	}


    
	public void setDepth(int depth) {
		this.depth = depth;
	}



	@Override
	public String toString() {
		String s = ((HelpR2D2State) (this.state)).name + "-" + this.operator; 
//		String s  = "NODE OF COST-" + this.cost;
		return s;
	}




	@Override
	public int compareTo(SearchTreeNode o) { 
		
		if(evaluation == SearchStrategies.UC){
			return  this.cost -  o.cost;
   		}  
		if((evaluation == SearchStrategies.GREEDY1)||(evaluation == SearchStrategies.GREEDY2)){
			return  this.heuristicValue -  o.heuristicValue;
   		} 
		
		if((evaluation == SearchStrategies.ASTAR1)||(evaluation == SearchStrategies.ASTAR2)){
			return  this.heuristicAndCost - o.heuristicAndCost;
   		}
		
		return 0;
	}
	
	

}
