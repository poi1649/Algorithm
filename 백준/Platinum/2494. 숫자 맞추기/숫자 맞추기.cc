#include <bits/stdc++.h>

using namespace std;

int n;
string cur;
string last;

void printResult(vector<vector<int>> &next, int i, int left_turn_num, vector<vector<int>> &result) {
    if (i == n) {
        return;
    }
    cout << i + 1 << " " << result[i][left_turn_num] << "\n";
    printResult(next, i + 1, next[i][left_turn_num], result);
}

int findMin(int i, int to_left[], int to_right[], int left_turn_num, vector<vector<int>> &dp, vector<vector<int>> &result, vector<vector<int>> &next) {
    if (i == n) {
        return 0;
    }
    if (dp[i][left_turn_num] != -1) {
        return dp[i][left_turn_num];
    }
    int cur_left_turn = to_left[i] - (left_turn_num % 10);
    if (cur_left_turn < 0) {
        cur_left_turn += 10;
    }
    int if_left = findMin(i + 1, to_left, to_right, (left_turn_num + cur_left_turn) % 10, dp, result, next) + cur_left_turn;
    int cur_right_turn = (to_right[i] + (left_turn_num % 10)) % 10;
    int if_right = findMin(i + 1, to_left, to_right, left_turn_num, dp, result, next) + cur_right_turn;
    if (if_left < if_right) {
        dp[i][left_turn_num] = if_left;
        result[i][left_turn_num] = cur_left_turn;
        next[i][left_turn_num] = (left_turn_num + cur_left_turn) % 10;
    }
    else {
        dp[i][left_turn_num] = if_right;
        result[i][left_turn_num] = -cur_right_turn;
        next[i][left_turn_num] = left_turn_num;
    }
    return dp[i][left_turn_num];
}


int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    cin >> cur;
    cin >> last;
    int to_left[n];
    int to_right[n];
    for (int i = 0; i < n; i++) {
        int x1, x2;
        x1 = cur[i] - '0';
        x2 = last[i] - '0';
        if (x2 - x1 > 0) {
            to_left[i] = x2 - x1;
            to_right[i] = 10 - x2 + x1;
            continue;
        }
        to_left[i] = 10 - x1 + x2;
        to_right[i] = x1 - x2;
    }
    vector<vector<int>> dp(n, vector<int>(10, -1));
    vector<vector<int>> result(n, vector<int>(10, -1));
    vector<vector<int>> next(n, vector<int>(10, -1));
    int ans = findMin(0, to_left, to_right, 0, dp, result, next);
    cout << ans;
    cout << "\n";
    printResult(next, 0, 0, result);
}
