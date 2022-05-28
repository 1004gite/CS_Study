## SOLID 원칙  
> - SRP(Single Responsibility Principle)  
> 한 클래스는 하나의 책임(역할)만 가져야 한다.  
> 
> 
> - OCP(Open Close Principle)  
> 소프트웨어 요소는 확장에는 열려있고, 변경에는 받혀 있어야 한다.  
> > OPC는 (Interface와) 상속을 이용한다.  
> > ex) Car라는 interface가 있고 이를 상속받는 여러 종류의 자동차가 있다.  
> > 이때, 새로운 차량 class를 만드려면(확장) 기존의 Car를 변경하지 않고 Car를 상속받아 구현한다.  
> 
> 
> - LSP(Liskov Substitution principle)  
> 객체는 프로그램의 정확성을 깨트리지 않으면서 하위 타입의 instance로 바꿀 수 있어야 한다.  
> > 어떤 class를 상속받는 자식 class를 만들 때 자식 class는 부모 class의 역할을 수행할 수 있어야 한다.  
> > 만약 수행할 수 없다면 부모 class를 사용하는 함수에 자식 class를 넣게 되면 프로그램의 정학성이 깨지게 된다.  
> > ex) Rctangle이라는 class에 w,h 변수가 있고 setter가 있다고 하자.  
> > "정사각형은 직사각형이다" 라는 명제에 따라 Square class가 Rectangle class를 상속받는다.  
> > 이떄, 정사각형은 가로,세로가 같기 때문에 w,h중 하나를 setting하면 나머지도 같게 해야한다.  
> > 만약, 가로, 세로를 5,6 으로 세팅하고 넓이를 구하는 함수에 두 class를 이용한다면 다른 값이 나와 프로그램의 정확성이 꺠지게 된다.  
> 
> 
> - ISP(Interface Separate Principle)  
> 특정 클라이언트를 위한 인터페이스 여러개가 범용 인터페이스 한개보다 낫다.  
> > ex) Car Interface에 Wheel, Handle에 대한 method가 무두 있다고 하자.  
> > 만약 핸들이 없는 자율주행 자동차 class를 생성하려면 해당 class는 불필요한 handle method까지 상속받아야 한다.  
> > 때문에, Handle, Wheel을 각각의 Interface로 나누는 것이 효율적이다.  
> 
> 
> - DIP(Dependency Inversion Principle)
> 구체화가 아닌 추상화에 의존해야 한다, 상위 객체와 하위 객체 모두 추상화에 의존해야 한다.  
> > ex) 모니터의 종류가 {크기, 패널의 종류, 전압}에 의해 10종류가 있다고 하자.  
> > 하위 객체에서 모니터를 상속받을 때 각각의 class를 상속받으면 하위 객체는 상위 객체의 모니터 하나하나에 의존적이게 된다.  
> > 만약 Monitor라는 Interface가 있고 각 모니터가 이를 상속받으면 하위 객체는 Monitor Interface를 사용하므로써 상위 객체의 구현으로부터 자유로원진다.  

## mvc  
Model, View, Controller로 이루어져 있다.  
> - Model  
> Model은 Controller의 요청을 받아 작업을 처리하고 View를 업데이트한다.  
> 
> 
> - View  
> View는 Controller에 Event를 전달한다.
> 
> 
> - Controller  
> Controller는 View에게 Event를 받고 Model에 작업을 요청한다.  
> 간단한 작업의 경우 직접 View를 업데이트 할 수 있다.  


## mvp  
Model, View, Presenter로 이루어져 있다.  
> - Model
> 사용자 데이터 및 View가 필요한 정보를 가자고 있다.  
> 
> 
> - View  
> View는 Presenter에게 Event를 전달한다.
> 
> 
> - Presenter  
> Presenter는 View에게 Event를 전달받고 Model의 데이터를 가공한다.  
> 이후, 필요하다면 View를 변경해준다.

## mvvm  
Model, View, ViewModel로 이루어져 있다.  
> - Model  
> View가 필요한 데이터 및 사용자 데이터, 데이터 베이스 등을 포함한다.
> 
> 
> - View  
> View는 Model의 데이터를 Observe한다. 이때 데이터바인딩을 사용할 수 있다.  
> Event 발생시 ViewModel에 이벤트 발생을 알린다.  
> 
> 
> - ViewModel  
> ViewModel은 View에서 Event를 받고 Model의 데이터를 바꾼다.  
> 
> 
> - Acc ViewModel  
> Acc ViewModel의 경우 ViewModel과 역할이 같으나 데이터의 생명주기를 같이 관리할 수 있다.  
> Actvity가 finish하기 전까지 유지된다. 이떄, Activity의 Context를 가지게 되면 Activity가 finish되어도 객체가 없어지지 않을 수 있으니 주의해야 한다.  


## Singleton  
하나의 class에 대해 하나의 전역 instance만을 소유한다.  
> - instace를 전역으로 공유하기 때문에 데이터의 공유가 쉽다.  
> - 멀티 쓰레드 환경에서는 syncronized 키워드를 이용하는 것이 좋고 dead lock에 유의해야 한다.  
> 
> 