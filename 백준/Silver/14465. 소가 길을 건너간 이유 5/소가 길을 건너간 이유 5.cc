#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, k, b;

void process() {
    cin >> n >> k >> b;
    bool broken[n + 1];
    memset(broken, false, sizeof(broken));
    for (int i = 1; i <= b; ++i) {
        int x;
        cin >> x;
        broken[x] = true;
    }
    int count = 0;
    for (int i = 1; i <= k; ++i) {
        if (broken[i]) {
            count++;
        }
    }
    int ans = count;
    for (int l = 1, r = k + 1; r <= n; ++l, ++r) {
        if (broken[l]) {
            count--;
        }
        if (broken[r]) {
            count++;
        }
        ans = min(ans, count);
    }
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
