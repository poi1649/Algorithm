#include <bits/stdc++.h>

using namespace std;

int n, m, a;
bool arr[1001][1001];

void process() {
    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        cin >> a;
        arr[i][0] = (1 == a);
    }
    for (int i = 1; i <= m; ++i) {
        cin >> a;
        arr[0][i] = (1 == a);
    }
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            arr[i][j] = (arr[i - 1][j] ^ arr[i][j - 1]);
        }
    }
    cout << (arr[n][m] ? 1 : 0) << '\n';
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
