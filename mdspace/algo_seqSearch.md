# 순차탐색 (Sequential Search)

## 1) 순차탐색 (Sequential Search)
- 데이터가 담겨있는 리스트를 앞에서부터 하나씩 비교하여 원하는 데이터를 찾는 방법

```python
# 순차탐색

import random
data = random.sample(range(100), 10)

def sequential_search(data, search):
    for i in range(len(data)):
        if data[i] == search:
            return i
    return -1

sequential_search(data, 13)
```

## 2) 순차탐색 알고리즘 분석
- 최악의 경우 리스트의 길이가 n일 때, n번 비교해야하기 때문에, 시간복잡도는 $O(n)$