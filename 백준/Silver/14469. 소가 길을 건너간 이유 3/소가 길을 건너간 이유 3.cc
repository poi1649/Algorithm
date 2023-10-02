#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, m, k;
void process() {
    cin >> n;
    vector<pair<int, int>> a;
    for (int i = 0; i < n; ++i) {
        int x, y;
        cin >> x >> y;
        a.emplace_back(x, y);
    }
    sort(a.begin(), a.end());

    int cur = 0;
    int i = 0;
    while (cur <= INT32_MAX) {
        if (i == n) {
            cout << cur;
            return;
        }
        if (cur >= a[i].first) {
            cur += a[i].second;
            ++i;
            continue;
        }
        cur++;
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
