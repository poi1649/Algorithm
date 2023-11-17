import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Tree {

        int[] tree;

        public Tree() {
            tree = new int[1048576];
        }

        public void update(int index, int value) {
            index += tree.length / 2;
            tree[index] = value;
            index /= 2;
            while (index > 0) {
                tree[index] = Math.max(tree[index * 2], tree[index * 2 + 1]);
                index /= 2;
            }
        }

        public int max(int l, int r) {
            l += tree.length / 2;
            r += tree.length / 2;
            int ans = 0;
            while (l <= r) {
                if (l % 2 != 0) {
                    ans = Math.max(tree[l], ans);
                }
                if (r % 2 == 0) {
                    ans = Math.max(tree[r], ans);
                }
                l = (l + 1) / 2;
                r = (r - 1) / 2;
            }
            return ans;
        }
    }

    static int t;
    static int d;
    static int n;
    static int k;
    static int[] mod = new int[500001];
    static int[] inputs = new int[500001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        k = parseInt(s[1]);
        d = parseInt(s[2]);
        final var s1 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            inputs[i] = parseInt(s1[i]);
        }
        final var tree = new Tree();
        int ret = 0;

        for (int i = 0; i < n; i++) {
            final var c = inputs[i];
            final var tmp = tree.max(Math.max(0, c - d), Math.min(c + d, 500000));

            int max = Math.max(tmp, mod[c % k]) + 1;
            mod[c % k] = Math.max(max, mod[c % k]);

            tree.update(c, max);
            ret = Math.max(ret, max);
        }

        System.out.println(ret);
    }
}

