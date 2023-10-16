#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, m, arr[100001], freq[1000001];
ll answers[100001];
ll answer;
pair<int, pair<int, int>> q[100001];

void add(int cur) {
    answer -= 1LL * freq[arr[cur]] * freq[arr[cur]] * arr[cur];
    freq[arr[cur]]++;
    answer += 1LL * freq[arr[cur]] * freq[arr[cur]] * arr[cur];
}

void remove(int cur) {
    answer -= 1LL * freq[arr[cur]] * freq[arr[cur]] * arr[cur];
    freq[arr[cur]]--;
    answer += 1LL * freq[arr[cur]] * freq[arr[cur]] * arr[cur];
}

void process() {
    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        cin >> arr[i];
    }
    for (int i = 1; i <= m; ++i) {
        int l, r;
        cin >> l >> r;
        q[i] = {i, {l, r}};
    }
    int sqrts = sqrt(n);
    sort(q + 1, q + m + 1, [&](pair<int, pair<int, int>> a, pair<int, pair<int, int>> b) {
        if (a.second.first / sqrts != b.second.first / sqrts) {
            return a.second.first / sqrts < b.second.first / sqrts;
        }
        return a.second.second < b.second.second;
    });
    auto& cur = q[1];
    int l = cur.second.first;
    int r = cur.second.second;
    for (int i = l; i <= r; ++i) {
        add(i);
    }
    answers[cur.first] = answer;

    for (int i = 2; i <= m; ++i) {
        while (l > q[i].second.first) {
            add(--l);
        }
        while (r < q[i].second.second) {
            add(++r);
        }
        while (l < q[i].second.first) {
            remove(l++);
        }
        while (r > q[i].second.second) {
            remove(r--);
        }
        answers[q[i].first] = answer;
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
