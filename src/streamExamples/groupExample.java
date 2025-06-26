package streamExamples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class groupExample {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("apple", "kiwi", "pear", "grape", "plum");
        Map<Integer,List<String>> group = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(group);
    }
}
