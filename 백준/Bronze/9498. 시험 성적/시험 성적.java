import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

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

    public static void process(BufferedReader br, int p) throws IOException {
        final var n = parseInt(br.readLine());

        if (n >= 90) {
            System.out.println("A");
            return;
        }
        if (n >= 80) {
            System.out.println("B");
            return;
        }
        if (n >= 70) {
            System.out.println("C");
            return;
        }
        if (n >= 60) {
            System.out.println("D");
            return;
        }
        System.out.println("F");
    }
}


