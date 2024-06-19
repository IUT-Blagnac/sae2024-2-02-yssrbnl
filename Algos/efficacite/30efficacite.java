package iut.sae.algo;


public class Algo{
    public static String RLE(String chaine){
        // Utilisation d'un StringBuilder (pour améliorer le temps d'execution) car l'object String ne concatène pas simplement plusieurs chaine.
        // Initialisation d'un compteur et d'un indice
        StringBuilder dBuilder = new StringBuilder();
        int cptChar = 1;
        char charC;
        int i = 0;

        while(i < chaine.length()){
            charC = chaine.charAt(i);

            while (i+1 < chaine.length() && charC == chaine.charAt(i+1)){
                cptChar++;
                i++;
                if (cptChar == 9) {
                    dBuilder.append(9).append(charC);
                    cptChar = 0;
                }
                
            } 
            if (cptChar > 0) {
                dBuilder.append(cptChar).append(charC);
            }
            cptChar = 1;
            i++;
        }
        // On retourne la méthode toString() du builder qui renvoie la chaîne compressée
        return dBuilder.toString();
    }

    public static String RLE(String chaine, int iteration) throws AlgoException{
        if (iteration == 0){
            return chaine;
        }
        else{
            return RLE(RLE(chaine), iteration-1);
        }
        
    }

    public static String unRLE(String chaine) throws AlgoException{
        if (chaine.length() == 0 || !Character.isDigit(chaine.charAt(0))) {
            return chaine;
        }
        StringBuilder dBuilder = new StringBuilder();

        // On parcourt la chaine de 2 en 2 (pour chaque couple compteur/caractère)
        for (int i = 0; i < chaine.length(); i+=2) {
            // On récupère le compteur devant le caractère...
            int cptChar = Character.getNumericValue(chaine.charAt(i));
            char carac = chaine.charAt(i+1);

            // ... et on ajoute le caractère le nombre de fois nécessaire (=compteur)
            for(int j = 0; j < cptChar; j++){
                dBuilder.append(carac);
            }
        }
    return dBuilder.toString();

    }

    public static String unRLE(String chaine, int iteration) throws AlgoException{
        if (iteration == 0){
            return chaine;
        }
        else {
            return unRLE(unRLE(chaine), iteration-1);
        }

    }
}

