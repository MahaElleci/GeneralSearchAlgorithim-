import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GeneralSearch { 

	public static int expandedNodesCount;   
	public static int counter; 
	
	/**
	 * Hashtable for hashing the history of the states to handle repeated states
	 */
	public static Hashtable<String,SearchTreeNode> history;
	
	
	/** 
	 * General Search constructor  
	 * 
	 * @param number expanded nodes  
	 * @param counter for the limit of the iterative deepening strategy 
	 */
	public GeneralSearch(int nodeCount , int c) { 
		
		this.expandedNodesCount= nodeCount;
		this.counter = c;
	} 
	
	
	
	
	/**
	 * General search algorithm
	 * @param problem
	 * @param strategy 
	 * 
	 * @return goal node
	 */
	
	public static SearchTreeNode GenericSearch(SearchProblem problem, SearchStrategies strategy){
		//expandedNodesCount = 0; 
		
		history = new Hashtable<String,SearchTreeNode>(); 
		
		State initialState = problem.initialState; 
		SearchTreeNode inputNode = new SearchTreeNode(initialState,null,null,0,0);  
		
		SearchTreeNode removedNode;
		ArrayList<SearchTreeNode> expandedNodes; 
		
		ArrayList<SearchTreeNode> queue = new ArrayList<SearchTreeNode>();
		
		queue.add(inputNode); 
		
//		System.out.println("Initial queue: " + queue.toString());
		
	
		do{	 
			
		if(queue.isEmpty()){  
			    System.out.println("FAILURE! Queue is empty!");
				return null;
		}
				else {
				removedNode = queue.remove(0);  
//				System.out.println("Removed node: " + removedNode);
			    expandedNodesCount++;
				if(problem.goalTestFunc(removedNode)==true){
				
					return removedNode;  
					
					
			    } else {
			    	
			    	expandedNodes = problem.transitionFunction(removedNode);
//			    	System.out.println("Children Nodes:  " +  expandedNodes.toString());
			    	for(int i=0; i<expandedNodes.size(); i++){  
			    		
			    		expandedNodes.get(i).setDepth(removedNode.depth+1); 
			    		
			    		if(!history.containsKey(((HelpR2D2State)expandedNodes.get(i).state).name)){ 
			    			 history.put(((HelpR2D2State)expandedNodes.get(i).state).name,expandedNodes.get(i));
				    			
			    			  switch(strategy){ 
			    			  
				    				case BF : queue.add(expandedNodes.get(i));  break;	
//				    		                  System.out.println("New Queue: " + queue.toString()); break; 
				    		                  
				    				case DF : queue.add(0, expandedNodes.get(i));  break;	
//		    		                          System.out.println("New Queue: " + queue.toString()); break; 
		    		                          
		    		                          
				    				case UC : int pathCost = problem.pathCostFunc(expandedNodes.get(i)); 
				    				          expandedNodes.get(i).setCost(pathCost);
				    					      queue.add(0, expandedNodes.get(i));   
				    				          Collections.sort(queue); break;	
//  		                          	      System.out.println("New Queue: " + queue.toString()); break;          
				    		                 
				    				case ID :  if(expandedNodes.get(i).depth <= counter){
		    									queue.add(0, expandedNodes.get(i)); break; 
		    									} else break; 
				    				
				    				case GREEDY1 : int heurstic1 = problem.heuristicFunctionOne(expandedNodes.get(i));  
				    				               expandedNodes.get(i).setHeuristicValue(heurstic1); 
				    				               queue.add(expandedNodes.get(i));
				    				               Collections.sort(queue); break;	
//				    				               System.out.println("New Queue: " + queue.toString()); break;
				    							 
				    	
				    				case ASTAR1 :  int heurstic2 = problem.heuristicFunctionOne(expandedNodes.get(i)); 
				    							   int pathCost2 = problem.pathCostFunc(expandedNodes.get(i));  
				    							   int value = heurstic2 + pathCost2; 
				    							   expandedNodes.get(i).setHeuristicAndCost(value);
				    							   queue.add(expandedNodes.get(i));
					    				           Collections.sort(queue); break;	
//					    				           System.out.println("New Queue: " + queue.toString()); break;
				    				
				    				case GREEDY2 : int heurst = problem.heuristicFunctionTwo(expandedNodes.get(i));  
				    				               expandedNodes.get(i).setHeuristicValue(heurst); 
				    				               queue.add(expandedNodes.get(i));
				    				               Collections.sort(queue); break;	
//				    				               System.out.println("New Queue: " + queue.toString()); break;
	    							 
	    	
				    				case ASTAR2 :  int heurst2= problem.heuristicFunctionTwo(expandedNodes.get(i)); 
				    							   int pathCost3 = problem.pathCostFunc(expandedNodes.get(i));  
				    							   int value1 = heurst2 + pathCost3; 
				    							   expandedNodes.get(i).setHeuristicAndCost(value1);
				    							   queue.add(expandedNodes.get(i));
					    				           Collections.sort(queue); break;	
//					    				           System.out.println("New Queue: " + queue.toString());  break;	          
							    				               
					    				         
				    			
				    		
				    		} 
			    		
			    		}
			    	}
			    }
			
			}   
		
	} while(true);	 
		
			
		
	}  
	


}
