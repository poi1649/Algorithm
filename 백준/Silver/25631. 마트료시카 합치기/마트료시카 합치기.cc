#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, x;

unordered_map<int, int> mp;

void process() {
    cin >> n;
    int max = 0;
    for (int i = 0; i < n; ++i) {
        cin >> x;
        if (mp.count(x)) {
            mp[x]++;
        } else {
            mp[x] = 1;
        }
        max = std::max(max, mp[x]);
    }
    cout << max << "\n";
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
