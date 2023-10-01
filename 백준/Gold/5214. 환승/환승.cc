#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, k, m;


void process() {
    cin >> n >> k >> m;
    vector<vector<int>> a(n + 1 + m);
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < k; ++j) {
            int x;
            cin >> x;
            a[n + 1 + i].push_back(x);
            a[x].push_back(n + 1 + i);
        }
    }
    bool checked[n + 1 + m];
    memset(checked, false, sizeof(checked));
    queue<pair<int, int >> q;
    q.emplace(1, 1);
    checked[1] = true;
    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        if (cur.first == n) {
            cout << cur.second - cur.second / 2 << '\n';
            return;
        }
        for (int &u: a[cur.first]) {
            if (checked[u]) continue;
            checked[u] = true;
            q.emplace(u, cur.second + 1);
        }
    }
    cout << -1 << '\n';
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
