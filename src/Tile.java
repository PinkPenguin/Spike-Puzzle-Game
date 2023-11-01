import java.util.ArrayList;
import java.util.Collections;

public class Tile {
	
//	private ArrayList<Node> pathArray = new ArrayList<Node>();
//	private Point prevNeighbourInPath;
	
	
	private int rounds;
	private boolean isWalkableTile = true;
	private ArrayList<TrapNode> nodes;
	//TODO: Add an array structure to keep track of visited rounds
	//This might mean i have to actually keep number of rounds saved here

		public Tile() {
			this.rounds = 0;
			this.nodes = new ArrayList<TrapNode>();
		}
		
		public Tile(int rounds) {
			this.nodes = new ArrayList<TrapNode>(Collections.nCopies(rounds, new TrapNode()));
			this.rounds = rounds;
		}
		
		
		public boolean isSafe(int round) {
			//TODO: Pulled the code out but probably is a better way
			addNodesIfMissing(round);

			if(this.nodes.get(round).isSafe() == true) {
				return true;
			}
			return false;
		}
		
		public void setTrap(int round, boolean bool) {
			//--------
			addNodesIfMissing(round);
			//--------
			this.nodes.get(round).setTrapped(bool);
		}
		
		public void reset() {
			
			this.nodes = new ArrayList<TrapNode>();
			Collections.fill(this.nodes, new TrapNode());
			
		}

		public boolean isWalkableTile () {
			return isWalkableTile;
		}

		public void setWalkableTile (boolean isWalkableTile) {
			this.isWalkableTile = isWalkableTile;
		}
		
		private void addNodesIfMissing (int round) {
			while(nodes.size() <= round) {
				nodes.add(new TrapNode());
			}
		}
		
		public void setVisited (int round) {
			nodes.get(round).setVisited(true);
		}
		
		public boolean hasVisited (int round) {
			return nodes.get(round).hasVisited();
		}
		
		public void setPrevNode (int round, Point3d point) {
			nodes.get(round).setPrevNode(point);
		}
		
		public Point3d getPrevNode (int round) {
			return nodes.get(round).getPrevNode();
		}
		
		public void setStepsToNode (int round, int steps) {
			nodes.get(round).setStepsToNode(steps);
		}
		
		public int getStepsToNode (int round) {
			return nodes.get(round).getStepsToNode();
		}

}
