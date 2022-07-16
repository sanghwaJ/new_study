# 삽입정렬 (Insertion Sort)

## 1) 삽입정렬
- 2번째 인덱스(A)부터 시작하여 해당 인덱스 앞에 있는 데이터(B)부터 비교, 2번째 데이터(A)의 값이 더 작으면 앞에 있는 데이터(B)를 뒤 인덱스로 복사한다.
- 이러한 작업을 더 큰 값을 만날때까지 반복하고, 큰 값을 만난 위치 뒤에 값을 이동시킨다.

```python
# 삽입정렬 슈도코드

for index1 in range(데이터길이 - 1):
    for index2 in range(index1 + 1, 0, -1):
        if data[index2] < data[index2 - 1]:
            swap(data[index2], data[index1])
        else:
            break
```

```python
# 삽입정렬

def insertion_sort(data):
    for index1 in range(len(data) - 1):
        for index2 in range(index1 + 1, 0, -1): # index+1부터 1까지 -1 단위로
            if data[index2] < data[index2 - 1]:
                data[index2], data[index2 - 1] = data[index2 - 1], data[index2]
            else:
                break
    return data

# 삽입정렬 테스트
import random

for index in range(50):
    data_list = random.sample(range(100), 50) # 0~99까지 랜덤하게 50개를 뽑은 리스트
    print(insertion_sort(data_list))
```

## 2) 삽입정렬 알고리즘 분석
- 시간복잡도(평균) : $O(n^2)$
- 시간복잡도(최선) : $O(n)$
- 시간복잡도(최악) : $O(n(n-1)/2)$