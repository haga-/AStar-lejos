import java.util.ArrayList;

public class PriorityQueue {
	ArrayList<Node> locations;
	
	public PriorityQueue() {
		locations = new ArrayList<Node>();
	}
	
	public boolean isEmpty() {
		return locations.isEmpty();
	}
	
	public Node poll() {
		if (isEmpty()) return null;
		int min = 0;
		for(int i = 0; i < locations.size(); i++) {
			if(locations.get(min).getFCost() > locations.get(i).getFCost())
				min = i;
		}
		
		Node front = locations.get(min);
		locations.remove(min);
		
		return front;
	}
	
	public void add(Node n) {
		locations.add(n);
	}
}