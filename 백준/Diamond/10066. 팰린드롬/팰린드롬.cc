#include <bits/stdc++.h>

using namespace std;


struct Node {
    int length;
    int link;
    map<char, int> next;
    long long appeared = 0;
    long long update = 0;
};

class Eertree {
public:
    vector<Node> tree;
    string s;
    int last;

    Eertree() {
        tree.push_back({-1, 0, {}});
        tree.push_back({0, 0, {}});
        last = 1;
    }

    int get_link(int v) {
        while (v && s[s.size() - tree[v].length - 2] != s.back()) {
            v = tree[v].link;
        }
        return v;
    }

    void addCharacter(char ch) {
        s += ch;
        int curr = get_link(last);

        if (!tree[curr].next.count(ch)) {
            int len = tree[curr].length + 2;
            int link = tree[get_link(tree[curr].link)].next[ch];
            tree.push_back({len, link ? link : 1, {}, 0, 0});
            tree[curr].next[ch] = tree.size() - 1;
        }

        last = tree[curr].next[ch];
        tree[last].update += 1;
    }

    void lazy_update() {
        for (int i = tree.size() - 1; i > 1; --i) {
            tree[tree[i].link].update += tree[i].update;
            tree[i].appeared += tree[i].update * tree[i].length;
            tree[i].update = 0;
        }
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    string n;
    cin >> n;
    Eertree eertree;
    for (char i: n) {
        eertree.addCharacter(i);
    }
    long long max_val = 0;

    eertree.lazy_update();
    for (int i = 2; i < eertree.tree.size(); ++i) {
        max_val = max(max_val, eertree.tree[i].appeared);
    }
    cout << max_val;

    return 0;
}
