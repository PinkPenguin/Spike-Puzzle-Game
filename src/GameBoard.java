import java.util.Random;

public class GameBoard {

	private int numRounds;
	public Tile[][] tiles;

	public GameBoard(int w, int h) {
		this.tiles = new Tile[h+2][w];
		this.numRounds = 0;
	}

	public void generateTiles() {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile();
			}
		}
	}

	public void generateTraps(int rounds) {
		this.numRounds = rounds;
		Random rng = new Random();

		for(int y = 1; y < this.tiles.length - 1; y++) {
			for(Tile c : tiles[y]) {
				//TODO: Keep an eye on the default weighting number here
				for(int i = 0; i<rounds; i++) {

					if(rng.nextInt(100) < 50) {
						c.setTrap(i, true);
					}	

				}
			}
		}
	}

	public void generateTraps(int rounds, int weight) {
		this.numRounds = rounds;
		Random rng = new Random();

		for(Tile[] cArray : this.tiles) {
			for(Tile c : cArray) {
				for(int i = 0; i<rounds; i++) {

					if(rng.nextInt(100) < weight) {
						c.setTrap(i, true);
					}	

				}
			}
		}
	}	

	public void draw() {
		StringBuilder sBuilder = new StringBuilder();
		for(Tile[] c1 : tiles) {
			for(Tile c2 : c1) {
				sBuilder.append("|");
				for(int i = 0; i < numRounds; i++) {
					if(c2.isSafe(i)) {
						sBuilder.append("  ");						
					}
					else {
						sBuilder.append((i + 1) + ",");
					}
				}
			}
			sBuilder.append("|" + "\n");
			//			sBuilder.toString();

		}
		System.out.println(sBuilder.toString());

	}

	public int getNumberOfRounds() {
		return numRounds;
	}

}

