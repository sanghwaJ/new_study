# Java - 상속

### Father 클래스

```Java
public class Father { // extends Object는 디폴트 상속 (hashCode, toString 등등)
	private int priVal; //X
	protected int proVal;
	public int pubVal;
	
	// private 풀어주기, 이 함수가 제공되어야 priVal 사용 가능
	public void setPri(int x) {
		priVal = x;
	}
	
	public Father() {
		System.out.println("Father constructor call");
	}

	public Father(int priVal, int proVal, int pubVal) {
		System.out.println("arg Father constructor call");
		this.priVal = priVal;
		this.proVal = proVal;
		this.pubVal = pubVal;
	}

	public void pubFn() {
		System.out.println("pubFn");
	}

	protected void proFn() {
		System.out.println("proFn");
	}

	private void priFn() { // X
		System.out.println("priFn");
	}
}
```

### Child 클래스

```Java
// private 권한을 제외하고 상속받음
public class Child extends Father { 
	public int childVal;

	public Child() {
		System.out.println("Child constructor call");
	}
	
	public Child(int childVal) {
		// 상위 클래스 Father의 인자를 3개받는 생성자호출
		super (30,40,50); 
		System.out.println("arg Child constructor call");
		this.childVal = childVal;
	}

	public void chFn() {
//		prival = 30; // X
//		priFn();
		proVal = 30;
		proFn();
	}
}	

/*
- 상속받은 것 -
protected int proVal;
public int pubVal;

public void pubFn() {
    System.out.println("pubFn");
}

protected void proFn() {
    System.out.println("proFn");
}
*/
```

### Body 클래스

```Java
public class Baby extends Child {
	public int babyVal;
	
	public Baby() {
		System.out.println("Baby constructor call");
	}
	
	public Baby(int babyVal) {
        /*
		baby의 상위 클래스인 child의 인자가 하나인 생성자 호출하고 20을 넣는다
        (꼭 생성자를 디폴트값으로 호출할필요 X) 
		꼭 맨 앞쪽에 써야한다
        */
		super(20); 
		System.out.println("arg Baby constructor call");
		this.babyVal = babyVal;
	}

	public void babyFn() {
		proVal = 40;
	}
	
	public void babyFn1() {
//		priVal = 50; // private은 상속 불가하기 때문에 접근 불가
	}
}
/*
- 상속받은 것 -
public int childVal;

protected int proVal;
public int pubVal;

public void pubFn() {
    System.out.println("pubFn");
}
	
protected void proFn() {
    System.out.println("proFn");
}
*/
```

### main 1

```Java
/*
- protected 동일 패키지 내에서는 내부 외부 접근이 가능하지만, 패키지가 다르면 내부에서만 사용 가능하다.

- private은 외부로 호출이 안되고, 내부에서만 호출이 가능하며, private은 상속받지 않는다.
- public은 내부 외부 접근 가능
*/

public class study40 {
	public static void main(String[] args) {
		Child child = new Child();
		child.childVal = 10;
		
		// private은 외부로 호출이 안되고, 
        // 내부에서만 호출이 가능하며, private은 상속받지 않는다.

//		child.priVal = 20; 
		
//		child.proVal = 30;
		child.pubVal = 40;
		
//		child.proFn();
		child.pubFn();
//		child.priFn();
	}
}

```

### main 2

```Java
public class study41 {
	public static void main(String[] args) {
		Baby baby = new Baby();
		baby.babyVal = 10;
		baby.childVal = 20;
		baby.pubVal = 30;
		
		// 패키지 클래스 내부에서만 사용가능
//		baby.proVal = 40; 
		
		baby.pubFn();

	}
}
```

### main 3

```Java
public class study42 {
	public static void main(String[] args) {
        /*
		- 메모리 할당 구조 - heap(proVal, pubVal, priVal, childVal, babyVal)
		- stack(baby) stack의 baby는 heap의 시작주소를 가지고있음
        */

		Baby baby = new Baby();
		baby.pubVal = 10;
		baby.childVal = 20;
		baby.babyVal = 30;
		
		// 직접접근불가, 함수호출
		// baby.proVal = 40; 
		// baby.babyFn(baby) (this.proVal(this가 받음))
		baby.babyFn();
		
		// 직접접근불가, 원 소유자인 Father 클래스에서 풀어줘야 함
		// baby.priVal = 50; 
		// baby.setPri(baby, 50)
		baby.setPri(50);
	}
}
```