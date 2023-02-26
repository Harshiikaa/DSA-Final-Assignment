/*Question 3
        a)
        You are given an even length array; divide it in half and return possible minimum product difference of any two subarrays.
        Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which is computed by calculating the difference after multiplying each element of the arrays a and b.
        Input: {5,2,4,11}
        Output: 2
        {5,4} {2,11} result into minimum product difference.*/

class MinimumProductDifference {
    // This is a static method that takes an integer array subarray and returns its product
    public static int product(int[] subaaray){
        // If the input array is empty, return 0
        if (subaaray.length == 0){
            return 0;
        }
        // Initialize a variable res to 1
        int res = 1;
        // Loop through the subarray and multiply each element with res
        for (int i = 0;i<subaaray.length;i++){
            res = res*subaaray[i];
        }
        // Return the result
        return res;
    }

    // This is a static method that takes an integer array and returns the minimum difference between the products of two subarrays
    public static int findminimumdifference(int[] array){
        // Initialize the minimum difference to the maximum integer value
        int mindiff = Integer.MAX_VALUE;
        // Get the length of the input array
        int n = array.length;
        // Loop through all possible combinations of subarrays
        for (int i = 0; i < (1 << n); i++) {
            // Check if the number of elements in the current subarray is equal to half of the input array size
            if (Integer.bitCount(i) == n / 2) {
                // Initialize two subarrays of equal size
                int[] subarray1 = new int[n / 2];
                int[] subarray2 = new int[n / 2];
                // Initialize two index variables for the two subarrays
                int index1 = 0;
                int index2 = 0;
                // Loop through the input array and add the elements to the appropriate subarray
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        subarray1[index1++] = array[j];
                    } else {
                        subarray2[index2++] = array[j];
                    }
                }
                // Get the product of the two subarrays
                int subarray1product = product(subarray1);
                int subarray2product = product(subarray2);
                // Calculate the current minimum difference between the two subarrays
                int curr_min_diff = 0;
                if (subarray2product>subarray1product){
                    curr_min_diff = subarray2product-subarray1product;
                }else{
                    curr_min_diff = subarray1product-subarray2product;
                }
                // Update the minimum difference if the current difference is smaller
                if (curr_min_diff<mindiff){
                    mindiff = curr_min_diff;
                }
            }
        }
        // Return the minimum difference
        return mindiff;
    }
    // This is the main method that calls the findminimumdifference method and prints the result
    public static void main(String[] args) {
        // Initialize an input array
        int[] array = {5, 2, 4, 11};
        // Call the findminimumdifference method with the input array and store the result in answer
        int answer = findminimumdifference(array);
        // Print the answer
        System.out.println(answer);
    }
}