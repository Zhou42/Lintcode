public class Solution {
    /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] num) {
        // write your code here
        if(num == null || num.length == 0){
            return Integer.MIN_VALUE;
        }
        
        int start = 0;
        int end = num.length - 1;
        int mid;
        
        while(start + 1 < end){
            mid = start + (end - start)/2;
            if(num[mid] < num[end]){ // 这里不能跟num[start]比较，也就是不能找第一个小于num[start]的元素，因为这样当offset为零的时候，会出错。也就是要找第一个于num[end]的元素
                end = mid;
            }else{
                start = mid;
            }
        }
        if(num[start] < num[end]){
            return num[start];
        }else{
            return num[end];
        }
        
    }
}
