#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m, k, v, c, parents[10001], ranks[10001];

void init() {
    for (int i = 0; i <= 10000; ++i) {
        parents[i] = i;
        ranks[i] = 1;
    }
}

int find(int x) {
    if (parents[x] == x) {
        return x;
    }
    return parents[x] = find(parents[x]);
}

void merge(int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y) {
        return;
    }
    if (ranks[x] < ranks[y]) {
        swap(x, y);
    }
    parents[y] = x;
    if (ranks[x] == ranks[y]) {
        ranks[x]++;
    }
}

void process() {
    cin >> n >> m >> k;
    vector<pair<int, int>> a(n + 1);
    for (int i = 1; i <= n; ++i) {
        cin >> v;
        a[i] = {v, i};
    }
    init();
    for (int i = 0; i < m; ++i) {
        cin >> v >> c;
        merge(v, c);
    }
    sort(a.begin(), a.end());
    int cost = 0;
    for (auto& i : a) {
        if (find(i.second) != find(0)) {
            merge(i.second, 0);
            cost += i.first;
        }
    }
    if (cost > k) {
        cout << "Oh no\n";
    } else {
        cout << cost << "\n";
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
