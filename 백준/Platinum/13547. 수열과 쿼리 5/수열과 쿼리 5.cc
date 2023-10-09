#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m, input[100001], answers[100001];

void process() {
    cin >> n;
    for (int i = 1; i <= n; ++i) {
        cin >> input[i];
    }
    vector<pair<int, pair<int, int>>> q;
    cin >> m;
    for (int i = 1; i <= m; ++i) {
        int l, r;
        cin >> l >> r;
        q.push_back({i, {l, r}});
    }
    sort(q.begin(), q.end(), [](const pair<int, pair<int, int>> &a, const pair<int, pair<int, int>> &b) {
        if (a.second.first / 320 != b.second.first / 320) {
            return a.second.first / 320 < b.second.first / 320;
        }
        return a.second.second < b.second.second;
    });
    unordered_map<int, int> s;
    auto fi = q.front();
    for (int i = fi.second.first; i <= fi.second.second; ++i) {
        if (s.find(input[i]) == s.end()) {
            s.insert({input[i], 1});
        } else {
            s[input[i]]++;
        }
    }
    int l = fi.second.first, r = fi.second.second;
    int ans = s.size();
    answers[fi.first] = s.size();
    q.erase(q.begin());
    for (auto &i: q) {
        while (r < i.second.second) {
            ++r;
            s[input[r]]++;
            if (s[input[r]] == 1) {
                ans++;
            }
        }
        while (r > i.second.second) {
            if (--s[input[r]] == 0) {
                ans--;
            }
            --r;
        }
        while (l < i.second.first) {
            if (--s[input[l]] == 0) {
                ans--;
            }
            ++l;
        }
        while (l > i.second.first) {
            --l;
            s[input[l]]++;
            if (s[input[l]] == 1) {
                ans++;
            }
        }
        answers[i.first] = ans;
    }
    for (int i = 1; i <= m; ++i) {
        cout << answers[i] << "\n";
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
