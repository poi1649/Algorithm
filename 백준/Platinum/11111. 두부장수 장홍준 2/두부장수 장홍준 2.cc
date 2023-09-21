#include <bits/stdc++.h>

using namespace std;
using ll = long long;

struct Edge {
    int from, to, f, c, w;
    Edge *rev;

    Edge(int from, int to, int c, int w) : from(from), to(to), c(c), f(0), w(w) {}

    int remain() { return c - f; }

    void add_flow(int f) {
        this->f += f;
        rev->f -= f;
    }
};

char inputs[51][51];
vector<vector<Edge *>> g(2502);
vector<pair<int, int>> directions = {{0,  1},
                                     {0,  -1},
                                     {1,  0},
                                     {-1, 0}};
int s = 0, t = 2501, total_w = 0;
int min_weight = 1e9;
int get_score(char a, char b) {
    if (a > b) {
        swap(a, b);
    }
    if (a == 'A') {
        if (b == 'A') {
            return 10;
        }
        if (b == 'B') {
            return 8;
        }
        if (b == 'C') {
            return 7;
        }
        if (b == 'D') {
            return 5;
        }
        if (b == 'F') {
            return 1;
        }
    }
    if (a == 'B') {
        if (b == 'B') {
            return 6;
        }
        if (b == 'C') {
            return 4;
        }
        if (b == 'D') {
            return 3;
        }
        if (b == 'F') {
            return 1;
        }
    }
    if (a == 'C') {
        if (b == 'C') {
            return 3;
        }
        if (b == 'D') {
            return 2;
        }
        if (b == 'F') {
            return 1;
        }
    }
    if (a == 'D') {
        if (b == 'D') {
            return 2;
        }
        if (b == 'F') {
            return 1;
        }
    }
    return 0;
}

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
    bool inQ[2502];
    memset(inQ, false, sizeof(inQ));
    while (true) {
        q.push(s);
        inQ[s] = true;
        vector<int> dist(2502, 1e9);
        vector<Edge *> preE(2502);
        vector<int> preV(2502, -1);
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
            total_w += preE[i]->w;
        }
        min_weight = min(min_weight, total_w);
        res++;
    }
    return res;
}

int n, m;

void process() {
    cin >> n >> m;
    string temp;
    for (int i = 1; i <= n; ++i) {
        cin >> temp;
        for (int j = 1; j <= m; ++j) {
            inputs[i][j] = temp[j - 1];
        }
    }
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            if (((i + j) % 2) == 0) {
                add_edge(s, (i - 1) * m + j, 1, 0);
                for (auto &direct: directions) {
                    int x = i + direct.first;
                    int y = j + direct.second;
                    if (x >= 1 && x <= n && y >= 1 && y <= m) {
                        add_edge((i - 1) * m + j, (x - 1) * m + y, 1, -get_score(inputs[i][j], inputs[x][y]));
                    }
                }
                continue;
            }
            add_edge((i - 1) * m + j, t, 1, 0);
        }
    }
    mcmf();
    if (min_weight == 1e9) {
        cout << 0 << "\n";
        return;
    }
    cout << -min_weight << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o =1;
//    cin >> o;
    while (o--) {
        process();
    }
}
