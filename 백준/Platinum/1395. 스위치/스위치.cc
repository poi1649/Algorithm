#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 262144;
int tree[TREE_SIZE];
bool lazy[TREE_SIZE];
int n, m, id = 1;

void init_tree() {
    for (int i = TREE_SIZE / 2 - 1; i > 0; --i) {
        tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
}

void propagate(int v, int l, int r) {
    if (!lazy[v]) {
        return;
    }
    if (l != r) {
        lazy[v * 2] = !lazy[v * 2];
        lazy[v * 2 + 1] = !lazy[v * 2 + 1];
    }
    tree[v] = (r - l + 1) - tree[v];
    lazy[v] = false;
}

void update(int l, int r, int nl = 1, int nr = TREE_SIZE / 2, int v = 1) {
    propagate(v, nl, nr);
    if (l > nr || r < nl) {
        return;
    }
    if (l <= nl && nr <= r) {
        lazy[v] = !lazy[v];
        propagate(v, nl, nr);
        return;
    }

    int mid = (nl + nr) / 2;
    update(l, r, nl, mid, v * 2);
    update(l, r, mid + 1, nr, v * 2 + 1);
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
    cin >> n >> m;
    for (int i = 0; i < m; ++i) {
        int t, l ,r;
        cin >> t >> l >> r;
        if (t ==0) {
            update(l, r);
        } else {
            cout << get(l, r) << "\n";
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
