/* BOJ 18513 샘터 */
#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <cstring>
#include <string>
#include <string.h>
#include <set>
#include <unordered_set>

#define endl '\n'

using namespace std;

typedef long long ll;

int n, k;
queue<int> q;
unordered_set<int> s;
int dx[] = { 1, -1 };

ll bfs()
{
    ll distance = 1, cnt = 0, ans = 0;
    while (!q.empty())
    {
        int q_size = q.size();
        while (q_size--)
        {
            int cur = q.front();
            q.pop();

            for (int i = 0; i < 2; i++)
            {
                int next = cur + dx[i];
                // 방문한적 없는
                //=집을 설치하지 않았거나, 샘물이 아닌 좌표인 경우
                if (!(s.count(next) >= 1))
                {
                    s.insert(next); // 방문처리하고
                    q.push(next); // 큐에 삽입

                    cnt++; // 설치한 집의 수 1 증가
                    ans += distance; // 불행도 계산 및 합산

                    if (cnt == k) // k개의 집을 모두 설치한 경우
                        return ans; // 총 불행도 반환

                }
            }
        }
        distance++; // 불행도 연산을 위한, 가장 가까운 샘물까지의 거리
    }
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    //freopen("input.txt", "r", stdin);

    cin >> n >> k;
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        q.push(a);
        s.insert(a); // 방문처리
    }

    ll ans = bfs();

    cout << ans << endl;

    return 0;
}