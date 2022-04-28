#include <iostream>
#include <deque>

using namespace std;

/*
순서 문제에 deque를 사용할 때는 기준 index를 어디로 잡았는지,
진행 방향이 어디인지를 고려해야 한다.
front가 기준일 때 순방향으로 가는 방향은 pop으로만 나아갈 수 있지만
역방향으로 가는 경우 pop을 하고 순서를 한번 밀어줘야 한다.
*/

struct node {
    // index는 풍선의 번호, num은 풍선 안에 들어있는 값이다.
    int index,value;
};

int main(void){

    int n;
    cin >> n;

    deque<node> d;

    for(int i =0; i < n; i++){
        node tmp;
        tmp.index = i+1;
        cin >> tmp.value;
        d.push_back(tmp);
    }

    while(!d.empty()){
        printf("%d\n",d.front().index);
        int move = d.front().value;
        d.pop_front();

        // front를 기준으로 풍선을 제거하므로 +라면 앞의 원소를 back으로
        // -라면 뒤의 원소를 front로 보낸다.
        if(move > 0){
            // 이번 번호의 풍선을 이미 터뜨렸고 front가 1 중가했으므로 앞으로 움직이는 입장에서는 1칸을 움직인 것과 같다.
            move--;
            while(move--){
                d.push_back(d.front());
                d.pop_front();
            }
        }
        else if(move < 0){
            // 뒤로 움직이는 입장에서는 어차피 이번 번호부터 뒤로 move만큼 움직이기 때문에 어차피 같다.
            while(move++){
                d.push_front(d.back());
                d.pop_back();
            }
        }
    }

}