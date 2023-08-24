import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    private static final int MAX = 200000;
    static int n;
    public static final int MD = 1_000_000_007;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();
    static ArrayList<Integer> y_compress;
    static HashMap<Integer, Integer> y_compress_map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));

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
        final YSegmentTree yTree = new YSegmentTree(MAX);
        final YSegmentTree middleTree = new YSegmentTree(MAX);
        int prevX = Integer.MIN_VALUE;
        Deque<Integer> yTemp = new LinkedList<>();
        Deque<Long> middleTemp = new LinkedList<>();
        while (!nodes.isEmpty()) {
            final Node poll = nodes.poll();
            prevX = poll.x;
            final Integer y = y_compress_map.get(poll.y);
            result += middleTree.findSum(1, y - 1);
            result %= MD;
            yTemp.addLast(y);
            middleTemp.addLast(yTree.findSum(y + 1, MAX));
            while (!nodes.isEmpty() && nodes.peek().x == prevX) {
                final Node node = nodes.poll();
                final Integer y2 = y_compress_map.get(node.y);
                result += middleTree.findSum(1, y2 - 1);
                result %= MD;

                yTemp.addLast(y2);
                middleTemp.addLast(yTree.findSum(y2 + 1, MAX));
            }
            while (!yTemp.isEmpty()) {
                final Integer y3 = yTemp.pollFirst();
                final Long middle = middleTemp.pollFirst();
                yTree.add(y3, 1);
                middleTree.add(y3, middle);
            }
        }
        System.out.println(result);
    }
}

class YSegmentTree {

    public long[] tree;

    public YSegmentTree(int size) {
        int k = 1;
        while (k < size) {
            k *= 2;
        }
        tree = new long[k * 2];
    }

    public void add(int index, long value) {
        index += tree.length / 2;
        index -= 1;
        while (index > 0) {
            tree[index] += value;
            tree[index] %= Main.MD;
            index /= 2;
        }
    }

    public long findSum(int left, int right) {
        long sum = 0;
        left = tree.length / 2 + left - 1;
        right = tree.length / 2 + right - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left];
                sum %= Main.MD;
                left++;
            }
            if (right % 2 == 0) {
                sum += tree[right];
                sum %= Main.MD;
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

