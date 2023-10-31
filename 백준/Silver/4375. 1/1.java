import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;

public class Main {

    static int t;
    static StringBuilder sb = new StringBuilder();

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
//        int z = 3;
        String s;
        while ((s = br.readLine()) != null) {
//        while (z-- != 0) {
//            s = br.readLine();
            int n = parseInt(s);
            int num = 1;
            int k = 1;
            while ((num % n) != 0) {
                num = (num * 10) + 1;
                num %= n;
                k++;
            }
            sb.append(k).append('\n');
        }
        System.out.print(sb);
    }
}


