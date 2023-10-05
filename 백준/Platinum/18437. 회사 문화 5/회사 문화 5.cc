#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 262144;
int tree[TREE_SIZE];
int lazy[TREE_SIZE];
int n, m, id = 1, starts[100001], range[100001], ids[100001];
vector<vector<int>> g(100001);

void init_tree() {
    for (int i = TREE_SIZE / 2 - 1; i > 0; --i) {
        tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
}

int dfs(int cur) {
    ids[cur] = id++;
    range[cur] = ids[cur];
    int sizz = g[cur].size();
    for (int i = 0; i < sizz; ++i) {
        if (i == 0) {
            starts[cur] = id;
        }
        range[cur] = dfs(g[cur][i]);
    }
    return range[cur];
}

void propagate(int v, int l, int r) {
    if (lazy[v] == 0) {
        return;
    }
    if (l != r) {
        lazy[v * 2] = lazy[v];
        lazy[v * 2 + 1] = lazy[v];
    }
    tree[v] = lazy[v] == 1 ? (r - l + 1) : 0;
    lazy[v] = 0;
}

void update(int l, int r, int w, int nl = 1, int nr = TREE_SIZE / 2, int v = 1) {
    propagate(v, nl, nr);
    if (l > nr || r < nl) {
        return;
    }
    if (l <= nl && nr <= r) {
        lazy[v] = w;
        propagate(v, nl, nr);
        return;
    }

    int mid = (nl + nr) / 2;
    update(l, r, w, nl, mid, v * 2);
    update(l, r, w, mid + 1, nr, v * 2 + 1);
    tree[v] = tree[v * 2] + tree[v * 2 + 1];
}

int get(int l, int r, int nl = 1, int nr = TREE_SIZE / 2, int v = 1) {
    propagate(v, nl, nr);

    if (l > nr || r < nl) {
        return 0;
    }
    if (l <= nl && nr <= r) {
        return tree[v];
    }
    int mid = (nl + nr) / 2;
    return get(l, r, nl, mid, v * 2) + get(l, r, mid + 1, nr, v * 2 + 1);
}

void print_tree() {
    for (int i = TREE_SIZE / 2; i < n + TREE_SIZE / 2; ++i) {
        cout << tree[i] << " ";
    }
    cout << "\n\n";
}

void process() {
    cin >> n;
    int x;

    cin >> x;
    for (int i = 2; i <= n; ++i) {
        cin >> x;
        g[x].push_back(i);
    }
    dfs(1);
    for (int i = 1; i < id; ++i) {
        tree[TREE_SIZE / 2 + i - 1] = 1;
    }
    init_tree();
    cin >> m;
    for (int i = 0; i < m; ++i) {
        int t, l;
        cin >> t >> l;
        if (t == 1) {
            if (starts[l] == 0) {
                continue;
            }
            update(starts[l], range[l], 1);
            continue;
        }
        if (t == 2) {
            if (starts[l] == 0) {
                continue;
            }
            update(starts[l], range[l], -1);
            continue;
        }
        if (starts[l] == 0) {
            cout << "0\n";
            continue;
        }
        cout << get(starts[l], range[l]) << "\n";
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
