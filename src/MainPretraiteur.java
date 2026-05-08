import java.util.Scanner;

public class MainPretraiteur {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEST DES PRETRAITEURS ===");
        System.out.print("Entrez une chaine : ");
        String rawInput = scanner.nextLine();
        System.out.println();

        IPretraiteurChaine minuscule = new MinusculePretraiteur();
        IPretraiteurChaine accent = new AccentPretraiteur();
        IPretraiteurChaine ponctuation = new PonctuationPretraiteur();
        IPretraiteurChaine motsVides = new MotsVidesPretraiteur();
        IPretraiteurChaine espaces = new EspacesPretraiteur();
        IPretraiteurChaine pipeline = new PretraiteurChaine();
        IPretraiteurNom pretraiteurNom = new PretraiteurNom();

        System.out.println("--- PretraiteurChaine (pipeline complet) ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + pipeline.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- MinusculePretraiteur ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + minuscule.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- AccentPretraiteur ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + accent.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- PonctuationPretraiteur ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + ponctuation.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- MotsVidesPretraiteur ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + motsVides.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- EspacesPretraiteur ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + espaces.pretraiter(rawInput));
        System.out.println();

        System.out.println("--- PretraiteurNom ---");
        Nom nom = new Nom(1, rawInput);
        String nomAvant = nom.toString();
        pretraiteurNom.pretraiter(nom);
        System.out.println("Input  : " + nomAvant);
        System.out.println("Output : " + nom.toString());
        System.out.println();

        System.out.println("--- TokeniseurSimple ---");
        ITokeniseur tokeniseurSimple = new TokeniseurSimple();
        String chainePretraitee = nom.getDernierNomPretraite();
        List<String> tokensSimple = tokeniseurSimple.tokeniser(chainePretraitee);
        System.out.println("Input  : " + chainePretraitee);
        System.out.println("Output : " + tokensSimple);
        System.out.println();

        System.out.println("--- TokeniseurNGramme (n=3) ---");
        ITokeniseur tokeniseurNGramme = new TokeniseurNGramme(3);
        List<String> tokensNGramme = tokeniseurNGramme.tokeniser(chainePretraitee);
        System.out.println("Input  : " + chainePretraitee);
        System.out.println("Output : " + tokensNGramme);
        System.out.println();

        System.out.println("--- Nom apres tokenisation ---");
        System.out.println(nom.toString());
        System.out.println();

        scanner.close();
    }
}
