import java.util.*;
/*
Question 5
        a)
        You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the x coordinate of left edge,
         yi represents x coordinate of right edge of rectangle and hi represents the height of the peaks of each rectangle.
         If you want to construct a border line over the peaks of rectangle represented in bar chart,
         return the key coordinates required to build a border line that contacts the peaks of the given chart.
        Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.

        Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
        Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}
*/

class BorderLine {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();
        List<int[]> heights=new ArrayList<>();
        transformBuilding(buildings,heights);
        //if heights of 2 points are same then place the point with smaller height first else place point with smaller starting point
        Collections.sort(heights,(a,b)->(a[0]==b[0]) ? a[1]-b[1] : a[0]-b[0]);// TC->O(nlog n)
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>((a,b)->(b-a));
        //seeding the Priority Queue
        pq.add(0);
        int prevMax=0;
        // We iterate through the starting and ending points of each building.
        for(int[] height:heights){ //O(n)
            if(height[1]<0){
                pq.add(-height[1]);
            }
            // If the current point represents the ending point of a building, we remove its height from the priority queue.
            else{
                pq.remove(height[1]); //O(log n)
            }
            // We update the current maximum height.
            int CurrentMax=pq.peek();
            // If the current maximum height is different from the previous maximum height, we add a new point to the result.
            if(CurrentMax!=prevMax)
            {
                List<Integer> subResult=new ArrayList<>();
                subResult.add(height[0]);
                subResult.add(CurrentMax);

                res.add(subResult);
                prevMax=CurrentMax;
            }
        }
        // We return the final result.
        return res;
    }

    // This method transforms each building into its starting and ending points with their heights.
    //example-->[1,2,3]-->[1,-3] & [2,3]-->here -(minus) is just for convention for starting point
    private void transformBuilding(int[][] buildings,List<int[]> heights)
    {
        for(int[] building:buildings)
        {
            heights.add(new int[]{building[0],-building[2]});
            heights.add(new int[]{building[1],building[2]});
        }
    }

    public static void main(String[] args) {
        // We initialize an array of building heights as input.
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        // We create an instance of the BorderLine class to call its getSkyline method.
        BorderLine solution = new BorderLine();
        // We call the getSkyline method to obtain the skyline of the input buildings.
        List<List<Integer>> ans = solution.getSkyline(height);
        // We print the result.
        System.out.println(ans);
    }
}


