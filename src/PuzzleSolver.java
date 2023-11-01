import java.util.ArrayList;

public class PuzzleSolver {
	
	//TODO: Consider adding the Game Board here so it doesn't have to be shuffled around in parameters.
	private int currentRound;
	private int stepCounter;
	private Point3d currentNode;
	private ArrayList<Point3d> solutions;

	public PuzzleSolver() {
		this.currentRound = 0;
		this.stepCounter = 0;
		this.solutions = new ArrayList<Point3d>();
		this.currentNode = new Point3d(0, 0, 0);
	}
	
	public ArrayList<Point3d> solve(GameBoard board) {
		
		for(int x = 0; x < board.tiles[0].length; x++) {
			for(int r = 0; r < board.getNumberOfRounds(); r++) {
				
				if(board.tiles[0][x].isWalkableTile()) {
					stepCounter = 0;
					currentRound = r;
					recursiveSolve(board, new Point3d(x, 0, r));
				}		
			}
		}
		
		int goalY = board.tiles.length - 1;
		for(int x = 0; x < board.tiles[0].length; x++) {
			for(int r = 0; r < board.getNumberOfRounds(); r++) {
				
				if(board.tiles[goalY][x].getStepsToNode(r) < Integer.MAX_VALUE) {
					
					solutions.add(new Point3d(x, goalY, r));
					
				}
			}
		}
//		recursiveSolve(board, new Point3d(0, 0, 0));
		
		return solutions;		
	}
	
	private void recursiveSolve(GameBoard board, Point3d point) {
		
		/*When visiting a new node, set it to Visited and save the Step Counter in the node.*/
		board.tiles[point.getY()][point.getX()].setVisited(currentRound);
		board.tiles[point.getY()][point.getX()].setStepsToNode(currentRound, stepCounter);
		
		/*If we're on one of the goal tiles, go back a step and keep going*/
		if(point.getY() == board.tiles.length - 1) {
			return;
		}
		
		/*Check DOWN*/
		if(board.tiles.length > point.getY() + 1
				&& board.tiles[point.getY() + 1][point.getX()].isSafe(nextRound(board))
				//&& !board.tiles[point.getY() + 1][point.getX()].hasVisited(nextRound(board))
				&& board.tiles[point.getY() + 1][point.getX()].isWalkableTile() 
				&& board.tiles[point.getY() + 1][point.getX()].getStepsToNode(nextRound(board)) > stepCounter + 1 ){
			
			/*Set the target node to point to where we came from.*/
			board.tiles[point.getY() + 1][point.getX()].setPrevNode(nextRound(board), currentNode);
			
			/*Point towards the node were going to and increment the step counter*/ 
			currentNode = new Point3d(point.getX(), point.getY() + 1, nextRound(board));
			stepCounter++;
			currentRound = nextRound(board);
			
			recursiveSolve(board, currentNode);
			

			/*Rewind 1 round when going back from finishing the recursion.
			 *Also reduce the step counter by one, and point the node back to where we are.*/
			currentRound = previousRound(board);
			stepCounter--;
			currentNode = new Point3d(point.getX(), point.getY() - 1, previousRound(board));
			
		}
		
		/*Check RIGHT*/
		if(board.tiles[0].length > point.getX() + 1 
				&& point.getX() > 0
				&& board.tiles[point.getY()][point.getX() + 1].isSafe(nextRound(board))
				//&& !board.tiles[point.getY()][point.getX() + 1].hasVisited(nextRound(board))
				&& board.tiles[point.getY()][point.getX() + 1].isWalkableTile()
				&& board.tiles[point.getY()][point.getX() + 1].getStepsToNode(nextRound(board)) > stepCounter + 1) {
			
			board.tiles[point.getY()][point.getX() + 1].setPrevNode(nextRound(board), currentNode);
			
			currentNode = new Point3d(point.getX() + 1, point.getY(), nextRound(board));
			stepCounter++;
			currentRound = nextRound(board);

			recursiveSolve(board, currentNode);
			
			currentRound = previousRound(board);
			stepCounter--;
			currentNode = new Point3d(point.getX() - 1, point.getY(), previousRound(board));
		}
		
		/*Check LEFT*/
		if(point.getX() - 1 >= 0
				&& point.getX() > 0
				&& board.tiles[point.getY()][point.getX() - 1].isSafe(nextRound(board))
//				&& !board.tiles[point.getY()][point.getX() - 1].hasVisited(nextRound(board))
				&& board.tiles[point.getY()][point.getX() - 1].isWalkableTile() 
				&& board.tiles[point.getY()][point.getX() - 1].getStepsToNode(nextRound(board)) > stepCounter + 1){

			board.tiles[point.getY()][point.getX() - 1].setPrevNode(nextRound(board), currentNode);
			
			currentNode = new Point3d(point.getX() - 1, point.getY(), nextRound(board));
			stepCounter++;
			currentRound = nextRound(board);

			recursiveSolve(board, currentNode);
			
			currentRound = previousRound(board);
			stepCounter--;
			currentNode = new Point3d(point.getX() + 1, point.getY(), previousRound(board));
			
}
		/*Check UP*/
		if(point.getY() - 1 >= 0
				&& point.getX() > 0
				&& board.tiles[point.getY() - 1][point.getX()].isSafe(nextRound(board))
//				&& !board.tiles[point.getY() - 1][point.getX()].hasVisited(nextRound(board))
				&& board.tiles[point.getY() - 1][point.getX()].isWalkableTile()
				&& board.tiles[point.getY() - 1][point.getX()].getStepsToNode(nextRound(board)) > stepCounter + 1) {
			
			board.tiles[point.getY() - 1][point.getX()].setPrevNode(nextRound(board), currentNode);
			
			currentNode = new Point3d(point.getX(), point.getY() - 1, nextRound(board));
			stepCounter++;
			currentRound = nextRound(board);

			recursiveSolve(board, currentNode);
			
			currentRound = previousRound(board);
			stepCounter--;
			currentNode = new Point3d(point.getX(), point.getY() + 1, previousRound(board));

}
		/*Check STAY*/
		if(board.tiles[point.getY()][point.getX()].isSafe(nextRound(board))
				&& point.getX() > 0
//				&& !board.tiles[point.getY()][point.getX()].hasVisited(nextRound(board))
				&& board.tiles[point.getY()][point.getX()].isWalkableTile()
				&& board.tiles[point.getY()][point.getX()].getStepsToNode(nextRound(board)) > stepCounter + 1) {

			board.tiles[point.getY()][point.getX()].setPrevNode(nextRound(board), currentNode);
			
			currentNode = new Point3d(point.getX(), point.getY(), nextRound(board));
			stepCounter++;
			currentRound = nextRound(board);
		
			recursiveSolve(board, currentNode);
			
			currentRound = previousRound(board);
			stepCounter--;
			currentNode = new Point3d(point.getX(), point.getY(), previousRound(board));
}
		
	}
	public void drawSolution(GameBoard board) {
		StringBuilder sBuilder = new StringBuilder();
		for(Tile[] c1 : board.tiles) {
			for(Tile c2 : c1) {
				sBuilder.append("|");
				for(int i = 0; i < board.getNumberOfRounds(); i++) {
					if(c2.getStepsToNode(i) < Integer.MAX_VALUE) {
						if(c2.getStepsToNode(i) < 10) {
							sBuilder.append("0" + c2.getStepsToNode(i) + ",");
						}
						else {
							sBuilder.append(c2.getStepsToNode(i) + ",");						
						}
					}
					else {
						sBuilder.append("--" + ",");
					}
				}
			}
			sBuilder.append("|" + "\n");
		}
		System.out.println(sBuilder.toString());

	}

	private int nextRound (GameBoard board) {
		return (currentRound + 1) % board.getNumberOfRounds();
	}
	
	private int previousRound (GameBoard board) {
		return ((currentRound - 1) % board.getNumberOfRounds() + board.getNumberOfRounds()) % board.getNumberOfRounds();
	}

}
