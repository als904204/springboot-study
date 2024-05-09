# Spring 공부 정리 Repository

---

## Q 스프링 프레임워크란?
자바 기반의 오픈 소스로 개발자가 비즈니스 로직에 집중할 수 있도록 도움을 주는 프레임워크로 아래와 같은 기능들을 제공해준다.


  - 의존성 주입 (DI)
  - 관점 지향 프로그래밍 (AOP)
  - MVC 패턴
  - 트랜잭션 관리

---

## Q 스프링 컨테이란?
스프링 컨테이너는 애플리케이션의 빈을 생성, 관리하고 의존성을 주입해 주는 역할

스프링 컨테이너는 XML, 자바 설정, yaml or properties, 어노테이션등을 통해 빈을 정의하고 생성 함

스프링 컨테이너는 빈의 생명 주기를 관리하며, 필요한 의존성을 주입 해줌

---

## Q 빈(Bean)이란?
빈은 스프링 컨테이너에 의해 관리되는 객체

---

## Q 의존성 주입 (DI)란?

객체간의 의존 관계를 외부에서 주입하는 것.

스프링에서는 컨테이너가 등록된 빈 객체의 의존성을 관리하고 주입해준다.

아래 코드와 같이 **MyService** 클래스가 **MyRepository**를 사용하고 있다면 **MyService** 클래스가 **MyRepository**를 의존한다고 볼 수 있다.

### 만약 스프링 DI를 사용하지 않는다면?
```java
public class MyService {
    private MyRepository myRepository;

    public MyService() {
        this.myRepository = new MyRepository(); // MyService 가 MyRepository를 생성함. 강한 의존 결합
    }

    public void save() {
        myRepository.saveB();
    }
}

public class MyRepository{
    public void saveB(){
        // .. 저장
    }
}

```
위 코드와 같이  **MyService** 클래스가 **MyRepository**를 생성하게 되고 이는 강한 의존을 하게 된다.

이를 해결하기 위해 총 3가지의 의존성 주입 방법이 있다. 생성자, 세터, 필드 주입(여기서는 생성자 주입만 설명)

### 1. DI 사용

```java

public class MyService {

    private MyRepository myRepository;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository; // 생성자로 주입받기만 하고, 외부에서 누군가가 MyRepository 를 생성해줌 (스프링 컨테이너에 등록된 MyRepository Bean을 생성하여 주입함)
    }

    public void save() {
        myRepository.saveB();
    }
}

public class MyRepository {

    public void saveB() {
        // .. 저장
    }
}
```

위 코드를 보면 **MyService** 클래스가 **MyRepository**를 더 이상 생성하지 않고, 생성자로  **MyRepository**를 받기만 함

외부에서 누군가가  **MyRepository** 객체를 만들어줘서 넘겨줘야 함

### 2. DI 사용 
```java
@Configuration
public class AppConfig {

    @Bean
    public MyRepository myRepository() {
        return new MyRepository();
    }

    @Bean
    public MyService myService(MyRepository myRepository) {
        return new MyService(myRepository);
    }
}
```

스프링 컨테이너에 해당  **MyService** 클래스와 **MyRepository**를 Bean로 등록함으로 써 해당 객체의 생명주기를 스프링 컨테이너가 관리하게 된다.

즉 객체의 생성과 주입, 소멸 단계를 스프링 컨테이너가 함으로 써 **IOC**가 된다

또한 MyService 가 MyRepository 를 생성하는게 아니기 때문에 의존성이 약해진다. 이 때 의존성이 없어지는게 아닌 약해지는 것이다.

---

## Q IOC란?

제어의 역전(IOC)은 프로그램의 흐름을 개발자가 아닌 프레임워크가 관리하는 것

이는 객체의 생성과 생명주기 관리를 프레임워크가 담당하게 함으로써, 개발자는 비즈니스 로직에 더 집중할 수 있게 된다.

즉 아래 코드와 같이 스프링의 어노테이션 (@Service,@Component,@Configuration,@Repository... 등) 을 이용하게 되면 해당 클래스가 스프링의 컨테이너 Bean으로 등록되어 해당 객체의 생명중기를 컨테이너가 다루게 된다.

```java
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private MyRepository myRepository;

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository; // 생성자로 주입받기만 하고, 외부에서 누군가가 MyRepository 를 생성해줌 (스프링 컨테이너에 등록된 MyRepository Bean을 생성하여 주입함)
    }

    public void save() {
        myRepository.saveB();
    }
}

@Repository
public class MyRepository {

    public void saveB() {
        // .. 저장
    }
}
```

---

## Q AOP란?
관점 지향 프로그래밍으로, 비즈니스 로직과 공통 관심사를 분리하는 프로그래밍

쉽게 말하자면 어떤 메소드가 실행될 때 해당 메소드가 걸리는 시간을 알기 위해 아래와 같이 코드를 작성할 수 있다.

```java
public void helloMethod() {
    long start = System.currentTimeMillis();
    int a = 1 + 1;
    long end = System.currentTimeMillis();
    long elapsedTime = end - start;
    System.out.println("걸린시간: " + elapsedTime + " ms");
}
```
다른 메소드도 위 코드와 같이 걸리는 시간을 측정하고 싶은데, 그럼 매번 시간 계산을 해야한다. 이 때 이 공통적인 메소드들을 어노테이션이나 모듈로 따로 제작하여 간편하게 사용할 수 있도록 한다.

- 트랜잭션
- 로깅
- 보안

![aop.png](img/aop.png)

---

## Q Spring MVC Framework란?

스프링 MVC 패턴에서 요청 흐름은 다음과 같다

![mvc.jpeg](img/mvc.jpeg)

1. 클라이언트가 url을 요청하면, 웹 브라우저에서 스프링으로 request가 보내진다.
2. Dispatcher Servlet이 Request를 받으면, Handler Mapping을 통해 해당 url을 담당하는 Controller를 탐색 후 찾아낸다.
3. 찾아낸 Controller로 request를 보내주고, 보내주기 위해 필요한 Model을 구성한다.
4. Model에서는 페이지 처리에 필요한 정보들을 DataBase에 접근하여 쿼리문을 통해 가져온다.
5. 데이터를 통해 얻은 Model 정보를 Controller에게 response하면 Controller는 이를 받아 Model을 완성시켜 Dispatcher Servlet에게 전달해준다.
6. Dispatcher Servlet은 View Resolver를 통해 request에 해당하는 view파일을 탐색 후 받아낸다.
7. 받아낸 View페이지에 파일에 Model을 보낸 후, 클라이언트에게 보낼 페이지를 완성시켜 받아낸다.
8. 완성된 View파일을 클라이언트에 response하여 화면에 출력한다.


**Dispatcher Servlet** 

    (Front Controller라고 보면 됨)
    모든 request 를 처리하는 중심 컨트롤러라고 보면 된다.
    
    서블릿 컨테이너에서 http프로토콜을 통해 들어오는 모든 request에 대해 제일 앞단에서 중앙집중식으로 처리해주는 역할을 한다.

**Handler Mapping**

    클라이언트의 request uri을 어떤 컨트롤러가 처리해야 할 지 찾아서 Dispatcher Servlet에게 전달해주는 역할을 담당한다
    
    ("/api/v1/users/") 요청이 들어오기 전에 Handler Mapping 이 해당 컨트롤러를 찾아줌

**Controller**

    실질적인 요청을 처리하는 곳이다. 
    
    Dispatcher Servlet이 프론트 컨트롤러라면, 이 곳은 백엔드 컨트롤러라고 볼 수 있다.
    
    모델의 처리 결과를 담아 Dispatcher Servlet에게 반환해준다.


**View Resolver**

    컨트롤러의 처리 결과를 만들 view를 결정해주는 역할을 담당한다. 다양한 종류가 있기 때문에 상황에 맞게 활용하면 된다.
    


