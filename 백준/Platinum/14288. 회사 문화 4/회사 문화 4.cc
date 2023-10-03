#include <bits/stdc++.h>

using namespace std;
using ll = long long;

const int TREE_SIZE = 262144;
int tree[TREE_SIZE][2];
int pos[100001];
int range[100001];
int n, m, a;

vector<vector<int>> g(100001);
int id = 1;

int dfs(int cur) {
    pos[cur] = id++;
    range[cur] = pos[cur];
    for (int to: g[cur]) {
        range[cur] = dfs(to);
    }
    return range[cur];
}

void add_down(int l, int r, int w) {
    l += TREE_SIZE / 2;
    r += TREE_SIZE / 2;
    while (l <= r) {
        if (l % 2 == 1) {
            tree[l][0] += w;
            l++;
        }
        if (r % 2 == 0) {
            tree[r][0] += w;
            r--;
        }
        l >>= 1;
        r >>= 1;
    }
}

int get_down(int x) {
    x += TREE_SIZE / 2;
    int res = 0;
    while (x > 0) {
        res += tree[x][0];
        x >>= 1;
    }
    return res;
}

void add_up(int x, int w) {
    x += TREE_SIZE / 2;
    while (x > 0) {
        tree[x][1] += w;
        x >>= 1;
    }
}

int get_up(int l, int r) {
    l += TREE_SIZE / 2;
    r += TREE_SIZE / 2;
    int res = 0;
    while (l <= r) {
        if (l % 2 == 1) {
            res += tree[l][1];
            l++;
        }
        if (r % 2 == 0) {
            res += tree[r][1];
            r--;
        }
        l >>= 1;
        r >>= 1;
    }
    return res;
}

void process() {
    cin >> n >> m;
    cin >> a;
    for (int i = 2; i <= n; ++i) {
        cin >> a;
        g[a].push_back(i);
    }
    bool is_down = true;
    dfs(1);
    int b, c = 0;
    for (int i = 0; i < m; ++i) {
        cin >> a;
        if (a == 1) {
            cin >> b >> c;
            if (is_down) {
                add_down(pos[b], range[b], c);
                continue;
            }
            add_up(pos[b], c);
        }
        if (a == 2) {
            cin >> b;
            cout << get_down(pos[b]) + get_up(pos[b], range[b]) << "\n";
        }
        if (a == 3) {
            is_down = !is_down;
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
    return 0;
}
