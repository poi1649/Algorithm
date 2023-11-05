import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int t;
    static int[] rank = new int[200001];
    static List<Integer> sa = new ArrayList<>();
    static int[] lcp = new int[200001];
    static char[] s;

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

    public static void process(BufferedReader br, int t) throws IOException {
        br.readLine();
        s = br.readLine().toCharArray();
        for (int i = 0; i < s.length; i++) {
            rank[i] = s[i] - 'a';
            sa.add(i);
        }
        for (int i = 1; i < s.length; i *= 2) {
            final int finalI = i;
            sa.sort((a, b) -> {
                if (rank[a] != rank[b]) {
                    return rank[a] - rank[b];
                }
                final var i1 = a + finalI < s.length ? rank[a + finalI] : -1;
                final var i2 = b + finalI < s.length ? rank[b + finalI] : -1;
                return i1 - i2;
            });
            int[] temp = new int[500001];
            temp[sa.get(0)] = 0;
            int r = 0;
            for (int j = 1; j < s.length; j++) {
                if (comp(sa.get(j - 1), sa.get(j), i)) {
                    r++;
                }
                temp[sa.get(j)] = r;
            }
            rank = temp;
        }

        int k = 0;
        for (int i = 0; i < s.length; i++) {
            if (rank[i] == 0) {
                continue;
            }
            int j = sa.get(rank[i] - 1);
            while (i + k < s.length && j + k < s.length && s[i + k] == s[j + k]) {
                k++;
            }
            lcp[rank[i]] = k;
            if (k > 0) {
                k--;
            }
        }
        int max = 0;
        for (int i = 1; i < s.length; i++) {
            max = Math.max(max, lcp[i]);
        }
        System.out.println(max);
    }

    private static boolean comp(final Integer integer, final Integer integer1, final int i) {
        if (rank[integer] != rank[integer1]) {
            return rank[integer] < rank[integer1];
        }
        final var i1 = integer + i < s.length ? rank[integer + i] : -1;
        final var i2 = integer1 + i < s.length ? rank[integer1 + i] : -1;
        return i1 < i2;
    }
}


