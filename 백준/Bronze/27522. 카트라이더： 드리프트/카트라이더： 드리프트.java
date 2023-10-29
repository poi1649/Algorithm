import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    static List<Record> records = new ArrayList<>();
    static int t;
    static StringBuilder sb = new StringBuilder();

    static class Record {

        int time;
        char team;

        public Record(final int time, final char team) {
            this.time = time;
            this.team = team;
        }
    }

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
        for (int i = 0; i < 8; i++) {
            final var s1 = br.readLine().split(" ");
            final var time = parseInt(s1[0].replaceAll(":", ""));
            records.add(new Record(time, s1[1].charAt(0)));
        }
        int t1Sum = 0;
        int t2Sum = 0;
        int t1Min = Integer.MAX_VALUE;
        int t2Min = Integer.MAX_VALUE;
        records.sort(Comparator.comparingInt(o -> o.time));
        for (int i = 0; i < 8; i++) {
            final var cur = records.get(i);
            if (cur.team == 'R') {
                t1Sum += score(i + 1);
                t1Min = Math.min(t1Min, i);
                continue;
            }
            t2Sum += score(i + 1);
            t2Min = Math.min(t2Min, i);
        }
        if (t1Sum != t2Sum) {
            System.out.println(t1Sum > t2Sum ? "Red" : "Blue");
            return;
        }
        System.out.println(t1Min < t2Min ? "Red" : "Blue");
    }

    private static int score(int rank) {
        switch (rank) {
            case 1:
                return 10;
            case 2:
                return 8;
            case 3:
                return 6;
            case 4:
                return 5;
            case 5:
                return 4;
            case 6:
                return 3;
            case 7:
                return 2;
            default:
                return 1;
        }
    }
}


