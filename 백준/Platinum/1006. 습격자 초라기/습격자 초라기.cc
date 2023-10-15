#include <bits/stdc++.h>

using namespace std;
using ll = long long;

int n, w, t, arr[20001], a[10010], b[10010], c[10010];

bool ck(int i1, int i2) {
    return (arr[i1] + arr[i2]) <= w;
}

void process() {
    cin >> n >> w;
    fill(a, a + n + 1, 0);
    fill(b, b + n + 1, 0);
    fill(c, c + n + 1, 0);
    fill(arr, arr + 2 * n, 987654321);

    for (int i = 0; i < 2 * n; ++i) {
        cin >> t;
        arr[i] = t;
    }
    int ans = 987654321;
    a[0] = 1;
    b[0] = 1;
    if (n == 1) {
        if (ck(0, 1)) {
            cout << 1 << '\n';
            return;
        }
        cout << 2 << '\n';
        return;
    }
    for (int i = 1; i <= n; ++i) {
        c[i] = min(a[i - 1] + 1, b[i - 1] + 1);
        int i2 = i + n;
        if (ck(i - 1, i2 - 1)) {
            c[i] = min(c[i], c[i - 1] + 1);
        }
        if (i > 1 && ck(i - 1, i - 2) && ck(i2 - 1, i2 - 2)) {
            c[i] = min(c[i], c[i - 2] + 2);
        }
        a[i] = c[i] + 1;
        if (ck(i, i - 1)) {
            a[i] = min(a[i], b[i - 1] + 1);
        }
        b[i] = c[i] + 1;
        if (ck(i2, i2 - 1)) {
            b[i] = min(b[i], a[i - 1] + 1);
        }
    }
    ans = min(ans, c[n]);
    if (ck(0, n - 1)) {
        a[1] = 2;
        b[1] = 2;
        if (ck(n + 1, n)) b[1] = 1;
        c[1] = 1;
        for (int i = 2; i <= n; ++i) {
            c[i] = min(a[i - 1] + 1, b[i - 1] + 1);
            int i2 = i + n;
            if (ck(i - 1, i2 - 1)) {
                c[i] = min(c[i], c[i - 1] + 1);
            }
            if (ck(i - 1, i - 2) && ck(i2 - 1, i2 - 2)) {
                c[i] = min(c[i], c[i - 2] + 2);
            }
            a[i] = c[i] + 1;
            if (ck(i, i - 1)) {
                a[i] = min(a[i], b[i - 1] + 1);
            }
            b[i] = c[i] + 1;
            if (ck(i2, i2 - 1)) {
                b[i] = min(b[i], a[i - 1] + 1);
            }
        }
        ans = min(ans, b[n - 1] + 1);
    }
    if (ck(n, 2 * n - 1)) {
        a[1] = 2;
        if (ck(0, 1)) a[1] = 1;
        b[1] = 2;
        c[1] = 1;
        for (int i = 2; i <= n; ++i) {
            c[i] = min(a[i - 1] + 1, b[i - 1] + 1);
            int i2 = i + n;
            if (ck(i - 1, i2 - 1)) {
                c[i] = min(c[i], c[i - 1] + 1);
            }

            if (ck(i - 1, i - 2) && ck(i2 - 1, i2 - 2)) {
                c[i] = min(c[i], c[i - 2] + 2);
            }
            a[i] = c[i] + 1;
            if (ck(i, i - 1)) {
                a[i] = min(a[i], b[i - 1] + 1);
            }
            b[i] = c[i] + 1;
            if (ck(i2, i2 - 1)) {
                b[i] = min(b[i], a[i - 1] + 1);
            }
        }
        ans = min(ans, a[n - 1] + 1);
    }
    if (ck(0, n - 1) && ck(n, 2 * n - 1)) {
        a[1] = 1;
        b[1] = 1;
        c[1] = 0;
        for (int i = 2; i <= n; ++i) {
            c[i] = min(a[i - 1] + 1, b[i - 1] + 1);
            int i2 = i + n;
            if (ck(i - 1, i2 - 1)) {
                c[i] = min(c[i], c[i - 1] + 1);
            }

            if (ck(i - 1, i - 2) && ck(i2 - 1, i2 - 2)) {
                c[i] = min(c[i], c[i - 2] + 2);
            }
            a[i] = c[i] + 1;
            if (ck(i, i - 1)) {
                a[i] = min(a[i], b[i - 1] + 1);
            }
            b[i] = c[i] + 1;
            if (ck(i2, i2 - 1)) {
                b[i] = min(b[i], a[i - 1] + 1);
            }
        }
        ans = min(ans, c[n - 1] + 2);
    }
    cout << ans << '\n';
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o;
    cin >> o;
    while (o--) {
        process();
    }
    return 0;
}
