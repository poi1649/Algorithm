#include <bits/stdc++.h>

using namespace std;

int n, m, s = 0, t = 2501;
int lv[2502], work[2502];


struct Edge {
    int from, to, cap, flw;
    Edge *rev;

    Edge(int u, int v, int c) : from(u), to(v), cap(c), flw(0) {}

    int remain() {
        return cap - flw;
    }

    void add_flow(int amount) {
        flw += amount;
        rev->flw -= amount;
    }
};

vector<Edge *> g2[2502];

void add_edge(int from, int to, int capacity) {
    Edge *e = new Edge(from, to, capacity);
    Edge *revE = new Edge(to, from, 0);
    e->rev = revE, revE->rev = e;
    g2[from].push_back(e), g2[to].push_back(revE);
}

bool bfs() {
    memset(lv, -1, sizeof lv);
    queue<int> q;
    q.push(s);
    lv[s] = 0;
    while (!q.empty()) {
        int now = q.front();
        q.pop();
        for (auto nxtE: g2[now]) {
            int nxt = nxtE->to;
            if (lv[nxt] == -1 && nxtE->remain() > 0) {
                lv[nxt] = lv[now] + 1;
                q.push(nxt);
            }
        }
    }
    return lv[t] != -1;
}

int dfs(int now, int flow) {
    if (now == t) {
        return flow;
    }
    for (int &i = work[now]; i < g2[now].size(); i++) {
        Edge *&nextE = g2[now][i];
        int nxt = nextE->to;
        if (lv[nxt] == lv[now] + 1 && nextE->remain() > 0) {
            int f = dfs(nxt, min(flow, nextE->remain()));
            if (f > 0) {
                nextE->add_flow(f);
                return f;
            }
        }
    }
    return 0;
}

int find_max_flow() {
    int ret = 0;
    while (bfs()) {
        memset(work, 0, sizeof work);
        int f = dfs(s, 1e9);
        if (f > 0) {
            ret += f;
        }
    }
    return ret;
}

int c;
int tmp;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> c;
    for (int x = 0; x < c; ++x) {
        cin >> n >> m;
        int sum = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                cin >> tmp;
                sum += tmp;
                if ((i + j) % 2 == 0) {
                    add_edge(s, (i - 1) * m + j, tmp);
                    if (i < n) {
                        add_edge((i - 1) * m + j, i * m + j, 1e9);
                    }
                    if (i > 1) {
                        add_edge((i - 1) * m + j, (i - 2) * m + j, 1e9);
                    }
                    if (j < m) {
                        add_edge((i - 1) * m + j, (i - 1) * m + j + 1, 1e9);
                    }
                    if (j > 1) {
                        add_edge((i - 1) * m + j, (i - 1) * m + j - 1, 1e9);
                    }
                    continue;
                }
                add_edge((i - 1) * m + j, t, tmp);
            }
        }
        int max_flow = find_max_flow();
        int ret = sum - max_flow;
        cout << ret << "\n";
        for (auto &v: g2) {
            v.clear();
        }
    }
    return 0;
}