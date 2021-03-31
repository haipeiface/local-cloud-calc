package demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Tester {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        long count = strings.stream().filter(string -> string.length() == 3).count();

        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());


    }
}
