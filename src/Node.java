public class Node implements Comparable<Node> {
  int x, y;
  double g, h;
  boolean reachable;
  public Node(int _x, int _y) {
    x = _x; y = _y;
    g = h = Double.MAX_VALUE;
    reachable = true;
  }

  public String toString() {
    return "X: " + x + " Y: " + y;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if(!(o instanceof Node)) return false;
    Node l = (Node) o;
    return x == l.x && y == l.y;
  }

  public double getFCost() {
    return g + h;
  }

  @Override
  public int compareTo(Node l) {
	if (x >= l.x && y > l.y || x > l.x && y >= l.y) return 1;
    if (this.equals(l)) return 0;
    return -1;
  }
}