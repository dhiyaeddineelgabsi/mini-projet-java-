import java.util.List;
import java.util.Scanner;

import nom.Nom;
import pretraiteur.IPretraiteurChaine;
import pretraiteur.IPretraiteurNom;
import pretraiteur.PretraiteurChaine;
import pretraiteur.PretraiteurNom;
import tokenizeurs.ITokeniseur;
import tokenizeurs.TokeniseurNGramme;
import tokenizeurs.TokeniseurSimple;

public class MainTokens {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEST DES TOKENISEURS ===");
        System.out.print("Entrez une chaine : ");
        String rawInput = scanner.nextLine();
        System.out.println();

        IPretraiteurChaine pipeline = new PretraiteurChaine();
        String normalise = pipeline.pretraiter(rawInput);

        System.out.println("--- PretraiteurChaine (pipeline complet) ---");
        System.out.println("Input  : " + rawInput);
        System.out.println("Output : " + normalise);
        System.out.println();

        System.out.println("--- TokeniseurSimple ---");
        ITokeniseur tokeniseurSimple = new TokeniseurSimple();
        List<String> tokensSimple = tokeniseurSimple.tokeniser(normalise);
        System.out.println("Input  : " + normalise);
        System.out.println("Output : " + tokensSimple);
        System.out.println();

        System.out.println("--- TokeniseurNGramme (n=3) ---");
        ITokeniseur tokeniseurNGramme = new TokeniseurNGramme(3);
        List<String> tokensNGramme = tokeniseurNGramme.tokeniser(normalise);
        System.out.println("Input  : " + normalise);
        System.out.println("Output : " + tokensNGramme);
        System.out.println();

        System.out.println("--- PretraiteurNom + tokens ---");
        Nom nom = new Nom(1, rawInput);
        IPretraiteurNom pretraiteurNom = new PretraiteurNom();
        pretraiteurNom.pretraiter(nom);
        System.out.println(nom.toString());
        System.out.println();

        scanner.close();
    }
}

