package streamExamples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UpperCaseExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "kiwi");

        List<String> upper=list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(upper);
    }
}
