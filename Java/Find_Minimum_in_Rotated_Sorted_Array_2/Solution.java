public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < num.length; i++) {
            min = Math.min(min, num[i]);
        }
        return min;
    }
}