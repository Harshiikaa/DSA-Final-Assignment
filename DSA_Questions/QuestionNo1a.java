import java.util.*;
/*
Question 1
        a)
        There are n nations linked by train routes. You are given a 2D array indicating routes between
        countries and the time required to reach the target country, such that E[i]=[xi,yi,ki], where
        xi represents the source country, yi represents the destination country, and ki represents the
         time required to go from xi to yi. If you are also given information on the charges, you must pay
         while entering any country. Create an algorithm that returns the cheapest route from county A to county
         B with a time constraint.

        Input: edge= {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}}
        Charges = {10,2,3,25,25,4}
        Source: 0
        Destination: 5
        Output: 64
        Time Constraint=14 min
        Note: the path 0, 3, 4, 5 will take minimum time i.e., 13 minutes and which costs around $64. We cannot take path 0,1,2,5 as it takes 15 min and violates time constraint which in 14 min.
*/

class Country {
    int id;
    int time;
    int cost;
    // Constructor for creating a Country object with the given ID, time, and cost
    public Country(int id, int time, int cost) {
        this.id = id;
        this.time = time;
        this.cost = cost;
    }
}

class CheapestRouteWithTimeConstraint {
    // Defined a static method for finding the cheapest route given a set of edges, charges for visiting each country, source and destination countries, and time constraint
    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // Create a graph represented as an adjacency list using a HashMap
        Map<Integer, List<Country>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];// Starting country of the edge
            int to = edge[1];// Ending country of the edge
            int time = edge[2];// Time taken to travel between the two countries
            int cost = charges[to];// Cost of visiting the destination country
            List<Country> list = graph.getOrDefault(from, new ArrayList<>());// Retrieve the list of countries for the starting country from the graph, or create a new one if it does not exist
            list.add(new Country(to, time, cost));// Add the destination country to the list with its corresponding time and cost
            graph.put(from, list);// Update the graph with the new list of countries for the starting country
        }

        // Initialize the distances and visited flags for all countries
        int[] distances = new int[charges.length];  // Array to store the shortest distances to each country
        boolean[] visited = new boolean[charges.length];// Array to keep track of whether each country has been visited or not
        Arrays.fill(distances, Integer.MAX_VALUE); // Set all distances to a very high value initially
        distances[source] = 0;// Set the distance to the source country as 0 since it is the starting point

        // Use a priority queue to select the node with the smallest distance adding the source country to the queue
        PriorityQueue<Country> queue = new PriorityQueue<>((a, b) -> a.time - b.time); // The priority queue is ordered based on the time taken to reach each country
        queue.offer(new Country(source, 0, charges[source]));

        // Use Dijkstra's algorithm with a time constraint to find the shortest path
        while (!queue.isEmpty()) {
            Country curr = queue.poll();// Remove the country with the smallest time from the priority queue
            if (curr.id == destination) {// If the destination country has been reached, return the cost of reaching it
                return curr.cost;
            }
            if (visited[curr.id]) {// If the current country has already been visited, skip it
                continue;
            }
            visited[curr.id] = true;// Mark the current country as visited
            for (Country neighbor : graph.getOrDefault(curr.id, new ArrayList<>())) { // For each neighbor of the current country
                int newTime = curr.time + neighbor.time; // Calculate the new time taken to reach the neighbor
                int newCost = curr.cost + charges[neighbor.id];// Calculate the new cost of visiting the neighbor
                if (newTime <= timeConstraint && newCost < distances[neighbor.id]) {// comparing the time constraint
                    distances[neighbor.id] = newCost;
                    queue.offer(new Country(neighbor.id, newTime, newCost));
                }
            }
        }
        return -1; // No path found
    }
    // driver code
    public static void main(String[] args) {
        int a [][]={{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        System.out.println(findCheapestRoute(a, new int[]{10, 2, 3, 25, 25, 4},0,5,14));
    }
}


