import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final int[] distances = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        final int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minimumCost = Integer.MAX_VALUE;
        long result = 0;
        for (int i = 0; i < distances.length; i++) {
            minimumCost = Math.min(minimumCost, costs[i]);
            result += (long) distances[i] * minimumCost;
        }

        System.out.println(result);
        br.close();
    }
}
