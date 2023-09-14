#include <bits/stdc++.h>

using namespace std;

int n, m, from, to, w, k;
vector<int> g[5001];
int cost[5001][5001];
string a;

int findK(int &start, int &k) {
    queue<pair<int, int>> q;
    int ans = 0;
    q.push({start, 1e9});
    vector<bool> visited(n + 1, false);
    visited[start] = true;
    while (!q.empty()) {
        auto curE = q.front();
        int cur = curE.first;
        w = curE.second;
        q.pop();
        if (cur != start) {
            if (w >= k) {
                ans++;
            }
            if (w < k) {
                continue;
            }
        }

        for (auto &next: g[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                q.push({next, min(w, cost[cur][next])});
            }
        }
    }
    return ans;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (int i = 1; i < n; ++i) {
        cin >> from >> to >> w;
        g[from].push_back(to);
        g[to].push_back(from);
        cost[from][to] = w;
        cost[to][from] = w;
    }
    for (int i = 0; i < m; ++i) {
        cin >> from >> k;
        cout << findK(k, from) << '\n';
    }
    return 0;
}