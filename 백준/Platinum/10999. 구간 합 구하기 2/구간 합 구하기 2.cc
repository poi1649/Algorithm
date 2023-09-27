#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 2097152;
//const int TREE_SIZE = 16;
int n, m, k;
ll tree[TREE_SIZE];
ll lazy[TREE_SIZE];

void init() {
    for (int i = TREE_SIZE / 2 - 1; i > 0; i--) {
        tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
}

void propagate(int node, int l, int r) {
    if (lazy[node] != 0) {
        tree[node] += (r - l + 1) * lazy[node];
        if (l != r) {
            lazy[node * 2] += lazy[node];
            lazy[node * 2 + 1] += lazy[node];
        }
        lazy[node] = 0;
    }
}

void add_w(int l, int r, ll w, int node = 1, int nl = 1, int nr = TREE_SIZE / 2) {
    propagate(node, nl, nr);
    if (r < nl || nr < l) {
        return;
    }
    if (l <= nl && nr <= r) {
        lazy[node] += w;
        propagate(node, nl, nr);
        return;
    }
    int mid = (nl + nr) / 2;
    add_w(l, r, w, node * 2, nl, mid);
    add_w(l, r, w, node * 2 + 1, mid + 1, nr);
    tree[node] = tree[node * 2] + tree[node * 2 + 1];
}

ll get_w(int l, int r, int node = 1, int nl = 1, int nr = TREE_SIZE / 2) {
    propagate(node, nl, nr);
    if (r < nl || nr < l) {
        return 0;
    }
    if (l <= nl && nr <= r) {
        return tree[node];
    }
    int mid = (nl + nr) / 2;
    return get_w(l, r, node * 2, nl, mid) + get_w(l, r, node * 2 + 1, mid + 1, nr);
}

void print_tree() {
//    cout << "tree:\n";
//    for (int i = 0; i < TREE_SIZE; ++i) {
//        cout << i << " ";
//    }
//    cout << "\n";
//    for (auto x : tree) {
//        cout << x << " ";
//    }
//    cout << "\n";
}

void process() {
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++) {
        cin >> tree[(TREE_SIZE / 2) + i];
    }
    init();
    print_tree();
    for (int i = 0; i < m + k; i++) {
        int t, l, r;
        cin >> t >> l >> r;

        if (t == 1) {
            ll w;
            cin >> w;
            add_w(l, r , w);
        } else {
            cout << get_w(l, r) << "\n";
        }
        print_tree();
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
