import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int q, n, k, x;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        q = parseInt(br.readLine());
        var s = br.readLine().split(" ");
        System.out.println(Long.parseLong(s[0]) + Long.parseLong(s[1]) + Long.parseLong(s[2]));
        br.close();
    }
}