import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HelpR2D2 extends SearchProblem {  
	
	 public static int m;
	 public static int n;  
	 
	 public static ArrayList<Location> initialRocksLocations; 
	 public static ArrayList<Location> padsLocations; 
	 public static ArrayList<Location> obstecalsLocations; 
	 
	 public static Location teleportalLocation; 
	 public static Location initialR2D2Location; 
	 
	 public HelpR2D2(Object[][] grid){
	    super();
		super.initialState = new HelpR2D2State(initialR2D2Location,initialRocksLocations,grid,0);
	 }
	
	/**
	 * Generate Grid Function 
	 * 
	 * @return the generated grid with random locations of grid items 
	 */ 
	 
	public static Object[][] GenGrid() {
		
		
		 m = 2 + (int) (Math.random() * 5);
		 n = 2 + (int) (Math.random() * 5);  
		 
		Object [] [] grid = new Object[m][n]; 
	
		int numberOfRocksAndPads = (int) (m * n * 0.2);  
		int numberOfObstacles = (int) (m * n * 0.15); 
		
		
		padsLocations = new ArrayList<Location>();  
		obstecalsLocations = new ArrayList<Location>(); 
		initialRocksLocations = new ArrayList<Location>();
		

/**
 * This loop for assigning the rocks randomly in the grid		
 */
		for(int i=0; i<numberOfRocksAndPads; i++){ 
			int rowIndex = 0 + (int) (Math.random() * m); 
			int columnIndex = 0 + (int) (Math.random() *n);
			   
			if(grid[rowIndex][columnIndex] == null){
			   grid[rowIndex][columnIndex] = GridItems.ROCK; 
			   initialRocksLocations.add(new Location(rowIndex,columnIndex));
			  
			} else {
				i--; 
				
			}			
		} 
/**
 * This loop for assigning the pressure pads randomly in the grid	
 */
        for(int i=0; i<numberOfRocksAndPads; i++){ 
			
			int rowIndex = 0 + (int) (Math.random() * m); 
			int columnIndex = 0 + (int) (Math.random() *n);
			   
			if(grid[rowIndex][columnIndex] == null){
			  grid[rowIndex][columnIndex] = GridItems.PRESSURE_PAD; 
			  padsLocations.add(new Location(rowIndex,columnIndex));
				
			} else {
				i--;
			}
		}  
        
/** 
 * This loop for assigning the unmovable obstcales randomly in the grid 
 */ 
         for(int i=0; i<numberOfObstacles; i++){ 
			
			int rowIndex = 0 + (int) (Math.random() * m); 
			int columnIndex = 0 + (int) (Math.random() *n);
			   
			if(grid[rowIndex][columnIndex] == null){
			  grid[rowIndex][columnIndex] = GridItems.OBSTACLE; 
			  obstecalsLocations.add(new Location(rowIndex,columnIndex));
				
			} else {
				i--;
			}
		}
/**
 * This loop for assigning the teleportal location      
 */
        while(true) {
        int rowIndex = 0 + (int) (Math.random() * m); 
		int columnIndex = 0 + (int) (Math.random() *n); 
		
		if(grid[rowIndex][columnIndex]==null){ 
			
		   grid[rowIndex][columnIndex] = GridItems.TELEPORTAL;
		   teleportalLocation = new Location(rowIndex,columnIndex);
		   break;
		 }  
		   } 
        
/** 
 * This loop for assigning R2D2 location
 */ 
        
        while(true) {
            int rowIndex = 0 + (int) (Math.random() * m); 
    		int columnIndex = 0 + (int) (Math.random() *n); 
    		
    		if(grid[rowIndex][columnIndex]==null){ 
    		initialR2D2Location = new Location(rowIndex,columnIndex);
    		   grid[rowIndex][columnIndex] = GridItems.R2D2;
    		   break;
    		 }  
    		   }
       
        System.out.println("Original grid: "+Arrays.deepToString(grid));  
    
        try {
			PrintWriter printWriter = new PrintWriter("R2D2.pl","UTF-8"); 
			printWriter.println("gridsize("+m+","+n+").");
			printWriter.println("wallrowsUp(-1).");
			printWriter.println("wallcolumnsLeft(-1)."); 
			printWriter.println("wallrowsDown("+m+")."); 
			printWriter.println("wallcolumnsRight("+n+").");
			printWriter.println("teleportal("+teleportalLocation.row+","+teleportalLocation.column+")."); 
			printWriter.println("r2d2At("+initialR2D2Location.row+","+initialR2D2Location.column+",S0).");
			for(int i = 0; i<padsLocations.size();i++)
				printWriter.println("pad("+padsLocations.get(i).row+","+padsLocations.get(i).column+").");
			
			for(int i = 0; i<initialRocksLocations.size();i++)
				printWriter.println("rockAt("+initialRocksLocations.get(i).row+","+initialRocksLocations.get(i).column+",S0).");
				
			for(int i = 0; i<obstecalsLocations.size();i++)
				printWriter.println("obstacle("+obstecalsLocations.get(i).row+","+obstecalsLocations.get(i).column+").");
			
			printWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return grid;
        
	} 
	 
	
	/**
	 * Updates the locations of the rocks after PUSH operations
	 * 
	 * @return the updated rocks locations list
	 */
	public static ArrayList<Location> updateRocksLocations(ArrayList<Location> rocksLocat,int oldRow, int oldColumn ,int newRow, int newColumn){
		 
		ArrayList<Location> result = new ArrayList<Location>(); 
		     for(int i=0; i<rocksLocat.size(); i++){
		    	 Location rockLoc = new Location (rocksLocat.get(i).row, rocksLocat.get(i).column);
		         result.add(rockLoc);	

			}   
		     
		     for(int i=0; i<result.size();i++){
		    	 if((result.get(i).row == oldRow)&&(result.get(i).column == oldColumn)){
						result.get(i).setRow(newRow);
						result.get(i).setColumn(newColumn);  
			 		}
		     }
			
            
			return result;
	}
	
	/**
	 * Updates grid according to the state 
	 * 
	 * @return new updated grid
	 */ 
	
	public static Object[][] updateGrid(Location r2d2 , ArrayList<Location> rocksLocations){ 
		
		Object[][] newGrid = new Object[m][n];  
        
        
        for(int i=0;i<padsLocations.size();i++) {
          newGrid[padsLocations.get(i).row][padsLocations.get(i).column]= GridItems.PRESSURE_PAD;	
        }
        for(int i=0;i<obstecalsLocations.size();i++){
            newGrid[obstecalsLocations.get(i).row][obstecalsLocations.get(i).column]= GridItems.OBSTACLE;	

        }
        for(int i=0;i<rocksLocations.size();i++){
            newGrid[rocksLocations.get(i).row][rocksLocations.get(i).column]= GridItems.ROCK;	
 
        } 
        newGrid[r2d2.row][r2d2.column] = GridItems.R2D2;
        newGrid[teleportalLocation.row][teleportalLocation.column]=GridItems.TELEPORTAL; 
        

        if((r2d2.row == teleportalLocation.row) && 
        		(r2d2.column == teleportalLocation.column)){
        	 newGrid[teleportalLocation.row][teleportalLocation.column]=GridItems.R2D2_ON_TELEPORTAL;
        }
           return newGrid;
		
	}
	
	/**
	 * Updates R2D2 Location in grid
	 */ 
	
	public static Location updateR2D2Location(HelpR2D2State state,int newRow, int newColumn){

		
		int r2d2Row = newRow;
		int r2d2Column = newColumn;
		Location loc = new Location(r2d2Row,r2d2Column);
		return loc;
		
	}
	
	/**
	 * Transition Function 
	 * 
	 * @param current state represented in the tree node
	 * @return all possible states coming from this state
	 */ 
	
	public ArrayList < SearchTreeNode > transitionFunction(SearchTreeNode node) {
	   
		HelpR2D2State st= (HelpR2D2State) node.state;
  	    Object[][] grid = st.grid;
  	    
  	  
	    ArrayList <SearchTreeNode> space = new ArrayList < SearchTreeNode > ();

	    int row = st.R2D2location.row;
	    int column = st.R2D2location.column;  
	    
	    if (column + 1 < n) { //EAST MOVE
	        if (grid[row][column + 1] == GridItems.ROCK) {
	            if (column + 2 < n) {                                                                                                                 
	                if  (grid[row][column + 2] == null || grid[row][column + 2] == GridItems.TELEPORTAL) {
	                   
	                	ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row, column + 1, row, column + 2);
	                	Location r2d2Loc = updateR2D2Location(st,row, column + 1);
	                	HelpR2D2State s1 = new HelpR2D2State(r2d2Loc, newRocksLocations,updateGrid(r2d2Loc,newRocksLocations),st.countOfRocksOnPads);   
	                    SearchTreeNode n1 = new SearchTreeNode(s1, Operation.FE_Push,node,1,1);
	                    space.add(n1); 
	                    
	                   
	                }
	                if (grid[row][column + 2] == GridItems.PRESSURE_PAD||grid[row][column + 2] == GridItems.TELEPORTAL){
		                   
	                	ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row, column + 1, row, column + 2);
	                	Location r2d2Loc = updateR2D2Location(st,row, column + 1);
	                	HelpR2D2State s10 = new HelpR2D2State(r2d2Loc, newRocksLocations,updateGrid(r2d2Loc,newRocksLocations),st.countOfRocksOnPads+1);  
	                    SearchTreeNode n10 = new SearchTreeNode(s10, Operation.FE_Push,node,1,1);
	                    space.add(n10);
	                  
	                }
	            }
	        } 
	        
	        if ((grid[row][column + 1] == GridItems.PRESSURE_PAD) || (grid[row][column + 1] == null) ||(grid[row][column + 1] == GridItems.TELEPORTAL)) {
                Location r2d2Loc = updateR2D2Location(st,row, column + 1);
	            HelpR2D2State s2 = new HelpR2D2State(r2d2Loc, st.rocksLocations,updateGrid(r2d2Loc,st.rocksLocations),st.countOfRocksOnPads);
	            SearchTreeNode n2 = new SearchTreeNode(s2, Operation.FE,node,1,1);
	            space.add(n2);
	        }


	    }

	    if (column - 1 >= 0) { //WEST MOVE
//	  
	        if (grid[row][column - 1] == GridItems.ROCK) {
	            if (column - 2 >= 0) {
	                if (grid[row][column - 2] == null || grid[row][column - 2] == GridItems.TELEPORTAL) {
	                	
	                	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row, column - 1, row, column -2 );
	                	 Location r2d2loc2 = updateR2D2Location(st,row, column - 1);
	                    HelpR2D2State s3 = new HelpR2D2State(r2d2loc2, newRocksLocations,updateGrid(r2d2loc2,newRocksLocations),st.countOfRocksOnPads);
	                    SearchTreeNode n3 = new SearchTreeNode(s3, Operation.FW_Push,node,1,1);
	                    space.add(n3);
	                 
	                }
	                if (grid[row][column - 2] == GridItems.PRESSURE_PAD || grid[row][column - 2] == GridItems.TELEPORTAL) {
	                	
	                	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row, column - 1, row, column -2 );
	                	 Location r2d2loc2 = updateR2D2Location(st,row, column - 1);
	                    HelpR2D2State s3 = new HelpR2D2State(r2d2loc2, newRocksLocations,updateGrid(r2d2loc2,newRocksLocations),st.countOfRocksOnPads+1);
	                    SearchTreeNode n3 = new SearchTreeNode(s3, Operation.FW_Push,node,1,1);
	                    space.add(n3);
	                }
	            }
	        } 
	        
	        if ((grid[row][column - 1] == GridItems.PRESSURE_PAD) || (grid[row][column - 1] == null) || (grid[row][column - 1] == GridItems.TELEPORTAL)) {
                Location r2d2loc2 = updateR2D2Location(st,row, column - 1);
                HelpR2D2State s4 = new HelpR2D2State(r2d2loc2, st.rocksLocations,updateGrid(r2d2loc2,st.rocksLocations),st.countOfRocksOnPads);
	            SearchTreeNode n4 = new SearchTreeNode(s4, Operation.FW,node,1,1);
	            space.add(n4);
	        }


	    }
	    if (row + 1 < m) { //SOUTH MOVe
	        if (grid[row + 1][column] == GridItems.ROCK) {
	            if (row + 2 < m) {
	                if (grid[row + 2][column] == null || grid[row + 2][column] == GridItems.TELEPORTAL) {	
	               	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row+1, column, row+2, column );
	               	 Location r2d2Loc3 = updateR2D2Location(st,row + 1, column);
                    HelpR2D2State s5 = new HelpR2D2State(r2d2Loc3, newRocksLocations,updateGrid(r2d2Loc3,newRocksLocations),st.countOfRocksOnPads);
                    SearchTreeNode n5 = new SearchTreeNode(s5, Operation.FS_Push,node,1,1);
                    space.add(n5);
	                }
	                if (grid[row + 2][column] == GridItems.PRESSURE_PAD || grid[row + 2][column] == GridItems.TELEPORTAL ) {	
		               	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row+1, column, row+2, column );
		               	 Location r2d2Loc3 = updateR2D2Location(st,row + 1, column);
		                    HelpR2D2State s5 = new HelpR2D2State(r2d2Loc3, newRocksLocations,updateGrid(r2d2Loc3,newRocksLocations),st.countOfRocksOnPads+1);
		                    SearchTreeNode n5 = new SearchTreeNode(s5, Operation.FS_Push,node,1,1);
		                    space.add(n5);
		              
		                }
	                
	            }
	        } 
	       
	        if ((grid[row + 1][column] == GridItems.PRESSURE_PAD) || (grid[row + 1][column] == null) ||(grid[row + 1][column] == GridItems.TELEPORTAL) ) {
                Location r2d2Loc3 = updateR2D2Location(st,row + 1, column); 
	            HelpR2D2State s6 = new HelpR2D2State(r2d2Loc3, st.rocksLocations,updateGrid(r2d2Loc3,st.rocksLocations),st.countOfRocksOnPads);
	            SearchTreeNode n6 = new SearchTreeNode(s6, Operation.FS,node,1,1);
	            space.add(n6);
	        }


	    }

	    if (row - 1 >= 0) { //North MOVE
	        if (grid[row - 1][column] == GridItems.ROCK) {
	            if (row - 2 >= 0) {
	                if (grid[row - 2][column] == null || grid[row - 2][column] == GridItems.TELEPORTAL) {
	                	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row-1, column, row-2, column );
	                    Location r2d2Loc4 = updateR2D2Location(st,row - 1, column);
	                    HelpR2D2State s7 = new HelpR2D2State(r2d2Loc4, newRocksLocations,updateGrid(r2d2Loc4,newRocksLocations),st.countOfRocksOnPads);
	                    SearchTreeNode n7 = new SearchTreeNode(s7, Operation.FN_Push,node,1,1);
	                    space.add(n7);
	                }
	                if (grid[row - 2][column] == GridItems.PRESSURE_PAD || grid[row - 2][column] == GridItems.TELEPORTAL) {
	                	 ArrayList<Location> newRocksLocations =  updateRocksLocations(st.rocksLocations,row-1, column, row-2, column );
	                    Location r2d2Loc4 = updateR2D2Location(st,row - 1, column);
	                    HelpR2D2State s7 = new HelpR2D2State(r2d2Loc4, newRocksLocations,updateGrid(r2d2Loc4,newRocksLocations),st.countOfRocksOnPads+1);
	                    SearchTreeNode n7 = new SearchTreeNode(s7, Operation.FN_Push,node,1,1);
	                    space.add(n7);
	                }
	            }
	        } 
	        
	        if ((grid[row - 1][column] == GridItems.PRESSURE_PAD) || (grid[row - 1][column] == null) || grid[row -1][column] == GridItems.TELEPORTAL) {
                Location r2d2Loc4 = updateR2D2Location(st,row - 1, column);
	            HelpR2D2State s8 = new HelpR2D2State(r2d2Loc4, st.rocksLocations,updateGrid(r2d2Loc4,st.rocksLocations),st.countOfRocksOnPads);
	           
	            	
	            SearchTreeNode n8 = new SearchTreeNode(s8, Operation.FN,node,1,1);
	            n8.setParentNode(node);
	            space.add(n8);
	        }


	    }
	   
	    	

	    return space;
	} 
	
	
	/**
	 * Path cost function
	 */ 
	
	@Override
	public int pathCostFunc(SearchTreeNode n) {
		int cost = 0;
		if(n==null) 
			   return cost; 
			
			else{ 
			   cost++;
			   cost+=pathCostFunc(n.getParentNode());
			} 
	  return cost;		
	}
	
	/** 
	 * Goal test function
	 * @return flag indicates whether a node passed the goal test or not
	 */

	public boolean goalTestFunc(SearchTreeNode n) {
		 if((((HelpR2D2State)n.state).R2D2location.row == teleportalLocation.row) && (((HelpR2D2State)n.state).R2D2location.column == teleportalLocation.column)
				 && (((HelpR2D2State)n.state).countOfRocksOnPads == padsLocations.size())){
		
			 return true; 
			 
		 }
		
		return false;
	}  
	
	
  /**
   * Path back track for input node
   * @return returns a string of the path operators of the node
   *
   */
	
	public static String pathBacktrack(SearchTreeNode n, boolean visualize){
		String s = ""; 
		
		
		if((n.getParentNode()==null) ||(n==null))
		   return s; 
		
		else{ 
		   s+= pathBacktrack(n.getParentNode(),visualize);   
		   s+= n.operator + " -> "; 
 
		} 
		
		if(visualize) 
			   System.out.println(Arrays.deepToString(((HelpR2D2State)n.state).grid)); 
		
	 return s;
		
	}  
	
	/**
	 * First heuristic function (Dropping OBSTACLES/ROCKS/PRESSURE_PADS constraints
	 */
	
	public int heuristicFunctionOne(SearchTreeNode node)
	{  
		int rowPos = 0;
		int colPos = 0;

		Location locR2D2 = ((HelpR2D2State)node.state).R2D2location;
		Location locTeleportal = teleportalLocation;
		
		if(locR2D2.row>=locTeleportal.row)
			rowPos = locR2D2.row - locTeleportal.row;
		else 
			rowPos =locTeleportal.row- locR2D2.row; 
		
		if(locR2D2.column>=locTeleportal.column)
			colPos = locR2D2.column-locTeleportal.column;
		else
			colPos =locTeleportal.column -locR2D2.column; 
		
		return rowPos+colPos;
		
		
	} 
	
	/**
	 * Second heuristic function (Dropping TELEPORTAL/OBSTACLES constraints
	 */

	public int heuristicFunctionTwo(SearchTreeNode node){ 
		int rowPos = 0;
		int colPos = 0;
         
		int estimateRow = 0;
		int estimateColumn = 0; 
		
		int val = 0 ;
		int minRock = 0;
		int minPad = 100000;
		
		HelpR2D2State state = (HelpR2D2State)node.state;
		Location locR2D2 = state.R2D2location;
		Location locRock = new Location(0, 0); 
		

		rowPos = state.rocksLocations.get(0).row;
		colPos = state.rocksLocations.get(0).column; 
		
		if(locR2D2.row>=rowPos)
			estimateRow = locR2D2.row - rowPos;
		else 
			estimateRow =rowPos- locR2D2.row; 
		
		if(locR2D2.column>=colPos)
			estimateColumn = locR2D2.column-colPos;
		else
			estimateColumn =colPos -locR2D2.column; 
		
		minRock = estimateRow+estimateColumn;
		locRock = state.rocksLocations.get(0);
		
		for(int i = 1; i<state.rocksLocations.size(); i++){  
			
				rowPos = state.rocksLocations.get(i).row;
				colPos = state.rocksLocations.get(i).column; 
				
				if(locR2D2.row>=rowPos)
					estimateRow = locR2D2.row - rowPos;
				else 
					estimateRow =rowPos- locR2D2.row; 
				
				if(locR2D2.column>=colPos)
					estimateColumn = locR2D2.column-colPos;
				else
					estimateColumn =colPos -locR2D2.column;  
				
				val = estimateRow+estimateColumn;
			
				if(val <= minRock){
					minRock = val;
					locRock = state.rocksLocations.get(i);
				}
		}
		
		
		for(int j=0; j<padsLocations.size(); j++){
			
			if( (locRock != padsLocations.get(j)) && !(state.rocksLocations.contains(padsLocations.get(j)))){
				
				rowPos = padsLocations.get(j).row;
				colPos = padsLocations.get(j).column; 
				
				if(locRock.row>=rowPos)
					estimateRow = locRock.row - rowPos;
				else 
					estimateRow =rowPos- locRock.row; 
				
				if(locRock.column>=colPos)
					estimateColumn = locRock.column-colPos;
				else
					estimateColumn =colPos -locRock.column; 
				
				val = estimateRow+estimateColumn;
				
				if(val <= minPad){
					minPad = val;
				}
				
				}
	}

		return minRock+minPad;
	
} 
	
	/**
	 * 
	 * Search function that calls the General Search function
	 * @return array of strings of sequence of operators, total cost to reach the goal, number of expanded nodes
	 */
	
	public static String[] search(Object[][] grid,  SearchStrategies strategy , boolean visualize){
		
		SearchTreeNode.evaluation = strategy;
		String[] result = new String[3]; 
		String cost = " "; 
		String nodes = " "; 
		SearchTreeNode goal;
		GeneralSearch gen = new GeneralSearch(0,0);
		
		HelpR2D2 problem = new HelpR2D2(grid); 
		
		if (strategy == SearchStrategies.ID){  
			
			  while(true){ 
				  
			goal = gen.GenericSearch(problem, strategy); 
			System.out.println("Current Iterative Deepining Limit: " + GeneralSearch.counter);
			 
			 if(goal!=null){ 
					
					result[0] = pathBacktrack(goal,visualize);  
					
					cost += problem.pathCostFunc(goal);
					result[1] = cost;  
					
					nodes += gen.expandedNodesCount; 
					result[2] = nodes;  
					
					return result;
				} 
			 
			gen.counter++; 
			 
			}
		}
		
		else {
		    goal = gen.GenericSearch(problem, strategy);
		} 
		
		if(goal!=null){ 
			
			result[0] = pathBacktrack(goal,visualize);  
			
			cost += problem.pathCostFunc(goal);
			result[1] = cost;  
			
			nodes += gen.expandedNodesCount; 
			result[2] = nodes; 
		} 

		
		return result;
	}
	

	
	public static void main(String[] args) { 
		Object[][] gridTest = GenGrid();

		
		/** demo test**/ 
		
//		      Object[][] demo = new Object[3][3];  
//		      
//			  m=3;
//			  n=3;  
//			  
//		      demo[0][0] = GridItems.R2D2;
//		      demo[2][0] = GridItems.OBSTACLE; 
//		      demo[0][1] = GridItems.ROCK;
//		      demo[0][2] = GridItems.PRESSURE_PAD; 
//		      demo[2][1] = GridItems.TELEPORTAL;
//		     
//		      
//		      padsLocations = new ArrayList<Location>(); 
//		      padsLocations.add(new Location(0,2)); 
//		      
//			  obstecalsLocations = new ArrayList<Location>(); 
//			  obstecalsLocations.add(new Location(2,0)); 
//				
//			  initialRocksLocations = new ArrayList<Location>(); 
//			  initialRocksLocations.add(new Location(0,1)); 
//			  
//			  teleportalLocation = new Location(2,1); 
//			  initialR2D2Location = new Location(0,0);
////		      
//		     System.out.println(Arrays.deepToString(demo));  
//		      
//	          
//		      String [] f1= search(demo, SearchStrategies.BF, true);  
//		      String [] f2= search(demo, SearchStrategies.DF,true); 
//		      String [] f3= search(demo, SearchStrategies.UC, true);
//		      String [] f4= search(demo, SearchStrategies.ID,true); 
//		      String [] f5= search(demo, SearchStrategies.GREEDY1, true); 
//		      String [] f6= search(demo, SearchStrategies.ASTAR1, true);
//		      String [] f7= search(demo, SearchStrategies.GREEDY2, true); 
//		      String [] f8= search(demo, SearchStrategies.ASTAR2, true);
//              System.out.println(Arrays.toString(f1));   
//              System.out.println(Arrays.toString(f2)); 
//              System.out.println(Arrays.toString(f3));
//              System.out.println(Arrays.toString(f4)); 
//              System.out.println(Arrays.toString(f5)); 
//              System.out.println(Arrays.toString(f6)); 
//              System.out.println(Arrays.toString(f7)); 
//              System.out.println(Arrays.toString(f8)); 
//              
              
              /** random generated grid testing **/ 
              
//              Object[][] gridTest = GenGrid();
//              String [] a1= search(gridTest, SearchStrategies.BF, false);  
//		      String [] a2= search(gridTest, SearchStrategies.DF,false); 
//		      String [] a3= search(gridTest, SearchStrategies.UC, false);
//		      String [] a4= search(gridTest, SearchStrategies.ID,false); 
//		      String [] a5= search(gridTest, SearchStrategies.GREEDY1, false); 
//		      String [] a6= search(gridTest, SearchStrategies.ASTAR1, false);
//		      String [] a7= search(gridTest, SearchStrategies.GREEDY2, false); 
//		      String [] a8= search(gridTest, SearchStrategies.ASTAR2, false);
//              System.out.println(Arrays.toString(a1));   
//              System.out.println(Arrays.toString(a2)); 
//              System.out.println(Arrays.toString(a3));
//              System.out.println(Arrays.toString(a4)); 
//              System.out.println(Arrays.toString(a5)); 
//              System.out.println(Arrays.toString(a6)); 
//              System.out.println(Arrays.toString(a7)); 
//              System.out.println(Arrays.toString(a8)); 
//              

	  		
		
	}

}