#include <bits/stdc++.h>

using namespace std;

typedef long long ll;
const int maxn = 4000005;
ll inputs[maxn], q[maxn], dp[maxn], sum;
int n, l;

int main() {
    cin >> n >> l;
    for (int i = 1; i <= n; i++) {
        cin >> inputs[i];
        sum += inputs[i];
    }
    int head, tail;
    head = tail = 0;

    for (int i = 1; i <= n; i++) {
        dp[i] = inputs[i] + dp[q[head]];
        while (head <= tail && i - q[head] > l) head++;
        while (head <= tail && dp[i] < dp[q[tail]]) tail--;
        q[++tail] = i;
    }

    ll mn = dp[n];
    for (int i = n - l; i <= n; i++) {
        mn = min(mn, dp[i]);
    }
    cout << sum - mn;
    return 0;
}
