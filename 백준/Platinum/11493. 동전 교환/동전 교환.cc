#include <bits/stdc++.h>

using namespace std;

struct Edge {
    int from, to, f, c, w;
    Edge *rev;

    Edge(int from, int to, int c, int w) : from(from), to(to), c(c), f(0), w(w) {}

    int remain() { return c - f; }

    void add_flow(int x) {
        f += x;
        rev->f -= x;
    }
};

int n, m, s = 0, t = 801;
int total_weight = 0;
int a, b;
int dist[802];
Edge* preE[802];
int preV[802];
vector<vector<Edge *>> g(802);

void add_edge(int from, int to, int c, int w) {
    Edge *e1 = new Edge(from, to, c, w);
    Edge *e2 = new Edge(to, from, 0, -w);
    e1->rev = e2;
    e2->rev = e1;
    g[from].push_back(e1);
    g[to].push_back(e2);
}



int mcmf() {

    int res = 0;
    queue<int> q;

    bool inQ[802];
    memset(inQ, false, sizeof(inQ));
    while (true) {
        q.push(s);
        inQ[s] = true;
        memset(preV, -1, sizeof(preV));
        fill(dist, dist + 802, 1e9);
        dist[s] = 0;
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            inQ[curr] = false;
            for (auto &e: g[curr]) {
                if (e->remain() > 0 && dist[e->to] > dist[curr] + e->w) {
                    preE[e->to] = e;
                    preV[e->to] = curr;
                    dist[e->to] = dist[curr] + e->w;
                    if (!inQ[e->to]) {
                        q.push(e->to);
                        inQ[e->to] = true;
                    }
                }
            }
        }


        if (preV[t] == -1) break;

        int flow = 1e9;
        for (int i = t; i != s; i = preV[i]) {
            flow = min(flow, preE[i]->remain());
        }
        for (int i = t; i != s; i = preV[i]) {
            preE[i]->add_flow(flow);
            total_weight += flow * preE[i]->w;
        }
        res++;
    }
    return res;
}


void process() {
    cin >> n >> m;
    for (int i = 1; i <= m; ++i) {
        cin >> a >> b;
        add_edge(a, b, 1e9, 1);
        add_edge(b, a, 1e9, 1);
    }
    for (int i = 1; i <= n; ++i) {
        cin >> a;
        if (a == 0) {
            add_edge(s, i, 1, 0);
        }
    }
    for (int i = 1; i <= n; ++i) {
        cin >> a;
        if (a == 0) {
            add_edge(i, t, 1, 0);
        }
    }
    mcmf();
    cout << total_weight << "\n";

    for (int i = 0; i <= n + 1; ++i) {
        g[i].clear();
    }
    total_weight = 0;
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
