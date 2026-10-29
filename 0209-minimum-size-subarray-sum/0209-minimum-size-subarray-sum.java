class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int currentSum = 0;
        int minLength = Integer.MAX_VALUE;
        
        // Right pointer ko aage badha kar window ko expand karein
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];
            
            // Jab tak currentSum target se bada ya barabar hai, window ko chhota karein
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                currentSum -= nums[left]; // Left element ko sum se hatayein
                left++; // Left pointer ko aage badhayein
            }
        }
        
        // Agar minLength update nahi hua, matlab koi aisa subarray nahi mila, toh 0 return karein
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
