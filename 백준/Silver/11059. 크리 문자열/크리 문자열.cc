#include <bits/stdc++.h>

using namespace std;
using ll = long long;

string s;
int sums[1001];

void process() {
    cin >> s;
    for (int i = 1; i <= s.size(); ++i) {
        sums[i] = sums[i - 1] + (s[i - 1] - '0');
    }

    int ans = s.size() / 2;

    while (true) {
        bool ok = false;
        for (int i = 0; i + ans * 2 <= s.size(); ++i) {
            if (sums[i + ans] - sums[i] == sums[i + ans * 2] - sums[i + ans]) {
                ok = true;
                break;
            }
        }
        if (ok) {
            break;
        }
        --ans;
    }
    cout << ans * 2;
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
