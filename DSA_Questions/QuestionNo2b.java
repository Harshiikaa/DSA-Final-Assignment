import java.util.LinkedList;
import java.util.Queue;

/* 2.b) You are given an array of binary trees that represent different cities where a certain corporation
has its branch office and the organization wishes to provide service by constructing a service center.
Building service centers at any node, i.e., a city can give service to its directly connected cities where
it can provide service to its parents, itself, and its immediate children. Returns the smallest number of
service centers required by the corporation to provide service to all connected cities. Note that: the root
node represents the head office and other connected nodes represent the branch office connected to the head
office maintaining some kind of hierarchy.

Input: tree= {0,0, null, 0, null, 0, null, null, 0}
Output: 2
Explanation: construction of two service centers denoted by black markk will be enough to provide service
to all cities.
*/
class TreeNode{
    TreeNode left;
    TreeNode right;
    int data;
    // Constructor for the tree node that takes a data value as input
    TreeNode(int data){
        this.data=data;
        this.left=this.right=null;
    }
    // Empty constructor for the tree node
    TreeNode(){

    }

    // Method for adding nodes to the tree, takes an array of input values
    public TreeNode addToTree(Object[] input) {
        // Check if the input is null or empty
        if (input == null || input.length == 0) {
            return null;
        }

        // Create the root node of the tree using the first input value
        TreeNode root = new TreeNode((int) input[0]);
        // Create a queue to keep track of nodes to be added to the tree
        Queue<TreeNode> queue = new LinkedList<>();
        // Add the root node to the queue
        queue.offer(root);

        // Loop through the remaining input values
        for (int i = 1; i < input.length; i += 2) {
            // Remove the next node from the queue
            TreeNode curr = queue.poll();
            // If the input value is not null, add it as the left child of the current node
            if (input[i] != null) {
                curr.left = new TreeNode((int) input[i]);
                // Add the left child to the queue
                queue.offer(curr.left);
            }
            // If the next input value is not null and there is space for it, add it as the right child of the current node
            if (i+1 < input.length && input[i+1] != null) {
                curr.right = new TreeNode((int) input[i+1]);
                // Add the right child to the queue
                queue.offer(curr.right);
            }
        }
        // Return the root node of the completed tree
        return root;
    }
}
// Define a class for the Construction Service Center
class ConstructionServiceCenter{
    // Declare a variable to keep track of the number of cameras needed
    int res = 0;
    // Method to calculate the minimum number of cameras needed to cover the tree
    public int minCameraCover(TreeNode root) {
        // Check if the depth-first search (DFS) of the tree is less than 1
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    // DFS method for traversing the tree
    public int dfs(TreeNode root) {
        // Check if the current node is null
        if (root == null) return 2;
        // Recursively call the DFS method on the left and right children of the current node
        int left = dfs(root.left), right = dfs(root.right);
        // If either the left or right child is a leaf node, add a camera to the current node
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        // If either the left or right child has a camera, mark the current node as covered
        return left == 1 || right == 1 ? 2 : 0;
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Define the input values for the binary tree
        Object[] tree= {0,0, null, 0, null, 0, null, null, 0 , 0 ,null,0};
        // Create the binary tree using the input values
        TreeNode root = new TreeNode().addToTree(tree);
        int ans = new ConstructionServiceCenter().minCameraCover(root);
        System.out.println(ans);
    }
}
