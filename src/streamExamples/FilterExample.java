package streamExamples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); //Integer 1~10까지 숫자 리스트
        List<Integer> evenNumbers = list.stream() //stream 생성
                .filter(x -> x % 2 == 0) //x를 하나씩 2로 나머지 연산
                .collect(Collectors.toList()); //결과를 evenNumbers 리스트에 저장

        System.out.println(evenNumbers); //2 4 6 8 10
    }
}
