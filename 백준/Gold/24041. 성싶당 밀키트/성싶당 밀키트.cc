#include <bits/stdc++.h>

using namespace std;
using ll = long long;


int n, g, k;

ll s[200001];
ll l[200001];
bool o[200001];

ll find_g(ll i, ll day) {
    ll d;
    if (day - l[i] > 1) {
        d = day - l[i];
    } else {
        d = 1;
    }
    return s[i] * d;
}

void process() {
    cin >> n >> g >> k;
    int q;
    for (int i = 0; i < n; ++i) {
        cin >> s[i];
        cin >> l[i];
        cin >> q;
        q == 1 ? o[i] = true : o[i] = false;
    }
    ll maximum = 2e9 + 1;
    ll left = 1;
    ll right = maximum;
    vector<ll> v;
    while (left < right) {
        ll mid = (left + right) / 2;
        ll sum = 0;
        v.clear();
        for (int i = 0; i < n; ++i) {
            ll g_i = find_g(i, mid);
            sum += g_i;
            if (o[i]) {
                v.push_back(g_i);
            }
        }
        sort(v.begin(), v.end(), greater<ll>());
        for (int i = 0; i < min(k, (int) v.size()); ++i) {
            sum -= v[i];
        }
        if (sum > g) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    cout << left - 1 << endl;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int c = 1;
//    cin >> o;
    while (c--) {
        process();
    }
    return 0;
}
