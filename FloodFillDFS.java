// Time Complexity: O(m * n)
// Space Complexity: O(m * n) - Recursion stack
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// - DFS-based flood fill algorithm.
// - First, we store the original color at the starting pixel.
// - If the original color is the same as the new color, we return early to avoid infinite recursion.
// - Otherwise, we perform DFS starting at (sr, sc).
// - For each cell, if it has the original color, we change it to the new color and recurse in all four directions.
// - The recursion terminates when we go out of bounds or reach a cell that doesn't match the original color.


class Solution {
    int[][] dirs;
    int m,n;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        this.dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        this.m = image.length;
        this.n = image[0].length;
        //Get the original color
        int originalColor = image[sr][sc];
        //If the original color and given color is same return the image
        if(originalColor == color) return image;

        //Call dfs
        dfs(image,sr,sc,color,originalColor);

        return image;
    }
    private void dfs(int[][] image, int sr, int sc, int color, int originalColor){

        //Check if the neighbour has originalColor
        if(sr<0 || sr>=m || sc<0 || sc>=n || image[sr][sc] != originalColor) return;

        image[sr][sc] = color;

        //Go through all four directions
        for(int[] dir : dirs){
            int r = dir[0] + sr;
            int c = dir[1] + sc;
            dfs(image,r,c,color, originalColor);
        }

    }
}