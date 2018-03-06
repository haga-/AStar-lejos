import java.util.ArrayList;

public class Map {
	Node[][] map;
	int height, width;
		
	public Map(int h, int w) {
	  height = h; width = w;
	  map = new Node[height][width];
	  for(int i = 0; i < height; i++) {
		 for(int j = 0; j < width; j++) {
			 map[i][j] = new Node(i,j);
		 }
	  }
	}

  // Returns array of reachable neighbors
  public ArrayList<Node> neighbors(Node l, String looking_at, ArrayList<Boolean> robot_neighbors) {
    ArrayList<Node> neighbors = new ArrayList<Node>();
    if (looking_at.equals("F")) {
    	// Left
        if(l.y-1 >= 0 && robot_neighbors.get(1)) 
        	neighbors.add(map[l.x][l.y-1]);
        // Front
        if(l.x-1 >= 0 && robot_neighbors.get(2)) 
        	neighbors.add(map[l.x-1][l.y]);
        // Right
        if(l.y+1 < width && robot_neighbors.get(0)) 
        	neighbors.add(map[l.x][l.y+1]);
        
    }
    if (looking_at.equals("L")) {
    	// Left
    	if(l.x+1 < height &&  robot_neighbors.get(1))
    		neighbors.add(map[l.x+1][l.y]);
    	// Front
    	if(l.y-1 >= 0 &&  robot_neighbors.get(2))
    		neighbors.add(map[l.x][l.y-1]);
    	// Right
    	if(l.x-1 >= 0  &&  robot_neighbors.get(0))
    		neighbors.add(map[l.x-1][l.y]);
    	
    }
    if (looking_at.equals("R")) {
    	// Left
    	if(l.x-1 >= 0 && robot_neighbors.get(1))
    		neighbors.add(map[l.x-1][l.y]);
    	// Front
    	if(l.y+1 < width && robot_neighbors.get(2))
    		neighbors.add(map[l.x][l.y+1]);
    	// Right
    	if(l.x+1 < height && robot_neighbors.get(0))
    		neighbors.add(map[l.x+1][l.y]);
    }
    if (looking_at.equals("B")) {
    	// Left
    	if(l.y-1 >= 0 && robot_neighbors.get(0))
    		neighbors.add(map[l.x][l.y-1]);
    	// Front
    	if(l.x+1 < height && robot_neighbors.get(2))
    		neighbors.add(map[l.x+1][l.y]);
    	// Right
    	if(l.y+1 < width && robot_neighbors.get(1))
    		neighbors.add(map[l.x][l.y+1]);
    }
    return neighbors;
  }

  public double calculateGCost(Node start, Node current) {
    if(map[current.x][current.y].g == Double.MAX_VALUE) {
      map[current.x][current.y].g = Math.abs(start.x - current.x) + Math.abs(start.y - current.y);
    }
    return map[current.x][current.y].g;
  }

  public double calculateHCost(Node current, Node finish) {
    if(map[current.x][current.y].h == Double.MAX_VALUE) {
      map[current.x][current.y].h = Math.abs(finish.x - current.x) + Math.abs(finish.y - current.y);
    }
    return map[current.x][current.y].h;
  }

}
