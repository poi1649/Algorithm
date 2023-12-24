import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, k, m, result;

    static int[] arr = new int[10001];

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
        for (int i = 0; i < n; i++) {
            arr[parseInt(br.readLine())]++;
        }
        long count = n;
        long ans = 0;
        for (int i = 1; i <= 10000; i++) {
            if (ans < i * count) {
                ans = i * count;
            }
            count -= arr[i];
        }
        System.out.println(ans);
    }
}

