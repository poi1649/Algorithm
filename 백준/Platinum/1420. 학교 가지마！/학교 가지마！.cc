#include <bits/stdc++.h>

using namespace std;

int lv[23000];
int work[23000];
int n;
int s = -1, t = -1;
bool inputs[101][101];

struct Edge {
    int from, to, cap, flw;
    Edge *rev;

    Edge(int u, int v, int c) : from(u), to(v), cap(c), flw(0) {}

    int remain() { return cap - flw; }

    void add_flow(int amount) {
        flw += amount;
        rev->flw -= amount;
    }
};

vector<Edge *> g2[23000];

void add_line(int from, int to, int capacity) {
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

int dfs(int now, int tot) {
    if (now == t) return tot;
    for (int &i = work[now]; i < g2[now].size(); i++) {
        Edge *&nextE = g2[now][i];
        int nxt = nextE->to;
        if (lv[nxt] == lv[now] + 1 && nextE->remain() > 0) {
            int fl = dfs(nxt, min(tot, nextE->remain()));
            if (fl > 0) {
                nextE->add_flow(fl);
                return fl;
            }
        }
    }
    return 0;
}


int m;
string a;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;

    memset(inputs, 0, sizeof inputs);
    int sX, sY, eX, eY;

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; j++) add_line(((i - 1) * m + j) * 2, ((i - 1) * m + j) * 2 + 1, 1);


    for (int i = 1; i <= n; ++i) {
        cin >> a;
        for (int j = 0; j < m; ++j) {
            if (a[j] == '#') {
                continue;
            }
            if (a[j] == '.') {
                inputs[i][j + 1] = true;
            }
            if (a[j] == 'K') {
                inputs[i][j + 1] = true;
                s = (((i - 1) * m) + j + 1) * 2 + 1;
                sX = i, sY = j;
            }
            if (a[j] == 'H') {
                inputs[i][j + 1] = true;
                t = (((i - 1) * m) + j + 1) * 2;
                eX = i, eY = j;
            }
        }
    }

    if (sX == eX - 1 && sY == eY) {
        cout << -1 << "\n";
        return 0;
    }
    if (sX == eX && sY == eY - 1) {
        cout << -1 << "\n";
        return 0;
    }
    if (sX == eX && sY == eY + 1) {
        cout << -1 << "\n";
        return 0;
    }
    if (sX == eX + 1 && sY == eY) {
        cout << -1 << "\n";
        return 0;
    }

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= m; ++j) {
            if (!inputs[i][j]) continue;
            if (i < n && inputs[i + 1][j]) {
                add_line(((i - 1) * m + j) * 2 + 1, (i * m + j) * 2, 1);
                add_line(((i * m + j) * 2 + 1), ((i - 1) * m + j) * 2, 1);
            }
            if (j < m && inputs[i][j + 1]) {
                add_line(((i - 1) * m + j + 1) * 2 + 1, ((i - 1) * m + j) * 2, 1);
                add_line(((i - 1) * m + j) * 2 + 1, ((i - 1) * m + j + 1) * 2, 1);
            }
        }
    }
    int ans = 0;
    while (bfs()) {
        memset(work, 0, sizeof work);
        while (true) {
            int fl = dfs(s, 1e9 + 7);
            if (!fl) break;
            ans += fl;
        }
    }
    cout << ans << "\n";
    return 0;
}