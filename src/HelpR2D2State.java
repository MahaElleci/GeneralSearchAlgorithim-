import java.util.ArrayList;

/** 
 * State definition for the HelpR2D2 problem 
 * @author Home
 *
 */

public class HelpR2D2State extends State {  
	Location R2D2location; 
	ArrayList<Location> rocksLocations;  
	Object [][] grid;
	String name;
	int countOfRocksOnPads; 
	
	
	
	public HelpR2D2State(Location l, ArrayList<Location> rocksl,Object [][] grid,int n) { 
		this.R2D2location = l; 
		this.rocksLocations = rocksl; 
		this.grid = grid;  
		this.countOfRocksOnPads = n;
		name = ""+l.row + l.column+""+rocksl;
		
	}

}
