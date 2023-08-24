import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    static int n;
    public static final int MD = 1_000_000_007;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();
    static ArrayList<Integer> y_compress;
    static HashMap<Integer, Integer> y_compress_map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        final int t = parseInt(reader.readLine());
        for (int z = 0; z < t; z++) {
            n = parseInt(reader.readLine());
            y_compress = new ArrayList<>();
            y_compress_map = new HashMap<>();
            long result = 0;
            for (int i = 0; i < n; i++) {
                String[] split = reader.readLine().split(" ");
                final int y = parseInt(split[1]);
                nodes.add(new Node(parseInt(split[0]), y));
                y_compress.add(y);
            }
            Collections.sort(y_compress);
            int index = 1;
            for (Integer yCompress : y_compress) {
                if (!y_compress_map.containsKey(yCompress)) {
                    y_compress_map.put(yCompress, index++);
                }
            }
            final YSegmentTree yTree = new YSegmentTree(100000);
            while(!nodes.isEmpty()) {
                Node node = nodes.poll();
                final int y = y_compress_map.get(node.y);
                result += yTree.findSum(y, 100000);
                yTree.add(y);
            }
            System.out.println(result);
        }
    }
}

class YSegmentTree {
    public int[] tree;

    public YSegmentTree(int size) {
        int k = 1;
        while (k < size) {
            k *= 2;
        }
        tree = new int[k * 2];
    }

    public void add(int index) {
        index += tree.length / 2;
        index -= 1;
        while (index > 0) {
            tree[index]++;
            index /= 2;
        }
    }

    public int findSum(int left, int right) {
        int sum = 0;
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
}

class Node implements Comparable<Node> {

    public int x;
    public int y;

    public Node(int start, int end) {
        this.x = start;
        this.y = end;
    }

    @Override
    public int compareTo(Node o) {
        if (this.x == o.x) {
            return o.y - this.y;
        }
        return this.x - o.x;
    }
}

