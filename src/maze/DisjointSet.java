package maze;

import java.util.*;

public class DisjointSet {
    
    //TODO - write all the methods of this class
	
//	private Hashtable<MazeCell, ArrayList<MazeCell>> disjointSetMap = new Hashtable<MazeCell, ArrayList<MazeCell>>();
    
    /**
     * make a set out of the cells in the maze
     * @param maze
     */
    public void makeSet(MazeCell[][] maze) {
    	int row = maze.length;
    	int col = maze[0].length;
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		maze[i][j].createDisjointSet();
        	}
        }
    }

    /**
     * Create the union of the sets that contain cell1 and cell2.
     * If the two cells are in the same set, nothing changes,
     * else create the new set as a union. 
     * Please see the union-find algorithm.
     * @param cell1
     * @param cell2
     */
    public void union(MazeCell cell1, MazeCell cell2){
        MazeCell root1 = find(cell1);
        MazeCell root2 = find(cell2);
        if (!root1.equals(root2)) {
        	if (root1.getRank() < root2.getRank()) {
        		root1.setParent(root2);
        		root2.addRank();
        	} else {
        		root2.setParent(root1);
        		root1.addRank();
        	}
        }
    }

    /**
     * Find the set that the cell is a part of.
     * While finding the set, do the path compression as well.
     * @param cell
     * @return
     */
    public MazeCell find(MazeCell cell) {
    	if (cell.getParent().equals(cell)) {
    		return cell;
    	} else {
    		cell.setParent(find(cell.getParent()));
    		return cell.getParent();
    	}
    }
}
