import java.util.InputMismatchException;
import java.util.Scanner;

public class RunSpikePuzzle {

	public static void main(String[] args) {

		Scanner initScanner = new Scanner(System.in);
		int width = 0, height = 0; int rounds = 0;
		while(width < 1 || height < 1 || rounds < 1){
			try {
				width = 0;
				height = 0;
				System.out.println("Inputs must be a whole number greater than zero");
				System.out.println("Enter Width: ");
				width = initScanner.nextInt();
				System.out.println("Enter Height: ");
				height = initScanner.nextInt();
				System.out.println("Enter number of Rounds: ");
				rounds  = initScanner.nextInt();
				
			} catch (InputMismatchException e) {
				System.err.println("Err: Invalid input!");
				initScanner.next();
			}
		}

		initScanner.close();
		System.out.println("Width: " + width + "\n" + "Height: " + height + "\n" + "Rounds: " + rounds);

		GameBoard board = new GameBoard(width, height);
		board.generateTiles();
		board.generateTraps(rounds);
		board.draw();
		
		PuzzleSolver solver = new PuzzleSolver();
		solver.solve(board);
		solver.drawSolution(board);
		
		GameLoop gameLoop = new GameLoop();
//		gameLoop.start();
	}

}
