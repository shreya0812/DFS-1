// Time Complexity: O(m * n)
//   - For each cell, DFS could branch in up to 4 directions recursively
//   - Without memoization or pruning, the recursion may revisit the same cells multiple times

// Space Complexity: O(m * n)
//   - result[][] stores distances
//   - DFS recursion stack could go up to O(m * n) in the worst case (e.g. all 1s around 0)

// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// - DFS Approach
// - Start by scanning the matrix and calling DFS only on cells with value 1.
// - For each such cell, the dfs() function:
//     - Checks if it has an adjacent 0 (returns 1 if yes).
//     - Otherwise, explores its four directions recursively.
//     - To avoid re-computing, stores results in the result[][] array.
//     - If a direction hasn’t been computed yet (result[i][j] == 0), it calls DFS.
// - Each cell memoizes its result once, minimizing redundant work compared to pure DFS.



class Solution {
    int[][] dirs;  // Directions: up, right, down, left
    int m, n;      // Matrix dimensions
    int[][] result;

    public int[][] updateMatrix(int[][] mat) {
        this.dirs = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        this.m = mat.length;
        this.n = mat[0].length;
        this.result = new int[m][n];

        // Call dfs on  all 1s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    result[i][j] = dfs(mat,i,j);
                }
            }
        }

        // Return the result matrix
        return result;
    }

    private int dfs(int[][] mat, int i, int j){
        // Check all 4 directions for 0
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            // If within bounds
            if (r >= 0 && r < m && c >= 0 && c < n ) {
                if(mat[r][c] == 0)  return 1;
            }
        }

        //Check all 4 directions
        int top = (i>0 && result[i-1][j] != 0) ? result[i-1][j] : 9999;
        int left = (j>0 && result[i][j-1] != 0) ? result[i][j-1] : 9999;

        int right = 9999;
        if(j < n-1){
            if(result[i][j+1] == 0){
                result[i][j+1] = dfs(mat,i,j+1);
            }
            right = result[i][j+1];
        }

        int bottom = 9999;
        if(i < m-1){
            if(result[i+1][j] == 0){
                result[i+1][j] = dfs(mat,i+1,j);
            }
            bottom = result[i+1][j];
        }

        //Return the min of all directions + 1
        return Math.min(top,Math.min(left,Math.min(right,bottom))) + 1;
    }
}