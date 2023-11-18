import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int t;
    static int m;
    static int n;
    static Map<String, Integer> maps = new HashMap<>();
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
        for (int i = 0; i < n; i++) {
            final var s1 = br.readLine();
            if (s1.length() < m) {
                continue;
            }
            maps.merge(s1, 1, Integer::sum);
        }
        maps.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    if (!o1.getValue().equals(o2.getValue())) {
                        return o2.getValue() - o1.getValue();
                    }
                    if (o1.getKey().length() != o2.getKey().length()) {
                        return o2.getKey().length() - o1.getKey().length();
                    }
                    return o1.getKey().compareTo(o2.getKey());
                })
                .forEach(p -> sb.append(p.getKey()).append('\n'));
        System.out.print(sb);
    }
}

