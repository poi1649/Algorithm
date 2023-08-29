#include <bits/stdc++.h>

using namespace std;

int n;
string cur;
string last;

int findMin(int i, int to_left[], int to_right[], int left_turn_num, vector<vector<int>> &dp) {
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
    int if_left = findMin(i + 1, to_left, to_right, (left_turn_num + cur_left_turn) % 10, dp) + cur_left_turn;
    int cur_right_turn = (to_right[i] + (left_turn_num % 10)) % 10;
    int if_right = findMin(i + 1, to_left, to_right, left_turn_num, dp) + cur_right_turn;
    dp[i][left_turn_num] = min(if_left, if_right);
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
    int ans = findMin(0, to_left, to_right, 0, dp);
    cout << ans;
}
