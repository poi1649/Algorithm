import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class Main {

    static int[][] ability;
    static int[] team;
    static int result = 0;
    static Deque<Integer> results = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ability = new int[n][n];
        team = new int[n / 2];
        for (int i = 0; i < n; i++) {
            final int[] abilityForOneLine = Arrays.stream(br.readLine()
                            .split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            ability[i] = abilityForOneLine;
        }
        calculate(n, 0, 0);
        int min = Integer.MAX_VALUE;
        final int size = results.size();
        for (int i = 0; i < size / 2; i++) {
            final int tempMin = Math.abs(results.pollFirst() - results.pollLast());
            if (min > tempMin) {
                min = tempMin;
            }
        }
        System.out.println(min);
    }

    private static void calculate(int n, int depth, int start) {
        if (depth == (n / 2)) {
            results.add(result);
            return;
        }

        for (int i = start; i < n; i++) {
            team[depth] = i;

            for (int j = 0; j < depth; j++) {
                result += ability[team[j]][i];
                result += ability[i][team[j]];
            }

            calculate(n, depth + 1, i + 1);

            for (int j = 0; j < depth; j++) {
                result -= ability[team[j]][i];
                result -= ability[i][team[j]];
            }
        }
    }
}
