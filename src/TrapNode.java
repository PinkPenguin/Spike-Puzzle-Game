
//TODO: This entire thing should probably be a nested class in Tile.java
public class TrapNode {

	private boolean isSafe;
	private boolean visited;
	//TODO: Not sure if this should be a node or a point.
	//Point makes sense because its what the solver uses, but even that might be wrong.
	private Point3d prevNode;
	private int stepsToNode;
	
	public TrapNode() {
		this.isSafe = true;
		this.setVisited(false);
		this.stepsToNode = Integer.MAX_VALUE;
	}
	
	public TrapNode(boolean trapped) {
		this.isSafe = !trapped;
		this.setVisited(false);
	}
	
	public void setTrapped(boolean trapped) {
		this.isSafe = !trapped;
	}
	
	public boolean isSafe() {
		return this.isSafe;
	}

	public boolean hasVisited() {
		return this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Point3d getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Point3d prevNode) {
		this.prevNode = prevNode;
	}

	public int getStepsToNode() {
		return stepsToNode;
	}

	public void setStepsToNode(int steps) {
		this.stepsToNode = steps;
	}
}
