package streamExamples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 1, 7, 3, 2);

        List<Integer> sortedList = list.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(sortedList);
    }
}
