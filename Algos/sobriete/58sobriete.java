package iut.sae.algo;


public class Algo3 {

    public static String RLE(String in) {
        if (in == null || in.isEmpty()) {
            return "";
        }
        StringBuilder code = new StringBuilder();
        int longueur = in.length();
        int cpt = 1;
        for (int i = 0; i < longueur; i++) {
            char currentChar = in.charAt(i);
            while (i + 1 < longueur && currentChar == in.charAt(i + 1) && cpt < 9) {
                cpt++;
                i++;
            }
            code.append(cpt).append(currentChar);
            cpt = 1;
        }

        return code.toString();
    }

    public static String RLE(String in, int iterations) {
        String code = in;
        for (int i = 0; i < iterations; i++) {
            code = RLE(code);
        }
        return code;
    }

    public static String unRLE(String in) {
        if (in == null || in.isEmpty()) {
            return "";
        }
        StringBuilder code = new StringBuilder();
        int longueur = in.length();
        for (int i = 0; i < longueur; i += 2) {
            if (i + 1 < longueur && Character.isDigit(in.charAt(i))) {
                int cpt = Character.getNumericValue(in.charAt(i));
                char character = in.charAt(i + 1);
                for (int j = 0; j < cpt; j++) {
                    code.append(character);
                }
            } else {
                return "";
            }
        }
        return code.toString();
    }

    public static String unRLE(String in, int iterations) {
        String code = in;
        for (int i = 0; i < iterations; i++) {
            code = unRLE(code);
        }
        return code;
    }
}
