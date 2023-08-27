#include <bits/stdc++.h>

using namespace std;

struct Node {
    int length;
    int link;
    map<char, int> next;

};

class Eertree {
public:
    vector<Node> tree;
    string s;
    int last;

    int getLink(int v) {
        while (v && s[s.size() - tree[v].length - 2] != s.back()) {
            v = tree[v].link;
        }
        return v;
    }

public:
    Eertree() {
        tree.push_back({-1, 0, {}});
        tree.push_back({0, 0, {}});
        last = 1;
    }

    void addCharacter(char ch) {
        s += ch;
        int curr = getLink(last);

        if (!tree[curr].next.count(ch)) {
            int len = tree[curr].length + 2;
            int link = tree[getLink(tree[curr].link)].next[ch];
            tree.push_back({len, link ? link : 1, {}});
            tree[curr].next[ch] = tree.size() - 1;
        }

        last = tree[curr].next[ch];
    }


    int totalPalindromes() const {
        return tree.size() - 2;
    }
};

int minPalindromicPartition(const string& s) {
    Eertree eertree;
    int n = s.length();
    vector<int> dp(n, INT_MAX);

    for (int i = 0; i < n; i++) {
        eertree.addCharacter(s[i]);
        int v = eertree.last;
        while (v > 1) {
            int palindromeLen = eertree.tree[v].length;
            int startIndex = i - palindromeLen + 1;
            if (startIndex == 0) {
                dp[i] = 1;
            } else {
                dp[i] = min(dp[i], dp[startIndex - 1] + 1);
            }
            v = eertree.tree[v].link;
        }
    }

    return dp[n-1];
}
int main() {
    string s;
    cin >> s;
    cout << minPalindromicPartition(s);
    return 0;
}
