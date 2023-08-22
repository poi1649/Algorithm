import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        k = parseInt(split[2]);
        long[] elements = new long[n];
        for (int i = 0; i < n; i++) {
            elements[i] = parseLong(reader.readLine());
        }
        SegmentTree tree = new SegmentTree(elements);
        tree.makeSums();
        for (int i = 0; i < m + k; i++) {
            split = reader.readLine().split(" ");
            int a = parseInt(split[0]);
            int b = parseInt(split[1]);
            long c = parseLong(split[2]);
            if (a == 1) {
                tree.replaceSum(b - 1, c);
            } else {
                System.out.println(tree.findSum(b - 1, (int) (c - 1)));
            }
        }
    }
}

class SegmentTree {

    private final long[] tree;

    public SegmentTree(long[] elements) {
        int k = 1;
        while (k < elements.length) {
            k *= 2;
        }
        tree = new long[k * 2];
        System.arraycopy(elements, 0, tree, k, elements.length);
    }

    public void makeSums() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
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

    public void replaceSum(int index, long value) {
        long diff = value - tree[tree.length / 2 + index];
        int i = tree.length / 2 + index;
        while (i > 0) {
            tree[i] += diff;
            i /= 2;
        }
    }

}


