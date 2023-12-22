import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int q, n, k, m, result;

    static List<Node> list = new ArrayList<>();

    static class Node {

        int start, end;

        public Node(final int start, final int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        q = 1;
        int cur = 1;

        while (q-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        final var split = br.readLine().split(" ");
        n = parseInt(split[0]);
        m = parseInt(split[1]);
        for (int i = 0; i < n; i++) {
            final var split1 = br.readLine().split(" ");
            int start = parseInt(split1[0]);
            int end = parseInt(split1[1]) - 1;
            if (start > end) {
                int tmp = start;
                start = end;
                end = tmp;
            }
            list.add(new Node(start, end));
        }
        list.sort((o1, o2) -> {
            if (o1.start != o2.start) {
                return o1.start - o2.start;
            }
            return o1.end - o2.end;
        });
        int start = list.get(0).start;
        int end = list.get(0).end;
        int coveredLast = 0;
        int ans = 0;

        for (int i = 1; i < n; i++) {
            Node curr = list.get(i);
            if (curr.start > end) {
                int ts = Math.max(start, coveredLast);
                final var length = end - ts + 1;
                if (length > 0) {
                    ans += length / m;
                    if (length % m != 0) {
                        ans++;
                        coveredLast = end + (m - (length % m)) + 1;
                    }
                }
                start = curr.start;
                end = curr.end;
                continue;
            }

            end = Math.max(end, curr.end);
        }

        int ts = Math.max(start, coveredLast);
        final var length = end - ts + 1;
        if (length > 0) {
            ans += length / m;
            if (length % m != 0) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}

