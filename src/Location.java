/**
 * Location object represents a row and a column in the grid
 * @author Home
 *
 */

public class Location {

	int row;
	int column; 
	
	
	public Location(int r, int c){
		
		this.row = r;
		this.column = c;
	} 
	
	@Override
	public String toString() {
		String s = "ROW_"+this.row+"-Column_"+this.column;
		return s;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	} 
	
	
}
