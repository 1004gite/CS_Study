/*
최대 힙 구조를 만들어서 해결해도 되고
periority queue를 사용해서 해결도 가능하다.
*/

/*
최대 힙 구현
1. index에 부모가 있으면 자식은 (index*2 +1), (index*2 +2 )에 위치한다.
    즉,  부모는 (i-1)/2 에 위치하는 식이다.
2. 삽입 시 배열의 마지막에 원소를 추가하고 부모외 비교하여 더 크다면 부모와 위치를 바꾼다
    이는 부모보다 작거나 가장 상위 node가 될 떄까지 반복한다.
3. 가장 큰 원소의 삭제시 가장 하단의 원소와 상단을 바꾸고 가장 하단의 원소를 return 하고 지운다.
    가장 상단으로 간 작은 원소는 자식에 더 큰 원소가 없을 때까지 아래로 내려간다.
    이때 두 자식이 모두 더 크다면 더 큰쪽과 바꿔야 바꿨을 때 최대 힙이 유지된다.
*/

#include <iostream>
#include <vector>

using namespace std;

void insert(vector<int> &heap, int target){
    heap.push_back(target);
    int index = heap.size()-1;

    // 더한 원소의 자리를 찾는다.
    while(1){
        if(index == 0){ // 가장 최상위 node 까지 온 경우
            break;
        }

        // 부모와 비교
        int parentindex = (index-1)/2;

        if(heap[parentindex] >= heap[index]){ // 최대 힙 조건을 만족하는 경우
            break;
        }
        else{ // 만족하지 않는 경우 부모와의 위치를 바꾼다.
            int tmp;
            tmp = heap[parentindex];
            heap[parentindex] = heap[index];
            heap[index] = tmp;
            index = parentindex;
        }
    }
}

int pop(vector<int> &heap){
    // 힙이 비어있으면 0 반환
    if(heap.size() == 0){
        return 0;
    }
    // 처음, 마지막 위치 변환 후 마지막 원소 삭제
    int result = heap[0];
    heap[0] = heap[heap.size()-1];
    heap.pop_back();
    
    int index = 0;
    while(1){
        int left= index *2 +1,right= index *2 +2;
        int cmpindex;
        if(heap.size()-1 < left){
            // 마지막 node인 경우
            break;
        }
        else if(heap.size()-1 < right ){
            // 왼쪽 자식만 있는 경우
            cmpindex = left;
        }
        else {
            // 두 자식 모두 있는 경우
            // 이떄는 더 큰 자식과 비교한다.
            heap[left] > heap[right] ? cmpindex = left : cmpindex= right;

        }
        if(heap[index] >= heap[cmpindex]){
                // 큰것보다 크면 검사를 종료한다.
                break;
            }
            else{
                // 큰것보다 작으면 그것과 자리를 바꾼다.
                int tmp = heap[index];
                heap[index] = heap[cmpindex];
                heap[cmpindex] = tmp;
                index = cmpindex;
            }
    }

    return result;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    vector<int> heap;

    int n;
    cin >> n;

    while(n--){
        int input;
        cin >> input;

        if(input == 0){
            printf("%d\n",pop(heap));
        }
        else{
            insert(heap, input);
        }

    }

}