import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    static int t, n, k;
    static int[] arr;
    public static final int MD = 1_000_000_007;

    PriorityQueue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        final String[] s = reader.readLine().split(" ");
        n = parseInt(s[0]);
        k = parseInt(s[1]);
        final StringBuilder sb = new StringBuilder();
        sb.append("<");
        final SegmentTree tree = new SegmentTree(n);
        tree.makeSums();
        int temp;
        int fromIndex = 1;
        while ((temp = tree.findNthAndRemoveFrom(k, fromIndex)) != -1) {
            sb.append(temp).append(", ");
            fromIndex = temp;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(">");
        System.out.println(sb);
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

    public int[] tree;
    public int[] min;

    public SegmentTree(int n) {
        int k = 1;
        while (k < n) {
            k *= 2;
        }
        tree = new int[k * 2];
        for (int i = 0; i < n; i++) {
            tree[k + i] = 1;
        }
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
        left = tree.length / 2 + left - 1;
        right = tree.length / 2 + right - 1;
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

    public int findNthAndRemoveFrom(int target, int from) {
        if (tree[1] == 0) {
            return -1;
        }
        if (tree[1] < target) {
            target %= tree[1];
            if (target == 0) {
                target = tree[1];
            }
        }
        int left = from;
        int right = tree.length / 2;
        long tempCount = findSum(left, right);
        if (tempCount >= target) {
            while (left <= right) {
                int mid = (left + right) / 2;
                long count = findSum(left, mid);
                if (count >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    target -= count;
                }
            }
            int index = left;
            replaceSum(index, tree[tree.length / 2 + index - 1] - 1);
            return index;
        } else {
            target -= tempCount;
            left = 1;
            right = from - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                long count = findSum(left, mid);
                if (count >= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    target -= count;
                }
            }
            int index = left;
            replaceSum(index, tree[tree.length / 2 + index - 1] - 1);
            return index;
        }
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
        final int ind = tree.length / 2 + index - 1;
        int diff = value - tree[ind];
        int i = ind;
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


