import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final List<Integer> collect = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
        final int rows = collect.get(0);
        final int columns = collect.get(1);
        char[][] chars = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            chars[i] = br.readLine().toCharArray();
        }
        final int toFindRows = rows - 8 + 1;
        final int toFindColumns = columns - 8 + 1;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < toFindRows; i++) {
            for (int j = 0; j < toFindColumns; j++) {
                WB wb = new WB();
                BW bw = new BW();
                int count = 0;
                int count2 = 0;
                for (int k = i; k < i + 8; k++) {
                    wb.next();
                    bw.next();
                    for (int l = j; l < j + 8; l++) {
                        if (wb.getWB() != chars[k][l]) {
                            count++;
                        }
                        if (bw.getWB() != chars[k][l]) {
                            count2++;

                        }
                        wb.next();
                        bw.next();
                    }
                }
                int minCount = Integer.min(count2, count);
                min = Integer.min(min, minCount);
            }
        }
        System.out.println(min);

    }

    static class WB {
        private char WB = 'W';

        public void next() {
            if (WB == 'W') {
                WB = 'B';
                return;
            }
            WB = 'W';
        }

        private char getWB() {
            return WB;
        }
    }

    static class BW {
        private char WB = 'B';

        public void next() {
            if (WB == 'W') {
                WB = 'B';
                return;
            }
            WB = 'W';
        }

        private char getWB() {
            return WB;
        }
    }
}
