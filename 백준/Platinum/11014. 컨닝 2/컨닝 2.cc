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

int n, k;
int dp[6401];
bool checked[6401];
bool inputs[81][81];
vector<vector<int>> graph(6401);

bool dfs(int node) {
    for (int i = 0; i < graph[node].size(); i++) {
        int target = graph[node][i];
        if (checked[target])continue;
        checked[target] = true;
        if (dp[target] == 0 || dfs(dp[target])) {
            dp[target] = node;
            return true;
        }
    }
    return false;
}

int main() {
    fastio;
    int c;
    cin >> c;
    for (int q = 0; q < c; ++q) {
        cin >> n >> k;
        int allVertex = 0;
        memset(dp, 0, sizeof(dp));
        memset(inputs, false, sizeof(inputs));
        graph.clear();
        graph.resize(6401);

        for (int i = 1; i <= n; ++i) {
            string s;
            cin >> s;
            for (int j = 1; j <= k; ++j) {
                if (s[j - 1] == '.') {
                    allVertex++;
                    inputs[i][j] = true;
                }
            }
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; j = j + 2) {
                if (inputs[i][j]) {
                    int cur = (i - 1) * k + j;
                    if (inputs[i][j - 1]) {
                        graph[cur].push_back(cur - 1);
                    }
                    if (j < k && inputs[i][j + 1]) {
                        graph[cur].push_back(cur + 1);
                    }
                    if (inputs[i - 1][j - 1]) {
                        graph[cur].push_back((i - 2) * k + j - 1);
                    }
                    if (j < k && inputs[i - 1][j + 1]) {
                        graph[cur].push_back((i - 2) * k + j + 1);
                    }
                    if (i < n && inputs[i + 1][j - 1]) {
                        graph[cur].push_back(i * k + j - 1);
                    }
                    if (i < n && j < k && inputs[i + 1][j + 1]) {
                        graph[cur].push_back(i * k + j + 1);
                    }
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; j = j + 2) {
                if (inputs[i][j]) {
                    memset(checked, false, sizeof(checked));
                    if (dfs((i - 1) * k + j)) {
                        count++;
                    }
                }
            }
        }


        cout << allVertex - count << "\n";
    }

    return 0;
}
