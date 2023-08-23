import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    static int t, n, m;
    static int[] arr;
    public static final int MD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        arr = new int[n + 1];
        String[] split = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = parseInt(split[i - 1]);
        }
        SegmentTree tree = new SegmentTree(n);
        m = parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            split = reader.readLine().split(" ");
            int a = parseInt(split[0]);
            if (a == 1) {
                tree.addExtra(parseInt(split[1]) - 1, parseInt(split[2]) - 1, parseInt(split[3]));
                continue;
            }
            System.out.println(arr[parseInt(split[1])] + tree.findExtra(parseInt(split[1]) - 1));
        }
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

    public final long[] tree;
    public int[] min;

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

    public void makeMaxAndMin() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
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

    public long findExtra(int index) {
        long extra = 0;
        index = tree.length / 2 + index;
        while (index > 0) {
            extra += tree[index];
            index /= 2;
        }
        return extra;
    }

    public void addExtra(int left, int right, long value) {
        left = tree.length / 2 + left;
        right = tree.length / 2 + right;
        while (left <= right) {
            if (left % 2 == 1) {
                tree[left] += value;
                left++;
            }
            if (right % 2 == 0) {
                tree[right] += value;
                right--;
            }
            left /= 2;
            right /= 2;
        }
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

    public void replaceMaxAndMin(int index, int value) {
        int i = tree.length / 2 + index;
        tree[i] = value;
        min[i] = value;
        i /= 2;
        while (i > 0) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
            min[i] = Math.min(min[i * 2], min[i * 2 + 1]);
            i /= 2;
        }
    }

    public void replaceMin(int index, int value) {
        int i = tree.length / 2 + index;
        tree[i] = value;
        i /= 2;
        while (i > 0) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
            i /= 2;
        }
    }

    public void replaceSum(int index, int value) {
        long diff = value - tree[tree.length / 2 + index];
        int i = tree.length / 2 + index;
        while (i > 0) {
            tree[i] += diff;
            i /= 2;
        }
    }

    public void replaceMultiple(int index, int value) {
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

}


