import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    static int n, m, k;
    public static final int MD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        Node[] elements = new Node[n + 1];
        final String[] s = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            elements[i] = new Node(i, parseInt(s[i - 1]));
        }
        SegmentTree tree = new SegmentTree(n);
        Arrays.sort(elements, 1, n + 1);
        long result = 0;
        for (int i = 1; i <= n; i++) {
            tree.replaceSum(elements[i].index - 1, 1);
            result += tree.findSum(elements[i].index, n - 1);
        }
        System.out.println(result);
    }
}

class Node implements Comparable<Node> {

    public final int index;
    public final int value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.value);
    }


}

class SegmentTree {

    private final long[] tree;
    private long[] min;

    public SegmentTree(int n) {
        int k = 1;
        while (k < n) {
            k *= 2;
        }
        tree = new long[k * 2];
    }

    public void makeSums() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void makeMultiple() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
            tree[i] %= Main.MD;
        }
    }

    public void makeMax() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    public void makeMin() {
        for (int i = min.length / 2 - 1; i > 0; i--) {
            min[i] = Math.min(min[i * 2], min[i * 2 + 1]);
        }
    }

    public long findSum(int left, int right) {
        long sum = 0;
        left = tree.length / 2 + left;
        right = tree.length / 2 + right;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                left++;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public long findMultiple(int left, int right) {
        long result = 1;
        left = tree.length / 2 + left;
        right = tree.length / 2 + right;
        while (left <= right) {
            if (left % 2 == 1) {
                result *= tree[left];
                result %= Main.MD;
                left++;
            }
            if (right % 2 == 0) {
                result *= tree[right];
                result %= Main.MD;
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return result;
    }

    public long findMax(int left, int right) {
        long max = Long.MIN_VALUE;
        left = tree.length / 2 + left;
        right = tree.length / 2 + right;
        while (left <= right) {
            if (left % 2 == 1) {
                max = Math.max(max, tree[left]);
                left++;
            }
            if (right % 2 == 0) {
                max = Math.max(max, tree[right]);
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return max;
    }

    public long findMin(int left, int right) {
        long temp = Long.MAX_VALUE;
        left = tree.length / 2 + left;
        right = tree.length / 2 + right;
        while (left <= right) {
            if (left % 2 == 1) {
                temp = Math.min(temp, min[left]);
                left++;
            }
            if (right % 2 == 0) {
                temp = Math.min(temp, min[right]);
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return temp;
    }

    public void replaceSum(int index, long value) {
        long diff = value - tree[tree.length / 2 + index];
        int i = tree.length / 2 + index;
        while (i > 0) {
            tree[i] += diff;
            i /= 2;
        }
    }

    public void replaceMultiple(int index, long value) {
        long origin = tree[tree.length / 2 + index];
        int i = tree.length / 2 + index;
        tree[i] = value;
        i /= 2;
        while (i > 0) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1];
            tree[i] %= Main.MD;
            i /= 2;
        }
    }

    int fastPow(int base, int exp, int mod) {
        int result = 1;
        for (; exp > 0; exp >>= 1, base = (base * base) % mod) {
            if (exp > 1) {
                result = (result * base) % mod;
            }
        }

        return result;
    }
}


