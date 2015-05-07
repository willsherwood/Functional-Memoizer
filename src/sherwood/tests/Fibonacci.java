package sherwood.tests;

import sherwood.function.Memoized;
import sherwood.function.RecursiveFunction;

import java.math.BigInteger;

public class Fibonacci {

    public static int calls;

    private RecursiveFunction<Integer, Integer> fib = new RecursiveFunction<Integer, Integer>() {
        @Override
        public Integer apply (Integer n) {
            calls++;
            if (n <= 2)
                return 1;
            return self.apply(n-1) + self.apply(n-2);
        }
    };

    private RecursiveFunction<Long, BigInteger> bigFib = new RecursiveFunction<Long, BigInteger>() {
        @Override
        public BigInteger apply (Long n) {
            calls++;
            if (n <= 2)
                return BigInteger.ONE;
            return self.apply(n-1).add(self.apply(n-2));
        }
    };

    public void regularTest() {
        calls = 0;
        System.out.println(fib.apply(20) + " in " + calls + " calls.");
        calls = 0;
        System.out.println(new Memoized<>(fib).apply(20) + " in " + calls + " calls.");
    }

    public void bigIntTest() {
        calls = 0;
        System.out.println(new Memoized<>(bigFib).apply(20L) + " in " + calls + " calls.");
        calls = 0;
        System.out.println(new Memoized<>(bigFib).apply(200L) + " in " + calls + " calls.");
        calls = 0;
        System.out.println(new Memoized<>(bigFib).apply(1000L) + " in " + calls + " calls.");
    }
}
