#include <bits/stdc++.h>

using namespace std;


int t, n, a, b;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> t;
    for (int x = 0; x < t; ++x) {
        cin >> n;
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            cin >> a >> b;
            arr[i] = {a, b};
        }
        sort(arr.begin(), arr.end());
        int min = arr[0].second;
        int ans = 1;
        for (int i = 1; i < arr.size(); ++i) {
            if (arr[i].second <= min) {
                min = arr[i].second;
                ans++;
            }
        }
        cout << ans << '\n';
    }
}
