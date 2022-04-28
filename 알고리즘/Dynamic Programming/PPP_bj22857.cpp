/*
수열 S는 길이가 N이고 1이상인 정수로 이루어져 있다.
수열 S에서 최대 k번 원소를 삭제하여 만들 수 있는 수열들 중 짝수가 연속적으로 나오게 하는 가장 긴 길이를 구하자

풀이 1
1. 수의 값보다는 짝/홀 인지가 중요하다
2. 홀수는 0으로 표현하고 짝수는 연속되어있는 크기만큼을 한블럭으로 표현할 수 있다.
3. Greedy하게 접근하면
    합쳐졌을 때 블럭의 크기가 가장 커지도록 지우는 것이 유리하다.
    적은 0을 지워 클럭을 합치는 것이 유리하다.
4. K개 이하의 수를 지울 수 있기 때문에 한번씩 지울떄마다의 최대가 다음 최대를 보장하지 않는다.
    (3개를 지울때의 최선이 4개를 지울때의 최선 이전이 아닐 수 있다.)

풀이 2
1. Two Pointer를 이용한다.
2. 지울 수 있는 홀수의 수는 k개 이므로 지울 수 있는 홀수의 수가 -1개가 되기 전까지 end를 오른쪽으로 이동시킨다.
3. 더이상 지울 수 없다면 start를 오른쪽으로 옮기며 1개를 더 지울 수 있는 상태로 만든 후 반복한다.
*/

#include <iostream>
#include <vector>

using namespace std;

int main(void)
{

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, k, tmp;
    cin >> n >> k;

    vector<bool> s(n, false); // 홀수면 true
    for (int i = 0; i < n; i++)
    {
        cin >> tmp;
        // 홀수면 true로 
        if (tmp & 1)
            s[i] = true;
    }

    int max = 0;
    int nowsize = 0;
    int start = 0, end = 0;
    while (1)
    {
        if (start >= n || end >= n){
            // start가 끝을 넘어가면 끝난 것이고 end가 끝을 넘어가면 그 후에는 더 긴 결과가 나올 수 없음
            break;
        }
        if (s[end])
        {
            //지금 끝점이 홀수인 경우
            if( k == 0){
                // 더이상 지울 수 없으므로 start를 오른쪽으로 밀고 값 처리
                if(s[start]) k++;
                else nowsize--;
                start++;
                continue;
            }
            k--;
            end++;
        }
        else
        {
            // 지금 끝점이 짝수이면 길이+1
            nowsize++;
            end++;
        }

        // printf("start= %d, end = %d, k = %d\n",start,end,k);
        if( nowsize > max) max = nowsize;
    }

    printf("%d\n",max);
}
