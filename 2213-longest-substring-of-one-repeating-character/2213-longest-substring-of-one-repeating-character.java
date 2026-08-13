class Solution {
    class Node {
        int l, r, size, lmx, rmx, mx;
        char lc, rc;
    }

    private Node[] tr;
    private String s;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s;
        int n = s.length();
        tr = new Node[n * 4];
        for (int i = 0; i < tr.length; i++) {
            tr[i] = new Node();
        }
        build(1, 1, n);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            update(1, queryIndices[i] + 1, queryCharacters.charAt(i));
            ans[i] = tr[1].mx;
        }
        return ans;
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].size = 1;
            tr[u].lmx = 1;
            tr[u].rmx = 1;
            tr[u].mx = 1;
            tr[u].lc = s.charAt(l - 1);
            tr[u].rc = s.charAt(l - 1);
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build((u << 1) | 1, mid + 1, r);
        pushUp(u);
    }

    private void update(int u, int x, char ch) {
        if (tr[u].l == tr[u].r) {
            tr[u].lc = ch;
            tr[u].rc = ch;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) update(u << 1, x, ch);
        else update((u << 1) | 1, x, ch);
        pushUp(u);
    }

    private void pushUp(int u) {
        Node left = tr[u << 1];
        Node right = tr[(u << 1) | 1];
        
        tr[u].size = left.size + right.size;
        tr[u].lc = left.lc;
        tr[u].rc = right.lc == 0 ? left.rc : right.rc; // fallback or handle standard right char
        
        // Proper merge logic for fields
        tr[u].lmx = left.lmx;
        if (left.lmx == left.size && left.rc == right.lc) {
            tr[u].lmx = left.size + right.lmx;
        }
        
        tr[u].rmx = right.rmx;
        if (right.rmx == right.size && right.lc == left.rc) {
            tr[u].rmx = right.size + left.rmx;
        }
        
        tr[u].mx = Math.max(left.mx, right.mx);
        if (left.rc == right.lc) {
            tr[u].mx = Math.max(tr[u].mx, left.rmx + right.lmx);
        }
        
        // Correctly carry boundary characters for root/parents
        tr[u].lc = left.lc;
        tr[u].rc = right.rc;
    }
}