#include <bits/stdc++.h>

using namespace std;

int n, m;


void init_down(int i, vector<vector<int>> &matrix, vector<vector<int>> &down) {
    for (int j = 0; j < m; j++) {
        if (i == 0) {
            down[i][j] = matrix[i][j];
        } else {
            down[i][j] = down[i - 1][j] + matrix[i][j];
        }
    }
}

void init_right(int i, vector<vector<int>> &matrix, vector<vector<int>> &right, vector<vector<int>> &down) {
    if (i == 0) {
        return;
    }
    for (int j = m - 1; j >= 0; j--) {
        if (j == m - 1) {
            right[i][j] = down[i][j];
        } else {
            right[i][j] = max(right[i][j + 1] + matrix[i][j], down[i][j]);
        }
    }
}

void init_left(int i, vector<vector<int>> &matrix, vector<vector<int>> &left, vector<vector<int>> &down) {
    if (i ==0) {
        left[i][0] = down[i][0];
        for (int j = 1; j < m; ++j) {
            left[i][j] = left[i][j - 1] + matrix[i][j];
        }
        return;
    }
    for (int j = 0; j < m; j++) {
        if (j == 0) {
            left[i][j] = down[i][j];
        } else {
            left[i][j] = max(left[i][j - 1] + matrix[i][j], down[i][j]);
        }
    }
}

void findMax(int i, vector<vector<int>> &matrix, vector<vector<int>> &down, vector<vector<int>> &right,
            vector<vector<int>> &left) {
    if (i == n) {
        return;
    }
    init_down(i, matrix, down);
    init_right(i, matrix, right, down);
    init_left(i, matrix, left, down);
    for (int k = 0; k < m; k++) {
        down[i][k] = max(right[i][k], left[i][k]);
    }
    findMax(i + 1, matrix, down, right, left);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    vector<vector<int>> matrix(n, vector<int>(m));
    vector<vector<int>> dp(n, vector<int>(m));
    vector<vector<int>> right(n, vector<int>(m));
    vector<vector<int>> left(n, vector<int>(m));
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> matrix[i][j];
            dp[i][j] = -2e9;
            right[i][j] = -2e9;
            left[i][j] = -2e9;
        }
    }
    findMax(0, matrix, dp, right, left);
    cout << dp[n - 1][m - 1];
    return 0;
}
