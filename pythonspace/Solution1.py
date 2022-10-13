# 프로그래머스 - 전화번호 목록
def solution(phone_book):
    phone_book = sorted(phone_book)

    answer = True
    for p1, p2 in zip(phone_book, phone_book[1:]):
        if p2.startswith(p1):
            return False
    
    return answer

#phone_book = ["119", "97674223", "1195524421"]
phone_book = ["123","456","789"]
#phone_book = ["12","123","1235","567","88"]

print(solution(phone_book))