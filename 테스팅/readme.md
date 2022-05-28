## Junit5  

- Assert Functions  
> assertArrayEquals(arr1, arr2) : 두 배열이 같은지 검사한다  
> assertEquals(expected, val) : 두 값이 같은지 검사한다  
> assertSane(expected, actual) : 두 객체가 같은지 검사한다  
> assertTrue(condition) : 조건이 참인지 검사한다  
> assertNotNull(object) : 객체가 Null이 아니어야 한다

- Anotations
> @Test : Junit은 @Test anotation이 붙은 메소드를 실행한다.
> @DisplayName("name") : 테스트 프롬프트에 표시되는 이름을 설정한다  
> @BeforeAll : 테스트 시작 전 실행 // static method여야 한다  
> @AfterAll : 테스트가 끝난 후 실행 // static method여야 한다  
> @BeforeEach : 각 테스트 케이스 시작 전 실행된다  
> @AfterEach : 각 테스트 케이스 종료 후 실행된다  
> @Timeout(5000) : 테스트 케이스가 5초 내에 완료되어야 한다.  
> @Disabled : 테스트 클래스/메소드 를 비활성화한다

<br>

## 테스팅 디자인
- 한 모듈 내의 독립적인 path들은 한번 이상 테스팅 되어야 한다  
- true/false의 측면에서만 모든 논리적 결정을 수행해야 한다  
- 모든 loop는 경계값 및 범위 내에 대해 수행해야 한다  
- 내부 데이터 구조의 유효성을 보장할 수 있도록 수행해야 한다  

<br>

## Basis Path Testing  
- White Box Testing의 일종이다.  
> BlackBoxTesting : input에 대한 output이 잘 나오는지에 대해 테스트한다.  
>                   내부 동작보다는 프로그램의 요구에 중점을 둔다.
> WhiteBoxTesting : 내부 동작의 검증에 대해 testing이 이루어진다.  
- 선형적으로 독립적인 실행 경로를 찾기 위해 프로그램의 제어 흐름 그래프를 분석한다.  
> Controll Flow Graph(CFG, 선형 제어 그래프)  
> edge(선)은 제어 흐름을 나타낸다.  
> node(원)은 하나 이상의 action을 나타낸다.  
> regions는 edgeㄴ와 nodes로 둘러쌓인 영역을 나타낸다.  
> 조건을 포함하는 node는 prediction node라고 한다.  
- 순환 복잡도를 사용하여 선형 독립 경로의 수를 결정한 다음, 얻은 각 경로에 대한 테스트 케이스를 생성한다.  
> 순환 복잡도 계산법  
> 1) (# of edges) - (# of nodes) + 2  
> 2) (# of predicate nodes) + 1  
> 3) (# of regions) + 1  
