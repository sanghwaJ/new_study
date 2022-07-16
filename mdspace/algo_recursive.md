# 재귀용법 (Recursive Call)

## 1) 재귀용법 (Recursive Call, 재귀호출)
- 함수 안에서 동일한 함수를 호출하는 형태
- 코드를 작성할 때, 일정한 규칙(패턴)을 파악하는 것이 중요
- (참고) Python의 재귀호출은 1000번까지만으로 제한됨

## 2) 팩토리얼 함수의 시간복잡도와 공간복잡도
- factorial(n)은 n-1번의 factorial() 함수를 호출 (n-1번 반복문을 호출한 것과 동일)
- factorial() 함수를 호출할 때마다 지역변수 n이 생성됨
- 시간복잡도, 공간복잡도는 둘 다 $O(n)$

```python
# 팩토리얼

def factorial(num):
    if num > 1:
        return num * factorial(num - 1)
    else:
        return num

for i in range(10):
    print(factorial(i))
```

## 3) 재귀용법의 일반적인 형태
```python
# 일반적인 형태1
def func1(입력):
    if 입력 > 일정값: # 입력이 일정 값 이상이면
        return func1(입력 - 1) # 입력보다 작은 값
    else:
        return 일정값 or 입력값 # 재귀 호출 종료

# 일반적인 형태2
def func2(입력):
    if 입력 <= 일정값: # 입력이 일정 값보다 작으면
        return 일정값 or 입력값 # 재귀 호출 종료
    func2(입력보다 작은값)
    return 결과값
```

## 4) 재귀용법과 스택
- 재귀용법은 스택의 전형적인 예로, 함수 내부적으로 스택처럼 관리됨

## 5) 재귀용법을 활용한 프로그래밍 연습
```python
# 재귀 함수를 활용하여 1부터 num 까지의 곱이 출력되게 만들기

def multiple(data):
    if data <= 1:
        return data
    else:
        return data * multiple(data - 1)

multiple(10)
```

```python
# 숫자가 들어있는 리스트가 주어졌을 때, 리스트의 합을 리턴하는 함수 만들기

import random
data = random.sample(range(100), 10)
print(data)

def sum_list(data):
    if len(data) <= 1:
        return data[0]
    else:
        return data[0] + sum_list(data[1:])

sum_list(data)
```

```python
# 회문 판단하기(리스트 슬라이싱)

def palindrome(string):
    if len(string) <= 1:
        # 원래 string의 길이가 1이거나, 범위를 계속 좁혀 길이가 1만 남았으면 회문이므로 True
        return True 
    
    if string[0] == string[-1]:
        return palindrome(string[1:-1]) # 재귀를 이용하여 계속해서 범위를 좁혀나감
    else:
        return False
```

### 예제 1)
- 정수 n에 대해 n이 홀수이면 3 * n + 1을 하고, n이 짝수이면 n을 2로 나눔
- n이 1이 될 때까지 작업을 반복
  
```python
def func(n):
    print(n)
    if n == 1:
        return n
    
    if n % 2 == 1:
        return (func((3 * n) + 1))
    else:
        return (func(int(n / 2)))

func(100)
```

### 예제 2)
- 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타낼 수 있는 방법의 수 구하기
- 패턴을 찾아 보면, f(n) = f(n-1) + f(n-2) + f(n-3)과 동일 (꼭 패턴을 찾고, 로직을 찾아보자!)

```python
def func(data):
    if data == 1:
        return 1
    elif data == 2:
        return 2
    elif data == 3:
        return 4

    return func(data - 1) + func(data - 2) + func(data - 3)

func(10)
```