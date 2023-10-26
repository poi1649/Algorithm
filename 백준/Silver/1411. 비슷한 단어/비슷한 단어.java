import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    enum Node {
        W(1),
        O(2),
        L(3),
        F(4),
        ;

        int order;

        Node(final int order) {
            this.order = order;
        }

        public Node next() {
            if (this == Node.F) {
                return Node.W;
            }
            return Node.values()[this.order];
        }
    }

    static int[] count = new int[5];
    static List<String> inputs = new ArrayList<>();
    static int t;

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
        final var n = parseInt(br.readLine());
        char[] conv = new char[28];
        Arrays.fill(conv, (char) 0);

        for (int i = 0; i < n; i++) {
            char cur = 'a';
            final var e = br.readLine().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (final char c : e) {
                if (conv[c - 'a'] == 0) {
                    conv[c - 'a'] = cur++;
                }
                sb.append(conv[c - 'a']);
            }
            inputs.add(sb.toString());
            Arrays.fill(conv, (char) 0);
        }
        int ans = 0;

        for (int i = 0; i < n; i++) {
            final var cur = inputs.get(i);
            for (int j = i + 1; j < n; j++) {
                if (cur.equals(inputs.get(j))) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }
}


