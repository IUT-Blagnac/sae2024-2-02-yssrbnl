package iut.sae.algo;

public class Sobriete {

    public static String RLE(String in) {
        StringBuilder out = new StringBuilder("");
        int nbrLettres = 1;
        int taille = in.length();
        for (int i = 0; i < taille; i++) {
            if (i == taille - 1) {
                out.append(nbrLettres).append(in.charAt(i));
            } else if (in.charAt(i) != in.charAt(i + 1) || nbrLettres == 9) {
                out.append(nbrLettres).append(in.charAt(i));
                nbrLettres = 1;
            } else {
                nbrLettres++;
            }
        }
        return out.toString();
    }

    public static String RLE(String in, int iteration) {
        if (iteration <= 1) {
            return RLE(in);
        } else {
            return RLE(RLE(in, iteration - 1));
        }
    }

    public static String unRLE(String in) {
        StringBuilder out = new StringBuilder();
        int taille = in.length();
        for (int i = 0; i < taille; i += 2) {
            char c = in.charAt(i + 1);
            int nbrLettres = Character.getNumericValue(in.charAt(i));
            for (int j = 0; j < nbrLettres; j++) {
                out.append(c);
            }
        }
        return out.toString();
    }

    public static String unRLE(String in, int iteration) {
        if (iteration <= 1) {
            return unRLE(in);
        } else {
            return unRLE(unRLE(in, iteration - 1));
        }
    }
}
