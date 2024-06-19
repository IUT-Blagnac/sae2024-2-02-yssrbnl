public class Simplicité {
    
    public static String RLE(String in){
        String res="";
        int cptLettre=1;
        //parcour de la chaine d'entré si non vide
        for(int ¤=0;¤<in.length();¤++){  
            // test si on est au dernier caractère de la liste
            if(¤!=in.length()-1){
                // compare le caractère courant à son suivant
                if(in.charAt(¤)==in.charAt(¤+1)){
                    cptLettre+=1;
                }else{
                    // quand caractère suivant diférence, ajout dans le résultat du caractère courant et son nombre d'apparition
                    if (cptLettre>9) {
                        int nb9=cptLettre/9;
                        for(int j=0;j<nb9;j++){
                            res+=9;
                            res+=in.charAt(¤);
                            cptLettre-=9;
                        }
                        if (cptLettre!=0) {
                            res+=cptLettre;
                            res+=in.charAt(¤);
                        }
                        cptLettre=1;
                    }else{
                        res+=cptLettre;
                        res+=in.charAt(¤);
                        cptLettre=1; 
                    } 
                }
            }else{
                // fin de parcour, ajout du dernier caractère compté
                if (cptLettre>9) {
                    int nb9=cptLettre/9;
                    for(int j=0;j<nb9;j++){
                        res+=9;
                        res+=in.charAt(¤);
                        cptLettre-=9;
                    }
                    if (cptLettre!=0) {
                        res+=cptLettre;
                        res+=in.charAt(¤);
                    }
                    cptLettre=1;
                }else{
                    res+=cptLettre;
                    res+=in.charAt(¤);
                    cptLettre=1; 
                }
            }  
        }
        return res;
    }

    public static String RLE(String in, int iteration) throws AlgoException{
        String res=in;
        for(int ¤=0;¤<iteration;¤++){
            res=RLE(res);
        }
        return res;
    }

    public static String unRLE(String in) throws AlgoException{
        String res="";
        for(int ¤=0;¤<in.length();¤+=2){
            int cpt=Character.getNumericValue(in.charAt(¤));
            for(int j=0;j<cpt;j++){
                res+=in.charAt(¤+1);
            }
        }
        return res;
    }

    public static String unRLE(String in, int iteration) throws AlgoException{
        String res=in;
        for(int ¤=0;¤<iteration;¤++){
            res=unRLE(res);
        }
        return res;
    }
}
