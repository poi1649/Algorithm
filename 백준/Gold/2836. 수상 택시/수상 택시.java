import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int n, m;
    public static final int MD = 1_000_000_007;
    static List<Line> lines = new ArrayList<>();
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        final String s[] = reader.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            String[] split = reader.readLine().split(" ");
            int start = parseInt(split[0]);
            int end = parseInt(split[1]);
            if (start > end) {
                int temp = start;
                start = end;
                end = temp;
                lines.add(new Line(start, end));
            }
        }
        lines.sort(Line::compareTo);
        if (lines.isEmpty()) {
            System.out.println(m);
            return;
        }
        Line cur = lines.get(0);
        for (int i = 1; i < lines.size(); i++) {
            Line next = lines.get(i);
            if (cur.end >= next.start) {
                cur.end = Math.max(cur.end, next.end);
            } else {
                result += cur.end - cur.start;
                cur = next;
            }
        }
        result += cur.end - cur.start;
        System.out.println(m + result * 2);
    }
}


class Line implements Comparable<Line> {

    public int start;
    public int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.start, o.start);
    }
}

