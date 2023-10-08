#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, s, d, x, y, depths[100001], max_depths[100001];
bool checked[100001];
vector<vector<int>> g;

int init_depth(int depth, int cur) {
    depths[cur] = depth;
    max_depths[cur] = depth;
    for (int i : g[cur]) {
        if (i == s) {
            continue;
        }
        if (depths[i] == 0) {
            max_depths[cur] = max(init_depth(depth + 1, i), max_depths[cur]);
        }
    }
    return max_depths[cur];
}

int dfs(int cur, int depth) {
    int count = 0;
    checked[cur] = true;
    for (int i : g[cur]) {
        if (checked[i]) {
            continue;
        }
        if (max_depths[i] - depths[cur] > d) {
            count++;
            count += dfs(i, depth + 1);
        }
    }
    return count;
}

void process() {
    cin >> n >> s >> d;
    g.resize(n + 1);
    for (int i = 0; i < n - 1; ++i) {
        cin >> x >> y;
        g[x].push_back(y);
        g[y].push_back(x);
    }
    init_depth(0, s);
    cout << dfs(s, 0) * 2 << endl;
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
