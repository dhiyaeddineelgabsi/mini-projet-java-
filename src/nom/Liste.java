package nom;
import pretraiteur.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Liste {
    private String nomListe;
    private String chemin;
    private List<Nom> noms;
    private int lignesIgnorees;

    public Liste(String nomListe, String chemin) {
        this.nomListe = nomListe;
        this.chemin = chemin;
        this.noms = new ArrayList<>();
    }

    public void chargerDepuisCSV(String chemin) {
        chargerDepuisCSV(chemin, new PretraiteurNom());
    }

    public void chargerDepuisCSV(String chemin, PretraiteurNom pretraiteur) {
        this.chemin = chemin;
        this.noms.clear();
        this.lignesIgnorees = 0;

        if (!validerFormatCSV(chemin)) {
            System.out.println("[ERREUR] Fichier CSV invalide ou introuvable : " + chemin);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(chemin),
                StandardCharsets.UTF_8
        ))) {
            reader.readLine();

            int numeroLigne = 1;
            String ligne = reader.readLine();
            while (ligne != null) {
                numeroLigne++;
                if (!ligne.trim().isEmpty()) {
                    String[] parts = ligne.split(",", 2);
                    if (parts.length == 2 && !parts[0].trim().isEmpty() && !parts[1].trim().isEmpty()) {
                        Nom nom = new Nom(parts[0].trim(), parts[1].trim(), nomListe);
                        if (pretraiteur != null) {
                            pretraiteur.pretraiter(nom);
                        }
                        noms.add(nom);
                    } else {
                        signalerLigneIgnoree(numeroLigne, ligne);
                    }
                }
                ligne = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("[ERREUR] Lecture impossible : " + e.getMessage());
        }

        if (lignesIgnorees > 5) {
            System.out.println("[INFO] Autres lignes invalides ignorees : " + (lignesIgnorees - 5));
        }
    }

    public boolean validerFormatCSV(String chemin) {
        File fichier = new File(chemin);
        if (!fichier.exists() || !fichier.isFile()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fichier),
                StandardCharsets.UTF_8
        ))) {
            String premiereLigne = reader.readLine();
            return premiereLigne != null && premiereLigne.trim().equals("id,name");
        } catch (IOException e) {
            return false;
        }
    }

    public void ajouterNom(Nom nom) {
        noms.add(nom);
    }

    public List<Nom> getNoms() { return noms; }

    public String getNomListe() { return nomListe; }

    public String getChemin() { return chemin; }

    public int taille() { return noms.size(); }

    public int getLignesIgnorees() { return lignesIgnorees; }

    private void signalerLigneIgnoree(int numeroLigne, String ligne) {
        lignesIgnorees++;
        if (lignesIgnorees <= 5) {
            System.out.println("[AVERTISSEMENT] Ligne " + numeroLigne
                    + " ignoree dans " + nomListe + " : " + ligne);
        }
    }
}
