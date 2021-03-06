# 배열 (Array)

## 1) 배열(Array)
- 데이터를 나열하고, 각 데이터를 인덱스에 대응하도록 구성한 데이터 구조
- 파이썬에서는 리스트 타입이 배열 기능을 가지고 있음

## 2) 배열이 필요한 이유
- 같은 종류의 데이터를 효율적으로 관리하기 위해, 같은 종류의 데이터를 순차적으로 저장

## 3) 배열의 장점
- 빠른 접근이 가능(인덱스가 있기 때문)

## 4) 배열의 단점
- 연관된 데이터의 추가와 삭제가 어려움(배열은 정해진 사이즈가 있기 때문)

## 5) 파이썬과 C언어의 배열에서의 차이
- 미리 배열의 사이즈를 정해주어야 하는 C언어와는 달리, 파이썬은 배열의 사이즈를 정해줄 필요가 없음

## 6) 파이썬과 배열

```python
# 1차원 배열
data = [1, 2, 3, 4, 5]
print(data)
```

```python
# 2차원 배열
data = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print(data)
print(data[0])
print(data[0][1])
```

```python
# 연습 1: 2차원 배열에서 9, 8, 7 순서로 출력하기
print(data[2][2], data[2][1], data[2][0])
```

```python

```