#include <iostream>
#include <bits/stdc++.h>

static const int MAX = 200001;
using namespace std;
int t, n, m;
int tree[MAX];
map<int, int> y_compress;
set<int> y_lists;

struct Inputs {
    int type;
    int x;
    int y;
    int yEnd;
    Inputs(int type, int x, int y, int yEnd)
            : type(type), x(x), y(y), yEnd(yEnd) {}
    Inputs(int type, int x, int y)
            : type(type), x(x), y(y), yEnd(0) {}

};

void activate(int left, int right) {
    left = left + MAX / 2;
    right = right + MAX / 2;
    while (left <= right) {
        if (left % 2 == 1) {
            tree[left] += 1;
            left++;
        }
        if (right % 2 == 0) {
            tree[right] += 1;
            right--;
        }
        left /= 2;
        right /= 2;
    }
}

void deactivate(int left, int right) {
    left = left + MAX / 2;
    right = right + MAX / 2;
    while (left <= right) {
        if (left % 2 == 1) {
            tree[left] -= 1;
            left++;
        }
        if (right % 2 == 0) {
            tree[right] -= 1;
            right--;
        }
        left /= 2;
        right /= 2;
    }
}

int find_actives(int index) {
    index = index + MAX / 2;
    int result = 0;
    while (index > 0) {
        result += tree[index];
        index /= 2;
    }
    return result;
}

bool compare(const Inputs &a, const Inputs &b) {
    if (a.x == b.x) {
        return a.type < b.type;
    }
    return a.x < b.x;
}

vector<Inputs> inputs;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> t;
    while (t--) {
        cin >> n >> m;
        inputs.clear();
        for (int i = 0; i < n; i++) {
            int x, y;
            cin >> x >> y;
            y_lists.insert(y);
            inputs.push_back(Inputs(2, x, y));
        }
        for (int i = 0; i < m; i++) {
            int x, y, xEnd, yEnd;
            cin >> x >> xEnd >> y >> yEnd;
            y_lists.insert(y);
            y_lists.insert(yEnd);
            inputs.push_back(Inputs(1, x, y, yEnd));
            inputs.push_back(Inputs(3, xEnd,y, yEnd));
        }
        sort(inputs.begin(), inputs.end(), compare);
        int index = 0;
        for (auto & y : y_lists) {
            y_compress[y] = index++;
        }
        memset(tree, 0, sizeof(tree));
        long long result = 0;
        for (auto & input : inputs) {
            int type = input.type;
            if (type == 1) {
                activate(y_compress[input.y], y_compress[input.yEnd]);
            } else if (type == 2) {
                result += find_actives(y_compress[input.y]);
            } else {
                deactivate(y_compress[input.y], y_compress[input.yEnd]);
            }
        }
        cout << result << '\n';
    }

    return 0;
}
