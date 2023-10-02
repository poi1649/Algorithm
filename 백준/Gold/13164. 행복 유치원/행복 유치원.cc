#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, k, b;

void process() {
    cin >> n >> k;
    vector<int> a;
    int first;
    cin >> first;
    ll sum = 0;
    for (int i = 0; i < n - 1; ++i) {
        int x;
        cin >> x;
        a.push_back(x - first);
        sum += x - first;
        first = x;
    }
    sort(a.begin(), a.end(), greater<>());
    for (int i = 0; i < k - 1; ++i) {
        sum -= a[i];
    }
    cout << sum;
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
