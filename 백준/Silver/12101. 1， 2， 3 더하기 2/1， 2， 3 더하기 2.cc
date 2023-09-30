#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, k;
vector<vector<vector<int>>> dp;

void process() {
    cin >> n >> k;
    dp.resize( 12);
    dp[1].push_back({1});
    dp[2].push_back({1, 1});
    dp[2].push_back({2});
    dp[3].push_back({1, 1, 1});
    dp[3].push_back({1, 2});
    dp[3].push_back({2, 1});
    dp[3].push_back({3});
    for (int i = 4; i <= n; ++i) {
        for (int j = i - 1; j >= i - 3; --j) {
            for (auto &l: dp[j]) {
                //deep copy l and insert i -j in index 0
                vector<int> tmp = l;
                tmp.insert(tmp.begin(), i - j);
                dp[i].push_back(tmp);
            }
        }
    }
    if (k > dp[n].size()) {
        cout << -1 << "\n";
        return;
    }

    for (int i = 0; i < dp[n][k-1].size(); ++i) {
        if (i) cout << "+";
        cout << dp[n][k-1][i];
    }
    cout << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o = 1;
//    cin >> o;
    while (o--) {
        process();
    }
    return 0;
}
