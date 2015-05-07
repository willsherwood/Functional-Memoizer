package sherwood.tests;

public class Main {

    public static void main (String[] args) {
        new Fibonacci().regularTest();
        new Fibonacci().bigIntTest();
        System.out.println(new PrefixEvaluator("+ 3 - 4 * 1 - 3 4".split(" ")).evaluate.apply(0));
    }
}
