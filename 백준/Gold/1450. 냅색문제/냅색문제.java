import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static long[] longs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String[] s = br.readLine().split(" ");
        final int n = Integer.parseInt(s[0]);
        final long c = Long.parseLong(s[1]);
        longs = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        final ArrayList<Long> partOne = new ArrayList<>();
        final ArrayList<Long> partTwo = new ArrayList<>();
        check(0, n / 2, partOne, 0);
        check(n / 2, n, partTwo, 0);
        partTwo.sort(Long::compareTo);
        long result = 0;
        for (long target : partOne) {
            result += upperBound(c - target, partTwo);
        }
        System.out.println(result);
    }

    static void check(int index, long endAt, List<Long> result, long sum) {
        if (index == endAt) {
            result.add(sum);
            return;
        }
        check(index + 1, endAt, result, sum + longs[index]);
        check(index + 1, endAt, result, sum);
    }

    static long upperBound(long target, List<Long> list) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) > target) {
                hi = mid;
                continue;
            }
            lo = mid + 1;
        }
        return lo;
    }
}
