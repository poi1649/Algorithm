import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Pair {

        int id;
        int source;
        int dest;
        int comp;

        public Pair(final int id, final int source, final int dest, final int comp) {
            this.id = id;
            this.source = source;
            this.dest = dest;
            this.comp = comp;
        }
    }

    static int[] arr = new int[100_001];
    static long[] answers = new long[100_001];
    static int[] cnt = new int[100_000_1];
    static long answer = 0;
    static List<Pair> queries = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s1 = br.readLine().split(" ");
        final var n = parseInt(s1[0]);
        final var m = parseInt(s1[1]);

        int sqrt = (int) Math.sqrt(n);

        final var s2 = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            arr[i] = parseInt(s2[i - 1]);
        }

        for (int i = 0; i < m; i++) {
            final var s = br.readLine().split(" ");
            final var src = parseInt(s[0]);
            queries.add(new Pair(i, src, parseInt(s[1]), src / sqrt));
        }

        queries.sort(
                (o1, o2) -> {
                    if (o1.comp != o2.comp) {
                        return Integer.compare(o1.comp, o2.comp);
                    }
                    return Integer.compare(o1.dest, o2.dest);
                }
        );

        Pair cur = queries.get(0);
        int src = cur.source;
        int dest = cur.dest;

        for (int i = src; i <= dest; i++) {
            add(arr[i]);
        }
        answers[cur.id] = answer;

        for (int i = 1; i < m; i++) {
            cur = queries.get(i);
            while (src > cur.source) {
                src--;
                add(arr[src]);
            }

            while (dest < cur.dest) {
                dest++;
                add(arr[dest]);
            }

            while (src < cur.source) {
                remove(arr[src]);
                src++;
            }

            while (dest > cur.dest) {
                remove(arr[dest]);
                dest--;
            }
            answers[cur.id] = answer;
        }

        final var sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(answers[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void add(int cur) {
        answer -= (long) (cnt[cur]) * (cnt[cur]) * cur;
        cnt[cur]++;
        answer += (long) cnt[cur] * cnt[cur] * cur;
    }

    private static void remove(int cur) {
        answer -= (long) (cnt[cur]) * (cnt[cur]) * cur;
        cnt[cur]--;
        answer += (long) cnt[cur] * cnt[cur] * cur;
    }
}
