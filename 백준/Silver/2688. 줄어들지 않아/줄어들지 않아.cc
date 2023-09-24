#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m, k;

ll sums[64][10];

void init() {
    for (int i = 0; i < 10; ++i) {
        sums[0][i] = i + 1;
    }
}

ll find_sum(int l) {
    if (sums[l][0] != 0) {
        return sums[l][9];
    }
    if (sums[l - 1][0] == 0) {
        find_sum(l - 1);
    }
    if (l == 0) {
        return 1;
    }
    ll res = 0;
    for (int i = 0; i < 10; ++i) {
        res += sums[l - 1][i];
        sums[l][i] = res;
    }
    return res;
}

void process() {
    cin >> n;
    cout << find_sum(n - 1) << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o;
    cin >> o;
    init();
    while (o--) {
        process();
    }
    return 0;
}
