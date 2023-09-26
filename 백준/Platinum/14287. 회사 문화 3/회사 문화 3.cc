#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 262144;
int n, m, num[100001], range[100001], tree[TREE_SIZE];
vector<vector<int>> g(100001);
int id = 1;

int dfs(int cur) {
    num[cur] = id++;
    range[cur] = num[cur];
    for (auto &e: g[cur]) {
        range[cur] = dfs(e);
    }
    return range[cur];
}

void add_w(int i, int w) {
    int index = (TREE_SIZE / 2) + i;
    while (index > 0) {
        tree[index] += w;
        index /= 2;
    }
}

int get_w(int l, int r) {
    l = (TREE_SIZE / 2) + l;
    r = (TREE_SIZE / 2) + r;
    int res = 0;
    while (l <= r) {
        if (l % 2 == 1) {
            res += tree[l];
            l++;
        }
        if (r % 2 == 0) {
            res += tree[r];
            r--;
        }
        l /= 2;
        r /= 2;
    }
    return res;
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
            add_w(num[v], w);
        } else {
            cin >> v;
            cout << get_w(num[v], range[v]) << "\n";
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
