# 버블정렬 (Bubble Sort)

## 1) 정렬
- 정렬 : 어떠한 데이터들이 주어졌을 때, 이를 정해진 순서대로 나열하는 것
- 정렬은 데이터 작성 시, 빈번하게 필요로 함

## 2) 버블정렬
- 두 인접한 데이터를 비교해서, 앞에 있는 데이터가 뒤에 있는 데이터보다 크면 자리를 바꾸는 알고리즘

```python
# 버블정렬 슈도코드

for index in range(데이터길이 - 1):
    for index2 in range(데이터길이 - index - 1):
        if 앞데이터 > 뒤데이터:
            swap(앞데이터, 뒤데이터)
            if swap_count == 0: break
```

```python
# 버블정렬

def bubblesort(data):
    for index1 in range(len(data) - 1):
        swap = False # swap이 일어나는지 안일어나는지 판단
        # n회전을 하고나면 그 회전의 가장 큰 값은 맨 뒤로 정렬됨
        for index2 in range(len(data) - index1 - 1):
            if data[index2] > data[index2 + 1]:
                data[index2], data[index2 + 1] = data[index2 + 1], data[index2]
                swap = True
        
        if swap == False: # swap이 한번도 일어나지 않으면 False 그대로 유지되기 때문에
            break # break한다
    return data

# 버블정렬 테스트
import random

for index in range(50):
    data_list = random.sample(range(100), 50) # 0~99까지 랜덤하게 50개를 뽑은 리스트
    print(bubblesort(data_list))
```

## 3) 버블정렬 알고리즘 분석
- 시간복잡도(평균) : $O(n^2)$
- 시간복잡도(최선) : $O(n)$
- 시간복잡도(최악) : $O(n(n-1)/2)$