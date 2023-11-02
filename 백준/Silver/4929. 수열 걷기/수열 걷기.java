import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int t;
    static int[] arr = new int[20001];
    static int[] arr2 = new int[20001];
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
        final var s = br.readLine().split(" ");
        if (s[0].equals("0") && s.length == 1) {
            return;
        }
        final var s1 = br.readLine().split(" ");
        final var i1 = parseInt(s[0]);

        for (int i = 1; i <= i1; i++) {
            int num = parseInt(s[i]) + 10000;
            arr[num]++;
        }

        final var i2 = parseInt(s1[0]);

        for (int i = 1; i <= i2; i++) {
            int num = parseInt(s1[i]) + 10000;
            arr2[num]++;
        }

        long ans = 0;
        int cur = 0;
        long tmp1 = 0;
        long tmp2 = 0;

        while (cur <= 20000) {
            if (arr[cur] == 0 && arr2[cur] == 0) {
                cur++;
                continue;
            }

            boolean flag = (arr[cur] > 0) && (arr2[cur] > 0);

            if (arr[cur] > 0) {
                tmp1 += (cur - 10000);
                arr[cur]--;
            }
            if (arr2[cur] > 0) {
                tmp2 += (cur - 10000);
                arr2[cur]--;
            }

            if (flag) {
                ans += Math.max(tmp1, tmp2);
                tmp1 = 0;
                tmp2 = 0;
            }
        }

        ans += Math.max(tmp1, tmp2);
        System.out.println(ans);


        arr = new int[20001];
        arr2 = new int[20001];
        process(br, t);
    }
}


