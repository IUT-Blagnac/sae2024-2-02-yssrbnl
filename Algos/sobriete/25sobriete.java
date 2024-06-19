package iut.sae.algo;

public class Sobriete {
    public static String RLE(String in) {
        if (in == null || in.isEmpty()) { 
            return "";
        }

        int len = in.length();
        char[] resultat = new char[len * 2]; 
        int pos = 0;
        int cpt = 1;
        char dernierChar = in.charAt(0);

        for (int i = 1; i < len; i++) {
            if (in.charAt(i) == dernierChar) {
                if (cpt == 9) {
                    pos = append(resultat, pos, cpt, dernierChar);
                    cpt = 1;
                } else { 
                    cpt++;
                }
            } else {
                pos = append(resultat, pos, cpt, dernierChar);
                dernierChar = in.charAt(i);
                cpt = 1;
            }
        }
        pos = append(resultat, pos, cpt, dernierChar);
        return new String(resultat, 0, pos);
    }

    private static int append(char[] array, int pos, int count, char ch) {
        array[pos++] = (char) (count + '0');
        array[pos++] = ch;
        return pos;
    }

    public static String RLE(String in, int iteration) throws AlgoException {
        if (in == null || in.isEmpty()) {
            return "";
        }

        if (iteration <= 0) {
            throw new AlgoException("L'itération doit être supérieure à 0");
        }

        for (int i = 0; i < iteration; i++) {
            in = RLE(in);
        }

        return in;
    }

    public static String unRLE(String in) throws AlgoException {
        if (in == null || in.isEmpty()) {
            return "";
        }

        int len = in.length();
        char[] resultat = new char[len * 9]; // Pré-allocation
        int pos = 0;

        for (int i = 0; i < len; i++) {
            if (Character.isDigit(in.charAt(i))) {
                int nbFois = in.charAt(i) - '0';
                char ch = in.charAt(i + 1);
                for (int j = 0; j < nbFois; j++) {
                    resultat[pos++] = ch;
                }
                i++;
            }
        }
        return new String(resultat, 0, pos);
    }

    public static String unRLE(String in, int iteration) throws AlgoException {
        if (in == null || in.isEmpty()) {
            return "";
        }

        if (iteration <= 0) {
            throw new AlgoException("L'itération doit être supérieure à 0");
        }

        for (int i = 0; i < iteration; i++) {
            in = unRLE(in);
        }

        return in;
    }
}
