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
        while (true) {
            if (!process(br, cur++)) {
                break;
            }
        }
        br.close();
    }

    public static boolean process(BufferedReader br, int p) throws IOException {
        final var n = parseInt(br.readLine());
        if (n==0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Group ").append(p).append('\n');
        String[] names = new String[n + 1];
        boolean[][] arr = new boolean[n + 1][n];
        for (int i = 1; i <= n; i++) {
            final var split = br.readLine().split(" ");
            names[i] = split[0];
            for (int j = 1; j <= n - 1; j++) {
                arr[i][j] = split[j].equals("N");
            }
        }
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - 1; j++) {
                if (arr[i][j]) {
                    flag = false;
                    int index = i - j;
                    if (index <= 0) {
                        index += n;
                    }
                    sb.append(names[index]).append(" was nasty about ").append(names[i]).append("\n");
                }
            }
        }
        if (flag) {
            sb.append("Nobody was nasty\n");
        }
        System.out.println(sb);
        return true;
    }
}


