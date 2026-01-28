package org.ostech.springimplemetation.streamOps;

import java.util.List;
import java.util.stream.Collectors;

public class FirstSession {

    public static List<String> namesStartWithA(List<String> payload) {
        return payload.stream()
            .filter(a -> a.toLowerCase()
                .startsWith("a"))
            .toList();
    }

    public static int sumOfSquires(List<Integer> numbers) {
        return numbers.stream().map(a -> a * a).reduce(Integer::sum).get();
    }

    public static String joinStringWithComma(List<String> payload) {
        return payload.stream().collect(Collectors.joining(","));
    }

    public static int findMaxNumber(List<Integer> payload) {
        return payload.stream().max(Integer::compare).orElse(0);
    }

    public static List<Integer> findEvenNumbers(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).toList();
    }

    public static List<String> convertToUpperCase(List<String> names) {
        return names.stream().map(String::toUpperCase).toList();
    }

    public static long countElements(List<String> names) {
        return names.stream().count();
    }

    public static List<Integer> sortedNumbers(List<Integer> numbers) {
        return numbers.stream()
            .sorted().toList();
    }

    public static List<Integer> removeDuplicates(List<Integer> numbers) {
        return numbers.stream()
            .distinct()
            .toList();
    }
}
