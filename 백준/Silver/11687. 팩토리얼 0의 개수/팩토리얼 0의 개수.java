import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m;

    static class Problem {

        String name;
        int i, d;

        public Problem(final String name, final int i, final int d) {
            this.name = name;
            this.i = i;
            this.d = d;
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
        m = parseInt(br.readLine());
        long curr = 5;
        int tmp = 1;
        while (true) {
            if (tmp == m) {
                System.out.println(curr);
                return;
            }
            if (tmp > m) {
                System.out.println(-1);
                return;
            }
            curr += 5;
            long tp = curr;
            while (tp % 5 == 0) {
                tp /= 5;
                tmp++;
            }
        }
    }
}

