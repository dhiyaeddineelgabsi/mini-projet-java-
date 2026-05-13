package pretraiteur;
import java.util.Scanner;

import Tokenizeurs.ITokeniseur;
import Tokenizeurs.TokeniseurNGramme;
import Tokenizeurs.TokeniseurSimple;
import nom.Nom;

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
        ITokeniseur tokeniseurSimple = new TokeniseurSimple();
        ITokeniseur tokeniseurNGramme = new TokeniseurNGramme(2);

        System.out.println("--- PretraiteurChaine (pipeline complet) ---");
        System.out.println("Input  : " + rawInput);
        String pipelineOutput = pipeline.pretraiter(rawInput);
        System.out.println("Output : " + pipelineOutput);
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
        System.out.println("Tokens : " + nom.getTokensPretraites());
        System.out.println();

        System.out.println("--- TokeniseurSimple ---");
        System.out.println("Input  : " + pipelineOutput);
        System.out.println("Tokens : " + tokeniseurSimple.tokeniser(pipelineOutput));
        System.out.println();

        System.out.println("--- TokeniseurNGramme (n=2) ---");
        System.out.println("Input  : " + pipelineOutput);
        System.out.println("Tokens : " + tokeniseurNGramme.tokeniser(pipelineOutput));
        System.out.println();

        scanner.close();
    }
}
