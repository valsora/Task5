public class Task5 {
    public static void main(String[] args) {
        System.out.println("1. " + sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println("2. " + memeSum(248, 208));
        System.out.println("3. " + digitsCount(1289396387328L));
        System.out.println("4. " + totalPoints(new String[] {"trance", "recant"}, "recant"));
        System.out.println("5. " + longestRun(new int[] {1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
    }

    public static boolean sameLetterPattern(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.length() == 1) return true;
        for (int i = 1; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            for (int j = 0; j < i; j++) {
                if (s1.charAt(j) == c1) {
                    if (s2.charAt(j) != c2) return false; 
                } else {
                    if (s2.charAt(j) == c2) return false;
                }
            }
        }
        return true;
    }

    public static int memeSum(int a, int b) {
        String result = "";
        while (a > 0 || b > 0) {
            result = Integer.toString(a % 10 + b % 10) + result;
            a /= 10;
            b /= 10;
        }
        return Integer.parseInt(result);
    }

    public static int digitsCount(long n) {
        if (n == 0) return 0;
        return 1 + digitsCount(n / 10);
    }

    public static int totalPoints(String[] guesses, String word) {
        int pts = 0;
        for (String guess : guesses) {
            if (isGuessFormedOfWord(guess, word)) {
                switch (guess.length()) {
                    case 3:
                        pts += 1;
                        break;
                    case 4:
                        pts += 2;
                        break;
                    case 5:
                        pts += 3;
                        break;
                    case 6:
                        pts += 54;
                        break;
                    default:
                        break;
                }
            }
        }
        return pts;
    }

    public static boolean isGuessFormedOfWord(String guess, String word) {
        if (guess.length() > word.length()) return false;
        for (int i = 0; i < guess.length(); i++) {
            int index = word.indexOf(guess.charAt(i));
            if (index == -1) return false;
            word = word.substring(0, index) + word.substring(index + 1);
        }
        return true;
    }

    public static int longestRun(int[] arr) {
        if (arr.length == 1) return 1;
        int maxLengthInc = 0;
        int maxLengthDec = 0;
        int lengthInc = 1;
        int lengthDec = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1) lengthInc++;
            else lengthInc = 1;
            if (arr[i] == arr[i - 1] - 1) lengthDec++;
            else lengthDec = 1;
            if (lengthInc > maxLengthInc) maxLengthInc = lengthInc;
            if (lengthDec > maxLengthDec) maxLengthDec = lengthDec;
        }
        return Math.max(maxLengthInc, maxLengthDec);
    }
}
