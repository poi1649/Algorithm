#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, k;


void process() {
    cin >> n;
    vector<int> coins = vector<int>(n);
    int dp[10001][n];
    memset(dp, 0, sizeof(dp));
    for (int i = 0; i < n; ++i) {
        int coin;
        cin >> coin;
        coins[i] = coin;
        dp[coin][i] = 1;
    }
    cin >> k;

    for (int i = 0; i < n; i++) {
        for (int j = 1; j <= k; ++j) {
            for (int l = 0; l <= i; ++l) {
                if (j - coins[i] > 0) {
                    dp[j][i] += dp[j - coins[i]][l];
                }
            }
        }
    }

    int ans = 0;
    for (int i = 0; i < n; ++i) {
        ans += dp[k][i];
    }
    cout << ans << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o;
    cin >> o;
    while (o--) {
        process();
    }
    return 0;
}
