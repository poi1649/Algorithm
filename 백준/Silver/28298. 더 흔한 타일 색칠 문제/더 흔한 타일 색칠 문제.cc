#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m, k;

void process() {
    cin >> n >> m >> k;
    int alpha[k][k][26];
    int inputs[n][m];
    memset(alpha, 0, sizeof(alpha));
    memset(inputs, 0, sizeof(inputs));
    string s;
    for (int i = 0; i < n; ++i) {
        cin >> s;
        for (int j = 0; j < m; ++j) {
            inputs[i][j] = s[j] - 'A';
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            alpha[i % k][j % k][inputs[i][j]]++;
        }
    }

    int ans = 0;
    int maxes[k][k];
    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < k; ++j) {
            maxes[i][j] = 0;
            for (int l = 0; l < 26; ++l) {
                if (alpha[i][j][l] > alpha[i][j][maxes[i][j]]) {
                    maxes[i][j] = l;
                }
            }
        }
    }
    int total_block = (n / k) * (m / k);
    for (int i = 0; i < k; ++i) {
        for (int j = 0; j < k; ++j) {
            ans += total_block - alpha[i][j][maxes[i][j]];
        }
    }
    cout << ans << "\n";
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << (char) (maxes[i % k][j % k] + 'A');
        }
        cout << "\n";
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
