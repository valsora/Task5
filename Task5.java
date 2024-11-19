import java.util.ArrayList;
import java.util.Collections;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("1. " + sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println("2. " + memeSum(248, 208));
        System.out.println("3. " + digitsCount(1289396387328L));
        System.out.println("4. " + totalPoints(new String[] {"trance", "recant"}, "recant"));
        System.out.println("5. " + longestRun(new int[] {1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println("6. " + takeDownAverage(new String[] {"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println("7. " + canMove("Queen", "C4", "D6"));
        System.out.println("8. " + maxPossible(8732, 91255));
        System.out.println("9. " + timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("10. " + isNew(509));
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

    public static String takeDownAverage(String[] grades) {
        if (grades.length == 0) return null;
        double sum = 0;
        for (String stringGrade : grades) {
            sum += Double.parseDouble(stringGrade.substring(0, stringGrade.length() - 1));
        }
        double myGrade = (sum / grades.length - 5) * (grades.length + 1) - sum;
        if (myGrade < 0) return null; 
        return Math.round(myGrade) + "%";
    }

    public static boolean canMove(String name, String from, String to) {
        int fromLetter = Character.getNumericValue(from.charAt(0)) - 64;
        int fromNumber = Character.getNumericValue(from.charAt(1));
        int toLetter = Character.getNumericValue(to.charAt(0)) - 64;
        int toNumber = Character.getNumericValue(to.charAt(1));
        int letterShift = Math.abs(fromLetter - toLetter);
        int numberShift = Math.abs(fromNumber - toNumber);
        switch (name) {
            case "Pawn":
                return (fromLetter == toLetter && (fromNumber + 1 == toNumber || (fromNumber == 2 && toNumber == 4)));
            case "Knight":
                return ((letterShift == 1 && numberShift == 2) || (letterShift == 2 && numberShift == 1));
            case "Bishop":
                return (letterShift == numberShift);
            case "Rook":
                return (fromLetter == toLetter || fromNumber == toNumber);
            case "Queen":
                return (fromLetter == toLetter || fromNumber == toNumber || letterShift == numberShift);
            case "King":
                return (letterShift <= 1 && numberShift <= 1);
            default:
                return false;
        }
    }

    public static int maxPossible(int a, int b) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (b > 0) {
            arrayList.add(b % 10);
            b /= 10;
        }
        Collections.sort(arrayList);
        String s = Integer.toString(a);
        for (int i = 0; i < s.length(); i++) {
            if (arrayList.isEmpty()) return Integer.parseInt(s); 
            int biggist = arrayList.getLast();
            if (Character.getNumericValue(s.charAt(i)) < biggist) {
                s = s.substring(0, i) + biggist + s.substring(i + 1);
                arrayList.removeLast();
            }
        }
        return Integer.parseInt(s);
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) {
        SimpleDateFormat formatterIn = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        SimpleDateFormat formatterOut = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.ENGLISH);
        String tzIDA = "GMT";
        String tzIDB = "GMT";
        switch (cityA) {
            case "Los Angeles":
                tzIDA += "-0800";
                break;
            case "New York":
                tzIDA += "-0500";
                break;
            case "Caracas":
                tzIDA += "-0430";  
                break;
            case "Buenos Aires":
                tzIDA += "-0300";
                break;
            case "London":
                tzIDA += "+0000";  
                break;
            case "Rome":
                tzIDA += "+0100";  
                break;
            case "Moscow":
                tzIDA += "+0300";  
                break;
            case "Tehran":
                tzIDA += "+0330";
                break;
            case "New Delhi":
                tzIDA += "+0530";
                break;
            case "Beijing":
                tzIDA += "+0800";
                break;
            case "Canberra":
                tzIDA += "+1000";
                break;
            default:
                tzIDA += "+0000";
                break;
        }
        switch (cityB) {
            case "Los Angeles":
                tzIDB += "-0800";
                break;
            case "New York":
                tzIDB += "-0500";
                break;
            case "Caracas":
                tzIDB += "-0430";  
                break;
            case "Buenos Aires":
                tzIDB += "-0300";
                break;
            case "London":
                tzIDB += "+0000";  
                break;
            case "Rome":
                tzIDB += "+0100";  
                break;
            case "Moscow":
                tzIDB += "+0300";  
                break;
            case "Tehran":
                tzIDB += "+0330";
                break;
            case "New Delhi":
                tzIDB += "+0530";
                break;
            case "Beijing":
                tzIDB += "+0800";
                break;
            case "Canberra":
                tzIDB += "+1000";
                break;
            default:
                tzIDB += "+0000";
                break;
        }
        formatterIn.setTimeZone(TimeZone.getTimeZone(tzIDA));
        formatterOut.setTimeZone(TimeZone.getTimeZone(tzIDB));
        try {
            Date date = formatterIn.parse(timestamp);
            return formatterOut.format(date);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static boolean isNew(int n) {
        String s = Integer.toString(n);
        int firstDigit = Character.getNumericValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int digit = Character.getNumericValue(s.charAt(i));
            if (digit < firstDigit && digit != 0) return false;
        }
        return true;
    }
}
