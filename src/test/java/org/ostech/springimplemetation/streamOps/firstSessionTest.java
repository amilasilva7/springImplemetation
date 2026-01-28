package org.ostech.springimplemetation.streamOps;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class firstSessionTest {

    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 6);
    List<String> names = List.of("Alice", "Bob", "Andrew", "John");

    @Test
    void namesStartWithA() {
        List<String> expected = Arrays.asList("Amila", "Amara", "Ambani");
        List<String> payload = Arrays.asList("Amila", "Pradeep", "Silva", "Amara", "Ambani", "buddhi");

        assertEquals(expected, FirstSession.namesStartWithA(payload));
    }

    @Test
    void sumOfSquires() {
        List<Integer> payload = Arrays.asList(5, 5, 5);
        assertEquals(75, FirstSession.sumOfSquires(payload));
    }

    @Test
    void joinStringWithComma() {
        List<String> payload = Arrays.asList("Amila", "Pradeep", "Silva");
        assertEquals("Amila,Pradeep,Silva", FirstSession.joinStringWithComma(payload));
    }

    @Test
    void findMaxNumber() {
        List<Integer> payload = Arrays.asList(5, 5, 5);
        List<Integer> payload2 = Arrays.asList(5, 5, 5, 6);
        assertEquals(5, FirstSession.findMaxNumber(payload));
        assertEquals(6, FirstSession.findMaxNumber(payload2));
    }

    @Test
    void findEvenNumbers() {
        List<Integer> payload = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> expect = Arrays.asList(2, 4, 6, 8);
        assertEquals(expect, FirstSession.findEvenNumbers(payload));
    }

    @Test
    void convertToUpperCase() {
        List<String> expected = Arrays.asList("AMILA", "AMARA", "AMBANI");
        List<String> payload = Arrays.asList("Amila", "Amara", "Ambani");

        assertEquals(expected, FirstSession.convertToUpperCase(payload));
    }

    @Test
    void countElements() {
        List<String> payload = Arrays.asList("AMILA", "AMARA", "AMBANI");
        assertEquals(3, FirstSession.countElements(payload));
    }

    @Test
    void sortedNumbers() {
        List<Integer> payload = Arrays.asList(9, 1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(expected, FirstSession.sortedNumbers(payload));
    }

    @Test
    void filterAndMap() {
        List<String> result = names.stream()
            .filter(a -> a.startsWith("A"))
            .map(String::toLowerCase)
            .toList();

        assertEquals(List.of("alice", "andrew"), result);
    }

    @Test
    void limitResults() {
        List<Integer> result = numbers.stream()
            .limit(3)
            .toList();
        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    void skipResults() {
        List<Integer> result = numbers.stream()
            .skip(2).limit(2)
            .toList();
        assertEquals(List.of(3, 4), result);
    }

    @Test
    void sumValues() {
        long sum = numbers.stream()
            .mapToInt(Integer::intValue).sum();
        assertEquals(27, sum);
    }

    @Test
    void isExist() {
        boolean isExist = numbers.stream()
            .anyMatch(n -> n == 5);
        boolean isExist2 = numbers.stream()
            .anyMatch(n -> n == 10);
        assertTrue(isExist);
        assertFalse(isExist2);
    }

    @Test
    void findFirst() {
        String first = names.stream()
            .findFirst()
            .orElse(null);

        assertEquals("Alice", first);
    }

    @Test
    void findFirstMatchingElement() {
        String firstMatch = names.stream()
            .filter(a -> a.startsWith("A"))
            .findFirst()
            .orElse(null);

        String firstMatch2 = names.stream()
            .filter(a -> a.startsWith("z"))
            .findFirst()
            .orElse(null);

        assertEquals("Alice", firstMatch);
        assertNull(firstMatch2);

    }
}
