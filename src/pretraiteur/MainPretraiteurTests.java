package pretraiteur;

import java.util.Arrays;
import java.util.Collections;

import nom.Nom;

public class MainPretraiteurTests {

    public static void main(String[] args) {
        IPretraiteurChaine minuscule = new MinusculePretraiteur();
        check("abc".equals(minuscule.pretraiter("ABC")), "MinusculePretraiteur");

        IPretraiteurChaine accent = new AccentPretraiteur();
        check("Ess".equals(accent.pretraiter("\u00C9\u00DF")), "AccentPretraiteur");

        IPretraiteurChaine ponctuation = new PonctuationPretraiteur();
        check("abc12".equals(ponctuation.pretraiter("abc-12!")), "PonctuationPretraiteur");

        IPretraiteurChaine motsVides = new MotsVidesPretraiteur();
        check("ahmed ali".equals(motsVides.pretraiter("ahmed ben ali")), "MotsVidesPretraiteur");

        IPretraiteurChaine espaces = new EspacesPretraiteur();
        check("a b".equals(espaces.pretraiter("  a   b  ")), "EspacesPretraiteur");

        IPretraiteurChaine pipeline = new PretraiteurChaine();
        check("ahmed ali".equals(pipeline.pretraiter("Ahmed Ben Ali!")), "PretraiteurChaine pipeline");

        IPretraiteurNom pretraiteurNom = new PretraiteurNom();
        Nom nom = new Nom(1, "Ahmed Ben Ali!");
        pretraiteurNom.pretraiter(nom);
        check("ahmed ali".equals(nom.getNomPretraite()), "PretraiteurNom normalized name");
        check(nom.getTokens().equals(Arrays.asList("ahmed", "ali")), "PretraiteurNom tokenizes name");
        check(pretraiteurNom.pretraiterListe(null).isEmpty(), "PretraiteurNom null list");
        check(pretraiteurNom.pretraiterListe(Collections.singletonList(new Nom(2, "Jean Dupont"))).size() == 1,
                "PretraiteurNom list");

        ITokeniseur legacyInterface = chaine -> Collections.singletonList(chaine);
        check(legacyInterface.tokeniser("ok").equals(Collections.singletonList("ok")),
                "pretraiteur.ITokeniseur interface");

        System.out.println("OK pretraiteur: all tests passed");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
