#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m, k;
int w[2][3];
int ans;

void dfs(int lastJ, int depth, int sum) {
    if (depth == n) {
        if (sum >= k) {
            ans++;
        }
        return;
    }
    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < 3; ++j) {
            if (j == lastJ) {
                dfs(j, depth + 1, sum + w[i][j] / 2);
            } else {
                dfs(j, depth + 1, sum + w[i][j]);
            }
        }
    }
}

void process() {
    cin >> n >> k;
    for (int i = 0; i < 2; ++i) {
        for (int j = 0; j < 3; ++j) {
            cin >> w[i][j];
        }
    }
    dfs(-1, 0, 0);
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
