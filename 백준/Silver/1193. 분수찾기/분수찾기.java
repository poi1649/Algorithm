import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int n = 1;
        while (x - n > 0) {
            x -= n;
            n++;
        }
        if (n % 2 == 0) {
            System.out.println((x) + "/" + (n - x + 1));
            return;
        }
        System.out.println((n - x + 1) + "/" + x);
    }
}
