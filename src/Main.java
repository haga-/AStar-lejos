import lejos.nxt.Button;;

class AStar {
	
  public static void main(String args[]) {
    Map map = new Map(5,5);
    MyMap came_from = new MyMap();
    MyMap cost_so_far = new MyMap();
    PriorityQueue frontier = new PriorityQueue();
    
    // 
    Node start = map.map[4][2];
    Node goal = map.map[0][2];
    
    Robot robot = new Robot(start);
    frontier.add(start);

    came_from.put(start, start);
    cost_so_far.put(start, 0.0);
    double new_cost = 0;
    
    while (!frontier.isEmpty()) {
      Node current = frontier.poll();
      System.out.println(current);
      Button.waitForAnyPress();
      robot.moveTo(current, came_from);
      if (current.equals(goal)) break;
      for(Node next : map.neighbors(robot.my_pos, robot.looking_at, robot.neighbors())) {
        new_cost = (Double) cost_so_far.get(current) + map.calculateGCost(current, next);
        if(cost_so_far.get(next) == null) {
          cost_so_far.put(next, new_cost);
          map.calculateHCost(next, goal);
          frontier.add(next);
          //System.out.println("F next: " + next.getFCost());
          came_from.put(next, current);
        }
      }
    }
    // Shows reconstructed path
    System.out.println(goal);
    Node current = (Node) came_from.get(goal);
    while(!current.equals(start)) {
      System.out.println(current);
      current = (Node) came_from.get(current);
    }
    System.out.println(current);
    
    Button.waitForAnyPress();
  }

}
