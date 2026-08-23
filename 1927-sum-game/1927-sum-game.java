class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftDiffQ = 0, rightDiffQ = 0;
        
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                leftDiffQ++;
            } else {
                leftSum += c - '0';
            }
        }
        
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') {
                rightDiffQ++;
            } else {
                rightSum += c - '0';
            }
        }
        
        if ((leftDiffQ + rightDiffQ) % 2 != 0) {
            return true;
        }
        
        return 2 * (leftSum - rightSum) != 9 * (rightDiffQ - leftDiffQ);
    }
}