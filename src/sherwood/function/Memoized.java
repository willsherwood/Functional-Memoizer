package sherwood.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoized<T, R> implements Function<T, R> {

    private Function<T, R> original;
    private Map<T, R> dict;

    public Memoized (RecursiveFunction<T, R> function) {
        this(function, new HashMap<>());
    }

    public Memoized (RecursiveFunction<T, R> function, Map<T, R> initialDictionary) {
        this.original = function;
        function.reroute(this);
        this.dict = initialDictionary;
    }

    @Override
    public R apply (T t) {
        if (dict.containsKey(t))
            return dict.get(t);
        R r = original.apply(t);
        dict.put(t, r);
        return r;
    }
}
