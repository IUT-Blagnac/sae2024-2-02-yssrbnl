package iut.sae.algo;

/**
 * Cette classe contient des méthodes pour la compression et la décompression
 * d'une chaîne de caractères en utilisant l'algorithme Run-Length Encoding (RLE).
 *
 * <p>Les méthodes {@code RLE} permettent de compresser une chaîne en remplaçant
 * les séquences répétitives de caractères par un nombre suivi du caractère.
 * Les méthodes {@code unRLE} permettent de décompresser une chaîne encodée
 * avec RLE pour retrouver la chaîne originale.</p>
 *
 * @version 1.0
 */
public class AlgoSimpleLisible {

    /**
     * Compresse une chaîne de caractères en utilisant l'algorithme de Run-Length Encoding (RLE).
     *
     * @param in La chaîne à compresser.
     * @return La chaîne compressée.
     */
    public static String RLE(String in) {
        // Vérifie si la chaîne d'entrée est nulle ou vide
        if (in == null || in.isEmpty()) {
            return "";
        }

        // Initialisation de la chaîne de résultat
        String result = "";
        // Caractère courant
        char currentChar = in.charAt(0);
        // Compteur d'occurrences du caractère courant
        int count = 1;

        // Parcourt la chaîne d'entrée à partir du deuxième caractère
        for (int i = 1; i < in.length(); i++) {
            // Caractère suivant
            char nextChar = in.charAt(i);
            // Si le caractère suivant est le même que le courant et que le compteur est inférieur à 9
            if (nextChar == currentChar && count < 9) {
                count++;
            } else {
                // Ajoute le compteur et le caractère courant à la chaîne de résultat
                result += count + "" + currentChar;
                // Met à jour le caractère courant et réinitialise le compteur
                currentChar = nextChar;
                count = 1;
            }
        }

        // Ajoute la dernière séquence compressée à la chaîne de résultat
        result += count + "" + currentChar;
        return result;
    }

    /**
     * Compresse une chaîne de caractères plusieurs fois en utilisant l'algorithme de Run-Length Encoding (RLE).
     *
     * @param in La chaîne à compresser.
     * @param iteration Le nombre d'itérations de compression.
     * @return La chaîne compressée après plusieurs itérations.
     */
    public static String RLE(String in, int iteration) {
        // Vérifie si le nombre d'itérations est négatif
        if (iteration < 0) {
            throw new IllegalArgumentException("Le nombre d'itérations doit être non négatif.");
        }

        // Vérifie si la chaîne d'entrée est nulle ou vide
        if (in == null || in.isEmpty()) {
            return "";
        }

        // Initialisation de la chaîne de résultat
        String result = in;
        // Effectue la compression RLE le nombre de fois spécifié
        for (int i = 0; i < iteration; i++) {
            result = RLE(result);
        }
        return result;
    }

    /**
     * Décompresse une chaîne de caractères compressée en utilisant l'algorithme de Run-Length Encoding (RLE).
     *
     * @param in La chaîne à décompresser.
     * @return La chaîne décompressée.
     */
    public static String unRLE(String in) {
        // Vérifie si la chaîne d'entrée est nulle ou vide
        if (in == null || in.isEmpty()) {
            return "";
        }

        // Vérifie si la longueur de la chaîne est paire
        if (in.length() % 2 != 0) {
            throw new IllegalArgumentException("La chaîne d'entrée doit avoir une longueur paire.");
        }

        // Initialisation de la chaîne de résultat
        String result = "";

        // Parcourt la chaîne d'entrée deux caractères à la fois
        for (int i = 0; i < in.length(); i += 2) {
            // Récupère le nombre de répétitions du caractère
            int count = in.charAt(i) - '0';
            // Récupère le caractère à répéter
            char c = in.charAt(i + 1);

            // Ajoute le caractère le nombre de fois indiqué à la chaîne de résultat
            for (int j = 0; j < count; j++) {
                result += c;
            }
        }

        return result;
    }

    /**
     * Décompresse une chaîne de caractères plusieurs fois en utilisant l'algorithme de Run-Length Encoding (RLE).
     *
     * @param in La chaîne à décompresser.
     * @param iteration Le nombre d'itérations de décompression.
     * @return La chaîne décompressée après plusieurs itérations.
     */
    public static String unRLE(String in, int iteration) {
        // Vérifie si le nombre d'itérations est négatif
        if (iteration < 0) {
            throw new IllegalArgumentException("Le nombre d'itérations doit être non négatif.");
        }

        // Vérifie si la chaîne d'entrée est nulle ou vide
        if (in == null || in.isEmpty()) {
            return "";
        }

        // Initialisation de la chaîne de résultat
        String result = in;
        // Effectue la décompression RLE le nombre de fois spécifié
        for (int i = 0; i < iteration; i++) {
            result = unRLE(result);
        }
        return result;
    }
}
