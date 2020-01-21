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

@FunctionalInterface
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

    /*
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

    /*
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

    /*
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

    /*
     * <p>
     * Would not have figured this out without reading https://stackoverflow.com/questions/37814655/fizzbuzz-using-jdk8-lambda
     * </p>
     * <p>
     * This approach uses no loops or conditionals. It creates streams of Optionals and effectively zips them
     * </p>
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt4() {
        // create an infinite Optional iterator by flattening a nullable array where every 3rd string is "Fizz"...
        Iterator<Optional<String>> fizz = Stream.generate(() -> new String[]{null, null, "Fizz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        // as per above, but every 5th string is "Buzz"...
        Iterator<Optional<String>> buzz = Stream.generate(() -> new String[]{null, null, null, null, "Buzz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        // as per above, but every 15th string is "FizzBuzz"...
        Iterator<Optional<String>> fizzbuzz = Stream.generate(() -> new String[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, "FizzBuzz"})
                .flatMap(array -> Arrays.stream(array))
                .map(n -> Optional.ofNullable(n))
                .iterator();

        /*
         * Zip the streams together by chaining orElse. Return the integer if all 3 iterators return null.
         * This works because <iterator>.next() is always evaluated even if the value itself is not used
         */
        return IntStream.rangeClosed(1, 100)
                .mapToObj(n -> fizzbuzz.next().orElse(fizz.next().orElse(buzz.next().orElse(Integer.toString(n)))))
                .collect(Collectors.toList());
    }

    /*
     * As per attempt 4 but attempts to do away with the array initialisations.
     * Could easily be refactored a little, but regardless is a horrific way of doing things.
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt5() {
        Iterator<Optional<String>> iterator = IntStream.rangeClosed(1, 100).mapToObj(n -> Optional.of(Integer.toString(n))).iterator();
        Iterator<Optional<String>> fizz = IntStream.rangeClosed(1, 100)
                .mapToObj(n -> iterator.next().filter(i -> Integer.valueOf(i) % 3 != 0))
                .map(n -> Optional.of(n.orElse("Fizz")))
                .map(n -> n.filter(m -> m.equals("Fizz")))
                .iterator();

        Iterator<Optional<String>> iterator2 = IntStream.rangeClosed(1, 100).mapToObj(n -> Optional.of(Integer.toString(n))).iterator();
        Iterator<Optional<String>> buzz = IntStream.rangeClosed(1, 100)
                .mapToObj(n -> iterator2.next().filter(i -> Integer.valueOf(i) % 5 != 0))
                .map(n -> Optional.of(n.orElse("Buzz")))
                .map(n -> n.filter(m -> m.equals("Buzz")))
                .iterator();

        Iterator<Optional<String>> iterator3 = IntStream.rangeClosed(1, 100).mapToObj(n -> Optional.of(Integer.toString(n))).iterator();
        Iterator<Optional<String>> fizzbuzz = IntStream.rangeClosed(1, 100)
                .mapToObj(n -> iterator3.next().filter(i -> Integer.valueOf(i) % 15 != 0))
                .map(n -> Optional.of(n.orElse("FizzBuzz")))
                .map(n -> n.filter(m -> m.equals("FizzBuzz")))
                .iterator();

        return IntStream.rangeClosed(1, 100)
                .mapToObj(n -> fizzbuzz.next().orElse(fizz.next().orElse(buzz.next().orElse(Integer.toString(n)))))
                .collect(Collectors.toList());
    }

    /*
     * Credit to @stefank for this much neater solution which involves no loops, conditionals or array initialising
     */
    @VisibleForTesting
    List<String> fizzBuzzAttempt6() {
        return integers().map(i -> i.toString())
                .map(i -> replaceIfMultiple(i, 15, "FizzBuzz"))
                .map(i -> replaceIfMultiple(i, 5, "Buzz"))
                .map(i -> replaceIfMultiple(i, 3, "Fizz"))
                .limit(100)
                .collect(Collectors.toList());
    }

    static Stream<Integer> integers() {
        return Stream.iterate(1, i -> i + 1);
    }

    static String replaceIfMultiple(String intStr, int modulus, String replacement) {
        return Optional.of(intStr).filter(s -> !isMultiple(s, modulus)).orElse(replacement);
    }

    static boolean isMultiple(String intStr, int modulus) {
        return isInt(intStr) && Integer.parseInt(intStr) % modulus == 0;
    }

    static boolean isInt(String s) {
        return s.matches("[0-9]+");
    }
}
