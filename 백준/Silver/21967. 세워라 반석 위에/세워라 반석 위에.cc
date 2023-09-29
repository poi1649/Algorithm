#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, inputs[1000000];

void process() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> inputs[i];
    }
    int ans = 0;
    for (int i = 1; i <= 8; ++i) {
        int min = i;
        int max = i + 2;
        int count = 0;
        for (int j = 0; j < n; ++j) {
            if (inputs[j] >= min && inputs[j] <= max) {
                count++;
                ans = std::max(ans, count);
                continue;
            }
            ans = std::max(ans, count);
            count = 0;
        }
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
