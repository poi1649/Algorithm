import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static int recursiveCount = 0;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        fib(n);
        System.out.println(recursiveCount + " " + (n - 2));
    }

    public static int fib(int n) {
        if (n == 1 || n == 2) {
            recursiveCount++;
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

}
