import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    static int t;
    static int n;
    static int m;
    static Set<String> maps = new HashSet<>();
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

    public static void process(BufferedReader br, int cur) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maps.add(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            final var o = br.readLine();
            if (maps.contains(o)) {
                ans.add(o);
            }
        }

        sb.append(ans.size()).append('\n');
        ans.stream()
                .sorted()
                .forEach(o -> sb.append(o).append('\n'));
        System.out.print(sb);
    }
}

