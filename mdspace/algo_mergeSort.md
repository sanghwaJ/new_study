# 병합정렬 (Merge Sort)

## 1) 병합정렬
- 재귀용법을 활용한 정렬 알고리즘
- 리스트를 절반으로 잘라 비슷한 크기의 두 부분 리스트로 나눈 후, 각 부분 리스트를 재귀적으로 병합정렬을 이용해 정렬하고, 다시 두 부분 리스트를 다시 하나의 정렬된 리스트로 합친다.

## 2) 병합정렬의 이해
```python
* 데이터가 4개인 경우
    * data_list = [1, 9, 3, 2]
        * 먼저 [1, 9], [3, 2]로 나눈다.
        * 앞부분을 [1], [9]로 나누고, 정렬해서 합친다. [1, 9]
        * 뒷부분을 [3], [2]로 나누고, 정렬해서 합친다. [2, 3]
        * 이제 [1, 9]와 [2, 3]을 합치면서
            * 1 < 2, [1]
            * 9 > 2, [1, 2]
            * 9 > 3, [1, 2, 3]
            * 맨마지막 남은 9를 맨 뒤에 추가. [1, 2, 3, 9]
* 즉 데이터를 나누는 함수(split)와 데이터를 합치는 함수(merge) 2가지 함수가 필요하다.
```

## 3) 병합정렬 슈도코드

```python
def split(list):
    if len(list) <= 1: 
        return list
    left = list[:데이터 2등분]
    right = list[데이터 2등분:]
    return merge(split(left), split(right))

def merge(left, right):
    if left[lp] < right[rp]:
        list.append(left[lp])
        lp += 1
    else:
        list.append(right[rp])
        rp += 1
    return list
```

## 3) 병합정렬 알고리즘 구현

### 3-1) mergesplit 함수
- 만약 리스트 갯수가 한 개이면, 해당 값 리턴
- 그렇지 않다면, 리스트를 앞과 뒤 두 개로 나누기
- left = mergesplit(앞)
- right = mergesplit(뒤)
- merge(left, right)

### 3-2) merge 함수
- 리스트 변수 생성(sorted)
- left_index, right_index = 0
- while left_index < len(left) or right_index < len(right):
- 만약 left_index나 right_index가 이미 left 또는 right 리스트를 다 순회했다면, 반대쪽의 데이터를 그대로 리스트에 넣고, 해당 인덱스 1 증가
    ```python
    if left[left_index] < right[right_index]:
        sorted.append(left[left_index])
        left_index += 1
    else:
        sorted.append(right[right_index])
        right_index += 1
    ```

```python
# 프로그래밍 연습 1 (split)

def split(data):
    medium = int(len(data) / 2)
    left = data[:medium]
    right = data[medium:]
    print(left, right)

data_list = [3,4,2,1,5]
split(data_list)
```

```python
# mergesplit
def mergesplit(data):
    if len(data) <= 1:
        return data
    medium = int(len(data) / 2)
    left = mergesplit(data[:medium])
    right = mergesplit(data[medium:])
    return merge(left, right)       

# merge
def merge(left, right):
    merged = list()
    left_point, right_point = 0, 0

    # case 1 : left와 right가 둘 다 남아 있을 때
    while left_point < len(left) and right_point < len(right):
        if left[left_point] < right[right_point]:
            merged.append(left[left_point])
            left_point += 1
        else:
            merged.append(right[right_point])
            right_point += 1

    # case 2 : left만 남아있을 때
    while left_point < len(left):
        merged.append(left[left_point])
        left_point += 1

    # case 3 : right만 남아있을 때
    while right_point < len(right):
        merged.append(right[right_point])
        right_point += 1

    return merged

# 병합정렬 코드 TEST
import random
data_list = random.sample(range(100), 10)
mergesplit(data_list)
```

## 4) 병합정렬 알고리즘 분석
- 각 단계의 시간 복잡도 : $2^i * n/2^i = O(n)$
- 만들어지는 각 단계의 갯수 : $O(logn)$
- 따라서 시간복잡도는, $O(n) * O(logn) = O(nlogn)$
