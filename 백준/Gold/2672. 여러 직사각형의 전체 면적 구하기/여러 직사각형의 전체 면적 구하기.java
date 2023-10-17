import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Line> lines = new ArrayList<>();

    static int[] using = new int[30010];

    static class Line {

        public Line(final int x1, final int x2, final int y, final boolean isEnd) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.isEnd = isEnd;
        }

        int x1;
        int x2;
        int y;
        boolean isEnd;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final var n = parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            final var s = br.readLine().split(" ");
            int x1 = (int) (parseDouble(s[0]) * 10);
            int x2 = (int) (parseDouble(s[0]) * 10 + parseDouble(s[2]) * 10);
            int y = (int) (parseDouble(s[1]) * 10);
            int y2 = (int) (parseDouble(s[3]) * 10);
            lines.add(new Line(x1, x2, y, false));
            lines.add(new Line(x1, x2, y + y2, true));
        }
        lines.sort((o1, o2) -> {
            if (o1.y != o2.y) {
                return o1.y - o2.y;
            }
            if (o1.isEnd != o2.isEnd) {
                return o1.isEnd ? 1 : -1;
            }
            return o1.x1 - o2.x1;
        });
        int sum = 0;
        int curLength = lines.get(0).x2 - lines.get(0).x1;
        for (int i = lines.get(0).x1 + 1; i <= lines.get(0).x2; i++) {
            using[i]++;
        }
        int curY = lines.get(0).y;
        for (int i = 1; i < lines.size(); i++) {
            Line line = lines.get(i);
            sum += curLength * (line.y - curY);
            if (line.isEnd) {
                for (int j = line.x1 + 1; j <= line.x2; j++) {
                    using[j]--;
                    if (using[j] == 0) {
                        curLength--;
                    }
                }
            } else {
                for (int j = line.x1 + 1; j <= line.x2; j++) {
                    using[j]++;
                    if (using[j] == 1) {
                        curLength++;
                    }
                }
            }
            curY = line.y;
        }
        if (sum % 100 == 0) {
            System.out.println(sum / 100);
        } else {
            System.out.println((double) sum / 100);
        }
    }

}
