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
