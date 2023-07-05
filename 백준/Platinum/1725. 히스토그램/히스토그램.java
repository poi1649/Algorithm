import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long[] histogram;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final StringBuilder sb = new StringBuilder();
        final int n = Integer.parseInt(br.readLine());
        histogram = new long[n];
        for (int i = 0; i < n; i++) {
            histogram[i] = Long.parseLong(br.readLine());
        }
        long max = calculate(0, n);
        sb.append(max).append(System.lineSeparator());
        System.out.print(sb);
    }

    static long calculate(int from, int length) {
        if (length == 1) {
            return histogram[from];
        }
        int delta = 0;
        if (length % 2 == 1) {
            delta = 1;
        }
        // 2 1 4 5 2 3 3
        // 0 1 2 3 4 5 6
        final int half = length / 2;
        int middleStart = from + half;
        long max = Math.max(calculate(from, half), calculate(middleStart, half + delta));

        int left = middleStart - 1;
        int right = middleStart;
        long minHeight = Math.min(histogram[left], histogram[right]);
        int count = 2;
        long tempMax = minHeight * count;

        while (left > from || right < from + length - 1) {
            int target;
            if (left <= from) {
                target = right + 1;
                right++;
            } else if (right >= from + length - 1) {
                target = left - 1;
                left--;
            } else if (histogram[left - 1] <= histogram[right + 1]) {
                target = right + 1;
                right++;
            } else {
                target = left - 1;
                left--;
            }

            count++;
            minHeight = Math.min(minHeight, histogram[target]);
            tempMax = Math.max(tempMax, minHeight * count);
        }

        return Math.max(tempMax, max);
    }
}

