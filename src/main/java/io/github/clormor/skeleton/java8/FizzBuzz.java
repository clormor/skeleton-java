package io.github.clormor.skeleton.java8;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface FizzBuzz {
    List<String> fizzBuzz();
}

class FizzBuzzLegacy implements FizzBuzz {

    @Override
    public List<String> fizzBuzz() {
        List<String> result = Lists.newArrayList();
        for (int n = 1; n <= 100; n++) {
            result.add(fizzBuzz(n));
        }
        return result;
    }

    private String fizzBuzz(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return ("FizzBuzz");
        }
        if (n % 3 == 0) {
            return ("Fizz");
        }
        if (n % 5 == 0) {
            return "Buzz";
        }
        return Integer.toString(n);
    }
}

class FizzBuzzJava8 implements FizzBuzz {


    @Override
    public List<String> fizzBuzz() {
        return fizzBuzzAttempt4();
    }

    /**
     * This naive approach fails:
     * <p>
     * [1] The filter/map operation returns a filtered stream, not the entire stream.
     * So after the first filter/map, we are left with a stream of 6 strings
     * <p>
     * [2] Even if that weren't the case, we'd also need to handle the case where
     * Integer.valueOf is now a non-numeric String, which makes the code messy
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt1() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(Integer::toString)
                .filter(n -> Integer.valueOf(n) % 5 == 0 && Integer.valueOf(n) % 3 == 0).map(e -> "FizzBuzz")
                .filter(n -> Integer.valueOf(n) % 5 == 0).map(e -> "Buzz")
                .filter(n -> Integer.valueOf(n) % 5 == 0).map(e -> "Fizz")
                .collect(Collectors.toList());
    }

    /**
     * This approach works, but uses shorthand conditionals.
     * <p>
     * The lambda function used in mapToObj is kinda messy to read.
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt2() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(n -> (n % 5 == 0) ?
                        ((n % 3 == 0) ? "FizzBuzz" : "Buzz") :
                        ((n % 3 == 0) ? "Fizz" : Integer.toString(n)))
                .collect(Collectors.toList());
    }

    /**
     * <p>
     * This approach works in the same way as fizzBuzz2, but is cleaner.
     * </p>
     * <p>
     * The lambda function in fizzBuzz2 is replaced with a method reference.
     * The referenced method uses shorthand conditionals.
     * </p>
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt3() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(FizzBuzzJava8::fizzBuzzMethodRef)
                .collect(Collectors.toList());
    }

    private static String fizzBuzzMethodRef(int n) {
        return (n % 5 == 0) ?
                ((n % 3 == 0) ? "FizzBuzz" : "Buzz") :
                ((n % 3 == 0) ? "Fizz" : Integer.toString(n));
    }

    /**
     * <p>
     * Would not have figured this out without reading https://stackoverflow.com/questions/37814655/fizzbuzz-using-jdk8-lambda
     * </p>
     * <p>
     * This approach uses no loops or conditionals. It creates streams of Optionals and effectively zips them
     * </p>
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt4() {
        // create an infinite stream of strings by flattening a nullable string array
        Iterator<Optional<String>> fizz = Stream.generate(() -> new String[]{null, null, "Fizz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        Iterator<Optional<String>> buzz = Stream.generate(() -> new String[]{null, null, null, null, "Buzz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        Iterator<Optional<String>> fizzbuzz = Stream.generate(() -> new String[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, "FizzBuzz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        return IntStream.rangeClosed(1, 100)
                .mapToObj(n -> fizzbuzz.next().orElse(fizz.next().orElse(buzz.next().orElse(Integer.toString(n)))))
                .collect(Collectors.toList());
    }
}
