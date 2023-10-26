import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    static boolean[] chk = new boolean[10];
    static StringBuilder sb = new StringBuilder();
    static int t;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = parseInt(br.readLine());
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        System.out.print(sb);
        br.close();
    }

    public static void process(BufferedReader br, int cur) throws IOException {
        long x = parseInt(br.readLine());
        if (x == 0) {
            sb.append("Case #").append(cur).append(": ").append("INSOMNIA").append("\n");
        }
        for (int i = 0; i <= 9; i++) {
            chk[i] = false;
        }
        for (int i = 1; i <= 1234567890; i++) {
            if (calc(x * i)) {
                sb.append("Case #").append(cur).append(": ").append(x * i).append("\n");
                return;
            }
        }
    }

    public static boolean calc(long x) {
        while (x > 0) {
            chk[(int) (x % 10)] = true;
            x /= 10;
        }
        for (int i = 0; i <= 9; i++) {
            if (!chk[i]) {
                return false;
            }
        }
        return true;
    }
}


