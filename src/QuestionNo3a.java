/*Question 3
        a)
        You are given an even length array; divide it in half and return possible minimum product difference of any two subarrays.
        Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which is computed by calculating the difference after multiplying each element of the arrays a and b.
        Input: {5,2,4,11}
        Output: 2
        {5,4} {2,11} result into minimum product difference.*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinimumProductDifference {
    public static List<int[]> findSubarrays(int[] arr) {
        List<int[]> subArr = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                int[] subarray = new int[len];
                for (int k = 0; k < len; k++) {
                    subarray[k] = arr[i + k];
                }
                subArr.add(subarray);
            }
        }
        return subArr;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,2,11};
        List<int[]> subarrays = findSubarrays(arr);
        int lenght = subarrays.size();
        int[] productArray = new int[lenght];
        int i = 0;
        for (int[] arrP : subarrays) {
            int product = 1;
            for (int element : arrP) {
                product *= element;
            }
            productArray[i] = product;
            i++;
        }
        Arrays.sort(productArray);
        System.out.println(Arrays.toString(productArray));
        int minDiff = productArray[1]-productArray[0];
        for (int j = 2 ; j < productArray.length ; j++) {
            minDiff = Math.min(minDiff, productArray[j]-productArray[j-1]);
        }
        System.out.println(minDiff);
    }
}
