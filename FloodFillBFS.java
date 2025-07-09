// Time Complexity: O(m * n)
// Space Complexity: O(m * n)

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach:
// 1. Use Breadth-First Search (BFS) starting from the given pixel (sr, sc).
// 2. Check if the starting pixel's color is already equal to the target color;
//    if so, return the image as no change is needed.
// 3. Initialize a queue to process pixels level by level.
// 4. For each pixel, check its four neighbors (up, down, left, right).
//    - If a neighbor has the same original color, change it to the target color
//      and add it to the queue for further processing.
// 5. Repeat the process until all connected pixels with the same original color are filled.
// 6. Return the modified image.

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        int m = image.length;
        int n = image[0].length;
        //Get the original color
        int originalColor = image[sr][sc];
        //If the original color and given color is same return the image
        if(originalColor == color) return image;
        //Add to queue
        q.add(new int[]{sr,sc});
        image[sr][sc] = color;

        while(!q.isEmpty()){
            //Get the image pixel and color it to given color
            int[] curr = q.poll();
            //Go through all four directions
            for(int[] dir : dirs){
                int r = dir[0] + curr[0];
                int c = dir[1] + curr[1];
                //Check if the neighbour has originalColor
                if(r>=0 && r<m && c>=0 && c<n && image[r][c] == originalColor){
                    //Change it to given color and add to queue
                    image[r][c] = color;
                    q.add(new int[]{r,c});
                }
            }
        }
        return image;
    }
}