#include <bits/stdc++.h>
using namespace std;

int dp[15 * 15][1 << 14], n, m;
int MOD = 9901;

int check(int k, int s) {

    if (k == n * m && !s) {
        return 1;
    }

    if (k >= n * m) {
        return 0;
    }

    int &temp = dp[k][s];

    if (~temp) {
        return temp;
    }

    temp = 0;

    if (s & 1) {
        temp = check(k + 1, s >> 1);
    } else {
        temp += check(k + 1, (s >> 1) | (1 << (m - 1)));
        if ((k % m) != (m - 1) && !(s & 2)) {
            temp += check(k + 2, s >> 2);
        }
    }
    return temp % MOD;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    memset(dp, -1, sizeof dp);
    cout << check(0, 0);
}