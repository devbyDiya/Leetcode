/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Base case: problem guarantees we need a previous, current, and next node.
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstIndex = -1;
        int prevIndex = -1;
        int currentIndex = 1; // Start at the second node (index 1)

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            ListNode nextNode = curr.next;

            // Check if current node is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > nextNode.val;
            boolean isMinima = curr.val < prev.val && curr.val < nextNode.val;

            if (isMaxima || isMinima) {
                // If this is the first critical point found
                if (firstIndex == -1) {
                    firstIndex = currentIndex;
                } else {
                    // Update the minimum distance between consecutive critical points
                    minDistance = Math.min(minDistance, currentIndex - prevIndex);
                }
                // Update the most recent critical point tracker
                prevIndex = currentIndex;
            }

            // Move pointers forward
            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        // If less than 2 critical points were found, return [-1, -1]
        if (firstIndex == prevIndex) {
            return new int[]{-1, -1};
        }

        // Max distance is always the last critical point minus the first critical point
        int maxDistance = prevIndex - firstIndex;

        return new int[]{minDistance, maxDistance};
    }
}
