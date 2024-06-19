package iut.sae.algo;

public class Algo {

    public static String RLE(String in) {
        StringBuilder rle = new StringBuilder();
        int compteur = 1;
        int TailleIn=in.length();
        for (int i = 0; i < TailleIn; i++) {
            if (i+1 < TailleIn && in.charAt(i) == in.charAt(i+1) && compteur!=9) {
                compteur++;
            } else {
                rle.append(compteur).append(in.charAt(i));
                compteur = 1;
            }
        }
        return rle.toString();
    }




    public static String RLE(String in, int iteration) throws AlgoException{
        String resultat=in;
        for(int i=0;i<iteration;i++){
            resultat=RLE(resultat);
        }

        return resultat;
    }

    public static String unRLE(String in) throws AlgoException {
        StringBuilder resultat = new StringBuilder();
        int tailleIn = in.length();
        int nbBoucle;
        for (int i = 0; i < tailleIn; i++) {
            nbBoucle = in.charAt(i) - '0';//Transforme un caractère en entier.
            i++;
            char caractere = in.charAt(i);//Récupère le caractère à l'indice i de la chaîne de caractères.
            for (int j = 0; j < nbBoucle; j++) {
                resultat.append(caractere);
            }
        }

        return resultat.toString();
    }

    public static String unRLE(String in, int iteration) throws AlgoException{
        String resultat=in;
        for(int i=0;i<iteration;i++){
            resultat=unRLE(resultat);
        }
        return resultat;

    }



}
