// Time Complexity: O(m * n)
// Space Complexity: O(m * n)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// - Breadth-First Search (BFS) solution.
// - We start by pushing all 0s into the queue, treating them as starting points.
// - All 1s are marked as -1 to indicate they are unvisited and need to be updated.
// - While processing the queue:
//     - For each cell, we look in 4 directions (up, right, down, left).
//     - If a neighbor cell is unvisited (-1), we set its value to (current cell value + 1) and enqueue it.

class Solution {
    int[][] dirs;  // Directions: up, right, down, left
    int m, n;      // Matrix dimensions

    public int[][] updateMatrix(int[][] mat) {
        this.dirs = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
        this.m = mat.length;
        this.n = mat[0].length;

        Queue<int[]> q = new LinkedList<>();

        // Add all 0s to the queue and mark 1s as -1 (unvisited)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[]{i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        // Perform BFS
        while (!q.isEmpty()) {
            // Pop the current from queue
            int[] curr = q.poll();

            // Check all 4 directions
            for (int[] dir : dirs) {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];

                // If within bounds and cell is unvisited
                if (r >= 0 && r < m && c >= 0 && c < n && mat[r][c] == -1) {
                    // Add the neighbor to the queue
                    q.add(new int[]{r, c});

                    // Set its distance to current cell's distance + 1
                    mat[r][c] = mat[curr[0]][curr[1]] + 1;
                }
            }
        }

        // Return the matrix with updated distances
        return mat;
    }
}