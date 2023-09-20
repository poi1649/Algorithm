#include <bits/stdc++.h>

using namespace std;

int n, m, a;
vector<pair<int, int>> v;

void process() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> a >> m;
        v.push_back({a, m});
    }
    std::sort(v.begin(), v.end());
    int maximum = 0;
    int ans = 0;
    for (int i = 0; i < n; ++i) {
        int price = v[i].first;
        int temp = 0;
        for (int j = i; j < n; ++j) {
            if ((price - (v[j].second)) > 0) {
                temp += price - v[j].second;
            }
        }
        if (temp > maximum) {
            maximum = temp;
            ans = price;
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
}
