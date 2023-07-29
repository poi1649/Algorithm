import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static int[] inorder;
    static int[] postorder;

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        postorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check(0, n - 1, 0, n - 1);
        System.out.println(sb);
    }

    static void check(int inStart, int inEnd, int postStart, int postEnd) {

        if (inStart == inEnd) {
            sb.append(inorder[inStart]).append(" ");
            return;
        }
        if (inStart > inEnd || postStart > postEnd) {
            return;
        }
        int root = postorder[postEnd];
        sb.append(root).append(" ");

        int inRootIndex = -1;

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root) {
                inRootIndex = i;
                break;
            }
        }
        int newPostEnd = postStart + inRootIndex - 1 - inStart;

        check(inStart, inRootIndex - 1, postStart, newPostEnd);
        check(inRootIndex + 1, inEnd, newPostEnd + 1, postEnd - 1);
    }
}


