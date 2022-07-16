# JavaScript - Prototype, Class

## 1. Prototype

JavaScript에서 Class의 개념에 해당한다.

```JavaScript
function Student(name) {
  this.name = name
}

Student.prototype.greet = function greet() {
  return 'Hello, ${this.name}!'
}

const me = new Student('Gildong')

console.log(me) // Gildong 출력
console.log(me.greet()) // Hello, Gildong! 출력
```

## 2. Prototype 상속

```JavaScript
function Person(name) {
  this.name = name
}

Person.prototype.greet = function greet() {
  return 'Hi, ${this.name}!'

function Student(name) {
  this.__proto__.constructor(name) // Person의 name 상속(자기 자신의 prototype constructor)
}

Student.prototype.greet = function greet() {
  return '${this.name} is studying.'
}

// Prototype 설정
Object.setProtoTypeOf(Student.prototype, Person.prototype)

const me = new Student('Gildong')

console.log(me.greet()) // Hello, Gildong! 출력
console.log(me.study()) // Gildong is studying. 출력
```

## 3. 기타 Prototype 관련 유용한 함수들

### 3-1. instanceOf

어떠한 Prototype의 instance인지 아닌지(Prototype을 거쳤는지 아닌지)를 판단

```JavaScript
console.log(me instanceof Student) // true 출력
console.log(me instanceof Person) // true 출력

const anotherPerson = new Person('Foo')
console.log(anotherPerson instanceof Student) // false 출력
console.log(anotherPerson instanceof Person) // true 출력

console.log([] instanceof Array, [] instanceof Object) // true true 출력
```

### 3-2. Class

앞서 Prototype이 JavaScript에서의 Class라고 설명하였는데, 최근에는 Prototype 형식으로 코드를 작성하기 보단, 아래와 같은 형식으로 코드를 작성하는 추세이다.

```JavaScript
class Person {
  constructor(name) {  
    this.name = name
  }

  greet() {
    return 'Hi, ${this.name}!'
  }
}

class Student extends Person {
  constructor(name) {
    super(name) // Person Class의 name 상속
  }

  study() {
    return '${this.name} is studying.' 
}

const me = new Student('Gildong')
console.log(me.greet()) // Hello, Gildong! 출력
console.log(me.study()) // Gildong is studying. 출력
```