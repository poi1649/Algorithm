#include <bits/stdc++.h>

using namespace std;


int n, m;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    int a, b;
    vector<pair<int, int>> g;
    for (int i = 0; i < m; ++i) {
        cin >> a >> b;
        g.push_back({a, b});
    }
    std::sort(g.begin(), g.end());
    int cur = 1;
    int ans = 1;
    for (int i = 0; i < m; ++i) {
        if (g[i].first <= cur) {
            cur = max(cur, g[i].second);
            continue;
        }
        ans += g[i].first - cur;
        cur = g[i].second;
    }
    ans += n - cur;
    cout << ans << '\n';
    return 0;
}
