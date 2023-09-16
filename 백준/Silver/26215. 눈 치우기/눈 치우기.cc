#include <bits/stdc++.h>

using namespace std;


int n;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n;
    vector<int> arr(n);
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    int ans = 0;

    while (true) {
        sort(arr.begin(), arr.end(), greater<>());
        if (ans > 1440) {
            cout << -1;
            return 0;
        }
        if (arr[0] == 0) {
            cout << ans;
            return 0;
        }
        ans++;
        arr[0]--;
        if (n > 1 && arr[1] > 0) {
            arr[1]--;
        }
    }
}
