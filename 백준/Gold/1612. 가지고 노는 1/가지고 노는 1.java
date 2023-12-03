import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m, x;
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
        n = parseInt(br.readLine());
        int prev = 1 % n;
        for (int i = 1; i < 10000000; i++) {
            if (prev == 0) {
                System.out.println(i);
                return;
            }
            prev = (((prev * (10 % n)) % n) + 1) % n;
        }
        if (prev == 0) {
            System.out.println(10000000);
            return;
        }
        System.out.println(-1);
    }
}
