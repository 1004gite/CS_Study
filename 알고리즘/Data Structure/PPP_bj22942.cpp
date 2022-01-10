/*
결국 풀이를 보고 푼 문제이다.
가장 하단에 처음 푼 풀이를 보면 tree를 이용해 중간에 검사를 그만두는 경우를 고려했는데
이는 arverage case의 시간복잡도를 줄이는 방법이라고 생각한다.
-> 테스트케이스를 모두 통과하기 위해서는 worst case에 대한 고려를 해야한다
    즉, 시간 복잡도를 줄이는 것을 목표로 한다.

brute force하게 구현하면 O(n^2)이다.
stack과 sort를 이용하면 O(nlogn)이다.

원의 시작과 끝을 괄호라고 생각하자, 먼저 열린 원이 닫힐때 이전에 열린 원이 닫히지 않았다면 겹치는 것이다.
열리는 부분의 좌표를 기준으로 정렬 후 비교해야 한다.

    // 다른 풀이로는 원의 크기순 or 좌표순으로 정렬한 후 그냥 비교하거나
    // 정렬 후 최근에 배치한 원에 대해서만 판단한는 풀이가 있었다.
    // 전자의 경우 정렬때문에 어느정도 빨리 no가 나오는 것 같다.
    // 후자의 경우 논리적으로 이해하지 못했다.
*/


#include <iostream>
#include <stack>
#include <vector>
#include <algorithm>

using namespace std;

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n;
    cin >> n;


    // {좌표, 여는점0/닫는점1, 원의 번호}
    vector< vector<int> > point;

    stack< vector<int> > S;

    while(n--){
        int x,r;
        cin >> x;
        cin >> r;
        vector<int> tmp;
        tmp.push_back(x-r);
        tmp.push_back(0);
        tmp.push_back(n);
        point.push_back(tmp);

        tmp[0] = x+r;
        tmp[1] = 1;
        point.push_back(tmp);
    }

    // 여는점 기준으로 sort
    sort(point.begin(), point.end());

    // 마지막 하나를 남기고 모두 통과했으면 마지막도 통과다.
    for(int i = 0; i < point.size()-1; i++){
        // 다음에 올 점과 겹치는지 우선 검사(접점)
        if(point[i+1][0] == point[i][0]){
                printf("NO\n");
                return 0;
            }
        if(point[i][1] == 0){
            S.push(point[i]);
        }
        else{
            // 여는점과 같은 원인지 검사
            // 만약 여는점이 다른 원이면 바깥 원이 먼저 닫혔는데 안쪽 원이 닫히지 않았음을 의미
            if(S.top()[2] != point[i][2]){
                printf("NO\n");
                return 0;
            }
            S.pop();
        }
    }
    printf("YES\n");
}







// #include <iostream>
// #include <cmath>
// #include <vector>

// using namespace std;

// /*
// 반복문 안에서 struct를 선언하면 반복문마다 같은 주소값을 가진다.
// 사용할 struct의 개수만큼 반복문 밖에서 선언하거나 헤더파일에 있는 자료구조를 최대한 이용하자.
// */

// /*
// 모든 원끼리 교점이 없어야 한다.
// 원의 중심이 x축 위에 있는 것이 보장된 상태이다.

// 다음 조건을 확인한다.
// 1. 큰 원 안에 작은 원의 중심이 있을 경우
// -> (중심끼리의 거리 + 작은 원의 반지름) < 큰 원의 반지름

// 2. 큰 원 밖에 작은 원의 중심이 있는 경우
// -> 중심끼리의 거리 > (큰 원의 반지름 + 작은 원의 반지름)

// 3. 어떤 큰 원 안에 작은 원들이 정상적으로 있는 경우에 같은 집합으로 묶는다.
// -> 2번 조건의 경우 해당 집합의 가장 큰 원과만 비교해도 무관하다.
// -> 1번 조건의 경우 자신이 어떤 집합의 가장 큰 원이 되면 그 안의 원들은 비교를 생략할 수 있다.

// 4. 집합의 개념 대신 tree를 사용한다. 작은 원들을 품는 원을 parent/ 안의 원들을 child로 본다.
// -> 비교 시 어떤 node를 품는다면 1,2번 조건을 비교 후 그 node의 parent로 들어간다.
// -> 품지 않는 모든 node와는 비교한다.
// */

// struct node{
//     int x;
//     int r;
//     node * parent;
//     vector<node *> children;
// };

// // base에서 시작하여 모든 node에 대해 dfs로 검사한다.
// // 겹치는 원이 있다면 false를 없다면 true를 반환한다.
// // 검사는 children을 기준으로 한다.
// int dfs(node *nowNode, node *target){

//     // 모든 children에 대해
//     for(int i =0; i < (*nowNode).children.size(); i++){
//         // target이 큰 원이면 allOK를 1로 두어 후보에 올린다.
//         // allOK가 1일때 1번조건을 만족하면 2로 바꾼다.
//         int allOK = 0;
//         node nowchild = *((*nowNode).children[i]);
//         int big, small, distance = abs(nowchild.x-(*target).x);
//         if((*target).r > nowchild.r){
//             allOK = 1;
//             big = (*target).r;
//             small = nowchild.r;
//         }
//         else{
//             big = nowchild.r;
//             small = (*target).r;
//         }

//         // 1번 조건을 검사한다.
//         if(distance <= big){
//             if((distance + small) >= big){
//                 // printf("big: %d, small: %d, distance: %d, 조건1\nnowchildX: %d, targetX: %d\n",big,small,distance,nowchild.x,(*target).x);
//                 return 0;
//             }
//             // target이 큰 원이고 조건을 통과한다면 이번 child를 품는 것이므로 표시한다. (allOK변수)
//             if(allOK == 1) allOK = 2;
//             // target이 작은 원이고 조건을 통과한다면 이번 child의 child가 될 수 있다.
//             if(allOK == 0) allOK = 3;
//         }
//         // 2번 조건을 검사한다.
//         if(distance > big && distance <= (small + big)){
//             return 0;
//         }


//         // 만약 allOK가 2이면 해당 child를 target의 children에 추가/ target의 parent를 바꾸고
//         // 해당 child에 대해서는 더이상 깊게 내려가지 않는다.
//         if(allOK == 2){
//             // target의 정보를 변경
//             // (*target).parent = nowNode;
//             // (*target).children.push_back((*nowNode).children[i]);
//             target->parent = nowNode;
//             (target->children).push_back((nowNode->children)[i]);

//             // nowNode의 nowchild를 target으로 변경
//             // (*(*target).parent).children[i] = target;
//             ((target->parent)->children)[i] = target;

//             // nowchild의 parent를 target으로 변경
//             // (*(*target).children.back()).parent = target;
//             ((target->children)[(target->children).size()-1])->parent = target;
//         }
//         // 아니라면 다음 이번 child에 대한 검사를 호출(dfs)
//         // 만약 중간에 false가 return 된다면 더이상 진행할 이유가 없기 때문에 바로 false를 return한다.
//         else{
//             // target을 현재 child의 child로 만든다.
//             // allOK가 3일때마다 수행하여 가능한 가장 깊은 node로 만든다.
//             if(allOK == 3){
//                 // (*target).parent = (*nowNode).children[i];
//                 // (*(*nowNode).children[i]).children.push_back(target);
//                 target->parent = (nowNode->children)[i];
//             }
//             int result = dfs((*nowNode).children[i],target);
//             if(result == 0){
//                 return 0;
//             }
//         }
//     }

//     // 모든 node를 만나는 동안 집합에 속하지 못했다면 basenode의 child이다.
//     if((*target).parent == nullptr){
//         return 2;
//     }
//     return 1;
// }

// int main(void){

//     cin.tie(0);
//     cin.sync_with_stdio(0);

//     int n;
//     cin >> n;

//     // base는 가장 상위 node로서 child만을 갖는다.
//     node topnode = node();
//     node *base = &topnode;
//     vector<node *> ch(0);
//     // (*base).children = ch;
//     base -> children = ch;

//     // 반복문 안에서 구조체 생성 후 포인터에 주소를 할당하면 같은 주소가 할당되는 것을 방지
//     vector<node> tmpV(n);

//     while(n--){

//         // node tmpnode = node();
//         // node *tmp = &tmpnode;
//         node *tmp = &(tmpV[n]);
//         // cin >> (*tmp).x;
//         // cin >> (*tmp).r;
//         cin >> tmp->x;
//         cin >> tmp->r;
//         // (*tmp).children = tmpch;
//         // (*tmp).parent = nullptr;
//         vector<node *> tmpch(0); // 여기서는 instance가 들어가기 때문에 반복문 안에서 생성
//         tmp->children = tmpch;
//         tmp->parent = nullptr;

//         //printf("tmp주소 %d\n",tmp);
//         // if(topnode.children.size()){
//         //     printf("직전 %d\n",(*(*base).children[0]).x);
//         // }
//         int re = dfs(base,tmp);
//         if(re == 0){
//             printf("NO\n");
//             return 0;
//         }
//         else if(re == 2){
//             // (*tmp).parent = base;
//             // (*base).children.push_back(tmp);
//             tmp->parent = base;
//             (base->children).push_back(tmp);
//         }
//     }

//     printf("YES\n");
//     return 0;
// }