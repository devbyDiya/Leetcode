class Solution {
    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int n = s.length();
        int i = 0;

        // 1. Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Handle string containing only spaces
        if (i == n) {
            return 0;
        }

        // 2. Check for sign
        int sign = 1;
        char firstChar = s.charAt(i);
        if (firstChar == '+') {
            i++;
        } else if (firstChar == '-') {
            sign = -1;
            i++;
        }

        // 3. Convert characters and handle overflow
        int total = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                break; // Stop parsing on non-digit character
            }

            int digit = ch - '0';

            // Check overflow before multiplying and adding
            if (total > Integer.MAX_VALUE / 10 || 
               (total == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + digit;
            i++;
        }

        return total * sign;
    }
}
