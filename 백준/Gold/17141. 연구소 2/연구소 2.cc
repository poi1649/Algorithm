#include <bits/stdc++.h>

using namespace std;

int dist[10][51][51], a, n, m, inputs[51][51];
vector<pair<int, int>> vs;
vector<pair<int, int>> directions = {{0,  1},
                                     {0,  -1},
                                     {1,  0},
                                     {-1, 0}};
int dp[1 << 11];

int dfs(bool selected[], int cnt, int status) {
    if (dp[status] != -1) return dp[status];
    if (cnt == m) {
        int maximum = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (inputs[i][j] == 1) {
                    continue;
                }
                int temp = 1e9;
                for (int k = 0; k < vs.size(); ++k) {
                    if (selected[k]) {
                        temp = min(temp, dist[k][i][j]);
                    }
                }
                maximum = max(maximum, temp);
            }
        }
        return dp[status] = maximum;
    }
    int ans = 1e9;
    for (int i = 0; i < vs.size(); ++i) {
        if (!selected[i]) {
            selected[i] = true;
            ans = min(ans, dfs(selected, cnt + 1, status | (1 << i)));
            selected[i] = false;
        }
    }
    return dp[status] = ans;
}

void find_shortest_path(int &i, pair<int, int> s) {
    queue<pair<int, int>> q;
    q.push(s);
    dist[i][s.first][s.second] = 0;
    while (!q.empty()) {
        auto curr = q.front();
        q.pop();
        for (int j = 0; j < 4; ++j) {
            int x = curr.first + directions[j].first;
            int y = curr.second + directions[j].second;
            if (x < 1 || x > n || y < 1 || y > n) continue;
            if (inputs[x][y] == 1) continue;
            if (dist[i][x][y] > dist[i][curr.first][curr.second] + 1) {
                dist[i][x][y] = dist[i][curr.first][curr.second] + 1;
                q.push({x, y});
            }
        }
    }
}

void process() {
    fill(&dist[0][0][0], &dist[0][0][0] + sizeof(dist) / sizeof(dist[0][0][0]), 1e9);
    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            cin >> a;
            inputs[i][j] = a;
            if (a == 2) {
                vs.push_back({i, j});
            }
        }
    }
    for (int i = 0; i < vs.size(); ++i) {
        find_shortest_path(i, vs[i]);
    }
    bool selected[10];
    memset(selected, false, sizeof(selected));
    memset(dp, -1, sizeof(dp));
    int ans = dfs(selected, 0, 0);
    if (ans == 1e9) cout << -1;
    else cout << ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o = 1;
//    cin >> o;
    while (o--) {
        process();
    }
}
