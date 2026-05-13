package tokenizeurs;

import java.util.Arrays;
import java.util.Collections;

public class MainTokenizeurs {

    public static void main(String[] args) {
        ITokeniseur simple = new TokeniseurSimple();
        check(simple.tokeniser(" Ahmed   Ben Ali ").equals(Arrays.asList("Ahmed", "Ben", "Ali")),
                "TokeniseurSimple split spaces");
        check(simple.tokeniser(null).isEmpty(), "TokeniseurSimple null returns empty list");

        ITokeniseur ngrammes = new TokeniseurNGramme(2);
        check(ngrammes.tokeniser("ali").equals(Arrays.asList("al", "li")),
                "TokeniseurNGramme creates n-grams");
        check(ngrammes.tokeniser("a").equals(Collections.singletonList("a")),
                "TokeniseurNGramme keeps short words");

        boolean exceptionLancee = false;
        try {
            new TokeniseurNGramme(0);
        } catch (IllegalArgumentException e) {
            exceptionLancee = true;
        }
        check(exceptionLancee, "TokeniseurNGramme rejects invalid n");

        System.out.println("OK tokenizeurs: all tests passed");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
