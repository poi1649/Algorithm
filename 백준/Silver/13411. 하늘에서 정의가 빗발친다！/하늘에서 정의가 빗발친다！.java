import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int q, n, m, result;
    static List<Target> targets = new ArrayList<>();

    static class Target {

        int number;
        double time;

        public Target(final int number, final double time) {
            this.number = number;
            this.time = time;
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
        for (int i = 1; i <= n; i++) {
            final var split = br.readLine().split(" ");
            int x = parseInt(split[0]);
            int y = parseInt(split[1]);
            int v = parseInt(split[2]);
            int c = x * x + y * y;
            var time = c /(double) (v * v);
            targets.add(new Target(i, time));
        }

        targets.sort((o1, o2) -> {
            if (Double.compare(o1.time, o2.time) == 0) {
                return o1.number - o2.number;
            }
            return Double.compare(o1.time, o2.time);
        });
        for (final Target target : targets) {
            System.out.println(target.number);
        }
    }
}

