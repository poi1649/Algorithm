import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static class Pair {
        int index, value;

        public Pair(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }

    static class Tree {

        public Tree(final long[] inputs) {
            for (int i = 0; i < inputs.length; i++) {
                arr[p / 2 + i] = inputs[i] % MOD;
            }
            for (int i = p / 2 - 1; i > 0; i--) {
                arr[i] = arr[i * 2] + arr[i * 2 + 1];
                arr[i] %= MOD;
            }
        }

        public long sum(int l, int r) {
            l += arr.length / 2;
            r += arr.length / 2;
            long sum = 0;
            while (l <= r) {
                if (l % 2 != 0) {
                    sum += arr[l] % MOD;
                }
                if (r % 2 == 0) {
                    sum += arr[r] % MOD;
                }
                sum %= MOD;
                l = (l + 1) / 2;
                r = (r - 1) / 2;
            }
            return sum;
        }

        public void updateZero(int index) {
            index += arr.length / 2;
            arr[index] = 0;
            index /= 2;
            while (index > 0) {
                arr[index] = arr[index * 2] + arr[index * 2 + 1];
                arr[index] %= MOD;
                index /= 2;
            }
        }
    }

    static int t;
    static int n;
    static int k;
    static int p;
    static int MOD = 1000000007;
    static long[] arr;

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
        final var s1 = br.readLine().split(" ");
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pairs.add(new Pair(i, parseInt(s1[i])));
        }

        p = 1;
        while (p <= n * 2) {
            p *= 2;
        }
        arr = new long[p];

        long[] arr2 = new long[n];

        Arrays.fill(arr2, 1);
        var tree = new Tree(arr2);
        pairs.sort((o1, o2) -> o1.value - o2.value);
        for (int i = 1; i < k; i++) {
            for (int j = 0; j < pairs.size(); j++) {
                final var pair = pairs.get(j);
                int l = pair.index;
                int r = n - 1;
                tree.updateZero(l);
                arr2[pair.index] = tree.sum(l, r);
            }
            tree = new Tree(arr2);
        }
        System.out.println(arr[1]);
    }
}


