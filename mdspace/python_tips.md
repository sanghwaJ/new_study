# Python Tips

## 1) list comprehension

### 1. list comprehension
- 직관적으로 리스트를 생성하는 방법으로, 기존 방법보다 실행 속도도 더 빠르며, 코드 생산성을 높여준다.

### 2. 사용법
```python
# 기존 사용법
li=[]
for i in range(5):
    li.append(i)

# list comprehension
[i for i in range(5)]
list(range(5))
```

### 3. 응용
```python
# 3-1. 수식 적용
[i*10 for i in range(5)] # [0, 10, 20, 30, 40]

# 3-2. 함수 적용
def test(x):
    x = str(x)+'ab'
    return x
 
[test(i) for i in range(5)] # ['0ab', '1ab', '2ab', '3ab', '4ab']

# 3-3. 조건문 사용 1 (오른쪽에 if문 사용)
[i for i in range(5) if i % 2 == 0] # [0, 2, 4]
[i for i in range(5) if i % 2 == 0 if i % 4 == 0] # [0, 4]

# 3-4. 조건문 사용 2 (왼쪽에 if문 사용)
[i if i % 2 == 0 else 'odd' for i in range(5)] 
# [0, 'odd', 2, 'odd', 4]

[i if i % 2 == 0 else 'odd-1' if i == 1 else 'odd-3'  for i in range(5)] 
# [0, 'odd-1', 2, 'odd-3', 4]

# 3-5. 이중 for문
[(i,j) for i in range(2) for j in range(3)]
# [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2)]
# 위의 코드는 아래와 같음
li=[]
for i in range(2):
    for j in range(3):
        li.append((i,j))
```

### 4. set comprehension
- 리스트 컴프리헨션에서 쓰는 대괄호([ ])대신, 중괄호({ })를 사용하면 된다.
```python
{i for i in range(5)} # {0, 1, 2, 3, 4}

{i:i for i in range(5)} # {0:0, 1:1, 2:2, 3:3, 4:4}
```

## 2) pass, continue, break

### 1. pass
- 아무것도 하지 않음
- 문법적으로 문장이 필요하지만, 프로그램이 특별히 할 일이 없을 때 사용
```python
# 빈 함수
def function():
    pass

# 빈 클래스
class my_class():
    pass
```

### 2. continue
- 특정 Loop에서 건너뛰고 싶을 때 사용
```python
for i in range(10):
    if i == 7:
        print("continue")
        continue
    print(i)
```

### 3. break
- 특정 Loop에서 건너뛰고, 종료
```python
i = 0

while True:
    i += 1
    if i == 7:
        print("continue")
        continue
    print(i)
    if i == 10:
        print("break")
        break
```

## 3) dictionary

### 1. 기본 dictionary 구성
```python
dic = {'name':'pey', 'phone':'0119993323', 'birth': '1118'}

# dictionary in list
a = { 'a': [1,2,3]}
```

### 2. dictionary 활용
```python
# 2-1. dictionary 쌍 추가하기
x = {1 : 'a'}
x[2] = 'b'

print(x) # {1 : 'a', 2 : 'b'}

x['name'] = 'king'

print(x) # {1 : 'a', 2 : 'b', 'name' : 'king'}
```
```python
# 2-2. dictionary 요소 삭제
x = {1 : 'a'}
x[2] = 'b'

print(x) # {1 : 'a', 2 : 'b'}

del x[1]

print(X) # {2 : 'b'}
```
```python
# 2-3. key를 사용해 value 얻기
x = {1 : 'a', 2 : 'b'}

print(x[1]) # 'a'
print(x[2]) # 'b'
```

### 3. dictionary 주의사항
- 딕셔너리에서 key는 중복을 허용하지 않기 때문에, 중복된 key가 있다면 하나를 제외한 나머지 것들을 무시함
```python
a = {1:'a', 1:'b'}
print(a) # {1: 'b'}
```

### 4. dictionary 관련 함수
```python
# 4-1. keys() : 딕셔너리의 key만 모아서 dict_keys 리스트를 리턴
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}

print(a.keys()) # dict_keys(['name', 'phone', 'birth'])
print(list(a.keys()) # ['name', 'phone', 'birth']
```

```python
# 4-2. values() : 딕셔너리의 value만 모아서 dict_values 리스트를 리턴
# 주의! values()는 list(a.values())처럼 사용이 불가
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}

print(a.values()) # dict_values(['pey', '0119993323', '1118'])
```

```python
# 4-3. items() : 딕셔너리의 key와 value 쌍을 튜플로 묶어 리스트를 리턴
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}

print(a.items()) # dict_items([('name', 'pey'), ('phone', '0119993323'), ('birth', '1118')])
```

```python
# 4-4. get() : key를 이용하여 value를 리턴
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}

print(a.get('name')) # 'pey'
```

```python
# 4-5. 해당 key가 딕셔너리 안에 있는지 조사하기 (in)
a = {'name': 'pey', 'phone': '0119993323', 'birth': '1118'}

'name' in a # True
'email' in a # False
```

## 4) lambda
- 이름이 없는 함수, 익명함수라고도 하며, 함수 사용을 간단하게 할 수 있도록 도와주는 파이썬 내장 라이브러리

### 1. lambda 활용
```python
# 1-1. lambda 매개변수 : 표현식
 
def add(n, m):
    return n + m
print(add(2, 3))
#5
 
print((lambda n,m : n + m)(2, 3))
#5
```

```python
# 1-2. lambda를 변수에 할당하여 재사용
 
lambdaAdd = lambda n, m : n + m
print(lambdaAdd(2, 3))
#5 
print(lambdaAdd(4, 5))
#9
```

```python
# 1-3. lambda 안에서 조건 사용

print((lambda n, m : n if n % 2 == 0 else m)(1, 3))
#3
print((lambda n, m : n if n % 2 == 0 else m)(2, 3))
#2
```

```python
# 1-4. lambda 함수를 이용하여 작성한 map, filter, reduce

lst = list(range(1, 11))
print(lst) # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# map() : 함수와 리스트를 인자로 받아 리스트의 원소를 하나씩 꺼내어 함수를 적용
m = list(map(lambda n : n * n, lst))
print(m) # [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
 
# filter() : 함수와 리스트를 인자로 받아 리스트의 원소를 하나씩 꺼내어 함수에 적용시킨 결과가 참인 것들로 새로운 리스트를 만듬
f = list(filter(lambda n : n % 2 == 0, lst))
print(f) # [2, 4, 6, 8, 10]

# reduce() : 함수와 문자열, 튜플, 리스트 등을 인자로 받아 원소들을 누적하여 함수에 적용
r = reduce(lambda n, m : n * m, lst)
print(r) # 3628800
```

## 5) sorted
- 데이터 정렬(숫자, 문자)을 도와주는 파이썬 내장 함수로, list.sort()로 사용하는 sort와 달리 변수를 지정하여 사용

### 1. list sorted
```python
a = [2, 4, 1, 9, 100, 29, 40, 10]

# 오름차순
b = sorted(a) 
# 내림차순
c = sorted(a, reverse=True)
```

### 2. dictionary sorted
```python
d = dict()
d['a'] = 66
d['i'] = 20
d['e'] = 30
d['d'] = 33
d['f'] = 50
d['g'] = 60
d['c'] = 22
d['h'] = 80
d['b'] = 11
 
# 1. 딕셔너리 출력
print("\n1. 기본 딕셔너리")
print(d)
 
# 딕셔너리 키 정렬 오름차순(디폴트)
print("\n2. 키 정렬 sorted(d.items())")
f = sorted(d.items())
print(f)
 
# 딕셔너리 키 정렬 내림차순
print("\n3. 키 정렬 sorted(d.items(), reverse=True)")
g = sorted(d.items(), reverse=True)
print(g)
 
# 딕셔너리 키만 정렬 및 출력1
print("\n4. 키만 정렬 sorted(d.keys())")
h = sorted(d.keys())
print(h)
 
# 딕셔너리 키만 정렬 및 출력2
print("\n5. 키만 정렬 sorted(d)")
i = sorted(d)
print(i)
```

### 3. dictionary value sorted (with Operator)
```python
import operator
 
d = {'b': 400, 'f': 300, 'a': 200, 'd': 100, 'c': 500}
 
print('1. 원본 딕셔너리')
print(d.items())
 
 
print('\n2. 딕셔너리 정렬 : sorted(d.items())')
f = sorted(d.items())
print(f)
 
 
print('\n3. 딕셔너리 정렬 : sorted(d.items(), key=operator.itemgetter(1))')
g = sorted(d.items(), key = operator.itemgetter(1))
print(g)
 
 
print('\n4. 딕셔너리 정렬 : sorted(d.items(), key=operator.itemgetter(1), reverse=True)')
h = sorted(d.items(), key = operator.itemgetter(1), reverse=True)
print(h)
```

### 3. dictionary value sorted (with lambda)
```python
d = {'blockdmask': 400, 'equal': 300, 'apple': 200, 'dish': 100, 'cook': 500}
 
print('1. 원본 딕셔너리')
print(d.items())
 
 
print('\n2. 딕셔너리 정렬 : sorted(d.items())')
f = sorted(d.items())
print(f)
 
 
print('\n3. 딕셔너리 정렬 : sorted(d.items(), key=lambda x: x[1])')
g = sorted(d.items(), key=lambda x: x[1])
print(g)
 
 
print('\n4. 딕셔너리 정렬 : sorted(d.items(), key=lambda x: x[1], reverse=True)')
h = sorted(d.items(), key=lambda x: x[1], reverse=True)
print(h)
```

## 6) 순열과 조합

### 1. 순열(Permutations)
- 서로 다른 n개에서 r개를 선택할 때, 순서를 고려하여 선택한 경우의 수를 나열하는 방법(AB와 BA를 서로 다른 것으로 여긴다는 것)
```python
from itertools import permutations

array = ['A', 'B', 'C', 'D']

permute = list(permutations(array, 2))

print(permute)
# [('A', 'B'), ('A', 'C'), ('A', 'D'), ('B', 'A'), ('B', 'C'), ('B', 'D'), ('C', 'A'), ('C', 'B'), ('C', 'D'), ('D', 'A'), ('D', 'B'), ('D', 'C')]
```

### 2. 조합(Combination)
- 서로 다른 n개에서 r개를 선택할 때, 순서를 고려하지 않고​ 경우의 수를 나열하는 방법(AB와 BA를 같은 것으로 여긴다는 것)
```python
from itertools import combinations

array = ['A', 'B', 'C', 'D']

permute = list(combinations(array, 2))

print(permute)
# [('A', 'B'), ('A', 'C'), ('A', 'D'), ('B', 'C'), ('B', 'D'), ('C', 'D')]
```

### 3. 중복순열(Product)
- 순열과 달리, 한 번 고른 것을 중복하여 다시 고르는 것(A, B, C, D에서 AA를 허용)
```python
from itertools import product

array = ['A', 'B', 'C', 'D']
string = 'ABCD'

array_rst1 = list(product(array, repeat = 2))
array_rst2 = [''.join(a) for a in array_rst1]

string_rst1 = list(product(string, repeat = 2))
string_rst2 = [''.join(b) for b in string_rst1]

print(array_rst1)
# [('A', 'A'), ('A', 'B'), ('A', 'C'), ('A', 'D'), ('B', 'A'), ('B', 'B'), ('B', 'C'), ('B', 'D'), ('C', 'A'), ('C', 'B'), ('C', 'C'), ('C', 'D'), ('D', 'A'), ('D', 'B'), ('D', 'C'), ('D', 'D')]
print(array_rst2)
# ['AA', 'AB', 'AC', 'AD', 'BA', 'BB', 'BC', 'BD', 'CA', 'CB', 'CC', 'CD', 'DA', 'DB', 'DC', 'DD']
print(string_rst1)
# [('A', 'A'), ('A', 'B'), ('A', 'C'), ('A', 'D'), ('B', 'A'), ('B', 'B'), ('B', 'C'), ('B', 'D'), ('C', 'A'), ('C', 'B'), ('C', 'C'), ('C', 'D'), ('D', 'A'), ('D', 'B'), ('D', 'C'), ('D', 'D')]
print(string_rst2)
# ['AA', 'AB', 'AC', 'AD', 'BA', 'BB', 'BC', 'BD', 'CA', 'CB', 'CC', 'CD', 'DA', 'DB', 'DC', 'DD']
```

### 4. 주의할점
- permutations 함수와 combinations 함수 모두 인자값을 리스트와 int형으로 받고 있는데, 두번째 인자값은 꼭 int형으로 들어와야하나, 첫번째 인자값은 리스트, 튜플, 문자열, 집합, 딕셔너리 모두 인자값으로 사용할 수 있다.
- 하지만 집합이나 딕셔너리에서는 자주 사용하지 않으며, 주로 리스트나 튜플, 문자열 자료형과 같이 반복 가능(iterable)한 자료형에 쓰인다.
- list()나 tuple()로 캐스팅을 해주어야 우리가 원하는대로 list나 tuple로 반환을 해준다.
  
## 7) collections
- 리스트, 튜플, 딕셔너리 등을 확장하여 제작된 파이썬의 내장 모듈.
- deque, defaultdict, Counter, namedtuple 등을 제공한다.

### 1. deque
- stack with deque
  - append() : 새로운 값을 오른쪽부터 입력
  - pop() : 가장 나중에 들어간 값(오른쪽부터) pop
```python
from collections import deque

deque_list = deque()
for i in range(5):
    deque_list.append(i)

print(deque_list) # deque([0, 1, 2, 3, 4, 5])

deque_list.pop() # 5
deque_list.pop() # 4
deque_list.pop() # 3

print(deque_list) # deque([0, 1])
```
- queue with deque
  - appendleft() : 새로운 값을 왼쪽부터 입력
  - popleft() : 가장 먼저 들어간 값(왼쪽부터) pop
  - queue 사용 조합 : appendleft() & pop() || append() & popleft()
```python
from collections import deque

deque_list = deque()
for i in range(5):
    deque_list.appendleft(i)

print(deque_list) # deque([4, 3, 2, 1, 0])

deque_list2 = deque()
for i in range(5):
    deque_list2.append(i)

deque_list2.popleft() # 0
deque_list2.popleft() # 1
```

- reversed() : 기존과 반대로 데이터 저장
```python
from collections import deque

deque_list = deque()
for i in range(5):
    deque_list.append(i)

print(deque_list) # deque([0, 1, 2, 3, 4])
print(deque(reversed(deque_list))) # deque([4, 3, 2, 1, 0])
```

- extend()와 extendleft() : 리스트 추가
```python
from collections import deque

deque_list = deque()
for i in range(5):
    deque_list.append(i)

deque_list.extend([5, 6, 7])
print(deque_list) # deque([0, 1, 2, 3, 4, 5, 6, 7])

deque_list.extendleft([-3, -2, -1])
print(deque_list) # deque([-3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7])
```

### 2. defaultdict
- 딕셔너리의 변수를 생성할 때, 기본 값을 지정해주는 모듈
```python
d = dict()
print(d["first"]) # 키를 생성하지 않고 호출하기 때문에 오류 발생
```
```python
from collections import defaultdict

d = defaultdict(lambda : 0)
print(d["fisrt"]) # 0 (설정한 defaultdict의 초기값 0 출력)
```
```python
# 초기값을 리스트로 설정
s = [('yellow', 1), ('blue', 2), ('yellow', 3), ('blue', 4), ('red', 1)]
d = defaultdict(list)
for i, j in s:
    d[i].append(j)

print(d.items()) # dict_items([('yellow', [1, 3]), ('blue', [2, 4]), ('red', [1])])
```

### 3. Counter
- 시퀀스 자료형의 데이터 요소의 갯수를 딕셔너리 형태로 반환하는 자료구조
- 리스트나 문자열과 같은 시퀀스 자료형 안의 요소 중 값이 같은 것이 몇개 있는지 반환

```python
from collections import Counter

text = list("galaxy")

print(text) # ['g', 'a', 'l', 'a', 'x', 'y']

c = Counter(text)

print(c) # Counter({'a': 2, 'g': 1, 'l': 1, 'x': 1, 'y': 1})

print(c["a"]) # 2
```

## 8) find, index, enumerate

### 1. find & index
- 문자열에서 원하는 문자나 문자열이 어디에 위치해 있는지를 알려줌
- 찾는 문자나 문자열이 없는 경우 find는 -1을 리턴하고, index는 ValueError를 발생(웬만하면 find 쓰자)
```python
S = 'ABCDE'

print(S.find('A')) # 0
print(S.index('A')) # 0
```
- 각 문자열이나 리스트의 인덱스를 저장하는 방식으로 find와 index 활용
```python
S = 'ABCDE'
S1, S2, S3, S4, S5 = (S.find(i) for i in S)

SS = ['A', 'B', 'C']
SS1, SS2, SS3 = ('ABC'.index(i) for i in SS)

print(S1, S2, S3, S4, S5) # 0 1 2 3 4 
print(SS1, SS2, SS3) # 0 1 2
```

### 2. enumerate
```python
arr = ['A', 'B', 'C', 'D', 'E']

enum_arr = []
for index, _arr in enumerate(arr):
    enum_arr.append([index, _arr])
print(enum_arr) # [[0, 'A'], [1, 'B'], [2, 'C'], [3, 'D'], [4, 'E']]

enum_tuple_arr = []
for index, _arr in enumerate(arr):
    enum_tuple_arr.append((index, _arr))
print(enum_tuple_arr) # [(0, 'A'), (1, 'B'), (2, 'C'), (3, 'D'), (4, 'E')]

enum_dict = {}
for index, _arr in enumerate(arr):
    enum_dict[index] = _arr
print(enum_dict) # {0: 'A', 1: 'B', 2: 'C', 3: 'D', 4: 'E'}
```

## 9) zip()
- 파이썬 내장 함수로, 두 그룹의 데이터를 서로 엮어준다.
- zip() 함수는 여러개의 순회 가능한(iterable) 객체를 인자로 받고, 각 객체가 담고 있는 원소를 튜플 형태로 차례로 접근할 수 있는 반복자(iterator)를 반환한다.

### 1. zip() 활용
```python
nums = [1. 2. 3]
strings = ["a", "b", "c"]

for pair in zip(nums, strings):
    print(pair)
# (1, 'a')
# (2, 'b')
# (3, 'c')
```
```python
nums = [1. 2. 3]
strings = ["a", "b", "c"]

pairs = list(zip(nums, strings))

dict_pairs = dict(zip(nums, strings))

print(pairs) # [(1, 'a'), (2, 'b'), (3, 'c')]
print(dict_pairs) # {1 : 'a', 2 : 'b', 3 : 'c'}

for n1, n2 in zip(nums, nums[1:]):
    print(n1, n2)
# 1 2
# 2 3
```
### 2. 병렬처리
```python
for nums, upper, lower in zip('12345', 'ABCDE', 'abcde'):
    print(nums, upper, lower)
# 1 A a 
# 2 B b
# 3 C c
# 4 D d
# 5 E e
```
### 3. upzip()
```python
nums = [1. 2. 3]
strings = ["a", "b", "c"]

pairs = list(zip(nums, strings))

print(pairs) # [(1, 'a'), (2, 'b'), (3, 'c')]

nums, strings = zip(*pairs)
print(nums) # (1, 2, 3)
print(letters) # ('a', 'b', 'c')
```
## 10) 문자열 포맷팅 

### 1. f-string
- Python 3.6 이상부터 사용 가능
- 문자열 앞에 접두사 f를 붙이고, 중괄호 안에 변수 삽입하여 사용

```python
# 1. 기본사용
s = 'Python'
print(f'I Love {s}') # I Love Python

# 2. 정수 자리수 관리
nums = [1, 3, 5, 7, 9, 11, 13, 15]
for num in nums:
    print(f'{num:02d}', end=' ') # 01 03 05 07 09 11 13 15 

# 3. 소수점 자리수 관리
f = 1.23456789
print(f'{f:.0f}') # 1
print(f'{f:.1f}') # 1.2
print(f'{f:.2f}') # 1.23
print(f'{f:.3f}') # 1.235 (1.2345에서 마지막 자리 반올림)
print(f'{f:.4f}') # 1.2346 (1.23456에서 마지막 자리 반올림)

# 4. f-string & list
nums = [100, 200, 300]
print(f'{nums[0]} + {nums[1]} + {nums[2]} = {nums[0]+nums[1]+nums[2]}') 
# 100 + 200 + 300 = 600

# 5. f-string & dictionary
dics = {'name':'Elon Musk', 'gender':'man', 'age':50}
print(f'name=>{dics["name"]}, gender=>{dics["gender"]}, age=>{dics["age"]}')
# name=>Elon Musk, gender=>man, age=>50
```

### 2. format()

```python
# 1. 기본사용
print('숫자 : {}, 실수 : {}'.format(5, 0.5)) # 숫자 : 5, 실수 : 0.5

# 2. 인덱스부여
print('실수 : {1}, 숫자 : {0}, 문자열 : {2}'.format(5, 0.5, '홍길동')) # 실수 : 0.5, 숫자 : 5, 문자열 : 홍길동

# 3. 변수지정
print('실수 : {a}, 숫자 : {b}, 문자열 : {c}'.format(a = 0.1, b = 100, c = '홍길동'))
# 실수 : 0.1, 숫자 : 100, 문자열 : '홍길동'

# 4. 정수 자리수 관리
nums = [1, 3, 5, 7, 9, 11, 13, 15]
for num in nums:
    print('{:02d}'.format(num), end=' ') # 01 03 05 07 09 11 13 15 

# 5. 소수점 자리수 관리
f = 1.23456789
print('{:.0f}'.format(f)) # 1
print('{:.1f}'.format(f)) # 1.2
print('{:.2f}'.format(f)) # 1.23
print('{:.3f}'.format(f)) # 1.235 (1.2345에서 마지막 자리 반올림)
print('{:.4f}'.format(f)) # 1.2346 (1.23456에서 마지막 자리 반올림)

# 6. 포맷변경
a = format(10000, ",")
print(a) # 10,000

# 7. 포맷변경 + 변수지정
text = "거스름돈 {price:,}원입니다."
print(text.format(price = 10000)) # 거스름돈 10,000원입니다.
```

## 11) find, startswith, endswith
- 문자열 중에 특정 문자를 찾고싶거나, 특정 문자로 시작하는 문자열, 특정 문자로 끝이나는 문자열을 찾고 싶다면 파이썬 내장함수 find(), startswith(), endswith()를 사용한다.

### 1. find(찾을 문자, 찾기 시작할 위치)
```python
s = '가나다라 마바사아 자차카타 파하'

s.find('마') # 5
s.find('가') # 1
s.find('가', 5) # -1 (없을 경우엔 -1을 리턴)
```
### 2. startswith(시작하는 문자, 시작 지점)
```python
s = '가나다라 마바사아 자차카타 파하'

s.startswith('가') # True
s.startswith('마') # False
s.startswith('마', s.find('마')) # True (s.find('마')는 '마'의 시작 인덱스를 알려줌)
s.startswith('마', 1) # False
```
### 3. endswith(끝나는 문자, 시작 지점, 끝 지점)
```python
s = '가나다라 마바사아 자차카타 파하'

s.endswith('하') # True
s.endswith('가') # False
s.endswith('마', 0, 6) # True
s.endwith('마', 0, 10) # False
```
## 12) global, nonlocal
### 1. 다른 Scope에 있는 변수 사용하기

<br/>

CASE 1) 더 넓은 범위에 있는 변수 '읽기'는 가능! 
- 다음과 같이 전역변수로 n을 선언하고, 이를 함수 내에서 읽기만 하는 경우 에러가 나지 않는다.
```python
n = 0 
def func(): 
    print(n) # 0 
func()
```
- 또한, 아래와 같이 전역변수는 아니지만, func2를 감싸고 있는 func1에서 선언한 변수 n은 func2 내부에서 사용이 가능하다.
```python
def func1(): 
    n = 1 
    def func2(): 
        print(n) # 1 
    func2() 
func1()
```

<br/>

CASE 2) 더 넓은 범위에 있는 변수 '변경'은 불가능!
- 아래와 같이 더 넓은 범위의 변수의 '읽기'는 가능하지만, '변경'은 불가능하며 에러가 발생한다.
```python
n = 0 
def func(): 
    n += 1 # error 
    print(n) 
func() 

def func1(): 
    n = 1 
    def func2(): 
        n += 1 # error 
        print(n) 
    func2() 
func1()
```

### 2. global
- 아래와 같이 전역변수 n을 함수 내에서 편집하기 위해 "global n"을 선언해주면, 문제 없이 사용이 가능하다. (함수 내의 변수가 아닌 전역변수를 사용할거야! 라는 뜻)
```python
n = 1 

def func1(): 
    def func2(): 
        global n 
        n += 1 
        print(n) # 2 
    func2() 
func1()
```

### 3. nonlocal
- 아래와 같이 func1에서 선언되어 있는 변수 n을 func2에서 편집하기 위해 "global n"을 선언하면 어떻게 될까? n은 전역변수가 아니기 때문에, global을 사용하여도 적용이 되지않는다.
```python
def func1(): 
    n = 1 
    def func2(): 
        global n 
        n += 1 
        print(n) # error 
    func2() 
func1()
```
- 따라서, 아래와 같이 현재의 Scope 내의 지역변수가 아니며, 전역변수도 아닌 변수 n을 사용할 때는 nonlocal로 선언해주면 편집이 가능하다. (지역변수는 아닌 변수를 사용할거야! 라는 뜻)
```python
def func1(): 
    n = 1 
    def func2(): 
        nonlocal n 
        n += 1 
        print(n) # 2 
    func2() 
func1()
```
