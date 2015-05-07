package sherwood.tests;

import sherwood.function.Memoized;
import sherwood.function.RecursiveFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PrefixEvaluator {

    private String[] toks;

    public Function<Integer, Long> evaluate = new Memoized<>(new RecursiveFunction<Integer, Long>() {
        @Override
        public Long apply (Integer x) {
            if (toks[x].matches("\\d+")) {
                return Long.valueOf(toks[x]);
            }
            return operators.get(toks[x]).apply(self.apply(x + 1), self.apply(x + 2));
        }
    });

    public PrefixEvaluator (String[] tokens) {
        this.toks = tokens;
    }

    private static Map<String, BiFunction<Long, Long, Long>> operators = new HashMap<>();

    static {
        operators.put("+", (x, y) -> x + y);
        operators.put("-", (x, y) -> x - y);
        operators.put("*", (x, y) -> x * y);
        operators.put("/", (x, y) -> x / y);
    }
}
