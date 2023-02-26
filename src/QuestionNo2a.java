/*Question 2
a)
You are given a 2D array containing hierarchical information about certain species,
with edge[i]=[xi,yi], where node xi is connected to xj. You are also provided an array of values associated
 with each species, such that value[i] reflects the ith nodes value. If the greatest common divisor of two
 values is 1, they are "relatively prime." Any other node on the shortest path from that node to the absolute
 parent node is an ancestor of certain species i. Return a list of nearest ancestors, where result[i] is the
 node i's nearest ancestor such that values[i] and value[result[i]] are both relative primes otherwise -1.

Input: values [3,2,6,6,4,7,12], edges= {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}}
Output: {-1,0, -1, -1,0,2, -1}
*/

import java.util.*;

class ListOfAncestors {
    // This method takes in two integers and returns their greatest common divisor (GCD)
    public int gcd(int n1, int n2) {
        // If the second integer is 0, then the GCD is the first integer
        if (n2 == 0) {
            return n1;
        }
        // Otherwise, recursively call the method with the remainder of the division of the first integer by the second integer
        return gcd(n2, n1 % n2);
    }

    // This method performs a depth-first search (DFS) on a tree to find the ancestors of each node that have a coprime value with that node
    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
        // If the node has already been visited, return
        if(visited[node]) return;
        // Otherwise, mark the node as visited
        visited[node] = true;
        // Initialize the closest ancestor to -1 and the minimum distance to the maximum integer value
        int ancestor = -1;
        int d = Integer.MAX_VALUE;
        // Iterate through all possible coprime values for the current node
        for(int i = 1; i < 51; i++) {
            // If the current coprime value is possible for the current node and there exists a node with that value in the map
            if(poss[nums[node]][i] && map.containsKey(i)) {
                // If the distance between the current node and the node with the current coprime value is less than or equal to the current minimum distance
                if(depth - map.get(i)[0] <= d) {
                    // Update the minimum distance and closest ancestor to the node with the current coprime value
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }
        // Set the ancestor for the current node
        ans[node] = ancestor;
        // If there exists a node in the map with the same value as the current node
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        // Add the current node to the map
        map.put(nums[node],new int[]{depth,node});
        // Recursively call the method on each child of the current node
        for(int child : tree[node]) {
            if(visited[child]) continue;
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }
        // If the current node already existed in the map, restore its original value in the map
        if(exist[0] != -1) map.put(nums[node], exist);
            // Otherwise, remove the current node from the map
        else map.remove(nums[node]);
        return;
    }
    // This method takes in an array of integers and a 2D array of edges and returns an array of integers representing the closest ancestor for each node that has a coprime value with that node
    public int[] getCoprimes(int[] nums, int[][] edges) {
        // Initialize a 2D boolean array to store whether or not two numbers are coprime
        boolean[][] poss = new boolean[51][51];
        // Iterate through all possible values of i and j from 1 to 50
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(gcd(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        // Get the length of the input array and create an array of LinkedLists to represent the tree
        int n = nums.length;
        LinkedList<Integer>[] tree = new LinkedList[n];
        // Initialize each element of the tree array to a new LinkedList
        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        // Add the edges to the tree by iterating through the edges array
        for(int edge[] : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        // Create an array to store the ancestor for each node and initialize each element to -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        // Create a HashMap to store information about each node in the tree
        Map<Integer,int[]> map = new HashMap<>();
        // Create a boolean array to track whether each node has been visited
        boolean[] visited = new boolean[n];
        // Perform a depth first search to traverse the tree and find the ancestor for each node
        dfs(nums, tree, 0, 0, visited, ans, map, poss);
        // Return the array of answers
        return ans;
    }
    // driver code
    public static void main(String[] args) {
        int[][] edges =  {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        int [] values = {3,2,6,6,4,7,12};
        // Create an instance of the ListOfAncestors class
        ListOfAncestors listOfAncestors = new ListOfAncestors();
        // Call the getCoprimes method to find the ancestors that are coprime to the node values
        int[] ans = listOfAncestors.getCoprimes(values,edges);
        // Convert the result to a List
        List<Integer> answer= new ArrayList<Integer>();
        for (int i = 0;i<ans.length;i++) {
            answer.add(ans[i]);
        }
        // Print the result
        System.out.println(answer);
    }
}