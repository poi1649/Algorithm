import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

    static int q, n, k, m, result;

    static List<Node> list = new ArrayList<>();

    static class Node {

        int start, end;

        public Node(final int start, final int end) {
            this.start = start;
            this.end = end;
        }
    }

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
        final var split = br.readLine().split("=");
        final var split1 = split[0].split("\\+");
        Map<Character, Integer> x3 = new HashMap<>();
        Map<Character, Integer> x1 = new HashMap<>();
        Map<Character, Integer> x2 = new HashMap<>();
        Map<Character, Integer> tmp = new HashMap<>();
        parse(x1, split1[0]);
        parse(x2, split1[1]);
        parse(x3, split[1]);
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                for (int l = 1; l <= 10; l++) {
                    for (final Entry<Character, Integer> x1e : x1.entrySet()) {
                        tmp.merge(x1e.getKey(), x1e.getValue() * i, Integer::sum);
                    }
                    for (final Entry<Character, Integer> x2e : x2.entrySet()) {
                        tmp.merge(x2e.getKey(), x2e.getValue() * j, Integer::sum);
                    }

                    boolean flag = false;
                    for (final Entry<Character, Integer> te : tmp.entrySet()) {
                        if (te.getValue() != (x3.get(te.getKey()) * l)) {
                            tmp.clear();
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        continue;
                    }
                    System.out.println(i + " " + j + " " + l);
                    return;
                }
            }
        }
    }

    public static void parse(Map<Character, Integer> map, String str) {
        final var charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; ) {
            char c = charArray[i];
            if (i == charArray.length - 1 || !Character.isDigit(charArray[i + 1])) {
                map.merge(c, 1, Integer::sum);
                i++;
                continue;
            }
            map.merge(c, charArray[i + 1] - '0', Integer::sum);
            i += 2;
        }
    }
}

