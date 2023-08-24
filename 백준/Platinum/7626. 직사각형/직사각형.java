import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    static int n;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();
    static List<Integer> y_compress;
    static HashMap<Integer, Integer> y_compress_map;
    public static ArrayList<Integer> length;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));

        n = parseInt(reader.readLine());
        y_compress = new ArrayList<>();
        y_compress_map = new HashMap<>();
        length = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            int x1 = parseInt(line[0]);
            int y1 = parseInt(line[2]);
            int x2 = parseInt(line[1]);
            int y2 = parseInt(line[3]);
            nodes.add(new Node(x1, y1, y2, true));
            nodes.add(new Node(x2, y1, y2, false));
            y_compress.add(y1);
            y_compress.add(y2);
        }

        y_compress = y_compress.stream().distinct().sorted().collect(Collectors.toList());
        int index = 1;
        final int distinctYSize = y_compress.size();

        for (int i = 0; i < distinctYSize; i++) {
            if (!y_compress_map.containsKey(y_compress.get(i))) {
                y_compress_map.put(y_compress.get(i), index);
                if (i == distinctYSize - 1) {
                    index++;
                    continue;
                }
                length.add(y_compress.get(i + 1) - y_compress.get(i));
                index++;
            }
        }

        YSegmentTree tree = new YSegmentTree(distinctYSize);
        tree.build();

        long result = 0;
        int prevX = 0;

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            result += (node.x - prevX) * tree.tree[1].curLength;

            int left = y_compress_map.get(node.yStart);
            int right = y_compress_map.get(node.yEnd);

            if (node.isStart) {
                tree.active(left, right - 1);
            } else {
                tree.deActive(left, right - 1);
            }
            prevX = node.x;
        }
        System.out.println(result);
    }
}

class YSegmentTree {

    public Y[] tree;

    public YSegmentTree(int size) {
        int k = 1;
        while (k < size) {
            k *= 2;
        }
        tree = new Y[k * 2];
        for (int i = 0; i < k; i++) {
            if (i < Main.length.size()) {
                tree[k + i] = new Y(Main.length.get(i), 0, 0);
                continue;
            }
            tree[k + i] = new Y(0, 0, 0);
        }
    }

    public void build() {
        for (int i = tree.length / 2 - 1; i > 0; i--) {
            tree[i] = new Y(tree[i * 2].length + tree[i * 2 + 1].length, 0, 0);
        }
    }

    public void deActive(int left, int right) {
        left = tree.length / 2 + left - 1;
        right = tree.length / 2 + right - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                if (tree[left].actives == 1) {
                    if (left >= tree.length / 2) {
                        tree[left].curLength = 0;
                    } else {
                        tree[left].curLength = tree[left * 2].curLength + tree[left * 2 + 1].curLength;
                    }
                    rebuildFrom(left / 2);
                }
                tree[left].actives--;
                left++;
            }
            if (right % 2 == 0) {
                if (tree[right].actives == 1) {
                    if (right >= tree.length / 2) {
                        tree[right].curLength = 0;
                    } else {
                        tree[right].curLength = tree[right * 2].curLength + tree[right * 2 + 1].curLength;
                    }
                    rebuildFrom(right / 2);
                }
                tree[right].actives--;
                right--;
            }
            left /= 2;
            right /= 2;
        }
    }

    public void rebuildFrom(int index) {
        while (index > 0) {
            if (tree[index].actives > 0) {
                tree[index].curLength = tree[index].length;
                index /= 2;
                continue;
            }
            tree[index].curLength = tree[index * 2].curLength + tree[index * 2 + 1].curLength;
            index /= 2;
        }
    }


    public void active(int left, int right) {
        left = tree.length / 2 + left - 1;
        right = tree.length / 2 + right - 1;
        while (left <= right) {
            if (left % 2 == 1) {
                if (tree[left].actives == 0) {
                    tree[left].curLength = tree[left].length;
                    rebuildFrom(left / 2);
                }
                tree[left].actives++;
                left++;
            }
            if (right % 2 == 0) {
                if (tree[right].actives == 0) {
                    tree[right].curLength = tree[right].length;
                    rebuildFrom(right / 2);
                }
                tree[right].actives++;
                right--;
            }
            left /= 2;
            right /= 2;
        }
    }

}

class Y {

    public long length;
    public long curLength;
    public int actives;

    public Y(long length, long curLength, int actives) {
        this.length = length;
        this.curLength = curLength;
        this.actives = actives;
    }

}

class Node implements Comparable<Node> {

    public int x;
    public int yStart;
    public int yEnd;
    boolean isStart;

    public Node(int x, int yStart, int yEnd, boolean isStart) {
        this.x = x;
        this.yStart = yStart;
        this.yEnd = yEnd;
        this.isStart = isStart;
    }

    @Override
    public int compareTo(Node o) {
        return this.x - o.x;
    }
}

