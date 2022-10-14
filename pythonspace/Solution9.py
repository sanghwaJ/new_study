# Softeer - 8단 변속기

import sys
#input = sys.stdin.readline

nums = list(map(int, input().split()))

ascending = False
descending = False
for i in range(len(nums) - 1):
    if nums[i] <= nums[i+1]:
        ascending = True
    elif nums[i] >= nums[i+1]:
        descending = True
        
if ascending and not descending:
    print("ascending")
elif not ascending and descending:
    print("descending")
else:
    print("mixed")