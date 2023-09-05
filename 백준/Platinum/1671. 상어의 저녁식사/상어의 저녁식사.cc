#include<iostream>
#include<vector>
using namespace std;
vector<int> graph[51];
int power[51][3];
int d[51];
bool check[51];
bool dfs(int node)
{
	for(int i = 0;i < graph[node].size();i++)
	{
		int target = graph[node][i];
		if (check[target] == true)continue;
		check[target] = true;
		if (d[target] == 0 || dfs(d[target]))
		{
			d[target] = node;
			return true;
		}
	}
	return false;
}
int main()
{
	int n;
	cin >> n;
	for (int i = 1;i <= n;i++)
	{
		cin >> power[i][0] >> power[i][1] >> power[i][2];
	}
	for (int i = 1;i < n;i++)
	{
		for (int j = i + 1;j <= n;j++)
		{
			if (power[i][0] == power[j][0] && power[i][1] == power[j][1] && power[i][2] == power[j][2]) 
			{
				graph[j].push_back(i);
			}
			else if (power[i][0] >= power[j][0] && power[i][1] >= power[j][1] && power[i][2] >= power[j][2])
			{
				graph[i].push_back(j);
			}
			else if (power[i][0] <= power[j][0] && power[i][1] <= power[j][1] && power[i][2] <= power[j][2])
			{
				graph[j].push_back(i);
			}
		}
	}
	int count = 0;
	for (int j = 0;j < 2;j++)
	{
		for (int i = 1;i <= n;i++)
		{
			fill(check, check + 51, false);
			if (dfs(i) == true)count++;
		}
	}
	printf("%d\n ", n-count);
	return 0;
}