public class Task5 {
    public static void main(String[] args) {
        System.out.println("1. " + sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println("2. " + memeSum(122, 81));
        System.out.println(". ");
        System.out.println(". ");
        System.out.println(". ");
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
        
    }
}