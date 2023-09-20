#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m;

void process() {
    cin >> n;
    priority_queue<ll, vector<ll>, greater<>> pq;
    for (int i = 0; i < n; ++i) {
        int x;
        cin >> x;
        pq.push(x);
    }
    ll sum = 0;
    while (pq.size() > 1) {
        ll x = pq.top();
        pq.pop();
        ll y = pq.top();
        pq.pop();
        sum += x + y;
        pq.push(x + y);
    }
    cout << sum << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o;
    cin >> o;
    while (o--) {
        process();
    }
}
