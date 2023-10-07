#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m;

void process() {
    cin >> n >> m;
    int inputs[n + 1][m + 1];
    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;
        for (int j = 0; j < m; ++j) {
            inputs[i + 1][j + 1] = s[j] - '0';
        }
    }
    int ans = 1;
    for (int size = 1; size <= n && size <= m; size++) {
        for (int i = 1; i + size <= n; ++i) {
            for (int j = 1; j + size <= m; ++j) {
                if (inputs[i][j] == inputs[i + size][j] &&
                    inputs[i][j] == inputs[i][j + size] &&
                    inputs[i][j] == inputs[i + size][j + size]) {
                    ans = max(ans, size + 1);
                }
            }
        }
    }
    cout << ans * ans << "\n";
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
