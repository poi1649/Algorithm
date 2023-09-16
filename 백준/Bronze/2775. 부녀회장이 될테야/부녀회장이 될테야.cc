#include <bits/stdc++.h>

using namespace std;

int dp[15][15];

int find_dp(int n, int k) {
    if (dp[n][k] != -1) {
        return dp[n][k];
    }
    if (k == 1) {
        return dp[n][k] = 1;
    }
    if (n == 0) {
        return dp[n][k] = k;
    }
    return dp[n][k] = find_dp(n - 1, k) + find_dp(n, k - 1);
}

int n, k, t;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> t;
    memset(dp, -1, sizeof(dp));
    for (int i = 0; i < t; ++i) {
        cin >> n >> k;
        cout << find_dp(n, k) << '\n';
    }
    return 0;
}
