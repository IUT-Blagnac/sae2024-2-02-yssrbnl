public class sobriete {
    public static String RLE(String chaine) {
        if (chaine.length() == 0) {
            return "";
        }


        char premierCaractere = chaine.charAt(0);
        String resultat = "";
        int cpt = 1;

        for (int i = 1; i < chaine.length(); i++) {
            if (chaine.charAt(i) == premierCaractere) {
                cpt++;
                if (cpt == 9) {
                    resultat += cpt + String.valueOf(premierCaractere);
                    cpt = 0;
                }
            } else {
                if (cpt > 0) {
                    resultat += cpt + String.valueOf(premierCaractere);
                }
                premierCaractere = chaine.charAt(i);
                cpt = 1;
            }
        }

        if (cpt > 0) {
            resultat += cpt + String.valueOf(premierCaractere);
        }
        return resultat;
    }

    public static String RLE(String chaineDeCaractere, int iteration) {
        String resultat = chaineDeCaractere;
        for (int i = 0; i < iteration; i++) {
            resultat = RLE(resultat);
        }
        return resultat;
    }

    public static String unRLE(String chaine) {
        String resultat = "";
        int lg = chaine.length();

        for (int i = 0; i < lg; i++) {
            char caractere = chaine.charAt(i);

            if (Character.isDigit(caractere)) {
                int nombre = Character.getNumericValue(caractere);
                char caractereSuivant = chaine.charAt(++i);

                for (int j = 0; j < nombre; j++) {
                    resultat += caractereSuivant;
                }
            } else {
                resultat += caractere;
            }
        }
        return resultat;
    }

    public static String unRLE(String chaine, int iteration) {
        String resultat = chaine;
        for (int i = 0; i < iteration; i++) {
            resultat = unRLE(resultat);
        }
        return resultat;
    }

}
