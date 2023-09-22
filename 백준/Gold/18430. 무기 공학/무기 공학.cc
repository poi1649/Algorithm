#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m, inputs[6][6];
bool selected[6][6];
int c = 0;
vector<pair<int, int>> directions = {
        {1,  1},
        {1,  -1},
        {-1, 1},
        {-1, -1}
};

int dfs(int sum, int x, int y) {
    int tmp_sum;
    int ret = 0;
    for (int i = x; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            if (i == x && j < y) continue;
            if (selected[i][j]) continue;
            selected[i][j] = true;
            for (auto &direct: directions) {
                int nx = i + direct.first;
                int ny = j + direct.second;
                if (nx < 1 || nx > n || ny < 1 || ny > m || selected[nx][j] || selected[i][ny]) continue;
                selected[nx][j] = true;
                selected[i][ny] = true;
                tmp_sum = sum + (inputs[i][j] * 2) + inputs[nx][j] + inputs[i][ny];
                ret = max(ret, dfs(tmp_sum, i, j));
                selected[nx][j] = false;
                selected[i][ny] = false;
            }
            selected[i][j] = false;
        }
    }
    return max(sum, ret);
}

void process() {
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; ++j) {
            cin >> inputs[i][j];
        }
    }
    int ans = 0;
    ans = dfs(ans, 1, 1);
    cout << ans << "\n";
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
