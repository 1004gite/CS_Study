/*

1. 나올수 있는 최대 수를 제대로 고려하지 않아서 헤멨다.
2. 1~12월 처럼 12개만 일을 압력하면 되는 경우는 하드코딩이 낫다.

input의 개수, 대여 가능 기간, 1분당 벌금이 주어진다.
이때 벌금은 어차피 1분단위이므로 대여기간, 대여 시각, 반납시각 등을 전부 분단위로 저장한다.
년도는 2021로 고정되어있기 때문에 (365 * 24 *60) 크기 이상의 데이터타입이 필요하다
+ 테스트 케이스의 개수가 최대 80000개이고 한번당 최대 요금은 int의 최대에 가깝기 때문에 큰 자료형을 써야 한다.
(2021은 윤년이 아니다)

대여와 반납에 대해 사람과 부품이 일치해야 반납이다.

반납이 들어올 때마다 대여목록을 검색하면 O( N *N )이 된다.

모든 목록을 받은 후 sort한다면 O( NlogN )이 된다.
이떄 정렬은 빌린사람(사전순), 부품, 시간(오름차순)으로 한다. (같은 부품을 반납 후 다시 빌릴 수 있기 때문에 시간도 기준으로 두어야 한다.)


//////////////
string변수에 한줄 입력받기 -> getline( cin, string 변수)
char 배열에 한줄 입력받기 -> cin.getline(변수, 길이, '\n'(제한자))
*/

#include <iostream>
#include <vector>
#include <string>
#include <cstring>
#include <algorithm>

using namespace std;

struct Info{
    string name, component;
    int time;
    Info(string name, string component, int time) 
    : name(name), component(component), time(time){}
};

bool ascending(Info a, Info b){
    // 이름, 부품, 시간 순서대로 우선순위를 가진다.
    if(a.name != b.name){
        return a.name < b.name;
    }
    if(a.component != b.component){
        return a.component < b.component;
    }
    return a.time < b.time;
}

void getFirstline(int * n, int * period, int * feePm){
    // 첫번째 줄의 정보를 받아옴
    char tmp[100];
    cin.getline(tmp,100, '\n');

    string tmps;
    tmps = strtok(tmp, " ");
    *n = stoi(tmps);

    *period = 0;
    tmps = strtok(NULL, "/");
    *period += stoi(tmps) * 24 * 60;
    tmps = strtok(NULL,":");
    *period += stoi(tmps) * 60;
    tmps = strtok(NULL," ");
    *period += stoi(tmps);

    tmps = strtok(NULL, "\n");
    *feePm = stoi(tmps);
}

void mgetline(Info * minfo){
    // 한줄을 받아 info 객체의 원소를 입력한다.
    char in[100];
    cin.getline(in,100,'\n');

    // 날짜를 분단위로 환산
    minfo->time = 0; // 구조체에 직접 접근
    string tmps;
    strtok(in, "-"); // 년도는 불필요하다.
    tmps = strtok(NULL, "-"); //월
    int month = stoi(tmps);
    int monthDay[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31};
    for(int i =0; i<month-1; i++){
        minfo->time += monthDay[i] * 24 * 60;
    }
    tmps = strtok(NULL, " "); // 일
    minfo->time += stoi(tmps) * 24 * 60;
    tmps = strtok(NULL, ":"); // 시
    minfo->time += stoi(tmps) * 60;
    tmps = strtok(NULL, " "); // 분
    minfo->time += stoi(tmps);

    // 부품 이름
    tmps = strtok(NULL, " ");
    minfo->component = tmps;
    // 사람 이름
    tmps = strtok(NULL, "\n");
    minfo->name = tmps;
}

int main(void){

    cin.tie(0);
    cin.sync_with_stdio(0);

    int n, period, feePm;
    getFirstline(&n,&period,&feePm);

    vector<Info> list;

    while(--n >= 0){
        Info tmp("","",0);
        mgetline(&tmp);
        list.push_back(tmp);
    }
    
    sort(list.begin(),list.end(), ascending);

    // 반납하지 않은 사람은 없고 같은 부품을 반납해야 다시 빌릴 수 있다
    // 같은 사람을 판단하는 동안 2개씩 받아서 요금을 더한다.
    // 요금이 0원이면 출력하지 않는다.
    string name = list[0].name;
    long long fee = 0, time, check = 0;
    for(int i = 0;i < list.size();){
        if(name != list[i].name){
            // 이름이 달라지면 다음 사람을 보는 것임
            if(fee != 0){
                printf("%s %lld\n",name.c_str(), fee);
                check = 1;
            }
            name = list[i].name;
            fee = 0;
        }
        // 대여, 반납 순으로 2개를 본 후 요금을 더한다.
        time = list[i+1].time - list[i].time;
        time -= period;
        if(time > 0){
            // 대여가능 기간을 넘김
            fee += time * feePm;
        }
        i += 2;
    }
    // 마지막 사람에 대한 처리
    if(fee != 0){
        printf("%s %lld\n",name.c_str(), fee);
        check = 1;
    }
    if(check == 0){
        printf("-1\n");
    }
}