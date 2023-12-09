import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        n = parseInt(br.readLine());
        List<Problem> problems = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            var s = br.readLine().split(" ");
            problems.add(new Problem(s[0].toUpperCase(), parseInt(s[1]), parseInt(s[2])));
        }
        problems.sort(Comparator.comparingInt(o -> o.i));
        StringBuilder sb = new StringBuilder();
        for (final Problem problem : problems) {
            sb.append(problem.name.charAt(problem.d - 1));
        }
        System.out.println(sb);
    }
}

