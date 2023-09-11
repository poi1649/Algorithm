//구현 2. mmap/write 이용
#pragma GCC optimize("O3")
#pragma GCC target("avx2")

#include <bits/stdc++.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <unistd.h>

using namespace std;

/////////////////////////////////////////////////////////////////////////////////////////////
/*
 * Author : jinhan814
 * Date : 2021-05-06
 * Source : https://blog.naver.com/jinhan814/222266396476
 * Description : FastIO implementation for cin, cout. (mmap ver.)
 */
constexpr int SZ = 1 << 20;

class INPUT {
private:
    char *p;
    bool __END_FLAG__, __GETLINE_FLAG__;
public:
    explicit operator bool() { return !__END_FLAG__; }

    INPUT() {
        struct stat st;
        fstat(0, &st);
        p = (char *) mmap(0, st.st_size, PROT_READ, MAP_SHARED, 0, 0);
    }

    bool IsBlank(char c) { return c == ' ' || c == '\n'; }

    bool IsEnd(char c) { return c == '\0'; }

    char _ReadChar() { return *p++; }

    char ReadChar() {
        char ret = _ReadChar();
        for (; IsBlank(ret); ret = _ReadChar());
        return ret;
    }

    template<typename T>
    T ReadInt() {
        T ret = 0;
        char cur = _ReadChar();
        bool flag = 0;
        for (; IsBlank(cur); cur = _ReadChar());
        if (cur == '-') flag = 1, cur = _ReadChar();
        for (; !IsBlank(cur) && !IsEnd(cur); cur = _ReadChar()) ret = 10 * ret + (cur & 15);
        if (IsEnd(cur)) __END_FLAG__ = 1;
        return flag ? -ret : ret;
    }

    string ReadString() {
        string ret;
        char cur = _ReadChar();
        for (; IsBlank(cur); cur = _ReadChar());
        for (; !IsBlank(cur) && !IsEnd(cur); cur = _ReadChar()) ret.push_back(cur);
        if (IsEnd(cur)) __END_FLAG__ = 1;
        return ret;
    }

    double ReadDouble() {
        string ret = ReadString();
        return stod(ret);
    }

    string getline() {
        string ret;
        char cur = _ReadChar();
        for (; cur != '\n' && !IsEnd(cur); cur = _ReadChar()) ret.push_back(cur);
        if (__GETLINE_FLAG__) __END_FLAG__ = 1;
        if (IsEnd(cur)) __GETLINE_FLAG__ = 1;
        return ret;
    }

    friend INPUT &getline(INPUT &in, string &s) {
        s = in.getline();
        return in;
    }
} _in;

class OUTPUT {
private:
    char write_buf[SZ];
    int write_idx;
public:
    ~OUTPUT() { Flush(); }

    explicit operator bool() { return 1; }

    void Flush() {
        write(1, write_buf, write_idx);
        write_idx = 0;
    }

    void WriteChar(char c) {
        if (write_idx == SZ) Flush();
        write_buf[write_idx++] = c;
    }

    template<typename T>
    int GetSize(T n) {
        int ret = 1;
        for (n = n >= 0 ? n : -n; n >= 10; n /= 10) ret++;
        return ret;
    }

    template<typename T>
    void WriteInt(T n) {
        int sz = GetSize(n);
        if (write_idx + sz >= SZ) Flush();
        if (n < 0) write_buf[write_idx++] = '-', n = -n;
        for (int i = sz; i-- > 0; n /= 10) write_buf[write_idx + i] = n % 10 | 48;
        write_idx += sz;
    }

    void WriteString(string s) { for (auto &c: s) WriteChar(c); }

    void WriteDouble(double d) { WriteString(to_string(d)); }
} _out;

/* operators */
INPUT &operator>>(INPUT &in, char &i) {
    i = in.ReadChar();
    return in;
}

INPUT &operator>>(INPUT &in, string &i) {
    i = in.ReadString();
    return in;
}

template<typename T, typename std::enable_if_t<is_arithmetic_v<T>> * = nullptr>
INPUT &operator>>(INPUT &in, T &i) {
    if constexpr (is_floating_point_v<T>) i = in.ReadDouble();
    else if constexpr (is_integral_v<T>) i = in.ReadInt<T>();
    return in;
}

OUTPUT &operator<<(OUTPUT &out, char i) {
    out.WriteChar(i);
    return out;
}

OUTPUT &operator<<(OUTPUT &out, string i) {
    out.WriteString(i);
    return out;
}

template<typename T, typename std::enable_if_t<is_arithmetic_v<T>> * = nullptr>
OUTPUT &operator<<(OUTPUT &out, T i) {
    if constexpr (is_floating_point_v<T>) out.WriteDouble(i);
    else if constexpr (is_integral_v<T>) out.WriteInt<T>(i);
    return out;
}

/* macros */
#define fastio 1
#define cin _in
#define cout _out
#define istream INPUT
#define ostream OUTPUT
/////////////////////////////////////////////////////////////////////////////////////////////


#include <bits/stdc++.h>

using namespace std;
typedef long long ll;

int n, m, curr;
int pre[102], c[102][102], f[102][102];
int INF = 1e9;
vector<vector<int>> graph(102);

int find_max_flow(int start, int end) {
    int ans = 0;
    while (1) {
        queue<int> q;
        memset(pre, -1, sizeof(pre));
        q.push(start);
        while (!q.empty()) {
            curr = q.front();
            q.pop();
            for (int next: graph[curr]) {
                if (f[curr][next] < c[curr][next] && pre[next] == -1) {
                    pre[next] = curr;
                    q.push(next);
                    if (next == end) break;
                }
            }
        }
        if (pre[end] == -1) break;
        int flow = INF;
        for (int i = end; i != start; i = pre[i]) flow = min(flow, c[pre[i]][i] - f[pre[i]][i]);
        for (int i = end; i != start; i = pre[i]) {
            f[pre[i]][i] += flow;
            f[i][pre[i]] -= flow;
        }
        ans += flow;
    }
    return ans;
}

int main() {
    fastio;
    cin >> n;
    int a, b;
    int s = 0;
    int e = 2 * n + 1;

    for (int i = 1; i <= n; ++i) {
        cin >> a;
        graph[s].push_back(i);
        c[s][i] = a;
        for (int j = 1; j <= n; ++j) {
            graph[i].push_back(j + n);
            graph[j + n].push_back(i);
            c[i][j + n] = INF;
        }
    }
    for (int i = 1; i <= n; ++i) {
        cin >> a;
        graph[i + n].push_back(e);
        c[i + n][e] = a;
    }


    int ans = find_max_flow(s, e);

    int curAns = 0;
    int mid, l = 0, r = INF;
    while (l < r) {
        memset(f, 0, sizeof(f));
        mid = (l + r) / 2;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                c[i][j + n] = mid;
            }
        }
        curAns = find_max_flow(s, e);
        if (curAns >= ans) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            c[i][j + n] = r;
        }
    }
    memset(f, 0, sizeof(f));
    cout << r << '\n';
    find_max_flow(s, e);

    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            cout << f[i][j + n] << ' ';
        }
        cout << '\n';
    }

    return 0;
}
