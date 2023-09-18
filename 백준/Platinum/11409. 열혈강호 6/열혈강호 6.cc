#include <bits/stdc++.h>

using namespace std;

int n, m, k, s = 0, t = 801;
int total_weight = 0;
int a, b;
int fl[802][802];
int cap[802][802];
int w[802][802];
int pre[802];
int dist[802];
vector<vector<int>> g(802);



int mcmf() {

    int res = 0;
    queue<int> q;

    bool inQ[802];
    memset(inQ, false, sizeof(inQ));
    while(true) {
        q.push(s);
        inQ[s] = true;
        memset(pre, -1, sizeof(pre));
        fill(dist, dist + 802, 1e9);
        dist[s] = 0;
        while (!q.empty()) {
            int curr = q.front();
            q.pop();
            inQ[curr] = false;
            for (auto& e: g[curr]) {
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
        res++;
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
        cap[s][i] = 1;
        w[s][i] = 0;
        w[i][s] = 0;
        for (int j = 0; j < x; ++j) {
            cin >> a >> b;
            g[i].push_back(a + 400);
            g[a + 400].push_back(i);
            cap[i][a + 400] = 1;
            w[i][a + 400] = -b;
            w[a + 400][i] = b;
        }
    }
    for (int i = 1; i <= m; ++i) {
        g[i + 400].push_back(t);
        g[t].push_back(i + 400);
        cap[i + 400][t] = 1;
        w[i + 400][t] = 0;
        w[t][i + 400] = 0;
    }
    int flw = mcmf();
    cout << flw << "\n";
    cout << -total_weight << "\n";
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
