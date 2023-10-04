#include <bits/stdc++.h>

using namespace std;
using ll = long long;


const int TREE_SIZE = 1048576;
bitset<17> tree[TREE_SIZE];
bitset<17> lazy[TREE_SIZE];
int n, m, a;

void init_tree() {
    for (int i = TREE_SIZE / 2 - 1; i > 0; --i) {
        tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
    }
}

void propagate(int v, int l, int r) {
    if (lazy[v].none()) {
        return;
    }
    if (l != r) {
        lazy[v * 2] ^= lazy[v];
        lazy[v * 2 + 1] ^= lazy[v];
    }
    else {
        tree[v] ^= lazy[v];
    }
    lazy[v].reset();
}

void update(int l, int r, bitset<17> w, int nl = 1, int nr = TREE_SIZE / 2, int v = 1) {

    propagate(v, nl, nr);
    if (l > nr || r < nl) {
        return;
    }
    if (l <= nl && nr <= r) {
        lazy[v] ^= w;
        propagate(v, nl, nr);
        return;
    }

    int mid = (nl + nr) / 2;
    update(l, r, w, nl, mid, v * 2);
    update(l, r, w, mid + 1, nr, v * 2 + 1);
    tree[v] = tree[v * 2] ^ tree[v * 2 + 1];
}

bitset<17> get(int l, int r, int nl = 1, int nr = TREE_SIZE / 2, int v = 1) {
    propagate(v, nl, nr);

    if (l > nr || r < nl) {
        return bitset<17>();
    }
    if (l <= nl && nr <= r) {
        return tree[v];
    }
    int mid = (nl + nr) / 2;
    return get(l, r, nl, mid, v * 2) ^ get(l, r, mid + 1, nr, v * 2 + 1);
}

void print_tree() {
    for (int i = 0; i < TREE_SIZE; ++i) {
        cout << tree[i].to_ullong() << " ";
    }
    cout << "\n\n";
}

void process() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        tree[TREE_SIZE / 2 + i] = bitset<17>(x);
    }
    init_tree();
//    print_tree();
    cin >> m;
    for (int i = 0; i < m; ++i) {
        int t, l, r;
        cin >> t >> l >> r;
        if (t == 1) {
            int x;
            cin >> x;
            update(l + 1, r + 1, bitset<17>(x));
        } else {
            cout << get(l + 1, r + 1).to_ullong() << "\n";
        }
//        print_tree();
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
