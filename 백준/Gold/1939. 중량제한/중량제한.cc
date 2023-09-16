#include <bits/stdc++.h>

using namespace std;


int t, n, a, b, m;
bool v[100001];
unordered_map<int, int> g[100001];

bool can_go(int from, int to, int c) {
    queue<int> q;
    memset(v, 0, sizeof(v));
    q.push(from);
    v[from] = true;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (auto &i : g[x]) {
            if (!v[i.first] && i.second >= c) {
                if (i.first == to) return true;
                v[i.first] = true;
                q.push(i.first);
            }
        }
    }
    return false;
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        cin >> a >> b >> t;
        if (g[a].count(b)) {
            g[a][b] = max(g[a][b], t);
            g[b][a] = max(g[b][a], t);
        } else {
            g[a][b] = t;
            g[b][a] = t;
        }
    }
    cin >> a >> b;
    int l = 0;
    int r = 1000000001;
    while (l < r) {
        int mid = (l + r) / 2;
        if (can_go(a, b, mid)) {
            l = mid + 1;
        } else {
            r = mid;
        }
    }
    cout << l - 1 << '\n';
    return 0;
}
