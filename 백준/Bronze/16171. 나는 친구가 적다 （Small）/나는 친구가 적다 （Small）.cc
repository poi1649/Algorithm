#include <bits/stdc++.h>

using namespace std;
using ll = long long;


void process() {
    string s, t;
    cin >> s;
    string input;
    for (char i : s) {
        if (!isdigit(i)) {
            input.push_back(i);
        }
    }
    cin >> t;
    if (strstr(input.c_str(), t.c_str()) == nullptr) {
        cout << "0\n";
        return;
    }
    cout << "1\n";
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
