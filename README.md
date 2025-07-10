## ✅ 람다식이란?

람다식은 **익명 함수**를 표현하는 방식입니다.
함수를 간단히 **화살표(`->`) 문법**으로 표현합니다.

### 🔹 기본형

```java
(매개변수) -> { 실행문 }
```

### 🔹 예시

```java
(x) -> x * x
(a, b) -> a + b
() -> System.out.println("Hi")
```

* 인터페이스 구현 없이 함수 전달 가능
* Stream, Comparator 등에서 자주 사용

---
## ✅ 자주 쓰이는 곳

### ✅ 1. **Stream API** – map, filter, sorted, forEach

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

names.stream()
    .filter(s -> s.length() >= 4) // Predicate<String>: 길이가 4 이상인 문자열만 통과
    .map(s -> s.toUpperCase())   // Function<String, String>: 대문자로 변환
    .sorted()                    // 기본 정렬 (String은 Comparable 구현)
    .forEach(s -> System.out.println(s)); // Consumer<String>: 출력
```

### 🧠 작동 순서

1. `filter`: 조건(`s.length() >= 4`)에 맞는 요소만 통과
2. `map`: 요소를 대문자로 변환
3. `sorted`: 알파벳순 정렬
4. `forEach`: 하나씩 콘솔에 출력

---

### ✅ 2. **컬렉션 정렬** – List.sort + 람다

```java
List<Integer> numbers = Arrays.asList(5, 2, 8, 1);

numbers.sort((a, b) -> b - a); // Comparator<Integer>: 내림차순 정렬 기준 제공

System.out.println(numbers); // [8, 5, 2, 1]
```

### 🧠 작동 원리

* `sort`는 내부적으로 두 요소 `a`, `b`를 받아 정수 리턴하는 **람다식(Comparator)** 필요
* `b - a` → 내림차순

  * 음수: b < a → 자리 바꿈 안 함
  * 양수: b > a → 자리 바꿈

---

### ✅ 3. **Thread 실행 (Runnable)**

```java
Runnable task = () -> System.out.println("Thread is running!"); // Runnable: run() 메서드 구현

new Thread(task).start(); // 새로운 쓰레드에서 run() 실행
```

### 🧠 작동 원리

* `Runnable`은 **매개변수 없고 리턴도 없는 함수형 인터페이스**
* `()`는 인자 없음
* `->` 뒤는 실행할 코드 (`System.out.println(...)`)
* 내부적으로 `new Runnable() { public void run() { ... } }` 로 처리됨

---

### ✅ 4. **Optional + 람다**

```java
Optional<String> nickname = Optional.of("민주");

nickname.ifPresent(n -> System.out.println("닉네임: " + n)); // Consumer<String>
```

### 🧠 작동 원리

* `ifPresent`는 값이 있으면 **람다에 전달**
* `n -> ...`는 Consumer 역할: n 받아서 출력

---

### ✅ 5. **이벤트 리스너 (JavaFX or Swing)**

```java
button.setOnAction(e -> System.out.println("버튼 클릭됨!")); // EventHandler<ActionEvent>
```

### 🧠 작동 원리

* `setOnAction`은 이벤트 발생 시 실행할 **람다(핸들러)** 요구
* `e`는 이벤트 정보 → 콘솔에 출력하는 역할
* 내부적으로 `handle(ActionEvent e)` 구현됨

---

### ✅ 총정리: 자주 쓰이는 람다 위치 & 인터페이스

| 분야           | 메서드                      | 인터페이스 (함수형)       | 예시 람다                        |
| ------------ | ------------------------ | ----------------- | ---------------------------- |
| **Stream**   | `filter`                 | `Predicate<T>`    | `x -> x > 5`                 |
|              | `map`                    | `Function<T, R>`  | `s -> s.toUpperCase()`       |
|              | `forEach`                | `Consumer<T>`     | `s -> System.out.println(s)` |
| **sort**     | `sort(Comparator)`       | `Comparator<T>`   | `(a, b) -> a - b`            |
| **Thread**   | `new Thread(Runnable)`   | `Runnable`        | `() -> { ... }`              |
| **Optional** | `ifPresent`              | `Consumer<T>`     | `v -> System.out.println(v)` |
| **Event**    | `setOnAction` (JavaFX 등) | `EventHandler<T>` | `e -> handleClick(e)`        |

---

## ✅ Stream API란?

자바에서 \*\*컬렉션(List, Set 등)\*\*을 **함수형 방식으로 처리**하는 방법입니다.

> "반복문 없이도 필터링, 정렬, 매핑, 집계 등이 가능"

### 🔹 기본 구조

```java
컬렉션.stream()
       .중간연산1()
       .중간연산2()
       ...
       .최종연산();
```

---

## ✅ 3. 자주 쓰는 중간 연산 (filter/map/sorted 등)

| 메서드                  | 설명         | 예시                         |
| -------------------- | ---------- | -------------------------- |
| `filter(Predicate)`  | 조건 필터링     | `.filter(x -> x > 10)`     |
| `map(Function)`      | 변환/가공      | `.map(x -> x * 2)`         |
| `sorted()`           | 오름차순 정렬    | `.sorted()`                |
| `sorted(Comparator)` | 정렬 조건 지정   | `.sorted((a, b) -> b - a)` |
| `distinct()`         | 중복 제거      | `.distinct()`              |
| `limit(n)`           | 앞에서 n개 자르기 | `.limit(3)`                |

---

## ✅ 4. 자주 쓰는 최종 연산 (collect/forEach/count 등)

| 메서드                            | 설명       | 예시                                     |
| ------------------------------ | -------- | -------------------------------------- |
| `collect(Collectors.toList())` | 리스트로 변환  | `.collect(Collectors.toList())`        |
| `forEach()`                    | 하나씩 처리   | `.forEach(x -> System.out.println(x))` |
| `count()`                      | 개수 반환    | `.count()`                             |
| `toArray()`                    | 배열로 변환   | `.toArray()`                           |
| `anyMatch()`, `allMatch()`     | 조건 만족 여부 | `.anyMatch(x -> x > 0)`                |

---

## ✅ 5. 실전 예제

### 🔹 정수 리스트에서 짝수만 추출

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> even = nums.stream()
                         .filter(x -> x % 2 == 0)
                         .collect(Collectors.toList());
```

---

### 🔹 문자열 리스트를 길이순으로 정렬

```java
List<String> list = Arrays.asList("banana", "apple", "kiwi");
List<String> sorted = list.stream()
                          .sorted((a, b) -> a.length() - b.length())
                          .collect(Collectors.toList());
```

---

### 🔹 모든 문자열을 대문자로 변환

```java
List<String> list = Arrays.asList("hi", "hello", "world");
List<String> upper = list.stream()
                         .map(String::toUpperCase)
                         .collect(Collectors.toList());
```

---

### 🔹 Map의 key만 리스트로 받기

```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3); map.put("banana", 5);

List<String> keys = map.keySet()
                       .stream()
                       .collect(Collectors.toList());
```

---

### 🔹 Map을 value 기준으로 정렬

```java
map.entrySet().stream()
    .sorted((a, b) -> b.getValue() - a.getValue())
    .forEach(entry -> 
        System.out.println(entry.getKey() + ": " + entry.getValue()));
```

---

## ✅ 6. 자주 쓰는 패턴 요약

| 목적      | 패턴                                                       |
| ------- | -------------------------------------------------------- |
| 리스트 필터링 | `list.stream().filter(...).collect(Collectors.toList())` |
| 리스트 정렬  | `list.stream().sorted(...).collect(Collectors.toList())` |
| 리스트 변환  | `list.stream().map(...).collect(Collectors.toList())`    |
| for문 대체 | `list.stream().forEach(x -> ...)`                        |
| map 정렬  | `map.entrySet().stream().sorted(...).forEach(...)`       |

---

## ✅ 람다 + Stream을 언제 쓰면 좋을까?

| 상황                 | 왜 좋은가                                      |
| ------------------ | ------------------------------------------ |
| 필터링, 정렬, 변환이 많은 문제 | 코드가 짧고 가독성 ↑                               |
| 반복문이 중첩되는 경우       | `map + filter + forEach`로 간결하게 처리          |
| HashMap 순회 정렬      | entrySet().stream()으로 유용                   |
| 문자열/배열 변환          | `Arrays.stream()` 또는 `str.chars()` 등 활용 가능 |

---

## ✅ `::` 메서드 참조란?

* `람다식`을 대신해서 **메서드 이름만으로 참조**할 수 있도록 해주는 문법입니다.
* **`람다식이 단순히 메서드 하나만 호출하는 경우`에 사용하면 좋습니다.**

---

## ✅ 기본 문법

| 형태                          | 의미                 | 예시                    |
| --------------------------- | ------------------ | --------------------- |
| `ClassName::staticMethod`   | 정적 메서드 참조          | `Math::abs`           |
| `object::instanceMethod`    | 특정 객체의 인스턴스 메서드 참조 | `System.out::println` |
| `ClassName::instanceMethod` | 매개변수의 메서드 참조       | `String::toUpperCase` |
| `ClassName::new`            | 생성자 참조             | `User::new`           |

---

## 🔍 예시로 이해해보기

### 1. 정적 메서드 참조

```java
List<Integer> numbers = Arrays.asList(-3, -1, 2);

List<Integer> absList = numbers.stream()
    .map(Math::abs) // Math 클래스의 정적 메서드 abs 사용
    .collect(Collectors.toList());

System.out.println(absList); // [3, 1, 2]
```

> 아래와 같던 람다식을 줄인 것:

```java
.map(n -> Math.abs(n))
```

---

### 2. 인스턴스 메서드 참조 (매개변수 대상)

```java
List<String> list = Arrays.asList("java", "spring", "stream");

List<String> upperList = list.stream()
    .map(String::toUpperCase) // 각 요소(String)의 toUpperCase() 메서드 호출
    .collect(Collectors.toList());

System.out.println(upperList); // [JAVA, SPRING, STREAM]
```

> 아래 람다식과 동일:

```java
.map(s -> s.toUpperCase())
```

---

### 3. 객체의 인스턴스 메서드 참조

```java
List<String> list = Arrays.asList("A", "B", "C");

// System.out 객체의 println 메서드를 참조
list.forEach(System.out::println);
```

> 아래와 같던 코드 축약:

```java
list.forEach(s -> System.out.println(s));
```

---

### 4. 생성자 참조

```java
class User {
    String name;
    public User(String name) { this.name = name; }
}

List<String> names = Arrays.asList("Tom", "Jerry");

// String 하나를 받아서 User 객체로 바꾸는 생성자 참조
List<User> users = names.stream()
    .map(User::new)
    .collect(Collectors.toList());
```

> 아래와 같던 코드 축약:

```java
.map(name -> new User(name))
```

---

## ✅ 한눈에 비교

| 람다식                               | 메서드 참조                |
| --------------------------------- | --------------------- |
| `x -> x.toUpperCase()`            | `String::toUpperCase` |
| `x -> System.out.println(x)`      | `System.out::println` |
| `s -> new User(s)`                | `User::new`           |
| `(a, b) -> Integer.compare(a, b)` | `Integer::compare`    |

---

## ✨ 요약

* `::`는 메서드를 참조하는 문법으로 **람다식을 간결하게** 만든다.
* 조건: **람다식이 메서드 한 줄 호출일 때만** 사용 가능.
* 코드를 **간결 + 가독성 좋게** 만드는 데 유용하다.

---

## ✅ Comparable vs Comparator 정리

> **객체 정렬 기준을 정의하는 두 가지 방식**

---

## ✅ 핵심 차이

| 구분     | Comparable                          | Comparator                             |
| ------ | ----------------------------------- | -------------------------------------- |
| 정의 위치  | 클래스 내부 (`implements Comparable<T>`) | 클래스 외부에서 따로 정의                         |
| 인터페이스  | `Comparable<T>`                     | `Comparator<T>`                        |
| 메서드 이름 | `compareTo(T other)`                | `compare(T o1, T o2)`                  |
| 기준 개수  | **1개 (고정)**                         | **여러 개 가능 (유연)**                       |
| 사용 목적  | "기본 정렬 기준"                          | "다양한 정렬 조건 필요할 때"                      |
| 사용 예   | `Collections.sort(list)`            | `list.sort(Comparator.comparing(...))` |

---

## ✅ 기본 문법

### 📌 Comparable (기본 정렬 기준)

```java
public class Person implements Comparable<Person> {
    String name;
    int age;

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name); // 이름 기준 오름차순
    }
}
```

### 📌 Comparator (외부 정렬 기준)

```java
Comparator<Person> byAge = Comparator.comparingInt(Person::getAge);

List<Person> sorted = people.stream()
    .sorted(byAge)
    .collect(Collectors.toList());
```

---

## 🔍 예시로 이해하기

### 1. Comparable: 클래스 안에 정렬 기준 내장

```java
class User implements Comparable<User> {
    String name;
    public int compareTo(User other) {
        return this.name.compareTo(other.name); // 이름순 정렬
    }
}
List<User> users = ...;
Collections.sort(users); // compareTo() 기준으로 정렬됨
```

---

### 2. Comparator: 외부에서 정렬 기준 지정

```java
Comparator<User> ageComparator = Comparator.comparingInt(User::getAge);
users.sort(ageComparator); // 나이순 정렬

// 또는 람다식
users.sort((u1, u2) -> u1.getAge() - u2.getAge());
```

---

### 3. 복합 정렬 (이름 → 나이)

```java
users.sort(
    Comparator.comparing(User::getName)
              .thenComparingInt(User::getAge)
);
```

---

### 4. 내림차순 정렬

```java
users.sort(Comparator.comparing(User::getName).reversed());
```

---

## ✅ 한눈에 비교

| 람다식 정렬                                         | Comparator 메서드 참조 정렬                               |
| ---------------------------------------------- | -------------------------------------------------- |
| `(a, b) -> a.getName().compareTo(b.getName())` | `Comparator.comparing(User::getName)`              |
| `(a, b) -> b.getAge() - a.getAge()`            | `Comparator.comparingInt(User::getAge).reversed()` |
| `(a, b) -> new User(a)`                        | `User::new` (생성자 참조와 결합 가능)                        |

---

## ✅ 정리 문장 (암기용)

|`Comparable`|`Comparator`|
|-------------------------------------------|----------------------------------------------|
|👉 **"클래스 안에서 직접 비교 기준 정의"**|👉 **"클래스 밖에서 다양한 정렬 기준 정의"**|


"정렬은 비교가 있어야 가능하다!"
객체를 정렬하려면 반드시 Comparable 구현 or Comparator 전달이 필요하다.

---


