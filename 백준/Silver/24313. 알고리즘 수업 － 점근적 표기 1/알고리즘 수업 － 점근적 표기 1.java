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
        int c = Integer.parseInt(br.readLine());
        int nzero = Integer.parseInt(br.readLine());
        if (collect.get(0) * nzero + collect.get(1) <= c * nzero && c >= collect.get(0)) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }
}
