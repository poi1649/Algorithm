#include <iostream>
#include <deque>

using namespace std;
typedef long long ll;

int n, k;
ll sums[100001];
ll dp[100001];
deque<int> dq;

ll find_value(int i) {
    auto result = dp[i - 1] - sums[i];
    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> k;

    int efficiency;
    cin >> efficiency;
    sums[1] += efficiency;
    dp[1] = sums[1];
    dq.push_back(1);

    for (int i = 2; i <= n; i++) {
        cin >> efficiency;
        sums[i] = sums[i - 1] + efficiency;

        while (!dq.empty() && dq.front() + k < i) {
            dq.pop_front();
        }

        while (!dq.empty() && find_value(dq.back()) <= find_value(i)) {
            dq.pop_back();
        }

        dq.push_back(i);
        if (i <= k) {
            dp[i] = sums[i];
            continue;
        }
        dp[i] = sums[i] + find_value(dq.front());

    }

    cout << dp[n];
    return 0;
}
