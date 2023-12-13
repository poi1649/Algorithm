import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int q, n, m;
    static int[] arr = new int[1000001];
    static boolean[][] can = new boolean[1000001][3];

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
        var s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = parseInt(s[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int prev = i - 1;
            int next = i + 1;
            boolean flag = false;

            if (arr[i] != 0) {
                if (prev < 0 || arr[prev] != arr[i]) {
                    if (arr[next] != arr[i]) {
                        flag = true;
                    }
                }

                if (!flag) {
                    System.out.println(-1);
                    return;
                }
                sb.append(arr[i]).append(" ");
                continue;
            }

            for (int j = 1; j <= 3; j++) {
                arr[i] = j;
                if (prev < 0 || arr[prev] != arr[i]) {
                    if ((i == n - 1) || arr[next] != arr[i]) {
                        flag = true;
                        break;
                    }
                }
            }
            sb.append(arr[i]).append(" ");
            if (!flag) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sb);
    }
}

