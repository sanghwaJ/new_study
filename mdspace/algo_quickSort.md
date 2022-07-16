# 퀵정렬 (Quick Sort)

## 1) 퀵정렬
- 기준점(pivot)을 정해서, 기준점보다 작은 데이터는 왼쪽(left), 큰 데이터는 오른쪽(right)으로 모으는 정렬
- 각 왼쪽(left), 오른쪽(right)은 재귀용법을 사용해서 다시 동일 함수를 호출하여 위 작업을 반복(남은 데이터가 1개가 될 때까지)
- 함수는 left + pivot + right를 리턴

## 2) 퀵정렬 연습
```python
# 프로그래밍 연습 1
# 리스트를 리스트 슬라이싱을 이용하여 세개로 잘라, 각 리스트 변수에 넣고 출력

list = [1, 2, 3, 4, 5]

data1 = list[:2]
data2 = list[2]
data3 = list[3:]

print(data1)
print(data2)
print(data3)
```
```python
# 프로그래밍 연습 2
# 리스트에서 맨 앞의 데이터를 pivot 변수에 넣고, 
# pivot 변수 값을 기준으로 작은 데이터는 left 변수에, 그렇지 않은 데이터는 right 변수에 넣기

list = [4, 1, 2, 5, 7]
pivot = list[0]
left = []
right = []

for i in range(1, len(list)):
    if pivot > list[i]:
        left.append(list[i])
    else:
        right.append(list[i])

print(left)
print(right)
```

## 3) 퀵정렬 알고리즘 구현
- 만약 리스트 갯수가 한 개이면 해당 리스트를 리턴
- 그렇지 않으면, 리스트 맨 앞의 데이터를 pivot으로
- left, right 리스트 변수를 만들고, 맨 앞의 데이터를 뺀 나머지 데이터를 pivot과 비교하여 left, right에 append
- return quicksort(left) + [pivot] + quicksort(right)로 재귀 호출

```python
# Quick Sort

def qsort(data):
    if len(data) <= 1:
        return data

    left = []
    right = []
    pivot = data[0]

    for i in range(1, len(data)):
        if pivot > data[i]:
            left.append(data[i])
        else:
            right.append(data[i])

    return qsort(left) + [pivot] + qsort(right)

# qsort test
import random

data_list = random.sample(range(100), 10)

qsort(data_list)
```

```python
# Quick Sort (using list comprehension)

def qsort(data):
    if len(data) <= 1:
        return data

    pivot = data[0]

    left = [item for item in data[1:] if pivot > item]
    right = [item for item in data[1:] if pivot <= item]

    return qsort(left) + [pivot] + qsort(right)
```

## 4) 퀵정렬 알고리즘 분석
- 시간복잡도(평균, 최선) : $O(nlogn)$
- 시간복잡도(최악) : $O(n^2)$
