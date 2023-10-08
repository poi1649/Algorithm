#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m, k, v, c;
vector<pair<int, int>> a;

void process() {
    cin >> n >> m >> k;
    for (int i = 0; i < k; ++i) {
        cin >> v >> c;
        a.emplace_back(v, c);
    }
    ll l = 0;
    ll r = 2147483648;
    sort(a.begin(), a.end(), greater<>());
    while (l < r) {
        ll mid = (l + r) / 2;
        ll cnt = 0;
        ll tm = 0;
        for (auto &i : a) {
           if (i.second <= mid) {
               cnt++;
               tm+=i.first;
           }
           if (cnt == n) {
               break;
           }
        }
        if (tm >= m && cnt == n) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    if (l == 2147483648) {
        cout << -1 << "\n";
        return;
    }
    cout << r << "\n";
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
