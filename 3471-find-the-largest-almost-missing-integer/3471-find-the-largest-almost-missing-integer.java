class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            int maxVal = -1;
            for (int x : nums) maxVal = Math.max(maxVal, x);
            return maxVal;
        }
        int[] count = new int[51];
        for (int x : nums) {
            count[x]++;
        }
        if (k == 1) {
            int ans = -1;
            for (int i = 0; i <= 50; i++) {
                if (count[i] == 1) {
                    ans = i;
                }
            }
            return ans;
        }
        int ans = -1;
        if (count[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (count[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}