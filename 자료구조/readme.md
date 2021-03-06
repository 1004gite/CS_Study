# 자료구조 정리
<br>  

### List
> 순서가 있는 collection 이다.  

### Array  
> 크기가 정적인 연속적인 배열  

### ArrayList/ MutableList (Kotlin)  
> 추가, 수정이 가능한 List  
> mutableListOf, ArrayListOf 호출시 둘다 ArrayList를 반환한다.  
> index참조시 O(1)의 복잡도를 가지고 중간에 삽입시 O(N)의 복잡도를 가진다.(배열의 특성)  
> 꽉차면 기존 배열의 1.5배씩 늘리는 식으로 유지한다. -> 남는 메모리가 생긴다.

### Set  
> 중복이 불가능하고 순서가 없다.  

### Map  
> key_value 쌍으로 이루어져 있고 key는 중복이 불가능하다.

### 최대 힙
> 기본적으로 complete binary tree 이다  
> 모든 부모가 자식보다 큰 값을 갖는 구조

### Queue
> 먼저 들어온 객체가 먼저 나가는 구조 (FIFO)   
> 객체의 추가는 뒷부분에서만 일어나고 객체의 삭제는 앞에선만 일어난다.  
> kotlin에서는 인터페이스만 제공하며 LinkedList로 객체를 생성한다.  

### Stack
> 나중에 들어온 객체가 먼저 나가는 구조 (FILO)
> 객체의 추가와 삭제가 모두 top에서 일어난다.  
> kotlin에서는 ArrayList로 이용 가능  

### Priority Queue
> 중요도가 가장 높은 원소를 가장 위로 보내는 구조이다.  
> 넣는 순서에 상관없이 중요도가 높은 원소가 가장 높이 있게 된다.  
> 내부적으로는 heap 구조를 사용해 정렬에 O(logN) 의 복잡도를 가진다.

### 이진 검색 tree
> 이진 tree 구조로 오른 쪽 node는 항상 부모보다 크고 왼쪽 node는 항상 부모보다 작다.  
> 모든 subtree도 같은 성질을 만족한다.

### tree
> 사이클이 없는 무방향 그래프
> 어떤 두 node 사이에 항상 1개의 경로가 존재한다.
>> 두 leaf 간의 거리가 가장 먼 경로의 길이를 tree의 지름이라고 한다.  
>>  
>> tree를 전위 순회하면 (node, left, right) 왼쪽에 있는 node부터 읽게 되고  
>> 중위 순회하면 (left, node, right) 위에서부터 깊이가 같다면 왼쪽부터 읽게 된다.  
>> 후위 순회하면 깊은것부터 깊이가 같다면 왼쪽부터 읽게 된다.  
