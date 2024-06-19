package iut.sae.algo;

public class Algo {

    public static String RLE(String in) {
        if (in == null || in.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        char lastCharacter = in.charAt(0);
        char currentCharacter;
        int occurences = 1;

        for (int i = 1; i < in.length(); i++) {
            currentCharacter = in.charAt(i);

            if (lastCharacter == currentCharacter) {
                occurences++;
            } else {
                while (occurences > 9) {
                    result.append(9).append(lastCharacter);
                    occurences -= 9;
                }
                result.append(occurences).append(lastCharacter);
                occurences = 1;
                lastCharacter = currentCharacter;
            }
        }
        while (occurences > 9) {
            result.append(9).append(lastCharacter);
            occurences -= 9;
        }
        result.append(occurences).append(lastCharacter);

        return result.toString();
    }

    public static String RLE(String in, int iteration) throws AlgoException {
        String result = in;
        for (int i = 0; i < iteration; i++) {
            result = RLE(result);
        }
        return result;
    }

    public static String unRLE(String in) throws AlgoException {
        if (in == null || in.length() % 2 != 0) {
            throw new AlgoException();
        }
        if (in.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int occurence;
        char character;

        for (int i = 0; i < in.length(); i += 2) {
            occurence = in.charAt(i) - '0';
            character = in.charAt(i + 1);
            for (int j = 0; j < occurence; j++) {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String unRLE(String in, int iteration) throws AlgoException {
        String result = in;
        for (int i = 0; i < iteration; i++) {
            result = unRLE(result);
        }
        return result;

    }
}
