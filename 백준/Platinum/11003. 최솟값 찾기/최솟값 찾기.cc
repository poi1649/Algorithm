#include <bits/stdc++.h>

using namespace std;

int n, l;
vector<int> tree;

void update(int index, int value) {
    index += tree.size() / 2;
    tree[index] = value;
    while (index > 1) {
        index /= 2;
        tree[index] = min(tree[index * 2], tree[index * 2 + 1]);
    }
}

int findMin() {
    return tree[1];
}

void buildMin() {
    for (int i = tree.size() / 2 - 1; i > 0; i--) {
        tree[i] = min(tree[i * 2], tree[i * 2 + 1]);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> l;
    int k = 2;
    while (k < 2 * l) {
        k *= 2;
    }
    tree = vector<int>(k, INT_MAX);
    for (int i = 0; i < n; i++) {
        int index = (i % l);
        int x;
        cin >> x;
        update(index, x);
        cout << findMin() << " ";
    }
}