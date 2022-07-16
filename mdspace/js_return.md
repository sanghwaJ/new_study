# JavaScript - return

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>book_insert_form_ajax_button</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="./js/bookmanage.js"></script>
<body>
<center>
 
<form method="post" name="insert_book_form" action="BookInsertPro" onsubmit="return insertBookSave()">
</form>
 
</center>
</body>
</html>
```

- ./js/bookmanage.js 경로의 파일을 받는 insert_book_form이 있다고 가정하고, onsubmit 이벤트로 insertBookSave 함수를 호출했을 때를 비교한다.

## 1. return
```JavaScript
function insertBookSave() {
    var insert_book_form = document.insert_book_form;
    
    if (! insert_book_form_bookCode.value) {
        alert('도서 코드를 입력하세요.');
        
        insert_book_form.bookCode.focus();
        
        return;
    }
}
```

- return 뒤에 어떠한 값으로 return하는지 정해주지 않았기 때문에, insert_book_form_value에 값이 들어가 있지 않을 경우 null로 return.
- 따라서, insert_book_form은 submit을 하지 못하고 null을 반환하며, return 아래의 코드를 전부 실행하지 않고 return에서 끝이 난다.

## 2. return true

```JavaScript
function insertBookSave() {
    var insert_book_form = document.insert_book_form;
    
    if (! insert_book_form.bookCode.value) {
        alert('도서 코드를 입력하세요.');
        
        insert_book_form.bookCode.focus();
        
        return true;
    }
}
```

- insert_book_form이 submit을 하기 전에 insertBookSave 함수에서 유효성 검사를 하고, 통과가 되면 true를 return
- 즉, insert_book_form.bookCode.value에 값이 들어있지 않아도 submit을 한다.

## 3. return false

```JavaScript
function insertBookSave() {
    var insert_book_form = document.insert_book_form;
    
    if (! insert_book_form.bookCode.value) {
        alert('도서 코드를 입력하세요.');
        
        insert_book_form.bookCode.focus();
        
        return false;
    }
}
```

- insert_book_form.bookCode.value에 값이 들어있지 않으면 submit을 false로 return
  
## ※ 정리
- 유효성 검사나 return 값을 줄 때, return에 true인지 false인지 명확하게 return 값을 주는 것이 바람직하다.