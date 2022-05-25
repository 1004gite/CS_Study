## mvc  


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
> 