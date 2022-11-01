# 프로그래머스 - 캐시

def solution(cacheSize, cities):
    cache = []
    
    answer = 0
    for city in cities:
        city = city.lower()
        if city in cache:
            answer += 1
            cache.remove(city)
            cache.append(city)
        else:
            answer += 5
            if len(cache) < cacheSize:
                cache.append(city)
            elif len(cache) != 0 and len(cache) == cacheSize:
                del cache[0]
                cache.append(city)
                
    return answer