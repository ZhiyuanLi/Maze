package maze;

import java.util.ArrayList;
import java.util.Random;

/**
 * The <code>MazeCell</code> class stores information about each individual cell
 * in the maze. The boolean values <code>north</code>, <code>east</code>,
 * <code>west</code>, and <code>south</code> store which walls are up; e.g., if
 * <code>north</code> is <code>true</code>, then the north wall is up.
 * 
 * Each cell also has pointer to its four <code>MazeCell</code> neighbors,
 * <code>neighborN</code>, <code>neighborE</code>, <code>neighborS</code>, and
 * <code>neighborW</code>. If any of these values are null, it means this cell
 * is on the border of the maze.
 *
 *
 */
public class MazeCell {

	/**
	 * Instance variables for MazeCell
	 */
	private boolean north, east, south, west;
	private boolean visited, examined;
	private MazeCell neighborN, neighborE, neighborS, neighborW;
	private Random generator;
	private int row, col;
	private int rank;
	private MazeCell parent;

	/**
	 * Sets all the walls to <code>true</code> and initializes the random number
	 * generator.
	 */
	public MazeCell() {
		north = true;
		east = true;
		south = true;
		west = true;
		generator = new Random();
		visited = false;
		examined = false;
	}

	/**
	 * Create disjoint set for this cell
	 */
	public void createDisjointSet() {
		this.rank = 0;
		this.parent = this;
	}

	/**
	 * Sets the position of this cell, used to identify cell.
	 * 
	 * @param row
	 *            The row number represent this cell.
	 * @param col
	 *            The col number represent this cell.
	 */
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Add the rank by 1
	 */
	public void addRank() {
		this.rank++;
	}

	/**
	 * Returns the rank of this cell
	 * 
	 * @return <code>rank</code>
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Returns the parent of this cell.
	 * 
	 * @return <code>parent</code>
	 */
	public MazeCell getParent() {
		return this.parent;
	}

	/**
	 * Sets the parent of this cell to <code>parent</code>.
	 * 
	 * @param parent
	 *            The parent of this cell
	 */
	public void setParent(MazeCell parent) {
		this.parent = parent;
	}

	/**
	 * Sets the visited flag to <code>true</code>.
	 */
	public void visit() {
		visited = true;
	}

	/**
	 * Returns whether or not this cell has been visited.
	 * 
	 * @return <code>true</code> if the cell has been visited.
	 */
	public boolean visited() {
		return visited;
	}

	/**
	 * Sets the examined flag to <code>true</code>.
	 */
	public void examine() {
		examined = true;
	}

	/**
	 * Returns whether or not this cell has been examined.
	 * 
	 * @return <code>true</code> if the cell has been examined.
	 */
	public boolean examined() {
		return examined;
	}

	/**
	 * Sets the pointers to the neighbors of this cell. If a pointer is null,
	 * that means this cell is along the border of the maze.
	 * 
	 * @param n
	 *            The north neighbor of this cell.
	 * @param e
	 *            The east neighbor of this cell.
	 * @param s
	 *            The south neighbor of this cell.
	 * @param w
	 *            The west neighbor of this cell.
	 */
	public void setNeighbors(MazeCell n, MazeCell e, MazeCell s, MazeCell w) {
		neighborN = n;
		neighborE = e;
		neighborS = s;
		neighborW = w;
	}

	/**
	 * Sets whether or not there are walls up to the north, east, south, and
	 * west of this cell.
	 * 
	 * @param north
	 *            <code>true</code> if there's a wall on the north side of the
	 *            cell.
	 * @param east
	 *            <code>true</code> if there's a wall on the east side of the
	 *            cell.
	 * @param south
	 *            <code>true</code> if there's a wall on the south side of the
	 *            cell.
	 * @param west
	 *            <code>true</code> if there's a wall on the west side of the
	 *            cell.
	 */
	public void setWalls(boolean north, boolean east, boolean south, boolean west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	/**
	 * Returns whether or not this cell has all its walls up.
	 * 
	 * @return <code>true</code> if all walls are up.
	 */
	public boolean hasAllWalls() {
		return (north && east && south && west);
	}

	/**
	 * Returns whether or not this cell has its north wall up.
	 * 
	 * @return <code>true</code> if the cell's north wall is up.
	 */
	public boolean north() {
		return north;
	}

	/**
	 * Returns whether or not this cell has its south wall up.
	 * 
	 * @return <code>true</code> if the cell's south wall is up.
	 */
	public boolean south() {
		return south;
	}

	/**
	 * Returns whether or not this cell has its east wall up.
	 * 
	 * @return <code>true</code> if the cell's east wall is up.
	 */
	public boolean east() {
		return east;
	}

	/**
	 * Returns whether or not this cell has its west wall up.
	 * 
	 * @return <code>true</code> if the cell's west wall is up.
	 */
	public boolean west() {
		return west;
	}

	/**
	 * Gets the neighbors(accessible) of this cell. Returns an array of those
	 * neighbors. The length of the array is the number of neighbors this cell
	 * has.
	 * 
	 * @return An array with the neighbors contained within it.
	 */
	public MazeCell[] getNeighbors() {
		ArrayList<MazeCell> temp = new ArrayList<>();
		if (neighborN != null && north == false) {
			temp.add(neighborN);
		}
		if (neighborE != null && east == false) {
			temp.add(neighborE);
		}
		if (neighborS != null && south == false) {
			temp.add(neighborS);
		}
		if (neighborW != null && west == false) {
			temp.add(neighborW);
		}
		// return neighbors
		if (temp.isEmpty()) {
			return null; // return null if no access neighbors
		}
		MazeCell[] neibors = new MazeCell[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			neibors[i] = temp.get(i);
		}
		return neibors;
	}

	/**
	 * Knocks down the wall between this cell and the neighbor cell. The
	 * neighbor cell must actually be a neighbor of this cell. This method is
	 * used in conjunction with <code>neighborWithWalls</code>.
	 * 
	 * @param neighbor
	 *            The neighboring cell; must be one of the neighbors set in
	 *            <code>setNeighbors</code>.
	 */
	public void knockDownWall(MazeCell neighbor) {
		if (neighbor.equals(neighborN)) {
			this.north = false;
			neighbor.south = false;
		} else if (neighbor.equals(neighborE)) {
			this.east = false;
			neighbor.west = false;
		} else if (neighbor.equals(neighborS)) {
			this.south = false;
			neighbor.north = false;
		} else if (neighbor.equals(neighborW)) {
			this.west = false;
			neighbor.east = false;
		}
	}

	/**
	 * Use this function whenever you want to randomly pick one of the
	 * neighbors(accessible)
	 * 
	 * @return - random choice of one of the neighbors.
	 */
	public MazeCell getRandomNeighbor() {
		MazeCell[] neighbors = getNeighbors();
		if (neighbors != null) {
			return neighbors[generator.nextInt(neighbors.length)];
		} else {
			return null;
		}
	}

	/**
	 * Returns a neighbor that has one wall between.
	 * 
	 * @return Neighbor with one wall between.
	 * 
	 */
	public MazeCell neighborWithOneWallBetween() {
		if (neighborE != null && east == true) {
			return neighborE;
		}
		if (neighborS != null && south == true) {
			return neighborS;
		}
		if (neighborN != null && north == true) {
			return neighborN;
		}
		if (neighborW != null && west == true) {
			return neighborW;
		}
		return null;

	}

	/**
	 * Returns a neighbor that has all its walls intact.
	 * 
	 * @return Neighbor with all its walls intact.
	 */
	public MazeCell neighborWithWalls() {
		if (neighborE != null && neighborE.hasAllWalls()) {
			return neighborE;
		}
		if (neighborS != null && neighborS.hasAllWalls()) {
			return neighborS;
		}
		if (neighborN != null && neighborN.hasAllWalls()) {
			return neighborN;
		}
		if (neighborW != null && neighborW.hasAllWalls()) {
			return neighborW;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazeCell other = (MazeCell) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MazeCell [row=" + row + ", col=" + col + "]";
	}
}
