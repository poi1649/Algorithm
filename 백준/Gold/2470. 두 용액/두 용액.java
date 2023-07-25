import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static long[] longs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final long[] inputs = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        Arrays.sort(inputs);
        long min = Long.MAX_VALUE;
        long targetOne = 0;
        int anotherIndex = 0;
        for (long target : inputs) {
            int i = Arrays.binarySearch(inputs, -target);
            if (i > 0 && inputs[i] != 0) {
                if (target > 0) {
                    System.out.println(inputs[i] + " " + target);
                    return;
                }
                System.out.println(target + " " + inputs[i]);
                return;
            }

            if (i < 0) {
                i = Math.abs(i) - 1;
            }
            long temp = Long.MAX_VALUE;
            int index = 0;

            if (i > 0) {
                int tempI = i;
                if (inputs[i - 1] == target) {
                    tempI--;
                }
                if (tempI > 0) {
                    temp = Math.abs(inputs[tempI - 1] + target);
                    index = tempI - 1;
                }
            }

            if (i < n) {
                if (inputs[i] == target) {
                    i++;
                }
                if (i < n && temp > Math.abs(inputs[i] + target)) {
                    temp = Math.abs(inputs[i] + target);
                    index = i;
                }
            }
            if (min > temp) {
                min = temp;
                targetOne = target;
                anotherIndex = index;
            }
        }

        if (inputs[anotherIndex] > targetOne) {
            System.out.println(targetOne + " " + inputs[anotherIndex]);
            return;
        }

        System.out.println(inputs[anotherIndex] + " " + targetOne);
    }
}
