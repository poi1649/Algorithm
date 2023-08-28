#include <bits/stdc++.h>

using namespace std;


struct Node {
    int length;
    int link;
    map<char, int> next;
    bool flag[51];
};

class Eertree {
public:
    vector <Node> tree;
    string s;
    int last;

    Eertree() {
        tree.push_back({-1, 0, {}});
        tree.push_back({0, 0, {}});
        last = 1;
    }

    int getLink(int v) {
        while (v && s[s.size() - tree[v].length - 2] != s.back()) {
            v = tree[v].link;
        }
        return v;
    }

    void addCharacter(char ch, int n) {
        s += ch;
        int curr = getLink(last);

        if (!tree[curr].next.count(ch)) {
            int len = tree[curr].length + 2;
            int link = tree[getLink(tree[curr].link)].next[ch];
            tree.push_back({len, link ? link : 1, {}, {}});
            tree[curr].next[ch] = tree.size() - 1;
            tree[tree.size() - 1].flag[n] = true;
        }
        else {
            tree[tree[curr].next[ch]].flag[n] = true;
        }

        last = tree[curr].next[ch];
    }

};

int main() {
    int n;
    cin >> n;
    vector <string> strings(n);


    for (int i = 0; i < n; i++) {
        cin >> strings[i];
    }

    Eertree eertree;
    int firstSize = 0;
    for (int i = 0; i < n; i++) {
        eertree.s = "";
        eertree.last = 1;
        for (int j = 0; j < strings[i].size(); j++) {
            eertree.addCharacter(strings[i][j], i);
        }
        if (i == 0) {
            firstSize = eertree.tree.size();
        }
    }

    for (char i : strings[0]) {
        eertree.s = "";
        eertree.last = 1;
        eertree.addCharacter(i, 0);
    }
    int maxSize = 0;
    for (int i = firstSize - 1; i > 1; i--) {
        bool flag = true;
        for (int j = 0; j < n; ++j) {
            if (!eertree.tree[i].flag[j]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            maxSize = max(maxSize, eertree.tree[i].length);
        }
    }
    if (maxSize > 0) {
        cout << maxSize;
        return 0;
    }
    cout << 0 << endl;
    return 0;
}
