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

int n, m, k, s = 1, t = 1101;
vector<vector<Edge *>> g(301);
int lv[301];
int work[301];
bool adj_s[301];
vector<Edge *> edges;

void add_edge(int from, int to, int c) {
    Edge *e1 = new Edge(from, to, c);
    Edge *e2 = new Edge(to, from, 0);
    edges.push_back(e1);
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
        if (x == t) return true;
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

void separate(int start) {
    memset(adj_s, false, sizeof(adj_s));
    queue<int> q;
    q.push(start);
    adj_s[start] = true;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (auto e: g[x]) {
            if (!adj_s[e->to] && e->remain() > 0) {
                adj_s[e->to] = true;
                q.push(e->to);
            }
        }
    }
}

void process() {
    cin >> n >> m;
    t = n;
    for (int i = 1; i <= m; ++i) {
        int x, y, z;
        cin >> x >> y >> z;
        add_edge(x, y, z);
    }
    max_flow();
    int sz = edges.size();
    bool isChecked[sz];

    int result = 0;
    memset(isChecked, false, sizeof(isChecked));
    for (int i = 1; i <= n; ++i) {
        separate(i);
        for (int j = 0; j < sz; ++j) {
            auto edge = edges[j];
            if (isChecked[j]) continue;
            if (edge->c != 0 && edge->remain() == 0 && adj_s[edge->from] ^ adj_s[edge->to]) {
                result++;
                isChecked[j] = true;
            }
        }
    }


    cout << result << '\n';
    edges.clear();
    memset(adj_s, false, sizeof(adj_s));
    for (int i = 0; i <= n; ++i) {
        g[i].clear();
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o;
    cin >> o;
    while (o--) {
        process();
    }
}
