import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int[][] board = new int[9][9];
        final boolean[][] shouldFill = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(shouldFill[i], false);
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    shouldFill[i][j] = true;
                }
            }
        }

        fill(shouldFill, board);

        for (int j = 0; j < 9; j++) {
            System.out.println(Arrays.stream(board[j]).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    private static boolean fill(boolean[][] shouldFill, int[][] board) {
        int targetI = -1;
        int targetJ = -1;
        boolean targetSelected = false;
        boolean[] canUse = new boolean[10];

        Arrays.fill(canUse, true);
        canUse[0] = false;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (shouldFill[i][j]) {
                    targetI = i;
                    targetJ = j;
                    targetSelected = true;
                    break;
                }
            }
            if (targetSelected) {
                break;
            }
        }

        if (!targetSelected) {
            return true;
        }

        checkSameI(board, targetI, canUse);

        checkSameJ(board, targetJ, canUse);

        checkSameBlock(board, targetI, targetJ, canUse);

        for (int i = 1; i < 10; i++) {
            if (canUse[i]) {
                board[targetI][targetJ] = i;
                shouldFill[targetI][targetJ] = false;
                if (fill(shouldFill, board)) {
                    return true;
                }
                board[targetI][targetJ] = 0;
                shouldFill[targetI][targetJ] = true;
            }
        }
        return false;
    }

    private static void checkSameI(final int[][] board, final int targetI, final boolean[] canUse) {
        for (int i = 0; i < 9; i++) {
            if (board[targetI][i] != 0) {
                canUse[board[targetI][i]] = false;
            }
        }
    }

    private static void checkSameJ(final int[][] board, final int targetJ, final boolean[] canUse) {
        for (int i = 0; i < 9; i++) {
            if (board[i][targetJ] != 0) {
                canUse[board[i][targetJ]] = false;
            }
        }
    }

    private static void checkSameBlock(final int[][] board, final int targetI, final int targetJ, final boolean[] canUse) {
        if (targetI < 3) {
            if (targetJ < 3) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else if (targetJ < 6) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            }
        } else if (targetI < 6) {
            if (targetJ < 3) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else if (targetJ < 6) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else {
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            }
        } else {
            if (targetJ < 3) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else if (targetJ < 6) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            } else {
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        if (board[i][j] != 0) {
                            canUse[board[i][j]] = false;
                        }
                    }
                }
            }
        }
    }
}
