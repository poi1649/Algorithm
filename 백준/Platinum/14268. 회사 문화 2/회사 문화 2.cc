#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 262144;
int n, m, range[100001], num[100001], tree[TREE_SIZE];
vector<vector<int>> g(100001);
int id = 1;

int dfs(int cur) {
    num[cur] = id++;
    if (g[cur].empty()) {
        return range[cur] = num[cur];
    }
    int x = 0;
    for (auto &e: g[cur]) {
        x = max(dfs(e), x);
    }
    return range[cur] = x;
}

void add_w(int l, int r, int w) {
    l = (TREE_SIZE / 2) + l - 1;
    r = (TREE_SIZE / 2) + r - 1;
    while (l <= r) {
        if (l % 2 == 1) tree[l++] += w;
        if (r % 2 == 0) tree[r--] += w;
        l /= 2;
        r /= 2;
    }
}

int get_w(int x) {
    int ret = 0;
    x = (TREE_SIZE / 2) + x - 1;
    while (x > 0) {
        ret += tree[x];
        x /= 2;
    }
    return ret;
}

void process() {
    cin >> n >> m;
    int a;
    cin >> a;
    for (int i = 2; i <= n; i++) {
        cin >> a;
        g[a].push_back(i);
    }
    dfs(1);
    for (int i = 0; i < m; ++i) {
        int t, v, w;
        cin >> t;
        if (t == 1) {
            cin >> v >> w;
            add_w(num[v], range[v], w);
        } else {
            cin >> v;
            cout << get_w(num[v]) << "\n";
        }
    }
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
