import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int q, n, m, x;
    static int count;
    static int[][] arr;

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
        m = parseInt(split[0]);
        n = parseInt(split[1]);
        Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Integer::compare)
                .forEachOrdered(integer -> {
                    int left = 200 - m;
                    if (left > 0) {
                        m += integer;
                        count += 1;
                    }
                });
        System.out.println(count);
    }
}
