#include <iostream>
#include <string>

using namespace std;

/*
가장 하단의 주석처리된 코드는 명령어를 한번에 받은 뒤 일괄 처리하는 방식이고 최종 제출한 코드는
한줄씩 명령어를 처리하는 코드이다.

백준 기준 일괄처리는 4ms, 그때그때 처리하는 방식은 320ms로 꽤 많은 차이가 나는데
아 부분에 대한 이유는 공부가 필요할 듯 하다.
*/

int main(void){
    int n;
    // 최대 명령 개수가 10000이므로 최대 입력 개수는 100000
    int stack[10000]; 
    // stack에서 현재 비어있는 가장 위 index를 나타냄
    int p = 0;
    cin >> n;
    string command[n];
    int sub[n];
    int tmp = 0;
    while(tmp < n){
        cin >> command[tmp];
        if(command[tmp] == "push"){
            cin >> sub[tmp];
        }
        tmp++;
    }
    tmp = 0;

    while(n--){
        string command_tmp = command[tmp++];
        if(command_tmp == "push"){
            // 정수 x를 index p에 넣는다.
            stack[p] = sub[tmp-1];
            p ++;
        }
        else if(command_tmp == "pop"){
            if(p == 0){
                printf("-1\n");
            }
            else{
                printf("%d\n",stack[--p]);
            }
        }
        else if(command_tmp == "size"){
            printf("%d\n",p);
        }
        else if(command_tmp == "empty"){
            // 현재 index가 0이 아니라면 비어있지 않음
            if(p){
                printf("0\n");
            }
            else {
                printf("1\n");
            }
        }
        else if(command_tmp == "top"){
            if(p){
                printf("%d\n",stack[p-1]);
            }
            else{
                printf("-1/n");
            }
        }
    }
}







// #include <iostream>
// #include <string>

// using namespace std;

// int main(void){
//     int n;
//     // 최대 명령 개수가 10000이므로 최대 입력 개수는 100000
//     int stack[10000]; 
//     // stack에서 현재 비어있는 가장 위 index를 나타냄
//     int p = 0;
//     cin >> n;
//     string command;

//     while(n--){
//         cin >> command;
//         if(command == "push"){
//             // 정수 x를 받아 index p에 넣는다.
//             int x;
//             cin >> x;
//             stack[p] = x;
//             p ++;
//         }
//         else if(command == "pop"){
//             if(p == 0){
//                 printf("-1\n");
//             }
//             else{
//                 printf("%d\n",stack[--p]);
//             }
//         }
//         else if(command == "size"){
//             printf("%d\n",p);
//         }
//         else if(command == "empty"){
//             // 현재 index가 0이 아니라면 비어있지 않음
//             if(p){
//                 printf("0\n");
//             }
//             else {
//                 printf("1\n");
//             }
//         }
//         else if(command == "top"){
//             if(p){
//                 printf("%d\n",stack[p-1]);
//             }
//             else{
//                 printf("-1\n");
//             }
//         }
//     }
// }