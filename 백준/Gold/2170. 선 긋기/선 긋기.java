import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int t, n, k;
    static int[] arr;
    public static final int MD = 1_000_000_007;
    static List<Line> lines = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        n = parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] split = reader.readLine().split(" ");
            int start = parseInt(split[0]);
            int end = parseInt(split[1]);
            lines.add(new Line(start, end));
        }
        lines.sort(Line::compareTo);
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
        System.out.println(result);
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

