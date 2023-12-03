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
        int i = 1;
        if (n % 2 == 0 || n % 5 == 0) {
            System.out.println(-1);
            return;
        }
        while (true) {
            if (prev == 0) {
                System.out.println(i);
                return;
            }
            i++;
            prev = (((prev * (10 % n)) % n) + 1) % n;
        }
    }
}
