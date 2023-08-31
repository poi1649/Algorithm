#include <bits/stdc++.h>

using namespace std;

int dp[15 * 15][1 << 14], n, m;
int input[15 * 15];
int score[5][5];

int check(int k, int s) {

    if (k >= n * m) {
        return 0;
    }

    if (~dp[k][s]) {
        return dp[k][s];
    }

    int tempOne = check(k + 1, s >> 1);
    int tempTwo = 0;
    int tempThree = 0;

    if (s & 1) {
        return dp[k][s] = tempOne;
    } else {
        if (k + m < n * m) {
            tempTwo = check(k + 1, (s >> 1) | (1 << (m - 1))) + score[input[k]][input[k + m]];
        }
        if ((k % m) != (m - 1) && !(s & 2)) {
            tempThree = check(k + 2, s >> 2) + score[input[k]][input[k + 1]];
        }
    }
    return dp[k][s] = max(tempThree, max(tempOne, tempTwo));
}

void initScore() {
    score[0][0] = 10;
    score[0][1] = 8;
    score[0][2] = 7;
    score[0][3] = 5;
    score[0][4] = 1;
    score[1][0] = 8;
    score[1][1] = 6;
    score[1][2] = 4;
    score[1][3] = 3;
    score[1][4] = 1;
    score[2][0] = 7;
    score[2][1] = 4;
    score[2][2] = 3;
    score[2][3] = 2;
    score[2][4] = 1;
    score[3][0] = 5;
    score[3][1] = 3;
    score[3][2] = 2;
    score[3][3] = 2;
    score[3][4] = 1;
    score[4][0] = 1;
    score[4][1] = 1;
    score[4][2] = 1;
    score[4][3] = 1;
    score[4][4] = 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (int i = 0; i < n; ++i) {
        string temp;
        cin >> temp;
        for (int j = 0; j < m; ++j) {
            if (temp[j] == 'F') input[i * m + j] = 4;
            else input[i * m + j] = temp[j] - 'A';
        }
    }
    initScore();
    memset(dp, -1, sizeof dp);
    int result = check(0, 0);
    cout << result;
}