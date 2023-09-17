#include <bits/stdc++.h>

using namespace std;


struct Edge {
    int from, to, f, c;
    Edge *rev;

    Edge(int from, int to, int c) : from(from), to(to), c(c), f(0) {}

    int remain() { return c - f; }

    void add_flow(int x) {
        f += x;
        rev->f -= x;
    }
};

int n, m, k, s = 0, t = 1101;
vector<vector<Edge *>> g(1102);
int lv[1102];
int work[1102];

void add_edge(int from, int to, int f) {
    Edge *e1 = new Edge(from, to, f);
    Edge *e2 = new Edge(to, from, 0);
    e1->rev = e2;
    e2->rev = e1;
    g[from].push_back(e1);
    g[to].push_back(e2);
}


bool bfs() {
    memset(lv, -1, sizeof(lv));
    queue<int> q;
    q.push(s);
    lv[s] = 0;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (auto e: g[x]) {
            if (lv[e->to] == -1 && e->remain() > 0) {
                lv[e->to] = lv[x] + 1;
                q.push(e->to);
            }
        }
    }
    return lv[t] != -1;
}


int dfs(int cur, int f) {
    if (cur == t) return f;
    for (int &i = work[cur]; i < g[cur].size(); ++i) {
        Edge *e = g[cur][i];
        if (lv[e->to] == lv[cur] + 1 && e->remain() > 0) {
            int flow = dfs(e->to, min(f, e->remain()));
            if (flow > 0) {
                e->add_flow(flow);
                return flow;
            }
        }
    }
    return 0;
}

int max_flow() {
    int ret = 0;
    while (bfs()) {
        int f;
        memset(work, 0, sizeof(work));
        while ((f = dfs(s, 1e9)) > 0) {
            ret += f;
        }
    }
    return ret;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> m >> n;
    for (int i = 1; i <= m; ++i) {
        int x;
        cin >> x;
        add_edge(i + 100, t, x);
    }

    unordered_set<int> st;
    vector<unordered_set<int>> mp(1001);
    for (int i = 1; i <= n; ++i) {
        int x;
        cin >> x;
        st.clear();
        for (int j = 0; j < x; ++j) {
            int y;
            cin >> y;
            for (auto &it: mp[y]) {
                st.insert(it);
            }
            mp[y].insert(i);
            add_edge(i, y + 100, 1e9);
        }
        for (auto &it: st) {
            add_edge(i, it, 1e9);
        }
        cin >> x;
        add_edge(s, i, x);
    }
    int result = max_flow();
    cout << result << '\n';
}
