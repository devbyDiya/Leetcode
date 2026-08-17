class Solution {
    public int stoneGameV(int[] stoneValue) {
      int n = stoneValue.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }
        int[][] memo = new int[n][n];
        return dp(stoneValue, 0, n - 1, prefixSum, memo);
    }
    
    private int dp(int[] stoneValue, int i, int j, int[] prefixSum, int[][] memo) {
        if (i == j) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        
        int maxScore = 0;
        for (int k = i; k < j; k++) {
            int leftSum = prefixSum[k + 1] - prefixSum[i];
            int rightSum = prefixSum[j + 1] - prefixSum[k + 1];
            
            if (leftSum < rightSum) {
                maxScore = Math.max(maxScore, leftSum + dp(stoneValue, i, k, prefixSum, memo));
            } else if (leftSum > rightSum) {
                maxScore = Math.max(maxScore, rightSum + dp(stoneValue, k + 1, j, prefixSum, memo));
            } else {
                maxScore = Math.max(maxScore, leftSum + Math.max(
                    dp(stoneValue, i, k, prefixSum, memo),
                    dp(stoneValue, k + 1, j, prefixSum, memo)
                ));
            }
        }
        return memo[i][j] = maxScore;
    }
}