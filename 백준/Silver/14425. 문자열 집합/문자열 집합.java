import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final List<Integer> collect = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
        final Integer n = collect.get(0);
        final Integer m = collect.get(1);
        List<String> s = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            s.add(br.readLine());
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (s.contains(br.readLine())) {
                count++;
            }
        }
        System.out.println(count);
    }
}
