# 이진탐색 (Binary Search)

## 1) 이진탐색
- 데이터가 정렬되어 있다는 가정하에, 탐색할 자료를 둘로 나누어 데이터가 있을만한 곳을 탐색하는 방법

## 2) 분할 정복 알고리즘과 이진탐색
- 분할 정복 알고리즘 (Divide and Conquer)
  - Divide : 문제를 하나 또는 둘 이상으로 나눈다.
  - Conquer : 나눠진 문제가 충분히 작고, 해결 가능하다면 해결, 그렇지 않으면 다시 Divide.
- 이진탐색 (Binary Search)
  - Divide : 리스트를 두 개의 서브 리스트로 나눈다.
  - Conquer : 검색할 숫자(search) > 중간값(medium)이면, 뒷 부분의 서브 리스트에서 검색할 숫자를 찾고, 검색할 숫자(search) < 중간값(medium)이면, 앞 부분의 서브 리스트에서 검색할 숫자를 찾는다.

```python
# 이진탐색

def binary_search(data, search):
    if len(data) == 1 and search == data[0]:
        return True
    if len(data) == 1 and search != data[0]:
        return False
    if len(data) == 0:
        return False
    
    medium = len(data) // 2

    if search == data[medium]:
        return True
    else:
        if search > data[medium]:
            return binary_search(data[medium:], search)
        else:
            return binary_search(data[:medium], search)

# 이진탐색 TEST
import random

data = random.sample(range(100), 10)
sorted_data = sorted(data)
print(sorted_data)

binary_search(sorted_data, 10)
```

## 3) 이진탐색 알고리즘 분석
- n개의 리스트를 매번 2로 나누어 1이 될 때까지 비교연산 k회 진행
  <br/>
  $n*1/2*1/2·····=1​$ => $n*(1/2)^k=1​$ => $n=2^k=log_2​n=log_2​2^k​$ => $log_2​n=k$​
- Big O 표기법으로는 k+1이 최종 시간 복잡도이기 때문에,
  <br/>
  $O(log_2n+1)$
- Big O 표기법에서는 상수가 모두 삭제되므로, 
  <br/>
  $O(logn)$