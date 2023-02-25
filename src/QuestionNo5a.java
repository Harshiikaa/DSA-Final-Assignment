import java.util.*;
/*
Question 5
        a)
        You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi], where xi the x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi represents the height of the peaks of each rectangle. If you want to construct a border line over the peaks of rectangle represented in bar chart, return the key coordinates required to build a border line that contacts the peaks of the given chart.
        Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.


        Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
        Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}
*/

class KeyPoint {
    int x;
    int y;

    KeyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public static List<KeyPoint> getBorder(int[][] height) {
        List<KeyPoint> result = new ArrayList<>();

        int n = height.length;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int x1 = height[i][0];
            int x2 = height[i][1];
            int h = height[i][2];

            if (!map.containsKey(x1)) {
                map.put(x1, h);
            } else {
                map.put(x1, Math.max(map.get(x1), h));
            }

            if (!map.containsKey(x2)) {
                map.put(x2, 0);
            }
        }
        int max = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey();
            int h = entry.getValue();

            if (h != max) {
                result.add(new KeyPoint(x, h));
                max = h;
            }
        }
        return result;
    }

    public static  int[][] print(List<KeyPoint> result){
        int[][] arr = new int[result.size()][2];

        for (int i=0; i<result.size(); i++){
            arr[i][0]=result.get(i).x;
            arr[i][1]=result.get(i).y;

        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] m = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        System.out.println(Arrays.deepToString(print(getBorder(m))));
    }
}

/*
explain
        This implementation uses a TreeMap to store the x coordinates and their corresponding heights.
        It first inserts the x coordinates and heights into the map and updates the height if a duplicate x coordinate is encountered.
        Then it iterates through the map, checking if the height has changed.
        If it has, it adds a new KeyPoint with the current x coordinate and height to the result list.
*/
