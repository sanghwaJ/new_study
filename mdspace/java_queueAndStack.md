# Java - Queue & Stack

## 1-1. Queue
- 가장 먼저 넣은 데이터를 가장 먼저 꺼낼 수 있는 구조 (FIFO)
- 멀티태스킹을 위한 프로세스 스케줄링 방식을 구현하기 위해 많이 사용됨

## 1-2. Queue의 주요 기능
- Enqueue : 큐에 데이터를 넣는 기능
- Dequeue : 큐에서 데이터를 꺼내는 기능

## 1-3. Java에서 Queue 사용하기
- java.util 패키지의 Queue 클래스 사용
- Enqueue = add(value) OR offer(value)
- Dequeue = poll() OR remove()
- Queue 클래스에서 데이터 생성을 위해서는 java.util 패키지의 LinkedList 클래스를 사용해야 함

```java
import java.util.LinkedList;
import java.util.Queue;

// 선언
Queue<Integer> queue_int = new LinkedList<Integer>();
Queue<String> queue_str = new LinkedList<String>();

// 데이터 추가
queue_int.add(1);
queue_int.offer(2);

System.out.println(queue_int); // [1, 2]

// 데이터 삭제
queue_int.poll();
System.out.println(queue_int); // [2]

queue_int.remove();
System.out.println(queue_int); // []
```

```java
// ArrayList로 Queue 구현하기

import java.util.*;

// Java 제네릭 타입 활용
public class MyQueue<T>{
    private ArrayList<T> queue = new ArrayList<T>();
    // enqueue
    public void enqueue(T item){
        queue.add(item);
    }
    // dequeue
    public T dequeue(){
        if(queue.isEmpty()){
            return null;
        }
        return queue.remove(0);
    }
    // isNull check
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}

public static void main(String[] args) {
    MyQueue<Integer> mq = new MyQueue<Integer>();

    mq.enqueue(1);
    mq.enqueue(2);
    mq.enqueue(3);

    System.out.println(mq.dequeue());
} 
```

## 1-4. add() VS offer()
- add() : Queue에 값을 추가한 경우 true를 반환하며, Queue가 가득차서 더이상 추가할 수 없는 경우 IllegalStateException throw
- offer() : Queue에 값을 추가한 경우 true를 반환하며, Queue가 가득차서 더이상 추가할 수 없는 경우 false 반환(예외 throw를 하지 않음)

## 1-5. remove() VS poll()
- remove() : Queue 맨 앞에 있는 값을 반환한 뒤 삭제하며, Queue가 비어있는 경우 NoSuchElementException throw
- poll() : Queue 맨 앞에 있는 값을 반환한 뒤 삭제하며, Queue가 비어있는 경우 null 반환

## 2-1. Stack
- 가장 나중에 쌓은 데이터를 가장 먼저 꺼낼 수 있는 구조 (LIFO)

## 2-2. Stack의 주요 기능
- push : Stack에 데이터를 넣는 기능
- pop : Stack에서 데이터를 꺼내는 기능

## 2-3. Java에서 Stack 사용하기
- java.util 패키지에서 Stack 클래스 사용

```java
import java.util.Stack;

// 선언
Stack<Integer> stack_int = new Stack<Integer>();

// 데이터 추가
stack_int.push(1);
stack_int.push(2);
stack_int.push(3);

// 데이터 삭제
stack_int.pop()
```

```java
// ArrayList로 Stack 구현하기

import java.util.ArrayList;

// Java 제네릭 타입 활용
public class MyStack<T>{
    private ArrayList<T> stack = new ArrayList<T>();
    // push
    public void push(T item){
        stack.add(item);
    }
    // pop
    public T pop(){
        if(stack.isEmpty()){
            return null;
        }
        return stack.remove(stack.size() - 1);
    }
    // isNull check
    public boolean isEmpty(){
        return stack.isEmpty();
    }
}

public static void main(String[] args) {
    MyStack<Integer> mq = new MyStack<Integer>();

    mq.push(1);
    mq.push(2);
    mq.push(3);

    System.out.println(mq.pop());
} 
```