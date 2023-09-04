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

int n;
int dp[51];
bool checked[51];
bool isPrime[2000];
int excepted = 0;
vector<int> input(51);
vector<vector<int>> graph(51);

bool dfs(int cur) {
    for (auto next: graph[cur]) {
        if (checked[next] || next == excepted || next == 1) continue;
        checked[cur] = true;
        checked[next] = true;
        if (dp[next] == 0) {
            dp[cur] = next;
            dp[next] = cur;
            return true;
        }
        if (dfs(dp[next])) {
            dp[cur] = next;
            dp[next] = cur;
            return true;
        }
    }
    return false;
}

void initPrime() {
    memset(isPrime, true, sizeof(isPrime));
    for (int i = 2; i < 2000; ++i) {
        if(!isPrime[i]) continue;
        for (int j = 2; j * i < 2000; j++) {
            isPrime[i * j] = false;
        }
    }
}

int main() {
    fastio;

    cin >> n;
    input.resize(n + 1);
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        cin >> input[i];
    }

    initPrime();
    for (int i = 1; i <= n; i++) {
        for (int j = i + 1; j <= n; j++) {
            if (isPrime[input[i] + input[j]]) {
                graph[i].push_back(j);
                graph[j].push_back(i);
            }
        }
    }

    vector<int> list;
    for (int i = 2; i <= n; ++i) {
        int count = 0;
        if (isPrime[input[i] + input[1]]) {
            excepted = i;
            memset(dp, 0, sizeof(dp));
            for (int j = 2; j <= n; j++) {
                if (j == i) continue;
                memset(checked, false, sizeof(checked));
                if (dp[j] != 0 || dfs(j)) {
                    count++;
                }
            }
            if (count == n - 2) {
                list.push_back(input[i]);
            }
        }
    }

    if (list.empty()) {
        cout << -1;
        return 0;
    }
    std::sort(list.begin(), list.end());

    for(auto& i : list) {
        cout << i << " ";
    }
    return 0;
}
