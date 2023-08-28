#include <bits/stdc++.h>

using namespace std;

vector<int> parents;
vector<int> ranks;
vector<int> sizes;

void initDisjointSet(int n) {
    parents.resize(n + 1);
    ranks.resize(n + 1);
    sizes.resize(n + 1);
    for (int i = 0; i <= n; ++i) {
        parents[i] = i;
        ranks[i] = 0;
        sizes[i] = 1;
    }
}

int find(int x) {
    if (x == parents[x]) {
        return x;
    }
    return parents[x] = find(parents[x]);
}

void unite(int x, int y) {
    x = find(x);
    y = find(y);
    if (x != y) {
        if (ranks[x] < ranks[y]) {
            swap(x, y);
        }
        parents[y] = x;
        sizes[x] += sizes[y];
        if (ranks[x] == ranks[y]) {
            ++ranks[x];
        }
    }
}

int size(int x) {
    return sizes[find(x)];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m;
    cin >> n >> m;
    vector<vector<bool>> matrix(n, vector<bool>(m));
    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;
        for (int j = 0; j < m; ++j) {
            matrix[i][j] = s[j] == '1';
        }
    }
    initDisjointSet(n * m);
    vector<vector<int>> dx = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (auto& d : dx) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < n && y >= 0 && y < m && !matrix[i][j] && !matrix[x][y]) {
                    unite(i * m + j, x * m + y);
                }
            }
        }
    }
    set<int> s;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int temp = 1;
            if (!matrix[i][j]) {
                cout << 0;
                continue;
            }
            s.clear();
            for (auto& d: dx) {
                int x = i + d[0];
                int y = j + d[1];
                if (x >= 0 && x < n && y >= 0 && y < m && !matrix[x][y]) {
                    int parent = find(x * m + y);
                    if (s.count(parent)) {
                        continue;
                    }
                    temp += size(parent);
                    s.insert(parent);
                }
            }
            cout << temp % 10;
        }
        cout << '\n';
    }

    return 0;
}
