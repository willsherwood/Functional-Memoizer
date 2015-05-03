package sherwood.function;

import java.util.function.Function;

public abstract class RecursiveFunction<T, R> implements Function<T, R> {

    protected Function<T, R> self;

    public RecursiveFunction() {
        this.self = this;
    }

    public void reroute(Function<T, R> newSelf) {
        this.self = newSelf;
    }
}
