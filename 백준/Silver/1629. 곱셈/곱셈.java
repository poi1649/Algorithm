import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long a = Long.parseLong(st.nextToken()); // 밑 숫자
        long b = Long.parseLong(st.nextToken()); // 지수
        long c = Long.parseLong(st.nextToken()); // 나누기

        System.out.println(remainder(a, b, c));
    }

    private static long remainder(long a, long b, long c) {
        // 지수가 1이면 바로 나머지 구하기.
        if (b == 1) {
            return a % c;
        }
        // 지수가 1 이상이면 지수를 반으로 나누어 다시 나머지 구하기.
        else {

            long halfVal = remainder(a, b / 2, c);
            // 지수가 홀수일 때
            if (b % 2 == 1) {
                return (halfVal * halfVal % c) * a % c;
            }
            // 지수가 짝수일 때
            return halfVal * halfVal % c;
        }
    }
}
