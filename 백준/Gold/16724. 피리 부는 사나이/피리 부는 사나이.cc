#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m;
vector<vector<int>> graph;
int parent[1000001];
int ranks[1000001];

int find(int x) {
    if (parent[x] == x) {
        return x;
    }
    return parent[x] = find(parent[x]);
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
    parent[y] = x;
    if (ranks[x] == ranks[y]) {
        ranks[x]++;
    }
}

void process() {
    cin >> n >> m;
    graph.resize(n * m + 1);
    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;
        for (int j = 1; j <= m; ++j) {
            if (s[j - 1] == 'D') {
                graph[i * m + j].push_back((i + 1) * m + j);
            } else if (s[j - 1] == 'R') {
                graph[i * m + j].push_back(i * m + j + 1);
            } else if (s[j - 1] == 'U') {
                graph[i * m + j].push_back((i - 1) * m + j);
            } else if (s[j - 1] == 'L') {
                graph[i * m + j].push_back(i * m + j - 1);
            }
        }
    }

    int ans = 0;
    for (int i = 0; i < n; ++i) {
        for (int j = 1; j <= m; ++j) {
            parent[i * m + j] = i * m + j;
            ranks[i * m + j] = 0;
        }
    }
    for (int i = 1; i <= n * m; ++i) {
        for (int j : graph[i]) {
            merge(i, j);
        }
    }
    for (int i = 1; i <= n * m; ++i) {
        if (parent[i] == i) {
            ans++;
        }
    }
    cout << ans << "\n";
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
