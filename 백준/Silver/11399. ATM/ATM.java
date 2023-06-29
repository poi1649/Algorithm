import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(br.readLine());
        final List<Integer> inputs = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());
        int previous = 0;
        int result = 0;
        for (int input : inputs) {
            result += input + previous;
            previous += input;
        }
        System.out.println(result);
    }
}
