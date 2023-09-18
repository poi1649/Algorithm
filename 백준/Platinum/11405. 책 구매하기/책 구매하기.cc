#include <bits/stdc++.h>

using namespace std;

int n, m, k, s = 0, t = 201;
int total_weight = 0;
int a, b;
int fl[202][202];
int cap[202][202];
int w[202][202];
int pre[202];
int dist[202];
vector<vector<int>> g(202);


int mcmf() {

    int res = 0;
    queue<int> q;

    bool inQ[202];
    memset(inQ, false, sizeof(inQ));
    while (true) {
        q.push(s);
        inQ[s] = true;
        memset(pre, -1, sizeof(pre));
        fill(dist, dist + 202, 1e9);
        dist[s] = 0;
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            inQ[curr] = false;
            for (auto &e: g[curr]) {
                if (cap[curr][e] - fl[curr][e] > 0 && dist[e] > dist[curr] + w[curr][e]) {
                    pre[e] = curr;
                    dist[e] = dist[curr] + w[curr][e];
                    if (!inQ[e]) {
                        q.push(e);
                        inQ[e] = true;
                    }
                }
            }
        }

        if (pre[t] == -1) break;

        int flow = 1e9;
        for (int i = t; i != s; i = pre[i]) {
            flow = min(flow, cap[pre[i]][i] - fl[pre[i]][i]);
        }
        for (int i = t; i != s; i = pre[i]) {
            fl[pre[i]][i] += flow;
            fl[i][pre[i]] -= flow;
            total_weight += flow * w[pre[i]][i];
        }
        res += flow;
    }
    return res;
}


void process() {
    cin >> n >> m;
    for (int i = 1; i <= n; ++i) {
        int x;
        cin >> x;
        g[i].push_back(s);
        g[s].push_back(i);
        cap[s][i] = x;
    }
    for (int i = 1; i <= m; ++i) {
        int x;
        cin >> x;
        g[i + 100].push_back(t);
        g[t].push_back(i + 100);
        cap[i + 100][t] = x;
    }
    for (int i = 1; i <= m; ++i) {
        for (int j = 1; j <= n; ++j) {
            cin >> a;
            g[j].push_back(i + 100);
            g[i + 100].push_back(j);
            cap[j][i + 100] = 1e9;
            w[j][i + 100] = a;
            w[i + 100][j] = -a;
        }
    }
    int flw = mcmf();
//    cout << flw << "\n";
    cout << total_weight << "\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int o = 1;
//    cin >> o;
    while (o--) {
        process();
    }
}
