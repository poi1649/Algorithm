#include <iostream>
#include <string>

using namespace std;
#define ll long long

const int mod = 1000000007;

struct mat {
    ll a, b, c, d;

    mat operator*(mat &other) {
        ll new_a = ((a * other.a) % mod + (b * other.c) % mod) % mod;
        ll new_b = ((a * other.b) % mod + (b * other.d) % mod) % mod;
        ll new_c = ((c * other.a) % mod + (d * other.c) % mod) % mod;
        ll new_d = ((c * other.b) % mod + (d * other.d) % mod) % mod;
        return {new_a, new_b, new_c, new_d};
    }
};

mat mat_mul(ll n, mat A) {
    if (n == 1) return A;
    mat temp = mat_mul(n / 2, A);
    if (n % 2 == 0) return temp * temp;
    else return temp * temp * A;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    ll n;
    cin >> n;
    mat base = {4, -1, 1, 0};

    if (n % 2 == 1) {
        cout << 0 << endl;
    } else {
        ll k = n / 2;
        if (k == 1) {
            cout << 3 << endl;
            return 0;
        }
        mat zero = {3, 0, 1, 0};
        mat result = mat_mul(k - 1, base) * zero;
        cout << (result.a + mod) % mod;
    }
    return 0;
}