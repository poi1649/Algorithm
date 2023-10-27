import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] rs = new int[4000005];
    static int r;
    static int c;
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
        final var input = br.readLine();
        final var target = input.replace("", "*").toCharArray();
        r = 0;
        c = 0;
        long ans = 0;
        for (int i = 0; i < target.length; i++) {
            if (r + c < i) {
                c = i;
                r = 0;
            } else {
                rs[i] = Math.min(rs[2 * c - i], c + r - i);
            }
            while (i - rs[i] - 1 >= 0 && i + rs[i] + 1 < target.length && target[i - rs[i] - 1] == target[i + rs[i] + 1]) {
                rs[i]++;
            }
            if (r + c <= rs[i] + i) {
                r = rs[i];
                c = i;
            }
            if (target[i] == '*') {
                ans += rs[i] / 2;
            } else {
                ans += (rs[i] + 1) / 2;
            }
        }
        System.out.println(ans);
    }
}


