class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long right = 2000000000000000L; // safe upper bound (min coin * k)
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countValid(mid, coins) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private long countValid(long limit, int[] coins) {
        long total = 0;
        int n = coins.length;
        int subsets = 1 << n;

        for (int i = 1; i < subsets; i++) {
            long lcmVal = 1;
            int bitCount = 0;

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    bitCount++;
                    lcmVal = lcm(lcmVal, coins[j]);
                    if (lcmVal > limit) {
                        break; // overflow or exceeds limit
                    }
                }
            }

            if (lcmVal <= limit) {
                if (bitCount % 2 == 1) {
                    total += limit / lcmVal;
                } else {
                    total -= limit / lcmVal;
                }
            }
        }
        return total;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

     private long gcd(long a, long b) {
         while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}