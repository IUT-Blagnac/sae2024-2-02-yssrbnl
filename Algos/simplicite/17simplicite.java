package iut.sae.algo;

/**
 * Rendu pour Simplicité
 * <BR>
 * Classe contenant les algorithmes de compression et décompression RLE
 * <BR>
 * IUT de Blagnac - Université Toulouse II - Jean Jaurès
 * S2.02 - Exploration algorithmique d'un problème
 * Année universitaire 2023-2024
 */
public class simplicite {

    public static String RLE(String in) {
        if (in.isEmpty()) return "";

        StringBuilder resultat = new StringBuilder();
        int compteur = 1;
        char caractereActuel = in.charAt(0);

        for (int i = 1; i < in.length(); i++) {
            if (in.charAt(i) == caractereActuel) {
                compteur++;
                if (compteur == 9) {
                    resultat.append(compteur).append(caractereActuel);
                    compteur = 0;
                }
            } else {
                if (compteur > 0) {
                    resultat.append(compteur).append(caractereActuel);
                }
                caractereActuel = in.charAt(i);
                compteur = 1;
            }
        }

        if (compteur > 0) {
            resultat.append(compteur).append(caractereActuel);
        }

        return resultat.toString();
    }

    public static String RLE(String in, int iteration) throws AlgoException {
        if (iteration < 1) throw new AlgoException("L'itération doit être supérieure à 0");
        if (iteration == 1) return RLE(in);

        return RLE(RLE(in, iteration - 1));
    }

    public static String unRLE(String in, int iteration) throws AlgoException {
        if (iteration < 1) throw new AlgoException("L'itération doit être supérieure à 0");

        String resultat = in;
        for (int i = 0; i < iteration; i++) {
            resultat = unRLE(resultat);
        }
        return resultat;
    }

    public static String unRLE(String in) throws AlgoException {
        if (in.length() % 2 != 0) throw new AlgoException("Longueur d'entrée invalide");
        if (in.isEmpty()) return "";

        StringBuilder sortie = new StringBuilder();
        for (int i = 0; i < in.length(); i += 2) {

            char charCompteur = in.charAt(i);
            if (!Character.isDigit(charCompteur)) throw new AlgoException("Le caractère de comptage doit être un chiffre");

            char caractere = in.charAt(i + 1);
            if (!(caractere >= 32 && caractere <= 126)) throw new AlgoException("Caractère invalide rencontré : " + caractere);

            int compteur = Character.getNumericValue(charCompteur);
            if (compteur <= 0) throw new AlgoException("Le compteur doit être supérieur à zéro");

            sortie.append(String.valueOf(caractere).repeat(compteur));

        }
        return sortie.toString();
    }
}
