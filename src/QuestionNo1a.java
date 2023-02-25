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
            public Country(int id, int time, int cost) {
                this.id = id;
                this.time = time;
                this.cost = cost;
            }
        }

        class CheapestRouteWithTimeConstraint {
            public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
                // Create a graph represented as an adjacency list
                Map<Integer, List<Country>> graph = new HashMap<>();
                for (int[] edge : edges) {
                    int from = edge[0];
                    int to = edge[1];
                    int time = edge[2];
                    int cost = charges[to];
                    List<Country> list = graph.getOrDefault(from, new ArrayList<>());
                    list.add(new Country(to, time, cost));
                    graph.put(from, list);
                }

                // Initialize the distances and visited flags
                int[] distances = new int[charges.length];
                boolean[] visited = new boolean[charges.length];
                Arrays.fill(distances, Integer.MAX_VALUE);
                distances[source] = 0;

                // Use a priority queue to select the node with the smallest distance
                PriorityQueue<Country> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
                queue.offer(new Country(source, 0, charges[source]));

                // Dijkstra's algorithm with a time constraint
                while (!queue.isEmpty()) {
                    Country curr = queue.poll();
                    if (curr.id == destination) {
                        return curr.cost;
                    }
                    if (visited[curr.id]) {
                        continue;
                    }
                    visited[curr.id] = true;
                    for (Country neighbor : graph.getOrDefault(curr.id, new ArrayList<>())) {
                        int newTime = curr.time + neighbor.time;
                        int newCost = curr.cost + charges[neighbor.id];
                        if (newTime <= timeConstraint && newCost < distances[neighbor.id]) {
                            distances[neighbor.id] = newCost;
                            queue.offer(new Country(neighbor.id, newTime, newCost));
                        }
                    }
                }
                return -1; // No path found
            }
            public static void main(String[] args) {

                int a [][]={{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
                System.out.println(findCheapestRoute(a, new int[]{10, 2, 3, 25, 25, 4},0,5,14));
            }
        }


