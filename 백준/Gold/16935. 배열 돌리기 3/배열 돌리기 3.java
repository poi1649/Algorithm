import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int t;
    static int[][] arr = new int[101][101];
    static int[][] arr2 = new int[101][101];
    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        t = parseInt(br.readLine());
        t = 1;
        int cur = 1;
        while (t-- > 0) {
            process(br, cur++);
        }
        br.close();
    }

    public static void process(BufferedReader br, int t) throws IOException {
        final var s = br.readLine().split(" ");
        n = parseInt(s[0]);
        m = parseInt(s[1]);
        final var r = parseInt(s[2]);

        for (int i = 1; i <= n; i++) {
            final var s1 = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                arr[i][j] = parseInt(s1[j - 1]);
            }
        }

        final var s1 = br.readLine().split(" ");
        for (int i = 0; i < r; i++) {
            int com = parseInt(s1[i]);
            switch (com) {
                case 1:
                    upsideDown();
                    break;
                case 2:
                    inversion();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    divideAndRotateRight();
                    break;
                case 6:
                    divideAndRotateLeft();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void divideAndRotateLeft() {
        int tn = n / 2;
        int tm = m / 2;
        for (int i = 1; i <= tn; i++) {
            for (int j = 1; j <= tm; j++) {
                arr2[tn + i][j] = arr[i][j];
            }
            for (int j = tm + 1; j <= m; j++) {
                arr2[i][j - tm] = arr[i][j];
            }
        }
        for (int i = tn + 1; i <= n; i++) {
            for (int j = 1; j <= tm; j++) {
                arr2[i][j + tm] = arr[i][j];
            }
            for (int j = tm + 1; j <= m; j++) {
                arr2[i - tn][j] = arr[i][j];
            }
        }
        copyarr();
    }

    private static void divideAndRotateRight() {
        int tn = n / 2;
        int tm = m / 2;
        for (int i = 1; i <= tn; i++) {
            for (int j = 1; j <= tm; j++) {
                arr2[i][tm + j] = arr[i][j];
            }
            for (int j = tm + 1; j <= m; j++) {
                arr2[tn + i][j] = arr[i][j];
            }
        }
        for (int i = tn + 1; i <= n; i++) {
            for (int j = 1; j <= tm; j++) {
                arr2[i - tn][j] = arr[i][j];
            }
            for (int j = tm + 1; j <= m; j++) {
                arr2[i][j - tm] = arr[i][j];
            }
        }
        copyarr();
    }

    private static void copyarr() {
        for (int i = 1; i <= n; i++) {
            if (m >= 0) {
                System.arraycopy(arr2[i], 1, arr[i], 1, m);
            }
        }
    }

    private static void rotateLeft() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr2[m - j + 1][i] = arr[i][j];
            }
        }
        nmswap();
        copyarr();
    }

    private static void rotateRight() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr2[j][n - i + 1] = arr[i][j];
            }
        }
        nmswap();
        copyarr();
    }

    private static void nmswap() {
        int tmp = n;
        n = m;
        m = tmp;
    }

    private static void inversion() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m / 2; j++) {
                swap(i, j, i, m - j + 1);
            }
        }
    }

    private static void upsideDown() {
        for (int i = 1; i <= n / 2; i++) {
            for (int j = 1; j <= m; j++) {
                swap(i, j, n - i + 1, j);
            }
        }
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        int temp = arr[x1][y1];
        arr[x1][y1] = arr[x2][y2];
        arr[x2][y2] = temp;
    }
}


