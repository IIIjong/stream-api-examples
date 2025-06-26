## ✅ 1. 람다식이란?

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

## ✅ 2. Stream API란?

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
