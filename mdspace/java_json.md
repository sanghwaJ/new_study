# Java - JSON

## 1. JSON
- JavaScript Object Notation의 약자로, JavaScript에서 데이터를 전달하기 위해 만들어짐
- Key-Value 형태로 데이터를 가지고 있음

```javascript
{
   "pageInfo": {
         "pageName": "abc",
         "pagePic": "http://example.com/content.jpg"
    },
    "posts": [
         {
              "post_id": "123456789012_123456789012",
              "message": "Sounds cool. Can't wait to see it!",
              "likesCount": "2",
         }
    ]
}
```

## 2. JSONObject
- JSONObject는 JSON에서 Key-Value 쌍으로 표현하는 객체
- put(key, value)로 데이터를 입력할 수 있고, toString()을 통해 JSONObject가 가지고 있는 데이터를 JSON 형식으로 출력함

```java
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExample {

    public static void main(String[] args) throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("name", "Kim");
        jo.put("city", "Seoul");

        System.out.println(jo.toString()); // {"city":"Seoul","name":"Kim"}

    }
}
```

## 3. HashMap으로 JSONObject 생성
- HashMap에 저장된 데이터를 JSON으로 변환할 수도 있음

```java
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonExample2 {

    public static void main(String[] args) throws JSONException {

        Map<String, String> map = new HashMap<>();
        map.put("name", "Lee");
        map.put("city", "Seoul");

        JSONObject jo = new JSONObject(map);

        System.out.println(jo.toString()); // {"city":"Seoul","name":"Lee"}
    }
}
```

## 4. JSON 문자열로 JSONObject 객체 생성
- JSON 문자열로 JSONObject 객체를 생성할 수 있으며, 데이터를 추가할 수도 있음

```java
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonExample3 {

    public static void main(String[] args) throws JSONException {

        JSONObject jo = new JSONObject("{\"city\":\"Seoul\",\"name\":\"Park\"}");

        Iterator it = jo.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            System.out.println("key: " + key + ", value: " + jo.getString(key));
        }
        System.out.println(jo.toString()); // {"city":"Seoul","name":"Park"}

        jo.put("age", "30");
        System.out.println(jo.toString()); // {"city":"Seoul","name":"Park","age":"30"}
    }
}
```

## 5. POJO로 JSONObject 객체 생성
- POJO(Plain Old Java Object) : 아래의 Customer 클래스와 같이 get, set 메소드들만 있는 Java 클래스를 의미
- JSONObject는 POJO 객체를 인자로 받으며, Key와 Value를 추출하여 JSON 데이터를 추가함

```java
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExample4 {

    public static class Customer {
        private String name;
        private String city;

        Customer(String name, String city) {
            this.name = name;
            this.city = city;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }
        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws JSONException {
        Customer customer = new Customer("Jeong", "Seoul");
        JSONObject jo = new JSONObject(customer);
        System.out.println(jo.toString()); // {"city":"Seoul","name":"Jeong"}
    }
}
```

## 6. JSONArray
- Key-Value 형태에서 Array 타입을 Value로 받을 수도 있음

```java
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonExample5 {

    public static void main(String[] args) throws JSONException {

        JSONArray ja = new JSONArray();
        ja.put("Harry");
        ja.put("Sam");

        JSONObject jo = new JSONObject();
        jo.put("name", "Son");
        jo.put("city", "Seoul");
        jo.put("friends", ja);

        System.out.println(jo.toString()); // {"city":"Seoul","name":"Son","friends":["Harry","Sam"]}
    }
}
```

## 7. List로 JSONArray 객체 생성
- JSONArray 생성자는 인자로 List를 받고, List의 모든 데이터를 JSONArray에 추가할 수 있음

```java
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonExample6 {

    public static void main(String[] args) throws JSONException {

        List<String> list = new ArrayList<>();
        list.add("Harry");
        list.add("Sam");

        JSONArray ja = new JSONArray(list);

        JSONObject jo = new JSONObject();
        jo.put("name", "Hong");
        jo.put("city", "Seoul");
        jo.put("friends", ja);

        System.out.println(jo.toString()); // {"city":"Seoul","name":"Hong","friends":["Harry","Sam"]}
    }
}
```

## 8. JSON을 파일로 저장
- JSON 객체들이 가지고 있는 데이터를 JSON 형식의 문자열로 변환하고 파일로 저장할 수 있음

```java
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonExample7 {

    public static void main(String[] args) throws JSONException, IOException {

        JSONObject jo = new JSONObject();
        jo.put("name", "Ahn");
        jo.put("city", "Seoul");

        String jsonStr = jo.toString();
        File jsonFile = new File("/var/tmp/example.json");

        writeStringToFile(jsonStr, jsonFile);
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}
```