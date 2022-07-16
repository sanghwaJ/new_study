# 선택정렬 (Selection Sort)

## 1) 선택정렬
- 주어진 데이터 중 최소값을 찾아 해당 최소값을 데이터 맨 앞에 위치한 값과 교체하면서, 맨 앞의 위치를 제외한 나머지 데이터를 동일한 방법으로 반복하는 정렬 방법

```python
# 선택정렬 슈도코드

for stand in range(데이터길이 - 1):
    lowest = stand
    for index in range(stand + 1, 데이터길이):
        if data[lowest] > data[index]:
            lowest = index
    swap(lowest, stand)
```

```python
# 선택정렬

def selection_sort(data):
    for stand in range(len(data) - 1):
        lowest = stand
        # n회전을 하고나면 그 회전의 가장 작은 값은 맨 앞으로 정렬이되게 됨
        for index in range(stand + 1, len(data)):
            if data[lowest] > data[index]:
                lowest = index
        data[lowest], data[stand] = data[stand], data[lowest]
    return data

# 선택정렬 테스트
import random

for index in range(50):
    data_list = random.sample(range(100), 50) # 0~99까지 랜덤하게 50개를 뽑은 리스트
    print(selection_sort(data_list))
```

## 2) 선택정렬 알고리즘 분석
- 시간복잡도 : $O(n^2)$
