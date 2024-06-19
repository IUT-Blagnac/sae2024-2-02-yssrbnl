package iut.sae.algo;

public class AlgoEfficace {

    // Méthode pour effectuer l'encodage RLE (Run-Length Encoding)
    public static String RLE(String in) {
        if (in == null || in.isEmpty()) {
            return "";  // Retourne une chaîne vide si l'entrée est vide ou nulle
        }

        StringBuilder sb = new StringBuilder();  // StringBuilder pour construire le résultat
        int length = in.length();
        int count = 1;
        char currentChar = in.charAt(0);

        // Parcours la chaîne d'entrée à partir du deuxième caractère
        for (int i = 1; i < length; i++) {
            if (in.charAt(i) == currentChar) {
                count++;
            } else {
                appendEncoded(sb, count, currentChar);  // Appel de la méthode pour ajouter l'encodage au StringBuilder
                currentChar = in.charAt(i);
                count = 1;
            }
        }

        appendEncoded(sb, count, currentChar);  // Ajoute la dernière séquence au StringBuilder

        return sb.toString();  // Retourne le résultat encodé en tant que chaîne
    }

    // Méthode auxiliaire pour ajouter l'encodage au StringBuilder
    private static void appendEncoded(StringBuilder sb, int count, char currentChar) {
        if (count > 9) {
            // Si le nombre de répétitions est supérieur à 9, divise-le en chiffres individuels
            sb.append(9).append(currentChar);
            count -= 9;
            while (count > 9) {
                sb.append(9).append(currentChar);
                count -= 9;
            }
        }
        sb.append(count).append(currentChar);  // Ajoute le nombre et le caractère au StringBuilder
    }

    // Méthode pour appliquer l'encodage RLE de manière itérative 'iteration' fois
    public static String RLE(String in, int iteration) throws AlgoException {
        if (iteration < 1) {
            throw new AlgoException("Iteration count must be at least 1");
        }

        String result = in;
        for (int i = 0; i < iteration; i++) {
            result = RLE(result);  // Applique l'encodage RLE de base itérativement
        }

        return result;
    }

    // Méthode pour effectuer le décodage RLE (Run-Length Decoding)
    public static String unRLE(String in) throws AlgoException {
        if (in == null || in.isEmpty()) {
            return "";  // Retourne une chaîne vide si l'entrée est vide ou nulle
        }

        StringBuilder sb = new StringBuilder();  // StringBuilder pour construire le résultat
        int length = in.length();
        int count = 0;

        // Parcours la chaîne d'entrée
        for (int i = 0; i < length; i++) {
            char c = in.charAt(i);
            if (Character.isDigit(c)) {
                count = count * 10 + (c - '0');  // Calcule le nombre de répétitions
            } else {
                if (count == 0) {
                    throw new AlgoException("Invalid input format");
                }
                sb.append(String.valueOf(c).repeat(count));  // Répète le caractère 'count' fois
                count = 0;
            }
        }

        return sb.toString();  // Retourne le résultat décodé en tant que chaîne
    }

    // Méthode pour appliquer le décodage RLE de manière itérative 'iteration' fois
    public static String unRLE(String in, int iteration) throws AlgoException {
        if (iteration < 1) {
            throw new AlgoException("Iteration count must be at least 1");
        }

        String result = in;
        for (int i = 0; i < iteration; i++) {
            result = unRLE(result);  // Applique le décodage RLE de base itérativement
        }

        return result;
    }
}
