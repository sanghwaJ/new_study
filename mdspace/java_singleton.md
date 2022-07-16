# Java - 싱글톤 패턴

## 싱글톤 패턴이란?

애플리케이션이 시작될 때 어떤 클래스가 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인패턴.

생성자가 여러번 호출되더라도 실제로 생성되는 객체는 하나이며, 최초 생성 이후에 호출된 생성자는 최초에 이미 생성한 객체를 반환한다. (자바에선 생성자를 private로 선언해서 생성 불가하게 하고 getInstance()로 받아쓰기도 함)

=> 싱글톤 패턴은 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴이다. (인스턴스가 필요 할 때 똑같은 인스턴스를 만들어 내는 것이 아니라, 동일(기존) 인스턴스를 사용하게함)

<br/>

## 싱글톤 패턴, 왜 쓰는가?

고정된 메모리 영역을 얻으면서 한번의 new로 인스턴스를 사용하기 때문에 메모리 낭비를 방지할 수 있음.

또한 싱글톤으로 만들어진 클래스의 인스턴스는 전역 인스턴스이기 때문에 다른 클래스의 인스턴스들이 데이터를 공유하기 쉽다.

DBCP(DataBase Connection Pool)처럼 공통된 객체를 여러개 생성해서 사용해야하는 상황에서 많이 사용. (쓰레드풀, 캐시, 대화상자, 사용자 설정, 레지스트리 설정, 로그 기록 객체등)

안드로이드 앱 같은 경우 각 액티비티나 클래스별로 주요 클래스들을 일일이 전달하기가 번거롭기 때문에 싱글톤 클래스를 만들어 어디서나 접근하도록 설계하는 것이 편하기 때문...

(+ 인스턴스가 절대적으로 한개만 존재하는 것을 보증하고 싶을 경우 사용.)

(+ 두 번째 이용시부터는 객체 로딩 시간이 현저하게 줄어 성능이 좋아지는 장점!)

<br/>

## 싱글톤 패턴의 단점?

싱글톤 인스턴스가 너무 많은 일을 하거나 많은 데이터를 공유시킬 경우 다른 클래스의 인스턴스들 간에 결합도가 높아져 "개방-폐쇄 원칙" 을 위배하게 된다. (= 객체 지향 설계 원칙에 어긋남)

이렇듯, 결합도가 높아지게되면 수정이 어려워지고 테스트하기 어려워진다.

또한 멀티쓰레드환경에서 동기화처리를 안하면 인스턴스가 두개가 생성된다든지 하는 경우가 발생할 수 있음.

따라서, 반드시 싱글톤 패턴이 필요한 상황이 아니면 지양하는 것이 좋다고 한다..

## ※ 싱글톤 패턴 예시

```Java
package study3.my;

//Singletone 단일 객체: 로딩타임에 생성된 객체를 사용, 런타임시에는 객체생성불가 
class MySingle {
	private int a;
	private int b;
	
	// 자신의 클래스를 static으로 has-a
	private static MySingle instance = new MySingle(); 

	// 외부에서 호출할 수 있는 메소드, 자신의 객체 리턴
	public static MySingle getInstance() { 
		return instance; // MySingle.instance
	}

	public void SetAB(int x, int y) {
		a = x;
		b = y;
	}

	public int GetA() {
		return a;
	}

	public int GetB() {
		return b;
	}

	private MySingle() {
	} // (private 생성자를 둔다)
}

public class study37 {

	public static void main(String[] args) {
		// 주소값만 리턴받는다 (garbage collector 대상이 되지 않음)
		MySingle obj = MySingle.getInstance(); 
		
		obj.SetAB(10, 20); // obj.SetAB(obj, 10, 20);

		System.out.println(obj.GetA()); // MySingle obj = new MySingle();

		MySingle obj1 = MySingle.getInstance();
		
		System.out.println(obj1.GetB());
	}
}
```

<br/>

## ※ 멀티스레드 환경에서 안전한 싱글톤 패턴 만드는 법

### 1. Lazy Initialization(초기화 지연)

```Java
public class ThreadSafe_Lazy_Initialization{
    // private static으로 인스턴스 변수 생성
    private static ThreadSafe_Lazy_Initialization instance;
    //private로 생성자를 만들어 외부에서의 생성을 막음
    private ThreadSafe_Lazy_Initialization(){}
    // synchronized 동기화를 활용해 스레드를 안전하게 만듬 
    public static synchronized ThreadSafe_Lazy_Initialization getInstance(){
        if(instance == null){
            instance = new ThreadSafe_Lazy_Initialization();
        }
        return instance;
    }
}
```

### 2. Lazy Initialization + Double-checked Locking (1번의 성능 저하 완화)

=> 안전하게 스레드를 만들면서, 처음 생성 이후에는 synchronized를 실행하지 않기 때문에 성능 저하 완화 가능

```Java
public class ThreadSafe_Lazy_Initialization{
    private volatile static ThreadSafe_Lazy_Initialization instance;

    private ThreadSafe_Lazy_Initialization(){}

    public static ThreadSafe_Lazy_Initialization getInstance(){
        // 1번과 달리 조건문으로 인스턴스의 존재 여부 확인
    	if(instance == null) {
            // 조건문에서 synchronized를 통해 동기화시켜 인스턴스 생성
        	synchronized (ThreadSafe_Lazy_Initialization.class){   
                if(instance == null){
                    instance = new ThreadSafe_Lazy_Initialization();
                }
            }
        }
        return instance;
    }
}
```

### 3. Initialization on demand holder idiom (holder에 의한 초기화)

=> 클래스 안에 클래스(holder)를 두어 JVM의 클래스 로더 매커니즘과 클래스가 로드되는 시점을 이용한 방법 (실제로 가장 많이 사용되는 방법)

```Java
public class Something {
    private Something() {
    }
    
    private static class LazyHolder {
        // static이기 때문에 클래스 로딩 시점에서 한 번만 호출
        // final을 사용해 다시 값이 할당되지 않도록 함
        public static final Something INSTANCE = new Something();
    }
 
    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```