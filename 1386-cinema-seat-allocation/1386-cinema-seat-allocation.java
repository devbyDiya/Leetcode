class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowReserved = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // Set the (col - 1)-th bit to 1 to mark it reserved
            rowReserved.put(row, rowReserved.getOrDefault(row, 0) | (1 << (col - 1)));
        }
        
        // Start by assuming all rows can hold 2 families
        int maxFamilies = 2 * n;
        
        // Bitmasks for the three possible 4-seat blocks
        int leftMask = 0b0111100000;    // Columns 2, 3, 4, 5
        int middleMask = 0b0001111000;  // Columns 4, 5, 6, 7
        int rightMask = 0b0000011110;   // Columns 6, 7, 8, 9
        
        for (int mask : rowReserved.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            
            if (leftFree && rightFree) {
                // Both blocks are free, 2 families fit (no penalty)
                continue;
            } else if (leftFree || rightFree || middleFree) {
                // Only 1 family fits
                maxFamilies -= 1;
            } else {
                // No families fit
                maxFamilies -= 2;
            }
        }
        return maxFamilies;
    }

}