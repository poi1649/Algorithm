#include <bits/stdc++.h>

using namespace std;

int n, d, k, s = 0, t = 301;
struct Edge {
    int from, to, f, c;
    Edge* rev;
    Edge(int from, int to, int c) : from(from), to(to), c(c), f(0) {}
    int remain() { return c - f; }
    void add_flow(int x) {
        f += x;
        rev->f -= x;
    }
};
vector<vector<Edge*>> g(302);

void add_edge(int from, int to, int f) {
    Edge* e1 = new Edge(from, to, f);
    Edge* e2 = new Edge(to, from, 0);
    e1->rev = e2;
    e2->rev = e1;
    g[from].push_back(e1);
    g[to].push_back(e2);
}
int lv[302];

bool bfs() {
    memset(lv, -1, sizeof(lv));
    queue<int> q;
    q.push(s);
    lv[s] = 0;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (auto e : g[x]) {
            if (lv[e->to] == -1 && e->remain() > 0) {
                lv[e->to] = lv[x] + 1;
                q.push(e->to);
            }
        }
    }
    return lv[t] != -1;
}

int work[302];
int dfs(int cur, int f) {
    if (cur == t) return f;
    for (int& i = work[cur]; i < g[cur].size(); ++i) {
        Edge* e = g[cur][i];
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
            memset(work, 0, sizeof(work));
        }
    }
    return ret;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> k >> d;
    for (int i = 1; i <= d; ++i) {
        int x;
        cin >> x;
        add_edge(i + 200, t, x);
    }
    for (int i = 1; i <= n; ++i) {
        add_edge(s, i, k);
        int x;
        cin >> x;
        for (int j = 0; j < x; ++j) {
            int y;
            cin >> y;
            add_edge(i, y + 200, 1);
        }
    }
    cout << max_flow() << '\n';
}
